package com.video.generate.common.bean.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * 返回全局Id响应实体
 *
 * @author: master
 * @date: 2018/8/8
 */
@Getter
@Setter
@ToString
public class GenerateIdResponseVo {

    private long generateId;

    private long timestamp = new Date().getTime();
}
