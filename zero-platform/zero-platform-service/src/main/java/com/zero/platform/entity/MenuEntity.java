package com.zero.platform.entity;

import com.zero.base.ZeroEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.List;

/**
 * 角色信息实体对像
 */
@Data
@Table(name = "sys_t_menu")
@AllArgsConstructor
public class MenuEntity extends ZeroEntity {
    private String component;     //唯一标识
    private String billtype;    //	资源类型	0 文件夹  1 菜单  2 按钮
    private String label;        //	菜单名称		varchar(50)	50		FALSE	FALSE	TRUE
    private String icon;        //	图标		varchar(100)	100		FALSE	FALSE	FALSE
    private String path;         //	资源标识		varchar(50)	50		FALSE	FALSE	FALSE
    private Integer flevel;     //	等级		integer			FALSE	FALSE	TRUE
    private String isleaf;      //	是否叶子菜单 Y 是 N否		char(1)	1		FALSE	FALSE	TRUE
    private String sortby;      //	排序		int			FALSE	FALSE	FALSE
    private Long parentId;        //	上级菜单		bigint			FALSE	FALSE	FALSE
    private String allPath;     //	全路径		varchar(200)	200		FALSE	FALSE	FALSE
    private String remarks;     //	备注		varchar(200)	200		FALSE	FALSE	FALSE

    @Transient
    private Long userId;
    @Transient
    private Long roleId;
    @Transient
    private MenuMeta meta;
    @Transient
    private List<MenuEntity> children;

    public MenuEntity() {
        if(null == meta){
            meta = new MenuMeta();
        }
    }
}
