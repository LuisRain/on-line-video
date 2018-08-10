package com.video.user.repository;

import com.video.user.bean.domain.UserUpdateLog;
import org.springframework.data.repository.CrudRepository;

/**
 * @author: master
 * @date: 2018/7/22
 */
public interface UserUpdateLogRepository extends CrudRepository<UserUpdateLog, String> {
}
