package com.video.user.repository;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;

import javax.persistence.Entity;
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
