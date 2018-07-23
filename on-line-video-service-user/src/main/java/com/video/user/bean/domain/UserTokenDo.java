package com.video.user.bean.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

/**
 * @author: master
 * @date: 2018/7/22
 */
@Setter
@Getter
@ToString
@Entity
public class UserTokenDo {

    @Id
    private String userId;

    private String email;

    private String phone;

    private String password;

    private String salt;

    private String status;

    private boolean isAdmin = false;

    private Date createTime;

    private Date updateTime = new Date();
}
