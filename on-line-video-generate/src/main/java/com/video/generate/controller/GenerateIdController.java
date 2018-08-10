package com.video.generate.controller;

import com.video.generate.common.bean.vo.GenerateIdResponseVo;
import com.video.generate.common.service.GenerateIdFeignService;
import com.video.generate.service.GenerateIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author: master
 * @date: 2018/7/23
 */
@RestController
@RequestMapping(value = "system")
public class GenerateIdController implements GenerateIdFeignService {

    @Autowired
    private GenerateIdService generateIdService;

    @Override
    @GetMapping(value = "generate/default")
    public Long generateDefaultId() {
        Long generateId = generateIdService.nextId();
        return generateId;
    }

    @Override
    @PostMapping(value = "generate/custom")
    public GenerateIdResponseVo generateCustomId(@RequestParam(name = "datacenterId") long datacenterId, @RequestParam(name = "workerId") long workerId) {
        Long generateId = generateIdService.nextId(datacenterId, workerId);
        GenerateIdResponseVo responseVo = new GenerateIdResponseVo();
        responseVo.setGenerateId(generateId);
        return responseVo;
    }
}
