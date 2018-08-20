package com.video.external.note.bean.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * 短信日志表
 *
 * @author: master
 * @date: 2018/8/9
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
public class NoteLog {

    /**
     * 日志Id
     */
    @Id
    private long logId;

    /**
     * 短信Id
     */
    private long noteId;

    /**
     * 阿里云产生的流水号
     */
    private String bizId;

    /**
     * 短信发送后短信平台返回的代码
     */
    private String code;

    /**
     * 短信发送后短信平台返回的代码消息
     */
    private String message;
}
