package com.zero.platform.controller;

import com.zero.base.ZeroController;
import com.zero.common.result.JsonResult;
import com.zero.common.utils.JsonUtils;
import com.zero.platform.entity.AuthEntity;
import com.zero.platform.entity.MenuEntity;
import com.zero.platform.entity.UserEntity;
import com.zero.platform.enums.PlatformGloabl;
import com.zero.platform.enums.PlatfromEnum;
import com.zero.platform.service.IndexService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 系统登陆控制实现
 * @author  DILGUO
 * @date    2020-07-05
 */
@RestController
@RequestMapping("index")
@Api(value = "Index Controller", tags = {"系统管理登陆接口实现"})
public class IndexController extends ZeroController {

    //注入信息
    @Autowired
    private IndexService indexService;


    /**
     * 系统管理登陆处理
     * @return
     */
    @PostMapping("doLogin")
    @ApiOperation( value = "统管理登陆接口")
    public JsonResult doLogin(@RequestBody UserEntity userEntity){
        return indexService.doLogin(userEntity);
    }


    /**
     * 获取用户信息
     * @return
     */
    @GetMapping("getUserInfo")
    @ApiOperation( value = "统管理登陆接口")
    public JsonResult getUserInfo(){
        //获取当前用户
        UserEntity user = JsonUtils.tObject(super.getCurrencyUser(), UserEntity.class);
        //用户信息
        AuthEntity auth = new AuthEntity();
        auth.setUserInfo(user);
        //用户角色信息
        List<String> roles = indexService.searchUserRole(user.getId());
        auth.setRoles(roles.stream().collect(Collectors.joining(",")));
        //用户角色按钮信息
        List<String> permiss = indexService.searchUserBtn(user.getId(), PlatformGloabl.MENU_TYPE_3.getCode());
        if(ObjectUtils.isNotEmpty(permiss)){
            auth.setPermission(permiss);
        }
        return JsonResult.getSuccess(auth);
    }

    /**
     * 刷新token
     */
    @PostMapping("refesh")
    @ApiOperation( value = "刷新token")
    public JsonResult refesh(){
        //获取当前用户
        UserEntity user = JsonUtils.tObject(super.getCurrencyUser(), UserEntity.class);
        if(ObjectUtils.isEmpty(user)){
            return JsonResult.getSuccess(false);
        }
        return JsonResult.getSuccess(true);
    }

    /**
     * 退出系统
     * @return
     */
    @GetMapping("logout")
    @ApiOperation( value = "退出系统")
    public JsonResult logout(){
        //当前登陆用户
        String userKey = request.getHeader(super.token);
        super.redisUtil.removeObject(userKey);
        return JsonResult.getSuccess(true);
    }

    /**
     * 根据用户查询相应的功能菜单列表
     * @return
     */
    @GetMapping("getMenu")
    @ApiOperation( value = "获取系统菜单")
    public JsonResult getMenu(){
        //获取当前用户
        UserEntity user = JsonUtils.tObject(super.getCurrencyUser(), UserEntity.class);
        List<MenuEntity> list = indexService.searchUserMenu(user.getId(), 0L, "N");
        MenuEntity test = new MenuEntity();
        test.setLabel("缓冲");
        test.setPath("/cache");
        test.setComponent("views/util/cache");
        test.setIcon("icon-caidan");
        test.getMeta().setI18n("'cache");
        test.getMeta().setKeepAlive(true);
        list.add(test);

        MenuEntity hello = new MenuEntity();
        hello.setLabel("测试");
        hello.setPath("/hello");
        hello.setComponent("views/zero/index");
        hello.setIcon("icon-caidan");
        hello.getMeta().setI18n("'hello");
        hello.getMeta().setKeepAlive(true);
        list.add(hello);
        return JsonResult.getSuccess(list);
    }

    /**
     * 根据用户查询相应的功能菜单列表
     * @return
     */
    @GetMapping("getTopMenu")
    @ApiOperation( value = "获取系统菜单")
    public JsonResult getTopMenu(){
//        //获取当前用户
        UserEntity user = JsonUtils.tObject(super.getCurrencyUser(), UserEntity.class);
        List<MenuEntity> topMenu = new ArrayList<MenuEntity>();
        MenuEntity dashboard = new MenuEntity();
        dashboard.setLabel("dashboard");
        dashboard.setPath("/wel/index");
        dashboard.setIcon("el-icon-document");
        dashboard.getMeta().setI18n("dashboard");
        dashboard.setParentId(0L);
        topMenu.add(dashboard);
        //一级菜单
        List<MenuEntity> list = indexService.searchUserMenu(user.getId(), 0L, "Y");
        list.forEach(item->{
            MenuEntity menu = new MenuEntity();
            menu.setLabel(item.getLabel());
            menu.setPath(item.getPath());
            menu.setIcon(item.getIcon());
            menu.getMeta().setI18n(item.getLabel());
            menu.setParentId(item.getId());
            topMenu.add(menu);
        });
        return JsonResult.getSuccess(topMenu);
    }
}
