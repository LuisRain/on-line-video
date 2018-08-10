package com.video.external.note.service.impl;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.QuerySendDetailsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.QuerySendDetailsResponse;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.netflix.client.config.DefaultClientConfigImpl;
import com.netflix.client.config.IClientConfig;
import com.video.external.common.bean.vo.NoteCreateRequest;
import com.video.external.note.bean.domain.Note;
import com.video.external.note.bean.mate.AliyunNoteConfig;
import com.video.external.note.repository.NoteLongRepository;
import com.video.external.note.repository.NoteRepository;
import com.video.external.note.service.AliyunNoteService;
import javafx.beans.DefaultProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author: master
 * @date: 2018/8/10
 */
@Service
public class AliyunNoteServiceImpl implements AliyunNoteService {

    @Autowired
    private NoteRepository noteRepository;

    @Autowired
    private NoteLongRepository noteLongRepository;

    @Autowired
    private AliyunNoteConfig aliyunNoteConfig;

    @Override
    public void sendNote(NoteCreateRequest noteCreateRequest) {

    }

    @Override
    public void sendNote(NoteCreateRequest noteCreateRequest, boolean flag) {

    }

    @Override
    public void snedDatabaseNote(long noteId) {

    }

    /**
     * 连接阿里云并推送短信服务
     *
     * @param note 短信信息体
     * @return
     * @throws ClientException
     */
    private SendSmsResponse aliyunSendNote(Note note) throws ClientException {
        //初始化acsClient
        IClientProfile iClientConfig = DefaultProfile.getProfile("cn-hangzhou",
                aliyunNoteConfig.getAccessKeyId(), aliyunNoteConfig.getAccessKeySecret());
        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou",
                aliyunNoteConfig.getProduct(), aliyunNoteConfig.getDomain());
        IAcsClient iAcsClient = new DefaultAcsClient(iClientConfig);

        //组装请求对象
        SendSmsRequest sendSmsRequest = new SendSmsRequest();
        sendSmsRequest.setPhoneNumbers(note.getPhone());
        sendSmsRequest.setSignName(note.getSignatureType().name());
        sendSmsRequest.setTemplateCode(note.getTemplateType().name());
        sendSmsRequest.setTemplateParam(note.getNoteContent());
        // 短信扩展字段为发送的短信id
        sendSmsRequest.setOutId(String.valueOf(note.getNoteId()));

        // 发送响应
        SendSmsResponse sendSmsResponse = iAcsClient.getAcsResponse(sendSmsRequest);
        return sendSmsResponse;
    }

    /**
     * 查询短信发送记录，这个方法主要用户查询激活码发送后查询并记录到短信发送日志中
     *
     * @param bizId 流水号
     * @param note  短信基本信息体
     * @return
     * @throws ClientException
     */
    private QuerySendDetailsResponse querySendDetails(String bizId, Note note) throws ClientException {
        QuerySendDetailsResponse querySendDetailsResponse = querySendDetails(note.getPhone(), bizId, note.getCreateTime(), 1, 1);
        return querySendDetailsResponse;
    }

    /**
     * 共用的查询短信
     *
     * @param phone 手机号码
     * @param bizId 流水号
     * @param date  发送时间
     * @param page  当前页面
     * @param size  页面大小
     * @return
     * @throws ClientException
     */
    private QuerySendDetailsResponse querySendDetails(String phone, String bizId, Date date, long page, long size) throws ClientException {
        //初始化acsClient
        IClientProfile iClientConfig = DefaultProfile.getProfile("cn-hangzhou",
                aliyunNoteConfig.getAccessKeyId(), aliyunNoteConfig.getAccessKeySecret());
        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou",
                aliyunNoteConfig.getProduct(), aliyunNoteConfig.getDomain());
        IAcsClient iAcsClient = new DefaultAcsClient(iClientConfig);

        //组装请求对象
        QuerySendDetailsRequest querySendDetailsRequest = new QuerySendDetailsRequest();
        querySendDetailsRequest.setBizId(bizId);
        querySendDetailsRequest.setPhoneNumber(phone);
        querySendDetailsRequest.setSendDate(new SimpleDateFormat("yyyyMMdd").format(date));
        querySendDetailsRequest.setCurrentPage(page);
        querySendDetailsRequest.setPageSize(size);

        QuerySendDetailsResponse querySendDetailsResponse = iAcsClient.getAcsResponse(querySendDetailsRequest);
        return querySendDetailsResponse;
    }
}
