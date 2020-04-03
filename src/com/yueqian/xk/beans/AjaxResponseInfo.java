package com.yueqian.xk.beans;

/**
 * ajax响应的json格式
 */
public class AjaxResponseInfo<T> {
    private int code;//-1:参数不足，-2:权限不足，0:正常应答
    private String msg;//提示信息
    private T data;//核心数据

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
