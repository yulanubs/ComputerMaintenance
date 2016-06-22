package com.mr.cm.common.base.domain;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;

/**
 * Created by JekShow on 2016/4/10.
 */
public class Funcations implements Serializable {
    public static final long serialVersionUID = 3168310794325063626L;
    /**
     * 图片路径
     */
    @JSONField(name = "images")
    public String images;
    /**
     * 功能ID
     */
    @JSONField(name = "id")
    public String id;
    /**
     * 功能名称
     */
    @JSONField(name = "name")
    public String name;
    /**
     * action
     */
    @JSONField(name = "action")
    public String action;

    public Funcations(String images, String id, String name, String action) {
        this.images = images;
        this.id = id;
        this.name = name;
        this.action = action;
    }

    public Funcations() {
        super();
    }

    @Override
    public String toString() {
        return "Funcations{" +
                "action='" + action + '\'' +
                ", images='" + images + '\'' +
                ", id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
