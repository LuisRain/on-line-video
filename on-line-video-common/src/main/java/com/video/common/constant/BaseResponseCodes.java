package com.video.common.constant;

/**
 * 系统通用异常处理代码
 *
 * @author: master
 * @date: 2018/7/29
 */
public class BaseResponseCodes {

    /**
     * 系统响应成功
     */
    public static final int SYSTEM_RESPONSE_SUCCESS = 10000;
    /**
     * 系统响应失败
     */
    public static final int SYSTEM_RESPONSE_ERROR = 10004;
    /**
     * 系统请求参数错误
     */
    public static final int SYSTEM_PARAMETER_ERROR = 10001;
    /**
     * 系统非法操作
     */
    public static final int SYSTEM_ILLEGALITY_OPERATION = 10002;
    /**
     * 系统请求方法不支持
     */
    public static final int SYSTEM_METHOD_NONSUPPORT = 10005;
    /**
     * 系统繁忙在，降级处理
     */
    public static final int SYSTEM_BUSYNESS = 10006;
    /**
     * 身份验证失败
     */
    public static final int AUTH_TOKEN_DEFEATED = 1000;
    /**
     * 身份验证成功
     */
    public static final int AUTH_TOKEN_SUCCESS = 1001;
    /**
     * 身份验证码错误
     */
    public static final int AUTH_CODE_VERIFICATION_ERROR = 1002;
}
