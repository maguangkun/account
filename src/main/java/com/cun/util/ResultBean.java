package com.cun.util;

import java.io.Serializable;

public class ResultBean<T> implements Serializable {
    private static final long serialVersionUID=1L;

    public static final int SUCESS=0;//成功

    public static final int FAIL=1;//失败
    
    public static final int ERRORHTML = 2;//返回HTML重新登录页面
    
    public static final int NO_PERMESSION=2;

    private String msg="success";

    private int code=SUCESS;

    private T data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }


    public ResultBean(){
        super();
    }

    public ResultBean(T data){
        super();
        this.data=data;
    }

    public ResultBean(Throwable e){
        super();
        this.msg=e.toString();
        this.code=FAIL;
    }
}