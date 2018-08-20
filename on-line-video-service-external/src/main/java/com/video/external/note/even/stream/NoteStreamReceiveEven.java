package com.video.external.note.even.stream;

import com.aliyuncs.exceptions.ClientException;
import com.video.external.common.bean.vo.InformationCreateRequest;
import com.video.external.note.even.receive.NoteActivationStreamReceive;
import com.video.external.note.even.receive.NoteCustomStreamReceive;
import com.video.external.note.service.AliyunNoteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

/**
 * 接收发送短信的请求
 *
 * @author: master
 * @date: 2018/8/13
 */
@EnableBinding(value = {NoteActivationStreamReceive.class, NoteCustomStreamReceive.class})
@Component
@Slf4j
public class NoteStreamReceiveEven {

    @Autowired
    private AliyunNoteService aliyunNoteService;

    /**
     * 获取注册服务发出发送激活码的监听事件
     *
     * @param informationCreateRequest 消息体
     * @throws ClientException 短信消息发送异常
     */
    @StreamListener(target = NoteActivationStreamReceive.NOTE_ACTIVATION_CHANNEL)
    public void receiveNoteActivationCodeSendEven(InformationCreateRequest informationCreateRequest) throws ClientException {
        log.info("短信请求内容:" + informationCreateRequest.toString());
        if (informationCreateRequest.getAddress() != null && !(informationCreateRequest.getAddress().trim()).equals("")) {
            if (!(informationCreateRequest.getInformationContent().trim()).equals("") && informationCreateRequest.getInformationContent() != null) {
                aliyunNoteService.sendNote(informationCreateRequest);
            } else {
                log.info("短信内容为空!");
            }
        } else {
            log.info("发送短信号码为空!");
        }
    }

    /**
     * 后台管理人员自定义发送短信服务的监听事件
     *
     * @param informationCreateRequest 消息体
     * @param immediately       是否立即发送,true立即发送,false暂存数据库,null默认为false
     * @throws ClientException 短信消息发送异常
     */
    @StreamListener(target = NoteCustomStreamReceive.NOTE_CUSTOM_CHANNEL)
    public void receiveNoteCustomSendEven(InformationCreateRequest informationCreateRequest, Boolean immediately) throws ClientException {
        if (immediately == null) {
            immediately = false;
        }
        aliyunNoteService.sendNote(informationCreateRequest, immediately);
    }
}
