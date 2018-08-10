package com.video.generate.common.service;

import com.video.generate.common.bean.vo.GenerateIdResponseVo;
import org.springframework.web.bind.annotation.*;

/**
 * 对外生成全局Id的服务接口
 *
 * @author: master
 * @date: 2018/8/8
 */
@RestController
@RequestMapping(value = "system")
public interface GenerateIdFeignService {

    /**
     * 获得下一个ID (该方法是线程安全的)
     *
     * @return SnowflakeId
     */
    @GetMapping(value = "generate/default")
    Long generateDefaultId();

    /**
     * 指定工作号和数据中心号获取指定区域Id，线程安全
     *
     * @param datacenterId 数据中心号
     * @param workerId     工作号
     * @return
     */
    @PostMapping(value = "generate/custom")
    GenerateIdResponseVo generateCustomId(@RequestParam(name = "datacenterId") long datacenterId,
                                                  @RequestParam(name = "workerId") long workerId);


}
