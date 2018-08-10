package com.video.user.repository;

import com.video.user.bean.domain.UserToken;
import org.springframework.data.repository.CrudRepository;

/**
 * @author: master
 * @date: 2018/7/22
 */
public interface UserTokenRepository extends CrudRepository<UserToken, String> {
}
