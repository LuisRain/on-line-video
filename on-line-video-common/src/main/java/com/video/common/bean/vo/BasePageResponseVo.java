package com.video.common.bean.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * 分页响应实体
 *
 * @author: master
 * @date: 2018/7/29
 */
@Setter
@Getter
@ToString
@NoArgsConstructor
public class BasePageResponseVo<T>  implements Serializable {

    /**
     * 当前页
     */
    private int currentPage;
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
