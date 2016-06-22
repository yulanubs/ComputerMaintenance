package com.mr.cm.common.base.domain;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;

/**
 * @ClassName:ClassCurriculum <BR>
 * @Describe：课程信息<BR>
 * @Author: zhuxunkang
 * @Extends：<BR>
 * @Version:1.0
 * @date:2016-4-23 下午10:01:09
 */
public class ClassCurriculum implements Serializable {
    private static final long serialVersionUID = -5261072048083879761L;
    /**
     * 课程编号
     */
    @JSONField(name = "id")
    public String id;
    /**
     * 课程名称
     */
    @JSONField(name = "title")
    public String title;
    /**
     * 课程地址
     */
    @JSONField(name = "url")
    public String url;
    /**
     * 课程序号
     */
    @JSONField(name = "num")
    public String num;
    /**
     * 是否被选中
     */
    public boolean isSelected = false;

    public ClassCurriculum() {
        super();
        // TODO Auto-generated constructor stub
    }

    public ClassCurriculum(String id, String title, String url, String num) {
        super();
        this.id = id;
        this.title = title;
        this.url = url;
        this.num = num;
    }

    public ClassCurriculum(String id, String title, String url, String num,
                           boolean isSelected) {
        super();
        this.id = id;
        this.title = title;
        this.url = url;
        this.num = num;
        this.isSelected = isSelected;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

}
