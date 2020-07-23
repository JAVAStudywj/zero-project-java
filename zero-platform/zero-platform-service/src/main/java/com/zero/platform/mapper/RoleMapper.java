package com.zero.platform.mapper;

import com.zero.platform.entity.RoleEntity;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * 角色功能数据处理
 */
public interface RoleMapper extends Mapper<RoleEntity> {

    /**
     * 根据用户查询相应的角色列表
     * @param userId
     * @return
     */
    @Select("select role_id as roleId from sys_t_user_role where user_id = #{userId}")
    List<String> searchUserRole(Long userId);
}
