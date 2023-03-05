package com.example.security.enums;

import lombok.Getter;

/**
 * @author shawn
 * @version 1.0
 * @ClassName CustomExceptionCode
 * Description:
 * @date 2023/2/27 19:28
 */
@Getter
public enum CustomExceptionCode {
    /**
     * 公共错误码
     */
    SUCCESS( 200,"请求成功"),
    FAILED(400,"请求失败"),
    SYSTEM_FAILED(500,"系统错误，请联系管理员"),
    LOGIN_USER_NOT_EXISTED(401,"用户不存在"),
    LOGIN_FAILED(401,"认证失败(密码错误)"),
    LOGIN_SESSION_EXPIRED(402,"SESSION 失效，请重新登录！"),
    LOGIN_NO_ACCESS(403,"无权访问，need Authorities!!"),
    LOGIN_NEED(404,"需要登录"),
    LOGIN_LOCKED(405,"已被锁定"),
    LOGIN_VERIFICATION_FAILED(407,"验证码类错误"),

    TOKEN_INVALID(410,"token无效"),
    TOKEN_IS_BLACK(411,"token已被拉黑"),
    TOKEN_NOT_EXISTED(412,"token不存在"),

    SYSTEM_EXCEPTION(1000,"系统发生错误,请联系管理员"),
    PARAM_NULL(1001,"传入参数 [%s] 为空！"),
    FORM_EXCEPTION(1002,"提交表单异常");



    /**
     * 错误码
     */
    public int code;

    /**
     * 错误信息
     */
    public String msg;

    CustomExceptionCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
