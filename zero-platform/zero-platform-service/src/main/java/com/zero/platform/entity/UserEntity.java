package com.zero.platform.entity;

import com.zero.base.ZeroEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * 用户信息实体对像
 */
@Data
@Table(name = "sys_t_user")
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity extends ZeroEntity {
    private String name;        //	姓名	varchar(20)	20		FALSE	FALSE	TRUE
    private String username;    //	帐号	varchar(50)	50		FALSE	FALSE	TRUE
    private String password;    //	密码	varchar(50)	50		FALSE	FALSE	FALSE
    private String avatar;      //	头像	BIGINT			FALSE	FALSE	FALSE
    private String sex;         //	    性别	char(1)	1		FALSE	FALSE	FALSE
    private String telephone;   //	手机号	varchar(20)	20		FALSE	FALSE	FALSE
    private String email;       //	EMAIL	char(1)	1		FALSE	FALSE	FALSE
    private String deptId;      //	    部门ID	char(1)	1		FALSE	FALSE	FALSE
    private String remarks;     //	备注	char(1)	1		FALSE	FALSE	FALSE
    @Transient
    private RoleEntity role;
}
