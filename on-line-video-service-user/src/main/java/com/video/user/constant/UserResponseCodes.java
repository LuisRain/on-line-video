package com.video.user.constant;

import com.video.common.constant.BaseResponseCodes;

/**
 * @author: master
 * @date: 2018/7/30
 */
public class UserResponseCodes extends BaseResponseCodes {
    /**
     * 用户还没有激活
     */
    public static final int USER_NOT_ACTIVATION = 20001;
    /**
     * 用户不存在
     */
    public static final int USER_NOT_FOUND = 20002;
    /**
     * 用户密码错误
     */
    public static final int USER_PASSWORD_INACCURACY = 20003;
    /**
     * 用户已经被封禁
     */
    public static final int USER_BANNED = 20004;
    /**
     * 用户不是管理员
     */
    public static final int USER_NOT_ADMIN = 20005;
    /**
     * 用户邮箱已经存在
     */
    public static final int USER_EMAIL_EXIST = 20006;
    /**
     * 用户邮箱已经存在
     */
    public static final int USER_PHONE_EXIST = 20007;
    /**
     * 提交的邮箱信息为null
     */
    public static final int USER_EMAIL_NULL = 20008;
    /**
     * 提交的手机信息为空
     */
    public static final int USER_PHONE_NULL = 20009;
    /**
     * 提交的手机信息和邮箱信息都为空
     */
    public static final int USER_PHONE_AND_EMAIL_NULL = 20010;
}
