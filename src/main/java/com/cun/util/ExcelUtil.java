package com.cun.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.cun.entity.BillEntity;
import com.cun.entity.User;

public class ExcelUtil {

	public static void main(String[] args){
		List<BillEntity> list = new ArrayList<BillEntity>();
		for (int i = 0; i < 10; i++) {
			BillEntity b = new BillEntity();
			b.setId(i);
			b.setCargoManyPrice(new BigDecimal(i*10));
			b.setCargoOnePrice(new BigDecimal(i));
			b.setCargoName("货物"+i);
			b.setCargoWeight(new BigDecimal(i*100));
			b.setCreateBy("马广坤"+i);
			b.setCreateTime(new Date());
			b.setDebtState("欠");
			b.setEditTime(new Date());
			b.setRealityPrice(new BigDecimal(i*1000) );
			b.setRemark("备注"+i);
			b.setTakePerson("收货人"+i);
			b.setTakePersonPhone("23"+i);
			b.setUpdateBy("admin"+i);
			User user = new User();
			user.setUserName("pyphh ");
			b.setUserId(user);
			list.add(b);
		}
		excelDownload("账单",new String[]{"创建人","收货人","货物名称","收款状态","货物重量","货物单价","货物总价","实际总价","手机号","备注","主键","创建时间","删除时间","修改时间","创建账单人","修改人"}, list);
	}

	/**
	 * @param sheetName
	 *            名称
	 * @param obj1
	 *            第一行表头
	 * @param
	 * @throws ParseException
	 */
	public static void excelDownload(String sheetName, String[] str, List list)
			{
		// 声明一个工作簿
		HSSFWorkbook workbook = new HSSFWorkbook();
		// 生成一个表格，设置表格名称为"学生表"
		HSSFSheet sheet;
		try {
			sheet = workbook.createSheet(sheetName+ SqlUtil.formatDateString(new Date()));
		// 设置表格列宽度为10个字节
		sheet.setDefaultColumnWidth(10);
		// 创建第一行表头
		HSSFRow headrow = sheet.createRow(0);

		// 遍历添加表头(下面模拟遍历学生，也是同样的操作过程)
		for (int i = 0; i < str.length; i++) {
			// 创建一个单元格
			HSSFCell cell = headrow.createCell(i);
			// 创建一个内容对象
			HSSFRichTextString text = new HSSFRichTextString(str[i]);
			// 将内容对象的文字内容写入到单元格中
			cell.setCellValue(text);
		}
		for (int i = 0; i < list.size(); i++) {
			HSSFRow row1 = sheet.createRow(i+ 1);
			Object object = list.get(i);
			Object[] stringArr = getStringArr(object);
			for (int j = 0; j < stringArr.length; j++) {
				HSSFCell cell = row1.createCell(j);
				Object object2 = stringArr[j];
				HSSFRichTextString text;
				if(object2 instanceof User){
					text= new HSSFRichTextString(SqlUtil.getMenthodValue(object2,"userName"));
				}else if(object2 instanceof Date){
					text= new HSSFRichTextString(SqlUtil.formatDateString((Date)object2));
				}else{
					text= new HSSFRichTextString(object2.toString());
				}
			
				cell.setCellValue(text);
			}
		}
//		String path = "E:\\";
		String resource = ExcelUtil.class.getClassLoader().getResource("").getFile();
		String replaceAll = resource.replaceAll("target/classes/", "src/main/resources/static/excel/");
		File file = new File(replaceAll);
		FileOutputStream os = new FileOutputStream(replaceAll
				+ SqlUtil.formatDateStringHHMMSS(new Date()) + "账单.xls");
		workbook.write(os);
		workbook.close();
		os.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static Object[] getStringArr(Object obj) {
		Class<? extends Object> class1 = obj.getClass();
		Method method = null;
		Field[] declaredFields = class1.getDeclaredFields();
		Class<?> superclass = class1.getSuperclass();
		Field[] declaredFields2 = superclass.getDeclaredFields();
		Field[] concat = SqlUtil.concat(declaredFields, declaredFields2);
		Object[] arr = new Object[concat.length];
		for (int i = 0; i < concat.length; i++) {
			Field field = concat[i];
			String name = field.getName();
			try {
				method = class1.getMethod("get" + SqlUtil.captureName(name));
				Object invoke = method.invoke(obj);
				if (invoke == null) {
					arr[i] = "";
				} else {
					arr[i] = invoke;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return arr;
	}
}
