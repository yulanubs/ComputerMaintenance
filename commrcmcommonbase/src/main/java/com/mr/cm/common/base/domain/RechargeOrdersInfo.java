package com.mr.cm.common.base.domain;

import java.io.Serializable;


public class RechargeOrdersInfo implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public String escOrderid;
    public String orderId;
    public String amount;
    public String status;
    public String orderStatus;
    public String orderCreateTime;
    public String orderType;
    public String supportBuyType;
    public String prdName;
    public String prdNum;
    public String prdPic;
    public String mobile;
    public String isCanCancel;

    /**
     * 手机号归属地
     */
    public String modelAddre;
    /**
     * 优惠券编号
     */
    public String couponId;
    /**
     * 优惠券状态
     */
    public String couponAmount;
    /**
     * 优惠券生效时间
     */
    public String couponUseTime;
    /**
     * 订单修改时间
     */
    public String sellerModifyTime;
    /**
     * 退款回复时间
     */
    public String sellerRespondTime;
    /**
     * 促销卷
     */
    public String promotionTicket;
    /**
     * 充值面值
     */
    public String prdAmout;
    /**
     * 卡号
     */
    public String cardNum;
    /**
     * 支付方式
     */
    public String payType;
    /**
     * 支付时间
     */
    public String payTime;
    public String paymentTerminalTime;

}