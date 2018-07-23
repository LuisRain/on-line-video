package com.video.generate.service.impl;

import com.video.generate.bean.document.GenerateIdDo;
import com.video.generate.service.GenerateIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @author: master
 * @date: 2018/7/23
 */
@Service
public class GenerateIdServiceImpl implements GenerateIdService {

    @Autowired
    private GenerateIdDo generateIdDo;

    @Value("${com.generate.worker-id:1}")
    private long workerId = 0L;
    @Value("${com.generate.datacenter-id:1}")
    private long datacenterId = 0L;

    /**
     * 获得下一个ID (该方法是线程安全的)
     *
     * @return SnowflakeId
     */
    @Override
    public synchronized long nextId() {
        if ((workerId > generateIdDo.getMaxWorkerId()) || workerId < 0) {
            throw new IllegalArgumentException(String.format("worker Id can't be greater than %d or less than 0", generateIdDo.getMaxWorkerId()));
        }
        if ((datacenterId > generateIdDo.getMaxDatacenterId()) || datacenterId < 0) {
            throw new IllegalArgumentException(String.format("datacenter Id can't be greater than %d or less than 0", generateIdDo.getMaxDatacenterId()));
        }
        long timestamp = timeGen();

        //如果当前时间小于上一次ID生成的时间戳，说明系统时钟回退过这个时候应当抛出异常
        if (timestamp < (generateIdDo.getLastTimestamp())) {
            throw new RuntimeException(
                    String.format("Clock moved backwards.  Refusing to generate id for %d milliseconds", (generateIdDo.getLastTimestamp()) - timestamp));
        }

        //如果是同一时间生成的，则进行毫秒内序列
        if (generateIdDo.getLastTimestamp() == timestamp) {
            generateIdDo.setSequence((generateIdDo.getSequence() + 1) & generateIdDo.getSequenceMask());
            //毫秒内序列溢出
            if ((generateIdDo.getSequence()) == 0) {
                //阻塞到下一个毫秒,获得新的时间戳
                timestamp = tilNextMillis(generateIdDo.getLastTimestamp());
            }
        }
        //时间戳改变，毫秒内序列重置
        else {
            generateIdDo.setSequence(0L);
        }

        //上次生成ID的时间截
        generateIdDo.setLastTimestamp(timestamp);

        //移位并通过或运算拼到一起组成64位的ID
        return ((timestamp - generateIdDo.getTwepoch()) << generateIdDo.getTimestampLeftShift())
                | (generateIdDo.getDatacenterId() << generateIdDo.getDatacenterIdShift())
                | (generateIdDo.getWorkerId() << generateIdDo.getWorkerIdShift())
                | generateIdDo.getSequence();
    }

    /**
     * 阻塞到下一个毫秒，直到获得新的时间戳
     *
     * @param lastTimestamp 上次生成ID的时间截
     * @return 当前时间戳
     */
    @Override
    public long tilNextMillis(long lastTimestamp) {
        long timestamp = timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = timeGen();
        }
        return timestamp;
    }

    /**
     * 返回以毫秒为单位的当前时间
     *
     * @return 当前时间(毫秒)
     */
    @Override
    public long timeGen() {
        return System.currentTimeMillis();
    }
}
