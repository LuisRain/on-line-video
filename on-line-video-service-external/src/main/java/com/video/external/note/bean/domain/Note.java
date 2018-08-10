package com.video.external.note.bean.domain;

import com.video.common.bean.common.BaseDomain;
import com.video.external.common.bean.mate.NoteType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 短信发送表
 *
 * @author: master
 * @date: 2018/8/9
 */
@Getter
@Setter
@ToString
public class Note extends BaseDomain {

    /**
     * 短信Id
     */
    private long noteId;

    /**
     * 发送到指定用户的userId
     */
    private long userId;

    /**
     * 发送的电话号码
     */
    private String phone;

    /**
     * 发送的短信类型
     */
    private NoteType type;

    /**
     * 短信的内容
     */
    private String noteContent;

    /**
     * 短信是否有效，针对于验证码或者激活码
     */
    private boolean isEffective = false;

    /**
     * 短信是否已经发送出去，无论发送成功否
     */
    private boolean isSend = false;
}
