package com.zero.platform.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 菜单扩展属性
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenuMeta {

    private String i18n;
    private Boolean menu = false;        //是否菜单
    private Boolean keepAlive = false;   //
}
