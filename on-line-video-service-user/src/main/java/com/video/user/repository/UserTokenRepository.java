package com.video.user.repository;

import com.video.user.bean.domain.UserToken;
import org.springframework.data.repository.CrudRepository;

/**
 * @author: master
 * @date: 2018/7/22
 */
public interface UserTokenRepository extends CrudRepository<UserToken, String> {

    /**
     * 根据用户的邮箱查询用户的安全信息
     *
     * @param userId 用户Id
     * @return 用户安全信息
     */
    UserToken findByUserId(Long userId);

    /**
     * 根据用户的邮箱查询用户的安全信息
     *
     * @param email 邮箱
     * @return 用户安全信息
     */
    UserToken findByEmail(String email);

    /**
     * 根据用户的邮箱查询用户的安全信息
     *
     * @param phone 邮箱
     * @return 用户安全信息
     */
    UserToken findByPhone(String phone);

    /**
     * 根据用户的邮箱或者手机号码查询用户的安全信息
     *
     * @param email 邮箱
     * @return 用户安全信息
     */
    UserToken findByEmailOrPhone(String email, String phone);

    /**
     * 判断是否存在此userId
     *
     * @param userId 用户userId
     * @return
     */
    boolean existsUserTokenByUserId(Long userId);

    /**
     * 查询用需要用户的邮箱是否已经注册
     *
     * @param email 邮箱
     * @return true已经存在，false不存在
     */
    boolean existsByEmail(String email);

    /**
     * 查询用需要用户的手机是否已经注册
     *
     * @param phone 手机
     * @return true已经存在，false不存在
     */
    boolean existsByPhone(String phone);
}
