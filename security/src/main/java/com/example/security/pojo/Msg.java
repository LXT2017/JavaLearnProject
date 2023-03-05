package com.example.security.pojo;

import com.example.security.enums.CustomExceptionCode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author shawn
 * @version 1.0
 * @ClassName Msg
 * Description:
 * @date 2023/2/27 19:30
 */
public class Msg {
    /**
     * 状态码
     */
    private int code;
    /**
     * 提示信息
     */
    private String msg;
    /**
     * 用户返回给浏览器的数据
     */
    private Map<String, Object> extend = new HashMap<String, Object>();

    public static Msg success() {
        Msg result = new Msg();
        result.setCode(200);
        result.setMsg("请求成功！");
        return result;
    }

    public static Msg success(int code,String msg) {
        Msg result = new Msg();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }

    public static Msg fail() {
        Msg result = new Msg();
        result.setCode(400);
        result.setMsg("请求失败！");
        return result;
    }

    public static Msg problem(int code, String errorMessage) {
        Msg result = new Msg();
        result.setCode(code);
        result.setMsg(errorMessage);
        return result;
    }

    public static Msg fail(CustomExceptionCode exceptionCode){
        Msg result = new Msg();
        result.setCode(exceptionCode.code);
        result.setMsg(exceptionCode.msg);
        return result;
    }

    public static Msg fail(CustomExceptionCode exceptionCode, Object ... msg) {
        Msg result = new Msg();
        result.setCode(exceptionCode.code);
        result.setMsg(String.format(exceptionCode.msg, msg));
        return result;
    }

    public Msg add(String key, Object value) {
        this.getExtend().put(key, value);
        return this;
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

    public Map<String, Object> getExtend() {
        return extend;
    }

    public void setExtend(Map<String, Object> extend) {
        this.extend = extend;
    }

}
