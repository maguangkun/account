package com.cun.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang.StringUtils;

import com.cun.entity.BaseEntity;
import com.cun.entity.User;

public class SqlUtil {

	public static void main(String[] args) throws ParseException {
		// getSQL(new A("1","name",new User(),1),null,null,null);
		// String captureName = captureName("edDitTimename");
		// System.out.println(captureName);
		String formatDate = formatDateStringHHMMSS(new Date());
		System.out.println(formatDate);
	}

	public static String captureName(String str) {
		// 进行字母的ascii编码前移，效率要高于截取字符串进行转换的操作
		char[] cs = str.toCharArray();
		cs[0] -= 32;
		return String.valueOf(cs);
	}

	public static void getSQL(Object obj, CriteriaBuilder criteriaBuilder,
			List<Predicate> predicates, Root<?> root) {
		Class<? extends Object> class1 = obj.getClass();
		Method method = null;
		Field[] declaredFields = class1.getDeclaredFields();
		Class<?> superclass = class1.getSuperclass();
		Field[] declaredFields2 = superclass.getDeclaredFields();
		Field[] concat = concat(declaredFields, declaredFields2);
		for (Field field : concat) {
			String name = field.getName();
			Method method2;
			try {
				method2 = class1.getMethod("get" + captureName(name));
				Object invoke = method2.invoke(obj);
				if (name.equals("id") && invoke != null) {
					predicates
							.add(criteriaBuilder.equal(root.get(name), invoke));
				} else if (invoke != null
						&& (!"".equals(invoke))
						&& field.getType().getName()
								.equals("java.math.BigDecimal")) {
					predicates.add(criteriaBuilder
							.like(root.get(name).as(String.class), "%" + invoke
									+ "%"));
				} else if (name.equals("debtState")
						&& (!org.springframework.util.StringUtils
								.isEmpty(invoke))) {
					predicates
							.add(criteriaBuilder.equal(root.get(name), invoke));
				} else if (invoke != null && (!"".equals(invoke))) {
					predicates.add(criteriaBuilder
							.like(root.get(name).as(String.class), "%" + invoke
									+ "%"));
				}
				// if(invoke!=null) {
				// predicates.add(criteriaBuilder.like(root.get(name), "%" +
				// invoke + "%"));
				// }
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static Field[] concat(Field[] a, Field[] b) {
		Field[] c = new Field[a.length + b.length];
		System.arraycopy(a, 0, c, 0, a.length);
		System.arraycopy(b, 0, c, a.length, b.length);
		return c;
	}

	/**
	 * 将String 转换成Date
	 * 
	 * @param date
	 * @return
	 * @throws ParseException
	 */
	public static Date formatDate(String date) throws ParseException {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		if (StringUtils.isEmpty(date)) {
			return null;
		}
		return simpleDateFormat.parse(date);
	}

	/**
	 * 将String 转换成Date
	 * 
	 * @param date
	 * @return
	 * @throws ParseException
	 */
	public static Date formatDate(Date date) throws ParseException {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String format = simpleDateFormat.format(date);
		return formatDate(format);
	}

	/**
	 * 将String 转换成Date
	 * 
	 * @param date
	 * @return
	 * @throws ParseException
	 */
	public static String formatDateString(Date date) throws ParseException {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return simpleDateFormat.format(date);
	}

	public static String formatDateStringHHMMSS(Date date)
			throws ParseException {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
				"yyyy-MM-dd-HH-mm-ss");
		return simpleDateFormat.format(date);
	}

	public static String getMenthodValue(Object obj, String str){
		Class<? extends Object> class1 = obj.getClass();
			Method method2;
			Object invoke = "";
			try {
				method2 = class1.getMethod("get" + captureName(str));
				invoke = method2.invoke(obj);
			} catch (Exception e) {
					e.printStackTrace();
			}
			return invoke.toString();
		}
	}

class A extends BaseEntity {
	public A() {
		super();
	}

	private String ids;
	private String name;
	private User user;
	private Integer num;
	private BigDecimal big;

	public BigDecimal getBig() {
		return big;
	}

	public void setBig(BigDecimal big) {
		this.big = big;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public A(String ids, String name, User user, Integer num) {
		super();
		this.ids = ids;
		this.name = name;
		this.user = user;
		this.num = num;
	}
}
