package com.video.user.repository;

import com.video.user.bean.domain.UserActivation;
import org.springframework.data.repository.CrudRepository;

/**
 * @author: master
 * @date: 2018/8/20
 */
public interface UserActivationRepository extends CrudRepository<UserActivation, Long> {
}
