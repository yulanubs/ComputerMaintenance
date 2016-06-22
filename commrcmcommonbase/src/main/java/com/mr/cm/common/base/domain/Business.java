package com.mr.cm.common.base.domain;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;


public class Business implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 商家ID
     */
    @JSONField(name = "Merchant_Id")
    public String Merchant_Id;
    /**
     * 商家编号
     */
    @JSONField(name = "Merchant_Num")
    public String Merchant_Num;
    /**
     * 商家名称
     */
    @JSONField(name = "Merchant_Name")
    public String Merchant_Name;
    /**
     * 商家描述
     */
    @JSONField(name = "Merchant_Describe")
    public String Merchant_Describe;
    /**
     * 商家Logo
     */
    @JSONField(name = "Merchant_Logo")
    public String Merchant_Logo;
    /**
     * 商家关注
     */
    @JSONField(name = "Merchant_Follow_Nnum")
    public String Merchant_Follow_Nnum;
    /**
     * 商家商家距离
     */
    @JSONField(name = "Merchant_Distance")
    public String Merchant_Distance;

    public Business(String merchant_Id, String merchant_Num,
                    String merchant_Name, String merchant_Describe,
                    String merchant_Logo, String merchant_Follow_Nnum,
                    String merchant_Distance) {
        super();
        Merchant_Id = merchant_Id;
        Merchant_Num = merchant_Num;
        Merchant_Name = merchant_Name;
        Merchant_Describe = merchant_Describe;
        Merchant_Logo = merchant_Logo;
        Merchant_Follow_Nnum = merchant_Follow_Nnum;
        Merchant_Distance = merchant_Distance;
    }

    public Business() {
        super();
        // TODO Auto-generated constructor stub
    }


}
