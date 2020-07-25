package com.zero.platform.mapper;

import com.zero.platform.entity.MenuEntity;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * 功能菜单数据处理层
 */
public interface MenuMapper extends Mapper<MenuEntity> {

    /**
     * 根据用户查询所有具体的权限按钮信息
     * @param userId
     * @param billtype
     * @return
     */
    @Select("select distinct b.component as component " +
            "from sys_t_role_menu a inner join sys_t_menu b on a.menu_id = b.id " +
            "inner join sys_t_user_role c on a.role_id = c.role_id " +
            " where c.user_id = #{userId} and b.billtype = #{billtype}")
    List<String> searchUserBtn(@Param("userId") Long userId, @Param("billtype") String billtype);

    /**
     * 根据用户查询所有具体的权限功能菜单信息
     * @param param
     * @return
     */
    @Select("select distinct b.id, b.billtype, b.component, b.label, b.icon, b.path, b.isleaf, b.all_path as allPath, b.parent_id as parentId " +
            "from sys_t_role_menu a inner join sys_t_menu b on a.menu_id = b.id " +
            "inner join sys_t_user_role c on a.role_id = c.role_id " +
            "where c.user_id = #{param.userId} and b.billtype <> #{param.billtype} and b.is_flag = #{param.isFlag} and parent_id = #{param.parentId} " +
            "order by b.sortby ")
    List<MenuEntity> searchUserMenu(@Param("param") MenuEntity param);

}
