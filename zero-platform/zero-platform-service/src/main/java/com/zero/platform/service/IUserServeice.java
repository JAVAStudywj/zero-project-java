package com.zero.platform.service;

import com.zero.platform.entity.UserEntity;
import com.zero.platform.vo.UserVo;

public interface IUserServeice {
    /**
     * 根据ID查询用户详情信息
     * @param id
     * @return
     */
    UserEntity queryById(Long id);

    /**
     * 查询用户对像详情信息
     * @return
     */
    UserEntity queryUser(UserEntity userEntity);
}
