package com.video.user.bean.vo;

import com.video.user.bean.domain.UserInfo;
import com.video.user.bean.meta.UserStatusType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author: master
 * @date: 2018/7/29
 */
@Setter
@Getter
@ToString
public class UserTokenInfoVo {

    private String userId;

    private String email;

    private String phone;

    private String password;

    private UserStatusType status = UserStatusType.NON_ACTIVATED;

    private boolean isAdmin = false;
}
