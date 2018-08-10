package com.video.common.bean.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * 通用的响应消息体(可继承，公共使用，可抽取)
 *
 * @author: master
 * @date: 2018/7/29
 */
@Setter
@Getter
@ToString
public class BaseResponseVo  implements Serializable {

    /**
     * 响应代码
     */
    private long code;

    /**
     * 响应详细消息
     */
    private String message;

    /**
     * 响应时间戳
     */
    private long responseTimestamp;

    /**
     * 无数据的响应消息体
     *
     * @param code
     * @param message
     */
    public BaseResponseVo(long code, String message) {
        this.code = code;
        this.message = message;
        this.responseTimestamp = System.currentTimeMillis();
    }
}
