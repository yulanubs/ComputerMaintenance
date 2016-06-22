package com.mr.cm.common.base.domain;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.ArrayList;

/**
 * Created by JekShow on 2016/4/11.
 */
public class AdvertisementInfo extends ResultViewModle {
    /**
     * 功能列表
     */
    @JSONField(name = "list")
    public ArrayList<Funcations> list;

    public AdvertisementInfo() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @param returnCode
     * @param returnMessage
     * @param list
     */
    public AdvertisementInfo(String returnCode, String returnMessage, ArrayList<Funcations> list) {
        super(returnCode, returnMessage);
        this.list = list;
    }

    /**
     * @param returnCode
     * @param returnMessage
     */
    public AdvertisementInfo(String returnCode, String returnMessage) {
        super(returnCode, returnMessage);
    }

    @Override
    public String toString() {
        return "AdvertisementInfo{" +
                "list=" + list +
                '}';
    }
}
