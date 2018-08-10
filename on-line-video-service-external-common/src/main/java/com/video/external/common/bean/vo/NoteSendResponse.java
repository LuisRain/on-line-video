package com.video.external.common.bean.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 短信发送响应
 *
 * @author: master
 * @date: 2018/8/10
 */
@Getter
@Setter
@ToString
public class NoteSendResponse {

    /**
     * 发送短信的短信Id
     */
    private long nodeId;

    /**
     * 短信号码
     */
    private String phone;

    /**
     * 短信响应代码
     */
    private int code;

    /**
     * 短信响应代码消息
     */
    private String message;
}
