package com.video.external.common.bean.vo;

import com.video.external.common.bean.mate.NoteType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 创建短信请求
 *
 * @author: master
 * @date: 2018/8/10
 */
@Getter
@Setter
@ToString
public class NoteCreateRequest {

    private long userId;

    private String phone;

    private NoteType type;

    private String noteContent;
}
