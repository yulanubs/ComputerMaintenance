package com.mr.cm.common.base.domain;

import java.io.Serializable;

/**
 * @ClassName:SearchFeatured <BR>
 * @Describe：发现实体类<BR>
 * @Author: 朱勋康
 * @Extends：<BR>
 * @Version:1.0
 * @date:2016-4-16 下午8:40:54
 */
public class SearchFeatured implements Serializable {

    private static final long serialVersionUID = 1L;
    private String id;
    private String action;
    private String title;
    private String describe;
    private String imgurl;

    public SearchFeatured(String id, String action, String title,
                          String describe, String imgurl) {
        super();
        this.id = id;
        this.action = action;
        this.title = title;
        this.describe = describe;
        this.imgurl = imgurl;
    }

    public SearchFeatured() {
        super();
        // TODO Auto-generated constructor stub
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
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
