package com.ljdc.bean;

import com.google.gson.Gson;

/**
 * 服务器返回消息
 * Created with IntelliJ IDEA
 * User:邹旭
 * Date:2017/2/23 0023
 * Time:下午 2:44
 * Desc:略
 */
public class Message {
    private int code;
    private String msg;

    public Message(int code, String msg) {

        this.code = code;
        this.msg = msg;
    }

    public Message() {
    }

    /**
     * JASO格式返回字符串
     *
     * @return
     */
    @Override
    public String toString() {

        return new Gson().toJson(this);

    }

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
}
