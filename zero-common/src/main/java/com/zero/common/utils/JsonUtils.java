package com.zero.common.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * JSON处理类
 * @author: Dilguo
 * @create: 2020-01-14
 **/
public class JsonUtils{

    private static final long serialVersionUID = 1L;
    private static Logger logger = LoggerFactory.getLogger(JsonUtils.class);

    public JsonUtils(){
    }

    //返序列化时 忽略在JSON字符串中存在但Java对象实际没有的属性

    /**
     * String json预列化处理
     * Object可以是POJO，也可以是Collection或数组。
     * 如果对象为Null, 返回"null".
     * 如果集合为空集合, 返回"[]".
     * @param obj
     * @return
     * **
     * @author: Dilguo
     * @create: 2020-01-14
     */
    public static String toJson(Object obj){
        try{
           return JSONObject.toJSONString(obj);
        }catch(Exception e){
            logger.error("json序列化出错："+ obj, e);
            return null;
        }
    }

    /**
     * 返序列化处理
     * @param json
     * @param tClass
     * @param <T>
     * @return
     * **
     * @author: Dilguo
     * @create: 2020-01-14
     */
    public static <T> T tObject(String json, Class<T> tClass){
        JSONObject jsonObj = JSONObject.parseObject(json);
        return JSON.toJavaObject(jsonObj, tClass);
    }
}
