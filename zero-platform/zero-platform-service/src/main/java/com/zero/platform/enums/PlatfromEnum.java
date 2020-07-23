package com.zero.platform.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 *  异常错误信息枚举类
 * **
 * @author: Dilguo
 * @create: 2020-01-14
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum PlatfromEnum {
    RESULT_SUCCESS_LOGIN(200, "登陆成功"),
    RESULT_ERROR_USERNAME_EMPT(500,"用户名不允许为空"),
    RESULT_ERROR_USERNAME_NO(500,"用户不存在"),
    RESULT_ERROR_PASSWORD_EMPT(500,"密码不允许为空"),
    RESULT_ERROR_PASSWORD_NO(500,"密码不正确"),
    ;
    private  int code;      //状态码
    private String msg;     //状态描述
}
