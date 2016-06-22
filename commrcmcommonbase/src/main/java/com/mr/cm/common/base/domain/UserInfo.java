package com.mr.cm.common.base.domain;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;

public class UserInfo implements Serializable {

    public static final long serialVersionUID = 3168310794325063626L;
    /**
     * 用户ID
     */
    @JSONField(name = "userId")
    public String userId = "";
    /**
     * 用户手机
     */
    @JSONField(name = "userName")
    public String userName;
    /**
     * 用户昵称，支持中文
     */
    @JSONField(name = "userByname")
    public String userByname;
    /**
     * 性别
     */
    @JSONField(name = "userAge")
    public String userAge;
    /**
     * 等级
     */
    @JSONField(name = "userRank")
    public String userRank;
    /**
     * 金币
     */
    @JSONField(name = "userGold")
    public String userGold;
    /**
     * 状态 -1为冻结 ，0为离线 ，1为在线。
     */
    @JSONField(name = "userStatus")
    public String userStatus;
    /***
     * 用户登录验证信息
     */
    @JSONField(name = "userToken")
    public String userToken;
    /***
     * 用户邮箱
     */
    @JSONField(name = "userEmail")
    public String userEmail;
    /***
     * 用户头像
     */
    @JSONField(name = "userIconUrl")
    public String userIconUrl;
    /**
     * 绑定手机
     */
    @JSONField(name = "bindMobile")
    public String bindMobile = "0";

    public UserInfo() {
        super();
        // TODO Auto-generated constructor stub
    }

    public UserInfo(String userId, String userName, String userByname,
                    String userAge, String userRank, String userGold,
                    String userStatus, String userToken, String userEmail,
                    String userIconUrl, String bindMobile) {
        super();
        this.userId = userId;
        this.userName = userName;
        this.userByname = userByname;
        this.userAge = userAge;
        this.userRank = userRank;
        this.userGold = userGold;
        this.userStatus = userStatus;
        this.userToken = userToken;
        this.userEmail = userEmail;
        this.userIconUrl = userIconUrl;
        this.bindMobile = bindMobile;
    }


}