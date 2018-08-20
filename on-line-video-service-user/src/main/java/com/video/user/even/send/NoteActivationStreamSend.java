package com.video.user.even.send;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/**
 * @author: master
 * @date: 2018/8/13
 */
public interface NoteActivationStreamSend {
    String NOTE_ACTIVATION_CHANNEL = "note_activation_channel";

    @Output(value = NOTE_ACTIVATION_CHANNEL)
    MessageChannel output();
}
