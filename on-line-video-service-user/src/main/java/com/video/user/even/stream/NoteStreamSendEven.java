package com.video.user.even.stream;

import com.video.external.common.bean.vo.InformationCreateRequest;
import com.video.user.even.send.NoteActivationStreamSend;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

/**
 * 发送短信请求
 *
 * @author: master
 * @date: 2018/8/13
 */
@Slf4j
@Component
@EnableBinding(NoteActivationStreamSend.class)
public class NoteStreamSendEven {

    @Autowired
    private NoteActivationStreamSend noteActivationStreamSend;

    public void sendUserRegisterNote(InformationCreateRequest informationCreateRequest) {
        log.info("发送短信消息:" + informationCreateRequest.toString());
        noteActivationStreamSend.output().send(MessageBuilder.withPayload(informationCreateRequest).build());
    }
}
