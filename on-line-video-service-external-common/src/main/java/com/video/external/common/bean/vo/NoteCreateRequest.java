package com.video.external.common.bean.vo;

import com.video.external.common.bean.mate.NoteType;
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
public class NoteCreateRequest {

    /**
     * 要发送短信用户Id
     */
    private long userId;

    /**
     * 短信电话号码
     */
    private String phone;

    /**
     * 短信类型
     */
    private NoteType type;

    /**
     * 短信内容
     */
    private String noteContent;
}
