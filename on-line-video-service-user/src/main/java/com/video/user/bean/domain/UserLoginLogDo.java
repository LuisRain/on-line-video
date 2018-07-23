package com.video.user.bean.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author: master
 * @date: 2018/7/22
 */
@Setter
@Getter
@ToString
@Entity
public class UserLoginLogDo {

    @Id
    private String logId;

    private String userId;

    private String type;

    private String command;

    private String deviceId;

    private String lastIp;
}
