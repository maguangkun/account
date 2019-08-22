package com.cun.util;

import java.util.List;

public class Temp{
	private String msg;
	private List<String> data;
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public List<String> getData() {
		return data;
	}
	public void setData(List<String> data) {
		this.data = data;
	}
	public Temp(String msg, List<String> data) {
		super();
		this.msg = msg;
		this.data = data;
	}
	public Temp() {
		super();
	}
	
}