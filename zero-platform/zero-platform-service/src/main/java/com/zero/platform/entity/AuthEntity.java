package com.zero.platform.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 当前登陆用户相关权限信息
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthEntity {
    //用户信息
    private UserEntity userInfo;
    //角色信息
    private String roles;
    //按钮权限
    private Object permission;
}
