package com.video.external.note.bean.domain;

import com.video.common.bean.common.BaseDomain;
import com.video.external.common.bean.mate.InformationType;
import com.video.external.common.bean.mate.NoteSignatureType;
import com.video.external.common.bean.mate.NoteTemplateType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * 短信发送表
 *
 * @author: master
 * @date: 2018/8/9
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
public class Note extends BaseDomain {

    /**
     * 短信Id
     */
    @Id
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
    private InformationType type;

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

    /**
     * 是否立即发送,默认true立即发送
     */
    private boolean immediately = true;

    /**
     * 短信签名类型
     */
    private NoteSignatureType signatureType;

    /**
     * 短信模板类型
     */
    private NoteTemplateType templateType;
}
