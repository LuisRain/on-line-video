package com.video.user.service.impl;

import com.video.user.bean.domain.UserToken;
import com.video.user.bean.vo.UserTokenInfoVo;
import com.video.user.exception.UserTokenPhoneAndEmailNullException;
import com.video.user.repository.UserTokenRepository;
import com.video.user.service.UserTokenService;
import com.video.user.service.remote.GenerateIdService;
import com.video.user.util.EncryptionUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * @author: master
 * @date: 2018/7/29
 */

@Service
@Slf4j
public class UserTokenServiceImpl implements UserTokenService {

    @Autowired
    private GenerateIdService generateIdService;

    @Autowired
    private UserTokenRepository userTokenRepository;

    @Override
    public UserToken getUserTokenByEmail(String email) {
        return null;
    }

    @Override
    public UserToken getUserTokenByPhone(String phone) {
        return null;
    }

    @Override
    public UserToken getUserTokenByUserId(String userId) {
        return null;
    }

    @Override
    public List<UserTokenInfoVo> pageUserToken(int page, int size) {
        return null;
    }

    @Override
    public void saveUserToken(UserTokenInfoVo userTokenInfoVo) throws ExecutionException, InterruptedException {
        // 判断提交的信息中提交的是邮箱还是手机号码
        if (userTokenInfoVo.getEmail() != null || userTokenInfoVo.getPhone() != null) {
            UserToken userToken = new UserToken();
            userToken.setUserId(generateIdService.generateCustomId(1, 1).getGenerateId());
            userToken.setEmail(userTokenInfoVo.getEmail());
            userToken.setPhone(userTokenInfoVo.getPhone());
            userToken.setSalt(EncryptionUtils.randomSalt(8));
            userToken.setPassword(EncryptionUtils.encryption(userTokenInfoVo.getPassword(), userToken.getSalt()));
            userTokenRepository.save(userToken);
        } else {
            // 手机号或者邮箱都为空的时候抛出异常
            throw new UserTokenPhoneAndEmailNullException();
        }
    }
}
