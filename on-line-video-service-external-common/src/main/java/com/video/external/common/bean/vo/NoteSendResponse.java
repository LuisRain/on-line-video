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

    private String phone;

    private int code;

    private String message;
}
