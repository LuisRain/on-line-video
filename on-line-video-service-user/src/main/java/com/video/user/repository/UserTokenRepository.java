package com.video.user.repository;

import com.video.user.bean.domain.UserTokenDo;
import org.springframework.data.repository.CrudRepository;

/**
 * @author: master
 * @date: 2018/7/22
 */
public interface UserTokenRepository extends CrudRepository<UserTokenDo, String> {
}
