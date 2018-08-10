package com.video.external.note.bean.mate;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 阿里鱼短信发送配置
 *
 * @author: master
 * @date: 2018/8/11
 */
@Getter
@Setter
@ToString
@Configuration
@ConfigurationProperties(prefix = "aliyun.note")
public class AliyunNoteConfig {

    /**
     * 产品名称
     */
    private String product = "Dysmsapi";

    /**
     * 产品域名
     */
    private String domain = "dysmsapi.aliyuncs.com";

    /**
     * 访问短信服务key
     */
    private String accessKeyId = "LTAIstd90MCv2AhQ";

    /**
     * 访问短息服务的秘钥
     */
    private String accessKeySecret = "dzISvL3HuVB7Wh9vEuBci60M75mn6P";

}
