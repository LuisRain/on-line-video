package com.video.common.bean.common;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * 公共使用(需要抽出)
 *
 * @author: master
 * @date: 2018/7/29
 */

@Setter
@Getter
@ToString
public class BaseDomain  implements Serializable {

    private Date createTime;

    private Date updateTime = new Date();

    public void initiate() {
        Date date = new Date();
        this.createTime = date;
        updateTime = date;
    }

    public void update() {
        Date date = new Date();
        if (this.createTime == null) {
            this.createTime = date;
        }
        this.updateTime = date;
    }
}
