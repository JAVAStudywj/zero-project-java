package com.zero.common.advice;

import com.zero.common.result.ZeroResult;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * 返回结果信息
 * **
 * @author: Dilguo
 * @create: 2020-01-14
 */
@ControllerAdvice
public class ResultHandler {

    /**
     * 系统运行结果拦截
     * @param e
     * @return
     * **
     * @author: Dilguo
     * @create: 2020-01-14
     */
    @ExceptionHandler(ZeroResult.class)
    public ResponseEntity<ResultBean> handleException(ZeroResult e){
        return ResponseEntity.status(e.getResultEnum().getCode()).
                body(new ResultBean(e.getResultEnum()));
    }
}
