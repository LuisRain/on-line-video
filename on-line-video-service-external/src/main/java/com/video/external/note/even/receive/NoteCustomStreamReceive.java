package com.video.external.note.even.receive;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

/**
 * 自定义短信发送
 *
 * @author: master
 * @date: 2018/8/14
 */
public interface NoteCustomStreamReceive {
    String NOTE_CUSTOM_CHANNEL = "note_custom_channel";

    @Input(value = NoteCustomStreamReceive.NOTE_CUSTOM_CHANNEL)
    SubscribableChannel input();
}
