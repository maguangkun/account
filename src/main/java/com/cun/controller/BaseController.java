package com.cun.controller;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cun.util.ResultBean;

@ControllerAdvice(basePackages = {"com.cun.controller"})
public class BaseController {
	@ExceptionHandler(value = Exception.class)
	@ResponseBody
	public String getQxbg(Throwable ex){
		if(ex instanceof AuthorizationException){
			String userName = (String) SecurityUtils.getSubject().getPrincipal();
			if(StringUtils.isEmpty(userName)){
				ResultBean<String> s = new ResultBean<String>();
				s.setMsg("登录超时请重新登录");
				s.setCode(s.ERRORHTML);
				JSONObject fromObject = JSONObject.fromObject(s);
				return fromObject.toString();
			}
			ResultBean<String> s = new ResultBean<String>();
			s.setMsg("权限不够"+ex.getMessage());
			s.setCode(1);
			JSONObject fromObject = JSONObject.fromObject(s);
			return fromObject.toString();
		}
		ResultBean<String> s = new ResultBean<String>(ex);
		JSONObject fromObject = JSONObject.fromObject(s);
		return fromObject.toString();
	}

}
