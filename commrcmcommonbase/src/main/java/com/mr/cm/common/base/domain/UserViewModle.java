package com.mr.cm.common.base.domain;

import com.alibaba.fastjson.annotation.JSONField;

public class UserViewModle extends ResultViewModle {


    private static final long serialVersionUID = 1L;
    /**
     * 用户信息
     */
    @JSONField(name = "UserInfo")
    public UserInfo UserInfo;

    public UserViewModle() {
        super();
        // TODO Auto-generated constructor stub
    }

    public UserViewModle(String returnCode, String returnMessage) {
        super(returnCode, returnMessage);

    }

    public UserViewModle(String returnCode, String returnMessage,
                         UserInfo userInfo) {
        super(returnCode, returnMessage);
        UserInfo = userInfo;
    }

}
