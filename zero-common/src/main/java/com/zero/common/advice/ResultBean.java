package com.zero.common.advice;

import com.zero.common.enums.ResultEnum;
import lombok.Data;

/**
 * 返回结果信息对像
 * **
 * @author: Dilguo
 * @create: 2020-01-14
 */
@Data
public class ResultBean {

    private int status;
    private String message;
    private Long timestamp;

    public ResultBean(ResultEnum em){
        this.status = em.getCode();
        this.message = em.getMsg();
        this.timestamp = System.currentTimeMillis();
    }
}
