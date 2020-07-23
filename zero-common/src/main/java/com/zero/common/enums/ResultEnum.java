package com.zero.common.enums;

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
public enum ResultEnum {
    RESULT_ERROR_403(403,"无权限,请联系管理员"),
    RESULT_ERROR_404(404,"无法访问,请联系管理员"),
    RESULT_ERROR_500(500,"系统错误,请联系管理员"),
    ;
    private  int code;      //状态码
    private String msg;     //状态描述
}
