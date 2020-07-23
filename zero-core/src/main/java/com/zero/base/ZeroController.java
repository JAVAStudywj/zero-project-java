package com.zero.base;

import com.zero.common.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import javax.servlet.http.HttpServletRequest;

/**
 * Controller 基础袦
 * @author  DILGUO
 * @date    2020-07-07
 */
public class ZeroController {

    @Autowired
    protected RedisUtil redisUtil;

    @Value("${token}")
    protected String token;

    @Autowired
    protected HttpServletRequest request;

    public ZeroController(){
    }

    /**
     * 获取当前用户
     * @return
     */
    public String getCurrencyUser(){
        //当前登陆用户
        String userKey = request.getHeader(token);
        return String.valueOf(redisUtil.get(userKey));
    }
}
