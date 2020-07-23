package com.zero.platform.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 用户信息
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserVo {

    private Long id;
    private String name;        //	姓名	varchar(20)	20		FALSE	FALSE	TRUE
    private String username;    //	帐号	varchar(50)	50		FALSE	FALSE	TRUE
    private String password;    //	密码	varchar(50)	50		FALSE	FALSE	FALSE
    private String avatar;      //	头像	BIGINT			FALSE	FALSE	FALSE
    private String sex;         //	    性别	char(1)	1		FALSE	FALSE	FALSE
    private String telephone;   //	手机号	varchar(20)	20		FALSE	FALSE	FALSE
    private String email;       //	EMAIL	char(1)	1		FALSE	FALSE	FALSE
    private String deptId;      //	    部门ID	char(1)	1		FALSE	FALSE	FALSE
    private String remarks;     //	备注	char(1)	1		FALSE	FALSE	FALSE
    private String isFlag;      //	状态	char(1)	1		FALSE	FALSE	FALSE
    private Long createBy;      //	创建人	BIGINT			FALSE	FALSE	FALSE
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;    //	创建时间	time			FALSE	FALSE	FALSE
    private Long updateBy;     //   修改人	BIGINT			FALSE	FALSE	FALSE
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;    //	修改时间	time			FALSE	FALSE	FALSE
}
