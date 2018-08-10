package com.video.external.note.service;

import com.video.external.common.bean.vo.NoteCreateRequest;

/**
 * @author: master
 * @date: 2018/8/10
 */
public interface AliyunNoteService {

    /**
     * 发送一条短信(激活码直接使用)
     *
     * @param noteCreateRequest 短信的基本信息
     */
    void sendNote(NoteCreateRequest noteCreateRequest);

    /**
     * 创建短信是否立刻发送
     *
     * @param noteCreateRequest 创建短信的基本信息
     * @param flag              true立刻发送,false暂存数据库等待发送
     */
    void sendNote(NoteCreateRequest noteCreateRequest, boolean flag);

    /**
     * 发送数据库中预存的还没有发送的短信(创建但是并没有发送)
     *
     * @param noteId 短信Id
     */
    void snedDatabaseNote(long noteId);
}
