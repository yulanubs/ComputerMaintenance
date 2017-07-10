package com.mr.cm.common.base.domain;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;

public class ResultViewModle implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 响应码
     */
    @JSONField(name = "returnCode")
    public String returnCode="";
    /**
     * 响应状态信息
     */
    @JSONField(name = "returnMessage")
    public String returnMessage="";


    public ResultViewModle(String returnCode, String returnMessage) {
        super();
        this.returnCode = returnCode;
        this.returnMessage = returnMessage;
    }


    public ResultViewModle() {
        super();
        // TODO Auto-generated constructor stub
    }


    public static String filterhtml(String input) {
        String str = input.replaceAll("\\&[a-zA-Z]{1,10};", "").replaceAll(
                "<[^>]*>", "");
        str = str.replaceAll("[(/>)<]", "");

        return str;
    }

    public String getReturnCode() {
        return returnCode;
    }


    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }


    public String getReturnMessage() {
        return returnMessage;
    }


    public void setReturnMessage(String returnMessage) {
        this.returnMessage = returnMessage;
    }

}
