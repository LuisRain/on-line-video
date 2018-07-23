package com.video.user.repository;

import com.video.user.bean.domain.UserInfoDo;
import org.springframework.data.repository.CrudRepository;

/**
 * @author: master
 * @date: 2018/7/22
 */
public interface UserInfoRepository extends CrudRepository<UserInfoDo, String> {
}
