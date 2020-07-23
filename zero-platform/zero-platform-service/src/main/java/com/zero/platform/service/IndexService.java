package com.zero.platform.service;

import com.zero.common.result.JsonResult;
import com.zero.platform.entity.MenuEntity;
import com.zero.platform.entity.RoleEntity;
import com.zero.platform.entity.UserEntity;

import java.util.List;

/**
 * 登陆处理接口
 */
public interface IndexService {

    /**
     * 系统用户登陆处理
     * @param userEntity
     * @return
     */
    JsonResult doLogin(UserEntity userEntity);

    /**
     * 根据用户查询相应的角色列表
     * @param userId
     * @return
     */
    List<String> searchUserRole(Long userId);

    /**
     * 根据用户查询相应的角色列表
     * @param userId
     * @return
     */
    List<String> searchUserBtn(Long userId, String billtype);

    /**
     * 根据用户查询相应的功能菜单列表
     * @param userId
     * @return
     */
    List<MenuEntity> searchUserMenu(Long userId, Long parentId, String isTop);
}
