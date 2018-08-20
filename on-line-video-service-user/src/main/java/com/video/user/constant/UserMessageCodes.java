package com.video.user.constant;

import com.video.common.constant.BaseMessageCodes;

/**
 * 用户消息代码
 *
 * @author: master
 * @date: 2018/7/30
 */
public class UserMessageCodes extends BaseMessageCodes {
    /**
     * 用户还没有激活
     */
    public static final String USER_NOT_ACTIVATION = "user.not.activation";
    /**
     * 用户不存在
     */
    public static final String USER_NOT_FOUND = "user.not.found";
    /**
     * 用户密码错误
     */
    public static final String USER_PASSWORD_INACCURACY = "user.password.inaccuracy";
    /**
     * 用户已经被封禁
     */
    public static final String USER_BANNED = "user.banned";
    /**
     * 用户不是管理员
     */
    public static final String USER_NOT_ADMIN = "user.not.admin";
    /**
     * 用户邮箱已经存在
     */
    public static final String USER_EMAIL_EXIST = "user.email.exist";
    /**
     * 用户手机已经存在
     */
    public static final String USER_PHONE_EXIST = "user.phone.exist";
    /**
     *提交的邮箱信息为null
     */
    public static final String USER_EMAIL_NULL = "user.email.null";
    /**
     * 提交的手机信息为空
     */
    public static final String USER_PHONE_NULL = "user.phone.null";
    /**
     * 提交的手机信息和邮箱信息都为空
     */
    public static final String USER_PHONE_AND_EMAIL_NULL = "user.phone.and.email.null";
}
