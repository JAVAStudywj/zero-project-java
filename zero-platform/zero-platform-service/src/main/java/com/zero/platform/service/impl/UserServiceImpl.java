package com.zero.platform.service.impl;

import com.zero.base.ZeroService;
import com.zero.platform.entity.UserEntity;
import com.zero.platform.mapper.UserMapper;
import com.zero.platform.service.IUserServeice;
import com.zero.platform.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ZeroService implements IUserServeice {

    //注入信息
    @Autowired
    private UserMapper userMapper;

    /**
     * 根据ID查询用户详情信息
     * @param id
     * @return
     */
    public UserEntity queryById(Long id){
        return userMapper.selectByPrimaryKey(id);
    }

    /**
     * 查询用户对像详情信息
     * @return
     */
    public UserEntity queryUser(UserEntity userEntity){
        return null;
    }
}
