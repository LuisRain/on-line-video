package com.video.user.service;

import com.video.user.bean.domain.UserToken;
import com.video.user.bean.vo.UserTokenInfoVo;

import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * @author: master
 * @date: 2018/7/29
 */
public interface UserTokenService {

    /**
     * 根据邮箱获取用户的安全信息
     *
     * @param email 邮箱地址
     * @return 返回用户所有安全信息, 包含敏感数据
     */
    UserToken getUserTokenByEmail(String email);

    /**
     * 根据手机号码获取用户安全信息
     *
     * @param phone 手机号码
     * @return 返回用户所有安全信息, 包含敏感数据
     */
    UserToken getUserTokenByPhone(String phone);

    /**
     * 根据用户Id获取用户安全信息(登录后获取用户Id)
     *
     * @param userId 用户Id
     * @return 返回用户所有安全信息, 包含敏感数据
     */
    UserToken getUserTokenByUserId(String userId);

    /**
     * 分页获取用户安全信息,不包含安全信息
     *
     * @param page 页码
     * @param size 分页大小
     * @return 返回分页列表
     */
    List<UserTokenInfoVo> pageUserToken(int page, int size);

    /**
     * 创建并保存用户注册信息
     *
     * @param userTokenInfoVo 注册或者登录的信息实体
     */
    void saveUserToken(UserTokenInfoVo userTokenInfoVo) throws ExecutionException, InterruptedException;

}
