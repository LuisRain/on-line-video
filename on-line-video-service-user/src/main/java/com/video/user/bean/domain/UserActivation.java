package com.video.user.bean.domain;

import com.video.common.bean.common.BaseDomain;
import com.video.user.bean.meta.ActivationSendType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * 账户激活码记录表，此表记录用户第一次注册时发送的激活信息
 *
 * @author: master
 * @date: 2018/8/8
 */
@Getter
@Setter
@ToString
@Entity
public class UserActivation extends BaseDomain {

    /**
     * 用户userId
     */
    @Id
    private long userId;

    /**
     * 激活短码(由激活码原码内容经过短网址算法缩减生成)
     */
    private String activationShortCode;

    /**
     * 由用户的用户userId和password通过加密算法生成
     */
    private String activationCode;

    /**
     * 激活码发送类型
     */
    private ActivationSendType type;

    /**
     * 用户是否已经激活此账户
     */
    private boolean activation = false;
}
