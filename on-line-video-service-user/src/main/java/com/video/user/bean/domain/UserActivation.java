package com.video.user.bean.domain;

import com.video.common.bean.common.BaseDomain;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author: master
 * @date: 2018/8/8
 */
@Getter
@Setter
@ToString
@Entity
public class UserActivation extends BaseDomain {

    @Id
    private long userId;

    private String activationCode;

    private boolean activation = false;
}
