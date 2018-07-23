package com.video.generate.controller;

import com.video.generate.service.GenerateIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author: master
 * @date: 2018/7/23
 */
@RestController
@RequestMapping(value = "system")
public class GenerateIdController {

    @Autowired
    private GenerateIdService generateIdService;

    @Async
    @GetMapping(value = "generate/id")
    public Long generateId() {
        return generateIdService.nextId();
    }
}
