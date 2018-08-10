package com.video.external.note.bean.domain;

/**
 * 短信日志表
 *
 * @author: master
 * @date: 2018/8/9
 */
public class NoteLog {

    /**
     * 日志Id
     */
    private long logId;

    /**
     * 短信Id
     */
    private long noteId;

    /**
     * 短信发送后短信平台返回的代码
     */
    private int code;

    /**
     * 短信发送后短信平台返回的代码消息
     */
    private String message;
}
