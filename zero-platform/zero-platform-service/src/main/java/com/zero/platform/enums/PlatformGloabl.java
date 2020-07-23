package com.zero.platform.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 系统服务全局常量定义
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum PlatformGloabl {

    //功能菜单类型
    MENU_TYPE_1("1", "内部功能菜单"),
    MENU_TYPE_2("2", "外部功能菜单"),
    MENU_TYPE_3("3", "功能菜单按钮"),

    //是否叶子菜单
    MENU_ISLEAF_TRUE("Y", "是最终叶子菜单"),
    MENU_ISLEAF_FALSE("N", "菜单功能目录"),
    ;
    private  String code;      //状态码
    private String msg;     //状态描述
}
