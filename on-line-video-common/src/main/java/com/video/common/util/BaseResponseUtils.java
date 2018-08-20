package com.video.common.util;

import com.video.common.bean.common.BasePage;
import com.video.common.bean.vo.BaseDataResponseVo;
import com.video.common.bean.vo.BasePageResponseVo;
import com.video.common.bean.vo.BaseResponseVo;
import com.video.common.constant.BaseMessageCodes;
import com.video.common.constant.BaseResponseCodes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

/**
 * 统一响应工具
 *
 * @author: master
 * @date: 2018/7/29
 */
public class BaseResponseUtils {

    /**
     * 获取所在系统语言环境
     */
    private static final Locale LOCALE_DEFAULT = Locale.SIMPLIFIED_CHINESE;

    @Autowired
    private MessageSource messageSource;

    /**
     * 构建无数据成功响应消息
     *
     * @return 返回成功消息实体
     */
    public BaseResponseVo buildSuccessResponse() {
        return new BaseResponseVo(BaseResponseCodes.SYSTEM_RESPONSE_SUCCESS, this.getMessage(BaseMessageCodes.SYSTEM_RESPONSE_SUCCESS));
    }

    /**
     * 构建带有数据体的成功响应消息
     *
     * @param data 需要携带的数据体
     * @param <T>  数据体的指定类型
     * @return 返回带有数据体的消息实体
     */
    public <T> BaseDataResponseVo<T> buildSuccessResponse(T data) {
        return new BaseDataResponseVo<>(BaseResponseCodes.SYSTEM_RESPONSE_SUCCESS, this.getMessage(BaseMessageCodes.SYSTEM_RESPONSE_SUCCESS), data);
    }

    /**
     * 构建带有数据体的分页成功响应消息
     *
     * @param data        需要携带的数据体，带有分页信息和数据
     * @param currentPage 当前选择的页
     * @param <T>         数据体的指定类型
     * @return 返回带有数据体的分页消息实体
     */
    public <T> BaseDataResponseVo buildSuccessPageResponse(BasePage<T> data, int currentPage) {
        BasePageResponseVo pageResponseVo = new BasePageResponseVo();
        if (data != null) {
            pageResponseVo.setTotal(data.getTotal());
            pageResponseVo.setPages(data.getPages());
            pageResponseVo.setDatas(data.getDatas());
            pageResponseVo.setCurrentPage(currentPage);
        }
        return this.buildSuccessResponse(pageResponseVo);
    }

    /**
     * 构建错误响应消息
     *
     * @param responseCode 响应代码
     * @param messageCode  错误消息代码
     * @return 返回响应消息实体
     */
    public BaseResponseVo buildErrorResponse(int responseCode, String messageCode) {
        return new BaseResponseVo(responseCode, this.getMessage(messageCode));
    }

    /**
     * 根据消息代码获取指定语言消息
     *
     * @param code 消息代码
     * @return 返回消息
     */
    public String getMessage(String code) {
        return messageSource.getMessage(code, null, LOCALE_DEFAULT);
    }
}
