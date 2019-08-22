package com.cun.controller;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cun.entity.BillEntity;
import com.cun.entity.DictionaryType;
import com.cun.entity.User;
import com.cun.service.BillEntityService;
import com.cun.service.impl.BillEntityServiceImpl;
import com.cun.util.ListUtil;
import com.cun.util.ResultBean;
import com.cun.util.SqlUtil;

@Controller
@RequestMapping("/sc")
public class ScController {
	@Autowired
	private BillEntityServiceImpl billEntityServiceImpl;
//	@RequiresPermissions({"select"})
	@GetMapping("{url}")
	public String DaoHangView(@PathVariable String url,Model model){
		if(StringUtils.isEmpty(url)){
			return "main";
		}else if(url.equals("addScView")){
			User attribute = (User)SecurityUtils.getSubject().getSession().getAttribute("user");
			model.addAttribute("user", attribute);
			return "sc/addScView";
		}else if(url.equals("updScView")){
			return "sc/updScView";
		}else if(url.equals("getScView")){
			return "sc/getScView";
		}/*else if(url.equals("delScView")){
			return "/sc/delScView";
		}*/else if(url.equals("loginMain")){
			return "main";
		}
		return "main";
	}
	@ResponseBody
	@RequiresPermissions({"insert"})
	@PostMapping("addScView")
	public String addScView(BillEntity billEntity){
		try {
			billEntity.setCreateBy(billEntity.getUserId().getUserName());
			billEntity.setUpdateBy(billEntity.getUserId().getUserName());
			ResultBean<BillEntity> create = this.billEntityServiceImpl.create(billEntity);
			return JSONObject.fromObject(create).toString();
		} catch (RuntimeException e) {
			// TODO: handle exception
			return JSONObject.fromObject(new ResultBean(e)).toString();
		}
	}
	@ResponseBody
	@RequiresPermissions({"select"})
	@RequestMapping("getListScView")
	public String getListScView(@RequestParam("page") Integer page,@RequestParam("limit") Integer limit,BillEntity billEntity,HttpServletRequest req,String startDate,String endDate) throws ParseException{
		List<BillEntity> count = billEntityServiceImpl.findByDeleteTimeIsNullOrderByCreateTimeDesc();
		List<BillEntity> findAll = billEntityServiceImpl.myBasePage(billEntity, page, limit, SqlUtil.formatDate(startDate),SqlUtil.formatDate(endDate)).getContent();
		ListUtil<BillEntity> list = new ListUtil<BillEntity>();
		list.setCode("0");
		list.setCount(String.valueOf(count.size()));
		list.setMsg("成功");
		list.setData(findAll);
		JSONObject js = JSONObject.fromObject(list);
		return js.toString(); 
	}
	@RequiresPermissions({"select"})
	@RequestMapping("getScInfo")
	public String getScInfo(Integer id,Model model){
		if(StringUtils.isEmpty(id)){
			return "cc";
		}
		BillEntity billEntity = billEntityServiceImpl.get(id);
		Model addAttribute = model.addAttribute("billObj", billEntity);
		return "sc/updScView";
	}
	@ResponseBody
	@RequiresPermissions({"update"})
	@PostMapping("updScView")
	public String updScView(BillEntity billEntity){
		billEntity.setUpdateBy((String) SecurityUtils.getSubject().getPrincipal());
		billEntity.setEditTime(new Date());
		ResultBean<BillEntity> update = billEntityServiceImpl.update(billEntity);
		JSONObject fromObject = JSONObject.fromObject(update);
		return fromObject.toString();
	}
	@RequiresPermissions({"select"})
	@RequestMapping("getScInfos")
	public String getScInfos(Integer id,Model model){
		if(StringUtils.isEmpty(id)){
			return "cc";
		}
		BillEntity billEntity = billEntityServiceImpl.get(id);
		Model addAttribute = model.addAttribute("billObj", billEntity);
		return "/sc/infoScView";
	}
	@RequiresPermissions({"delete"})
	@PostMapping("delScView")
	@ResponseBody
	public String delScView(Integer id){
		if(StringUtils.isEmpty(id)){
			return "cc";
		}
		BillEntity billEntity = billEntityServiceImpl.get(id);
		billEntity.setDeleteTime(new Timestamp(System.currentTimeMillis()));
		ResultBean<BillEntity> deleteTime = billEntityServiceImpl.DeleteTime(billEntity);
		JSONObject fromObject = JSONObject.fromObject(deleteTime);
		return fromObject.toString();
	}
}