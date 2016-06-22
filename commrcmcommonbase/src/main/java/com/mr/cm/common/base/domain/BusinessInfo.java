package com.mr.cm.common.base.domain;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.ArrayList;

/**
 * @ClassName:BusinessInfo <BR>
 * @Describe：商家信息列表<BR>
 * @Author: 朱勋康
 * @Extends：<BR>
 * @Version:1.0
 * @date:2016-4-15 下午10:26:36
 */
public class BusinessInfo extends ResultViewModle {

    private static final long serialVersionUID = 178987987L;
    /**
     * 商家信息
     */

    @JSONField(name = "list")
    public ArrayList<Business> list;

}
