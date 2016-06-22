package com.mr.cm.common.base.domain;

import java.io.Serializable;
import java.util.ArrayList;

public class IndexItemInfo implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    /*"key":"key",

      "checkListingIds":"checkListingIds",

      "link":"link",

      "checkPrice":"checkPrice",

      "id":"id",

      "imgUrl":"imgUrl",

      "title":"title",

      "checkStoreIds":"checkStoreIds",

      "sectionId":"sectionId",

      "priority":"priority",

      "attribute1":"attribute1",

      "attribute2":"attribute2",

      "attribute3":"attribute3"
    	  
	  "attribute4":"attribute4",
	  
	  "attribute5":"attribute5",*/


    public String key;
    public String checkListingIds;
    public String link;
    public String checkPrice;
    public String id;
    public String imgUrl;
    public String title;
    public String checkStoreIds;
    public String sectionId;
    public String priority;
    public String attribute1;
    public String attribute2;
    public String attribute3;
    public String attribute4;
    public String attribute5;
    public String attribute6;
    public String image2;
    public String image1;
    public String type1;
    public String type2;
    public String action1;
    public String action2;
    public String limit;
    public String shareContent;
    public String shareUrl;
    public String shareImage;
    public String shareTitle;

    public String minversion = "";
    public String maxversion = "";
    public String imgurl2 = "";

    /**
     * 是否是新功能？0否:1是
     */
    public String newfunction = "";
    /**
     * 是否需要登录？0否:1是
     */
    public String needlogin = "";
    public String need_login = "";
    /**
     * 需要调用的api
     */
    public String api = "";
    /**
     * 是否需要显示右上数字？0否:1是
     */
    public String showbadge = "";
    /**
     * 是否通用？0,1android:0,1ios
     */
    public String devicetype = "";
    /**
     * 跳转响应
     */
    public String android_action = "";
    /**
     * 记录本地是否点击过
     */
    public String functionId = "";

    //for PersonalCenter 20150104
    public String showNum = "";

    //for loading 20150108
    public String resolution = "";
    public ArrayList<String> images = new ArrayList<String>();
    public String device_type = "";
    public String max_version = "";
    public String min_version = "";
    public String always_show = "";
    //just for loading image url 20150128
    public String loadingImageUrl = "";

    //for index 20150116
    /**
     * 组类型id
     */
    public String groupid = "";
    /**
     * 组排序
     */
    public String groupsort = "";
    /**
     * 日志title
     */
    public String logtitle = "";

    public String log_title = "";
    /**
     * 此字段用于邮掌柜App，首页九宫格是否有小红点，
     * 1：显示，0：不显示，
     * 若为1，还要判断其他条件决定是否显示小红点
     */
    public String default_function = "";

}
