package com.mr.cm.common.base.domain;

import java.io.Serializable;

public class InnovativeActivitiesInfo implements Serializable {

    private static final long serialVersionUID = 1L;
    private String title;
    private String describe;
    private String imgurl;

    public InnovativeActivitiesInfo(String title, String describe, String imgurl) {
        super();
        this.title = title;
        this.describe = describe;
        this.imgurl = imgurl;
    }

    public InnovativeActivitiesInfo() {
        super();
        // TODO Auto-generated constructor stub
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

}
