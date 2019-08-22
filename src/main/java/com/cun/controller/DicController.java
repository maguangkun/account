package com.cun.controller;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import net.sf.json.JSONObject;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cun.dao.DictionaryTypeDao;
import com.cun.entity.DictionaryType;
import com.cun.entity.Student;
import com.cun.entity.User;
import com.cun.service.DictionaryTypeService;
import com.cun.service.impl.DictionaryTypeServiceImpl;
import com.cun.util.ListUtil;
import com.cun.util.ResultBean;

/**
 * 字典控制器
 * @author GaoYU
 *
 */
@RequestMapping("/dic")
@Controller
public class DicController {
	@Autowired
	private DictionaryTypeServiceImpl dictionaryTypeServiceImpl;
//	@RequiresPermissions({"select"})
	@GetMapping("{url}")
	public String DaoHangView(@PathVariable String url){
		if(StringUtils.isEmpty(url)){
			return "main";
		}else if(url.equals("addDicView")){
			return "dic/addDicView";
		}else if(url.equals("updDicView")){
			return "dic/updDicView";
		}else if(url.equals("getDicView")){
			return "dic/getDicView";
//		}else if(url.equals("delDicView")){
//			return "/dic/delDicView";
		}else if(url.equals("listDicView")){
			return "dic/listDicView";
		}
		return "main";
	}
	@ResponseBody
	@RequiresPermissions({"delete"})
	@PostMapping("delDicView")
	public String delDicView(Integer id){
		if(StringUtils.isEmpty(id)){return new JSONObject().accumulate("msg", "error").toString();}
		String login = (String) SecurityUtils.getSubject().getPrincipal();
		dictionaryTypeServiceImpl.updateDelete_Time(new Timestamp(System.currentTimeMillis()),login,id);
//		return new JSONObject().accumulate("msg", "success").toString();
		ResultBean<String> resultBean = new ResultBean<String>();
		return JSONObject.fromObject(resultBean).toString();
	}
	@ResponseBody
	@RequiresPermissions({"update"})
	@PostMapping("updDicView")
	public String updDicView(Integer id,String typeName){
		if(StringUtils.isEmpty(id)||StringUtils.isEmpty(typeName)){return new JSONObject().accumulate("msg", "error").toString();}
		String login = (String) SecurityUtils.getSubject().getPrincipal();
		dictionaryTypeServiceImpl.updateTypeName(login, id, typeName);
		return JSONObject.fromObject(new ResultBean<String>()).toString();
	}
	@ResponseBody
	@RequiresPermissions({"select"})
	@RequestMapping("getListDicView")
	public String getListDicView(){
		List<DictionaryType> findAll = dictionaryTypeServiceImpl.findAllList();
		
//		ResultBean<List> list = new ResultBean<List>(findAll);
		ListUtil<DictionaryType> list = new ListUtil<DictionaryType>();
		list.setCode("0");
		list.setCount(String.valueOf(findAll.size()));
		list.setMsg("成功");
		list.setData(findAll);
		JSONObject js = JSONObject.fromObject(list);
		return js.toString();
	}
	@ResponseBody
	@RequiresPermissions({"insert"})
	@PostMapping("addDic")
	public String addDic(DictionaryType dictionaryType){
		dictionaryType.setCreateBy((String) SecurityUtils.getSubject().getPrincipal());
		dictionaryType.setUpdateBy((String) SecurityUtils.getSubject().getPrincipal());
		try {
			dictionaryTypeServiceImpl.create(dictionaryType);
		} catch (Exception e) {
			return JSONObject.fromObject(new ResultBean<String>(e)).toString();
		}
		
		InputStream resourceAsStream = DicController.class.getResourceAsStream("");
		Properties p = new Properties();
		try {
			p.load(resourceAsStream);
			String property = p.getProperty("aa");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return JSONObject.fromObject(new ResultBean<String>()).toString();
	}
}
