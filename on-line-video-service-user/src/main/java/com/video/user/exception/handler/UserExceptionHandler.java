package com.video.user.exception.handler;

import com.video.common.bean.vo.BaseResponseVo;
import com.video.common.exception.handler.BaseExceptionHandler;
import com.video.user.constant.UserMessageCodes;
import com.video.user.constant.UserResponseCodes;
import com.video.user.exception.UserNotFoundException;
import com.video.user.exception.UserPasswordInaccuracyException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author: master
 * @date: 2018/7/30
 */
@RestControllerAdvice
public class UserExceptionHandler extends BaseExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public BaseResponseVo userNotFound(UserNotFoundException e) {
        return new BaseResponseVo(UserResponseCodes.USER_NOT_FOUND, UserMessageCodes.USER_NOT_FOUND);
    }

    @ExceptionHandler(UserPasswordInaccuracyException.class)
    public BaseResponseVo userPasswordInaccuracy(UserPasswordInaccuracyException e) {
        return new BaseResponseVo(UserResponseCodes.USER_PASSWORD_INACCURACY, UserMessageCodes.USER_PASSWORD_INACCURACY);
    }
}
