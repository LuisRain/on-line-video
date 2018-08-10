package com.video.user.bean.domain;

import com.video.common.bean.common.BaseDomain;
import com.video.user.bean.meta.UserStatusType;
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
public class UserToken extends BaseDomain {

    @Id
    private long userId;

    private String email;

    private String phone;

    private String password;

    private String salt;

    private UserStatusType status = UserStatusType.NON_ACTIVATED;

    private boolean isAdmin = false;
}
