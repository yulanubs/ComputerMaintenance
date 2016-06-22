package com.mr.cm.common.base.domain;

import java.io.Serializable;

public class SearchLogo implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;
    private String action;
    private String imgurl;


    public SearchLogo(String id, String action, String imgurl) {
        super();
        this.id = id;
        this.action = action;
        this.imgurl = imgurl;
    }

    public SearchLogo() {
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

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

}
