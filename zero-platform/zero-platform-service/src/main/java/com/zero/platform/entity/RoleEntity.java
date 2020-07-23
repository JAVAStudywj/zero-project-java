package com.zero.platform.entity;


import com.zero.base.ZeroEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * 角色信息实体对像
 */
@Data
@Table(name = "sys_t_user")
@AllArgsConstructor
@NoArgsConstructor
public class RoleEntity extends ZeroEntity {
    private String code;        //	角色编码		varchar(20)	20		FALSE	FALSE	TRUE
    private String name;        //	角色名称		varchar(50)	50		FALSE	FALSE	TRUE
    private Integer sortby;     //	排序		int			FALSE	FALSE	TRUE
    private String remarks;     //	备注		varchar(200)	200		FALSE	FALSE	FALSE

    @Transient
    private Long userId;
    @Transient
    private Long roleId;
}
