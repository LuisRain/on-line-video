package com.video.external.util;

import java.util.Date;
import java.util.UUID;

/**
 * 专属外部服务生成Id的工具，没有使用发号中心全局生成的发号服务是因为不必要
 *
 * @author: master
 * @date: 2018/8/11
 */
public class GenerateIdUtils {

    public synchronized static long genterateId() {

        return System.currentTimeMillis();
    }
}
