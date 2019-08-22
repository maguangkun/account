package com.cun.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import com.cun.entity.DictionaryDetail;
import com.cun.entity.Student;

import net.sf.json.JSONObject;
import net.sf.json.JSONArray;

public class ListUtil<T> {
	private String code;
	private String msg;
	private String count;
	private List<T> data;
	public ListUtil(){}
	public static void main(String[] args) {
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getCount() {
		return count;
	}
	public void setCount(String count) {
		this.count = count;
	}
	public List<T> getData() {
		return data;
	}
	public void setData(List<T> data) {
		this.data = data;
	}
	@Override
	public String toString() {
		return "ListUtil [code=" + code + ", msg=" + msg + ", count=" + count
				+ ", data=" + data + "]";
	}
	public static String getSelect(List list,String msg,String[] strArr){
		StringBuffer sbf = new StringBuffer();
		sbf.append("{\"msg\":"+msg+",\"data\":[");
		for (Object lists : list) {
			ListUtil.getMethodParam(lists, strArr,sbf);
		}
		sbf.delete(sbf.length()-1, sbf.length());
		return sbf.toString();
	}
	private static String getMethodParam(Object obj,String[] str,StringBuffer sbf){
		Class<? extends Object> class1 = obj.getClass();
		sbf.append("{");
		for (String object : str) {
			try {
			Method method = class1.getMethod("get"+object);
			Object invoke = method.invoke(obj);
			sbf.append(""+object+""+":"+invoke+",");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		}
		sbf.delete(sbf.length()-1, sbf.length());
		sbf.append("},");
		return sbf.toString();
	}
	
}
