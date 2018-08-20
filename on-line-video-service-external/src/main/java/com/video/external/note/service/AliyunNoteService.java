package com.video.external.note.service;

import com.aliyuncs.dysmsapi.model.v20170525.QuerySendDetailsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.video.external.common.bean.vo.InformationCreateRequest;
import com.video.external.note.bean.domain.Note;

/**
 * 阿里云短信服务，此项目的短信服务暂时不提供过批量处理短信发送
 * 原因:资费问题，别别人没有对这个接口进行轰炸，自己人倒是对这个接口进行了轰炸
 *
 * @author: master
 * @date: 2018/8/10
 */
public interface AliyunNoteService {

    /**
     * 发送一条短信(激活码直接使用)
     *
     * @param informationCreateRequest 短信的基本信息
     */
    void sendNote(InformationCreateRequest informationCreateRequest) throws ClientException;

    /**
     * 创建短信是否立刻发送(属于后台人员操作使用)
     *
     * @param informationCreateRequest 创建短信的基本信息
     * @param flag                     true立刻发送,false暂存数据库等待发送
     */
    void sendNote(InformationCreateRequest informationCreateRequest, boolean flag) throws ClientException;

    /**
     * 发送数据库中预存的还没有发送的短信(创建但是并没有发送)
     *
     * @param noteId 短信Id
     */
    void snedDatabaseNote(long noteId) throws ClientException;

    /**
     * 查询激活短信日志
     *
     * @param bizId
     * @param note
     * @return
     */
    QuerySendDetailsResponse querySendActivationDetails(String bizId, Note note) throws ClientException;
}
