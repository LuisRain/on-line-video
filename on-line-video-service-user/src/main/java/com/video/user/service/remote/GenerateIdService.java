package com.video.user.service.remote;

import com.video.generate.common.service.GenerateIdFeignService;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * 对外生成全局Id的服务接口
 *
 * @author: master
 * @date: 2018/8/8
 */
@FeignClient(value = "on-line-video-service-generate")
public interface GenerateIdService extends GenerateIdFeignService {
}
