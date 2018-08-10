package com.video.common.bean.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 带数据的响应消息体(公共使用，可以抽取)
 *
 * @author: master
 * @date: 2018/7/29
 */
@Setter
@Getter

@ToString
public class BaseDataResponseVo<T> extends BaseResponseVo {

    /**
     * 存在响应数据
     */
    private T data;

    public BaseDataResponseVo(long code, String message, T data) {
        super(code, message);
        this.data = data;
    }
}
