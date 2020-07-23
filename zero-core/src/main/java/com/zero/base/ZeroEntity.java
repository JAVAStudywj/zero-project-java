package com.zero.base;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 *基础公共实体对像
 * @author  DILGUO
 * @date    2020-04-28
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ZeroEntity implements Serializable {

    @Id
    @KeySql(useGeneratedKeys = true)
    private Long id;            //	KEY	BIGINT			TRUE	FALSE	TRUE
    private String isFlag;      //	状态	char(1)	1		FALSE	FALSE	FALSE
    private Long createBy;      //	创建人	BIGINT			FALSE	FALSE	FALSE
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;    //	创建时间	time			FALSE	FALSE	FALSE
    private Long updateBy;     //   修改人	BIGINT			FALSE	FALSE	FALSE
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;    //	修改时间	time			FALSE	FALSE	FALSE

}
