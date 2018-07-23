package com.video.user.repository;

import com.video.user.bean.domain.UserUpdateLogDo;
import org.springframework.data.repository.CrudRepository;

/**
 * @author: master
 * @date: 2018/7/22
 */
public interface UserUpdateLogRepository extends CrudRepository<UserUpdateLogDo, String> {
}
