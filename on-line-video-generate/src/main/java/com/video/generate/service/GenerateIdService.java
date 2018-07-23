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
     * 阻塞到下一个毫秒，直到获得新的时间戳
     *
     * @param lastTimestamp 上次生成ID的时间截
     * @return 当前时间戳
     */
    long tilNextMillis(long lastTimestamp);

    /**
     * 返回以毫秒为单位的当前时间
     *
     * @return 当前时间(毫秒)
     */
    long timeGen();
}
