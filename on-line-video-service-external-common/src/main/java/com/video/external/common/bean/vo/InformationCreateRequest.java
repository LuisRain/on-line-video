package com.video.external.common.bean.vo;

import com.video.external.common.bean.mate.InformationType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 创建短信请求
 *
 * @author: master
 * @date: 2018/8/10
 */
@Getter
@Setter
@ToString
public class InformationCreateRequest {

    /**
     * 要发送短信用户Id
     */
    private long userId;

    /**
     * 消息发送地址(如电话号码，邮箱地址)
     */
    private String address;

    /**
     * 消息类型
     */
    private InformationType type;

    /**
     * 消息内容
     */
    private String InformationContent;
}
