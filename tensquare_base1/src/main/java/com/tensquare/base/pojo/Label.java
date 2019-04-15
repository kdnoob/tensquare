/**
 * Copyright ©2018 上海屹通. All rights reserved.
 *
 * @Title: Label.java
 * @Prject: yitong-mp-pers-recharge
 * @Description:
 * @Package: com.tensquare.base.pojo
 * @author: 张凯达（zhangkaida@yitong.com.cn）
 * @date: 2019-03-26 17:32
 * @version: V1.0
 */
package com.tensquare.base.pojo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @ClassName: Label
 * @Description:
 * @author: 张凯达（zhangkaida@yitong.com.cn）
 * @date: 2019-03-26 17:32
 */
@Entity
@Table(name = "tb_label")
public class Label implements Serializable {

    @Id
    private String id;
    private String labelname;//标签名称
    private String state;//状态
    private Long count;//使用数量
    private Long fans;//关注数
    private String recommend;//是否推荐

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLabelname() {
        return labelname;
    }

    public void setLabelname(String labelname) {
        this.labelname = labelname;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public Long getFans() {
        return fans;
    }

    public void setFans(Long fans) {
        this.fans = fans;
    }

    public String getRecommend() {
        return recommend;
    }

    public void setRecommend(String recommend) {
        this.recommend = recommend;
    }
}
