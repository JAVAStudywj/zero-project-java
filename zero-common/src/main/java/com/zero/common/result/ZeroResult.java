package com.zero.common.result;

import com.zero.common.enums.ResultEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 *  结果返回数据处理
 * **
 * @author: Dilguo
 * @create: 2020-01-14
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ZeroResult extends  RuntimeException {
    //返回结果信息枚举
    private ResultEnum resultEnum;

}
