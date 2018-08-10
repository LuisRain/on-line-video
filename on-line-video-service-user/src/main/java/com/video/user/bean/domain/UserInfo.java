package com.video.user.bean.domain;

import com.video.common.bean.common.BaseDomain;
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
public class UserInfo extends BaseDomain {

    @Id
    private long userId;

    private String nickname;

    private String headPortrait;

    private String signature;

    private boolean sex;

    private Date dateBirth;

    private String address;
}
