package com.computerdmaintenance.ui.wgt.event;

import android.view.View;

import com.mr.cm.common.base.domain.ResultViewModle;

import java.io.Serializable;

public class MrEvent implements Serializable {

    public static final int EVENT_CART_ADD = 0x0101;
    public static final int EVENT_CART_DEL = 0x0102;
    public static final int EVENT_CART_SAVE = 0x0103;
    public static final int EVENT_CART_REFRESH = 0x0104;
    public static final int EVENT_USER_LOGIN = 0x0201;
    public static final int EVENT_USER_LOGOUT = 0x0202;
    public static final int EVENT_USER_SETULEPWD = 0x0203;
    public static final int EVENT_ORDER_CONFIRM = 0x0401;
    public static final int EVENT_ORDER_PAID = 0x0402;
    public static final int EVENT_GAME_UPDATE = 0x0501;
    public static final int EVENT_ADDRESS_ADD = 0x0601;
    public static final int EVENT_ADDRESS_SELECT = 0x0602;
    public static final int EVENT_ADDRESS_DEL = 0x0603;
    public static final int EVENT_ADDRESS_MODIFY = 0x0604;
    public static final int EVENT_ADDRESS_SET_DEFAULT = 0x0605;
    public static final int EVENT_ADDRESS_AREAINFO = 0x0606;
    public static final int EVENT_ADDRESS_GET = 0x0607;
    public static final int EVENT_READ_BAR_CODE = 0x0701;
    public static final int EVENT_POSTCARD_BIND = 0x0801;
    public static final int EVENT_ULECARD_BIND = 0x0802;
    public static final int EVENT_HEARDER_CHANGE = 0x0901;
    public static final int EVENT_HEAD_STYLE = 0x0902;
    public static final int EVENT_UPDATE_FORCEFINISH = 0x0a01;
    public static final int EVENT_WGT_EXPIRE = 0x0C01;
    public static final int EVENT_FARIVOTE_DEL = 0x0d01;
    public static final int EVENT_STORE_FARIVOTE_DEL = 0x0d02;
    public static final int EVENT_WGT_DISPOSE = 0x0e01;
    public static final int EVENT_WGT_DISPOSE_EXPIRE = 0x0e02;
    public static final int EVENT_WGT_DISPOSE_QUIT = 0x0e03;
    public static final int EVENT_SELECTDISTANCE = 0x0f01;
    public static final int EVENT_MOREUNPAID = 0x0f02;
    public static final int EVENT_MORECOUPON = 0x0f03;
    public static final int EVENT_MOREPOINT = 0x0f04;
    public static final int EVENT_INNERMAIL = 0x0f05;
    public static final int EVENT_REFRE_BY_ACTION = 0x0f06;
    public static final int EVENT_ADD_TO_CAR_NUM = 0x0f07;
    public static final int EVENT_GO_BY_ACTION = 0x0f08;
    public static final int EVENT_UNION_PAY_RESULT = 0x1001;
    public static final int EVENT_UNION_PAY_RESULT_NEW = 0x1002;
    public static final int EVENT_ACTIVITY_RESUME = 0x1101;
    public static final int EVENT_ACTIVITY_PAUSE = 0x1102;
    public static final int EVENT_ACTIVITY_STOP = 0x1103;
    public static final int EVENT_INDEX_CITY = 0x1104;
    public static final int EVENT_FILTER_CITY = 0x1105;
    public static final int EVENT_FILTER_BRAND = 0x1106;
    public static final int EVENT_FILTER_PROMOTION = 0x1107;
    public static final int EVENT_FILTER_INFO = 0x1108;
    public static final int EVENT_STORE_FILTER_INFO = 0x1109;
    public static final int EVENT_ACTION = 0x1200;
    public static final int EVENT_CONTACT_PICK = 0x2000;
    public static final int EVENT_EXPIRE_WGT = 0x12365;
    public static final int EVENT_EXPIRE_WGT_LIST = 0x12366;
    public static final int EVENT_COUPONINFO_ORDERCONFIRM = 0x4000;
    // public static final int EVENT_EXPIRE_WGT_REFRESH_LIST = 0x12367;
    public static final int EVENT_MYCOLLECTION_HEARGOOD = 0x5001;
    public static final int ENENT_MYCOLLECTION_FAVORITESTORE = 0x5002;
    public static final int ENENT_ETCOMMENT_SUCCESS = 0x6001;
    public static final int EVENT_NETWORK_ERR = 0x1400;
    public static final int EVENT_NETWORK_SUCCESS = 0x1401;
    public static final int EVENT_GETCOUPON_SUCCESS = 0X1501;
    // add by dxy
    public static final int EVENT_REFRESH_POST_CARD_LIST = 0X1502;
    public static final int EVENT_REFRESH_ULE_CARD_LIST = 0X1503;
    public static final int EVENT_REFRESH_ULE_AVATAR_URL = 0X1504;
    public static final int EVENT_MOBILEREGISTER_PHONENUM = 0X1505;
    public static final int EVENT_WEB_LOGIN_RESULT_TYPE = 0X1507;
    //add by wangyan
    public static final int EVENT_REFRESH_QUICK_PAY_CARD_LIST = 0x1506;
    public static final int EVENT_MYMANAGE_QRCODE = 0x1601;
    public static final int EVENT_OPEN_FILE = 0X1508;
    private static final long serialVersionUID = 92717348637528675L;
    public int Event;
    public View Src;
    public ResultViewModle Data;

	/*
     * public UleEvent(String strdata) throws JSONException { super(strdata); //
	 * TODO Auto-generated constructor stub Event = EVENT_CART_ADD; Src = null;
	 * Data = null; }
	 */

    public MrEvent(int Event_id) {
        // TODO Auto-generated constructor stub
        Event = Event_id;
        Src = null;
        Data = null;
    }
}
