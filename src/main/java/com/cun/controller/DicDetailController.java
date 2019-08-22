package com.cun.controller;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cun.dao.DictionaryDetailDao;
import com.cun.dao.DictionaryTypeDao;
import com.cun.entity.DictionaryDetail;
import com.cun.service.DictionaryDetailService;
import com.cun.service.impl.DictionaryDetailServiceImpl;
import com.cun.util.ListUtil;
import com.cun.util.ResultBean;

/**
 * 字典明细控制器
 * @author MGK
 *
 */
@RequestMapping("/dicDetail")
@Controller()
public class DicDetailController {
	@Autowired
	private DictionaryDetailService dictionaryDetailServiceImpl;
//	@RequiresPermissions({"select"})
	@GetMapping("{url}")
	public String DaoHangView(@PathVariable String url,String id,Model model ){
		if(StringUtils.isEmpty(url)){
			return "main";
		}else if(url.equals("addDicDetailView")){
			model.addAttribute("id", id);
			return "dicDetail/addDicDetailView";
		}else if(url.equals("getDicDetailView")){
			return "dicDetail/getDicDetailView";
		}
		return "main";
	}
	@ResponseBody
	@RequiresPermissions({"insert"})
	@PostMapping("/addDicDetailView")
	public String  addDicDetailView(DictionaryDetail dictionaryDetail){
		dictionaryDetail.setCreateBy((String) SecurityUtils.getSubject().getPrincipal());
		dictionaryDetail.setUpdateBy((String) SecurityUtils.getSubject().getPrincipal());
		try {
			dictionaryDetailServiceImpl.create(dictionaryDetail);
		} catch (Exception e) {
			return JSONObject.fromObject(new ResultBean<String>(e)).toString();
		}
		return JSONObject.fromObject(new ResultBean<String>()).toString();
	}
	/**
	 * 通过字典类型ID拿到 他下面的所以子数字
	 * @return
	 */
	@ResponseBody
	@RequiresPermissions({"select"})
	@PostMapping("/getDicIdSelectCliend")
	public String getDicIdSelectCliend(String id){			
		List<DictionaryDetail> findAllListFinalByParentId = dictionaryDetailServiceImpl.findAllListFinalByParentId(id);
		ListUtil<DictionaryDetail> list = new ListUtil<DictionaryDetail>();
		list.setMsg("success");
		list.setData(findAllListFinalByParentId);
		return JSONObject.fromObject(list).toString();
	}
}
