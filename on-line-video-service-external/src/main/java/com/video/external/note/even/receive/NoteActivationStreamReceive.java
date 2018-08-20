package com.video.external.note.even.receive;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

/**
 * @author: master
 * @date: 2018/8/13
 */

public interface NoteActivationStreamReceive {

    String NOTE_ACTIVATION_CHANNEL = "note_activation_channel";

    @Input(value = NoteActivationStreamReceive.NOTE_ACTIVATION_CHANNEL)
    SubscribableChannel input();
}
