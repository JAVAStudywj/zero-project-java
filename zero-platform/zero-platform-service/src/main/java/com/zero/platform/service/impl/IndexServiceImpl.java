package com.zero.platform.service.impl;

import cn.hutool.core.util.IdUtil;
import com.zero.base.ZeroService;
import com.zero.common.gloabl.Gloabl;
import com.zero.common.result.JsonResult;
import com.zero.common.utils.JsonUtils;
import com.zero.common.utils.RedisUtil;
import com.zero.platform.entity.MenuEntity;
import com.zero.platform.entity.UserEntity;
import com.zero.platform.enums.PlatformGloabl;
import com.zero.platform.enums.PlatfromEnum;
import com.zero.platform.mapper.MenuMapper;
import com.zero.platform.mapper.RoleMapper;
import com.zero.platform.mapper.UserMapper;
import com.zero.platform.service.IndexService;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 *
 */
@Service
public class IndexServiceImpl extends ZeroService implements IndexService {

    //注入信息
    @Autowired
    private UserMapper userMapper;  //用户处理
    @Autowired
    private RoleMapper roleMapper;  //角色处理
    @Autowired
    private MenuMapper menuMapper;  //功能处理
    @Autowired
    private RedisUtil redisUtil;    //内存处理

    /**
     * 系统用户登陆处理
     * @param userEntity
     * @return
     */
    public JsonResult doLogin(UserEntity userEntity){
        //用户名称不允许为空
        if(StringUtils.isEmpty(userEntity.getUsername())){
            return JsonResult.getError(PlatfromEnum.RESULT_ERROR_USERNAME_EMPT.getMsg());
        }
        //密码不允许为空
        if(StringUtils.isEmpty(userEntity.getPassword())){
            return JsonResult.getError(PlatfromEnum.RESULT_ERROR_PASSWORD_EMPT.getMsg());
        }
        UserEntity param = new UserEntity();
        param.setUsername(userEntity.getUsername());
        UserEntity userObj = userMapper.selectOne(param);
        //用户不存在
        if(ObjectUtils.isEmpty(userObj)){
            return JsonResult.getError(PlatfromEnum.RESULT_ERROR_USERNAME_NO.getMsg());
        }
        //密码不正确
        if(!userEntity.getPassword().equals(userObj.getPassword())){
            return JsonResult.getError(PlatfromEnum.RESULT_ERROR_PASSWORD_NO.getMsg());
        }
        //将用户token 放入REDIS中
        String token = IdUtil.simpleUUID();
        redisUtil.putObject(token, JsonUtils.toJson(userObj), 5, TimeUnit.MINUTES);
        return JsonResult.getSuccess(token, PlatfromEnum.RESULT_SUCCESS_LOGIN.getMsg());
    }

    /**
     * 根据用户查询相应的角色列表
     * @param userId
     * @return
     */
    public List<String> searchUserRole(Long userId){
        return roleMapper.searchUserRole(userId);
    }

    /**
     * 根据用户查询相应的角色列表
     * @param userId
     * @return
     */
    public List<String> searchUserBtn(Long userId, String billtype){
        return menuMapper.searchUserBtn(userId, billtype);
    }

    /**
     * 根据用户查询相应的功能菜单列表
     * @param userId
     * @return
     */
    public List<MenuEntity> searchUserMenu(Long userId, Long parentId, String isTop){
        MenuEntity param = new MenuEntity();
        param.setUserId(userId);
        param.setParentId(parentId);
        param.setIsFlag(Gloabl.IS_FLAG_TRUE);
        param.setBilltype(PlatformGloabl.MENU_TYPE_3.getCode());
        List<MenuEntity> list = menuMapper.searchUserMenu(param);
        //递归调用 查询子菜单信息
        if(ObjectUtils.isNotEmpty(list) && !"Y".equals(isTop)){
            List<MenuEntity> childrenList = new ArrayList<MenuEntity>();
            for(int i = 0; i < list.size(); i++){
                MenuEntity item = list.get(i);
                item.getMeta().setI18n(item.getLabel());
                if(PlatformGloabl.MENU_ISLEAF_FALSE.getCode().equals(item.getIsleaf())){
//                    item.getMeta().setMenu(false);
                    childrenList = this.searchUserMenu(userId, item.getId(), "N");
                    list.get(i).setChildren(childrenList);
                }
            }
        }
        return list;
    }
}
