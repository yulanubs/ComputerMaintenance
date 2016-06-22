package com.mr.cm.common.base.domain;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;

/**
 * 广告实体
 * Created by JekShow on 2016/4/11.
 */
public class Advertisement implements Serializable {
    /**
     * 广告
     */
    @JSONField(name = "id")
    private String id;
    /**
     * 广告词
     */
    @JSONField(name = "name")
    private String name;
    /**
     * 广告类型
     */
    @JSONField(name = "type")
    private String type;
    /**
     * 广告action
     */
    @JSONField(name = "action")
    private String action;
    /**
     * 广告图片地址
     */
    @JSONField(name = "images")
    private String images;

    public Advertisement(String action) {
        super();
    }

    public Advertisement(String action, String id, String images, String name, String type) {
        this.action = action;
        this.id = id;
        this.images = images;
        this.name = name;
        this.type = type;
    }

    @Override
    public String toString() {
        return "Advertisement{" +
                "action='" + action + '\'' +
                ", id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", images='" + images + '\'' +
                '}';
    }
}
