package com.video.generate.service;

/**
 * @author: master
 * @date: 2018/7/23
 */
public interface GenerateIdService {

    /**
     * 获得下一个ID (该方法是线程安全的)
     *
     * @return SnowflakeId
     */
    long nextId();

    /**
     * 指定工作号和数据中心号获取指定区域Id，线程安全
     * @param datacenterId
     * @param workerId
     * @return
     */
    long nextId(long datacenterId, long workerId);


}
