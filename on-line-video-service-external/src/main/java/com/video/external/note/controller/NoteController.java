package com.video.external.note.controller;

import com.aliyuncs.exceptions.ClientException;
import com.video.external.common.bean.vo.InformationCreateRequest;
import com.video.external.note.service.AliyunNoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 短信控制器
 *
 * @author: master
 * @date: 2018/8/11
 */
@RestController
@RequestMapping(value = "note")
public class NoteController {

    @Autowired
    private AliyunNoteService aliyunNoteService;

    @PostMapping(value = "activation")
    public void sendActivationNote(@RequestBody @Validated InformationCreateRequest informationCreateRequest) throws ClientException {
        aliyunNoteService.sendNote(informationCreateRequest);
    }
}
