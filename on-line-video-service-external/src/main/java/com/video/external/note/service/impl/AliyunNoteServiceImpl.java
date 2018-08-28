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
import com.video.external.common.bean.mate.NoteSignatureType;
import com.video.external.common.bean.mate.NoteTemplateType;
import com.video.external.common.bean.vo.InformationCreateRequest;
import com.video.external.note.annotation.CheckNoteServiceEnable;
import com.video.external.note.bean.domain.Note;
import com.video.external.note.bean.domain.NoteLog;
import com.video.external.note.bean.mate.AliyunNoteConfig;
import com.video.external.note.repository.NoteLogRepository;
import com.video.external.note.repository.NoteRepository;
import com.video.external.note.service.AliyunNoteService;
import com.video.external.util.GenerateIdUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

/**
 * @author: master
 * @date: 2018/8/10
 */
@Slf4j
@Service
public class AliyunNoteServiceImpl implements AliyunNoteService {

    @Autowired
    private NoteRepository noteRepository;

    @Autowired
    private NoteLogRepository noteLogRepository;

    @Autowired
    private AliyunNoteConfig aliyunNoteConfig;

    @CheckNoteServiceEnable
    @Override
    @Async
    public void sendNote(InformationCreateRequest informationCreateRequest) throws ClientException {
        sendNote(informationCreateRequest, true);
    }

    @CheckNoteServiceEnable
    @Override
    public void sendNote(InformationCreateRequest informationCreateRequest, boolean immediately) throws ClientException {
        Note note = packageNote(informationCreateRequest, immediately);
        SendSmsResponse sendSmsResponse = null;
        if (immediately) {
            // 立刻发送,并写入数据库中
            sendSmsResponse = aliyunSendNote(note);
        }
        if (sendSmsResponse != null) {
            note.setSend(true);
            NoteLog noteLog = packageNoteLog(sendSmsResponse, note.getNoteId());
            noteLogRepository.save(noteLog);
        }
        // 不管成不成功都会保存到数据库中
        noteRepository.save(note);
    }

    @CheckNoteServiceEnable
    @Override
    public void snedDatabaseNote(long noteId) throws ClientException {
        Optional<Note> optionalNote = noteRepository.findById(noteId);
        if (optionalNote.isPresent()) {
            Note note = optionalNote.get();
            SendSmsResponse sendSmsResponse = aliyunSendNote(note);
            // 判断是否已经发送成功，并进行日志记录
            NoteLog noteLog = packageNoteLog(sendSmsResponse, noteId);
            note.setSend(true);
            noteLogRepository.save(noteLog);
            noteRepository.save(note);
            return;
        }
        // 在此处理此需要发送的短信不存在异常
    }

    @CheckNoteServiceEnable
    @Override
    public QuerySendDetailsResponse querySendActivationDetails(String bizId, Note note) throws ClientException {
        return querySendDetails(bizId, note);
    }

    /**
     * 封装短信实体信息
     *
     * @param informationCreateRequest 需要发送短信请求的基本信息
     * @return 短信完整信息实例
     */
    private Note packageNote(InformationCreateRequest informationCreateRequest, boolean immediately) {
        Note note = new Note();
        note.setNoteId(GenerateIdUtils.genterateId());
        note.setSignatureType(NoteSignatureType.覃玉初);
        note.setImmediately(immediately);
        note.setTemplateType(NoteTemplateType.SMS_142030014);
        note.setPhone(informationCreateRequest.getAddress());
        note.setNoteContent(informationCreateRequest.getInformationContent());
        note.setUserId(informationCreateRequest.getUserId());
        note.setType(informationCreateRequest.getType());
        note.update();
        return note;
    }

    /**
     * 封装短信发送后响应的消息日志
     *
     * @param sendSmsResponse 短信发送后阿里返回的响应消息体
     * @return 封装好的日志
     */
    private NoteLog packageNoteLog(SendSmsResponse sendSmsResponse, long noteId) {
        // 此处出现一个问题,发送信息是扩展字段是发送的短信noteId，根据阿里文档说明，响应后会返回此扩展字段，并没有发现获取方法
        // 暂时使用long noteId参数代替
        NoteLog noteLog = new NoteLog();
        noteLog.setLogId(GenerateIdUtils.genterateId());
        noteLog.setNoteId(noteId);
        noteLog.setBizId(sendSmsResponse.getBizId());
        noteLog.setCode(sendSmsResponse.getCode());
        noteLog.setMessage(sendSmsResponse.getMessage());
        return noteLog;
    }

    /**
     * 连接阿里云并推送短信服务
     *
     * @param note 短信信息体
     * @return
     * @throws ClientException
     */
    private SendSmsResponse aliyunSendNote(Note note) throws ClientException {
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");
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
        sendSmsRequest.setTemplateParam("{\"code\":\"" + note.getNoteContent() + "\"}");
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
     * 从阿里云短信服务中的查询短信
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