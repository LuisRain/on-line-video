package com.video.user.service.impl;

import com.video.external.common.bean.mate.InformationType;
import com.video.external.common.bean.vo.InformationCreateRequest;
import com.video.user.bean.domain.UserActivation;
import com.video.user.bean.domain.UserToken;
import com.video.user.bean.meta.ActivationSendType;
import com.video.user.bean.vo.UserTokenInfoVo;
import com.video.user.even.stream.NoteStreamSendEven;
import com.video.user.exception.UserEmailRepetitionException;
import com.video.user.exception.UserPhoneRepetitionException;
import com.video.user.exception.UserTokenPhoneAndEmailNullException;
import com.video.user.repository.UserActivationRepository;
import com.video.user.repository.UserTokenRepository;
import com.video.user.service.UserTokenService;
import com.video.user.service.remote.GenerateIdService;
import com.video.user.util.EncryptionUtils;
import com.video.user.util.ShortStringUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
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

    @Autowired
    private NoteStreamSendEven noteStreamSendEven;

    @Autowired
    private UserActivationRepository userActivationRepository;

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
    public void saveUserToken(UserTokenInfoVo userTokenInfoVo) throws ExecutionException, InterruptedException, UnsupportedEncodingException {
        if (StringUtils.isEmpty(userTokenInfoVo.getEmail()) && StringUtils.isEmpty(userTokenInfoVo.getPhone())) {
            // 手机号或者邮箱都为空的时候抛出异常
            throw new UserTokenPhoneAndEmailNullException();
        }
        if (!StringUtils.isEmpty(userTokenInfoVo.getEmail()) && userTokenRepository.existsByEmail(userTokenInfoVo.getEmail())) {
            throw new UserPhoneRepetitionException();
        } else if (!StringUtils.isEmpty(userTokenInfoVo.getPhone()) && userTokenRepository.existsByPhone(userTokenInfoVo.getPhone())) {
            throw new UserEmailRepetitionException();
        }
        // 判断提交的信息中提交的是邮箱还是手机号码
        UserToken userToken = new UserToken();
        userToken.setUserId(generateIdService.generateCustomId(1, 1).getGenerateId());
        userToken.setEmail(userTokenInfoVo.getEmail());
        userToken.setPhone(userTokenInfoVo.getPhone());
        userToken.setSalt(EncryptionUtils.randomSalt(8));
        userToken.setPassword(EncryptionUtils.encryption(userTokenInfoVo.getPassword(), userToken.getSalt()));
        if (userTokenRepository.save(userToken) != null) {
            // 生成激活码原码
            InformationCreateRequest informationCreateRequest = new InformationCreateRequest();
            String activationCode = EncryptionUtils.buildActivationCode(String.valueOf(userToken.getUserId()), userTokenInfoVo.getPassword());
            // 此服务类型可封装在InformationCreateRequest中，后期可以考虑
            ActivationSendType activationSendType = null;
            if (!StringUtils.isEmpty(userTokenInfoVo.getPhone())) {
                // 短信服务
                informationCreateRequest = packageNote(userToken, userTokenInfoVo.getPassword());
                informationCreateRequest.setType(InformationType.ACTIVATION_CODE);
                activationSendType = ActivationSendType.NOTE;
                noteStreamSendEven.sendUserRegisterNote(informationCreateRequest);
            } else {
                // 邮件服务
                activationSendType = ActivationSendType.EMAIL;
            }
            // 将短信激活码原码和短信激活码短码进行持久化处理
            userActivationRepository.save(packageUserActivation(informationCreateRequest, activationSendType, activationCode));
        }
    }

    /**
     * 构建短信验证码消息体
     *
     * @param userToken      用户的基础信息
     * @param activationCode 激活码原码
     * @return
     */
    private InformationCreateRequest packageNote(UserToken userToken, String activationCode) {
        InformationCreateRequest informationCreateRequest = new InformationCreateRequest();
        informationCreateRequest.setType(InformationType.ACTIVATION_CODE);
        informationCreateRequest.setAddress(userToken.getPhone());
        informationCreateRequest.setUserId(userToken.getUserId());
        String noteShortActivationCode = ShortStringUtil.shortStringText(activationCode);
        informationCreateRequest.setInformationContent(noteShortActivationCode);
        return informationCreateRequest;
    }

    /**
     * 将激活码记录进行持久化处理
     *
     * @param informationCreateRequest 包含部分激活码发送记录信息
     * @param activationSendType       发送信息的服务(比如短信或者邮件)
     * @param activationCode           激活码原码内容
     * @return
     */
    private UserActivation packageUserActivation(InformationCreateRequest informationCreateRequest, ActivationSendType activationSendType, String activationCode) {
        UserActivation userActivation = new UserActivation();
        userActivation.setUserId(informationCreateRequest.getUserId());
        userActivation.setActivationCode(activationCode);
        userActivation.setActivationShortCode(informationCreateRequest.getInformationContent());
        userActivation.update();
        return userActivation;
    }
}
