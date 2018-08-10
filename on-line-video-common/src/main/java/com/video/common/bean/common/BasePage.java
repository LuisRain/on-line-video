package com.video.common.bean.common;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * @author: master
 * @date: 2018/7/29
 */
@Setter
@Getter
@ToString
public class BasePage<T>  implements Serializable {
    /**
     * 总页数
     */
    private int pages;

    /**
     * 总数据量
     */
    private long total;

    /**
     * 返回数据列表
     */
    private List<T> datas;
}
