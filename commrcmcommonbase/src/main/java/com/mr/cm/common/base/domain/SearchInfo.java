package com.mr.cm.common.base.domain;

import java.io.Serializable;

public class SearchInfo implements Serializable {

    /**
     * serialVersionUID:TODO（用一句话描述这个变量表示什么）
     *
     * @since Ver 1.1
     */

    private static final long serialVersionUID = 1L;
    private String id;
    private String action;
    private String title;
    private String imgurl;

    public SearchInfo() {
        super();
        // TODO Auto-generated constructor stub
    }

    public SearchInfo(String id, String action, String title, String imgurl) {
        super();
        this.id = id;
        this.action = action;
        this.title = title;
        this.imgurl = imgurl;
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

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

}
