package com.video.external.note.bean.mate;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author: master
 * @date: 2018/8/16
 */
@Getter
@Setter
@ToString
@Configuration
@ConfigurationProperties(prefix = "note.service")
public class NoteServiceConfig {

    /**
     * 短信服务是否开启
     */
    private boolean enable = true;
}
