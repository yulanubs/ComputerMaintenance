package com.mr.cm.common.base.domain;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.ArrayList;

/**
 * Created by JekShow on 2016/4/10.
 */
public class FuncationInfo extends ResultViewModle {
    /**
     * 功能列表
     */
    @JSONField(name = "list")
    public ArrayList<Funcations> list;

    public FuncationInfo() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @param returnCode
     * @param returnMessage
     */
    public FuncationInfo(String returnCode, String returnMessage) {
        super(returnCode, returnMessage);
    }

    /**
     * @param returnCode
     * @param returnMessage
     * @param list
     */
    public FuncationInfo(String returnCode, String returnMessage, ArrayList<Funcations> list) {
        super(returnCode, returnMessage);
        this.list = list;
    }

    @Override
    public String toString() {
        return "FuncationInfo{" +
                "list=" + list +
                '}';
    }
}
