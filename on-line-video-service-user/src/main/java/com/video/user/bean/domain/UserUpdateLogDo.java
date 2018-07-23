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
public class UserUpdateLogDo {

    @Id
    private String logId;

    private String userId;

    private String attributeName;

    private String attributeOldVal;

    private String attributeNewVal;

    private Date updateDate;
}
