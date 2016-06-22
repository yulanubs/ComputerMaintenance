package com.computerdmaintenance.qq;

import android.content.Context;

import com.tencent.connect.UserInfo;
import com.tencent.connect.auth.QQAuth;
import com.tencent.connect.auth.QQToken;

public class QQUserInfo extends UserInfo {

    public QQUserInfo(Context arg0, QQAuth arg1, QQToken arg2) {
        super(arg0, arg1, arg2);
        // TODO Auto-generated constructor stub
    }

    public QQUserInfo(Context arg0, QQToken arg1) {
        super(arg0, arg1);
        // TODO Auto-generated constructor stub
    }

}
