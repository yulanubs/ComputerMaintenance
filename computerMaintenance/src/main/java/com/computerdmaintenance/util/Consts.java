package com.computerdmaintenance.util;

import android.content.Context;

import java.util.List;
import java.util.Map;

public class Consts {
    // public static final String SELECT_RETURN_URL = "http://my.beta.ule.com/myorder/mobileSelectReturn.do?";
    public static final String SELECT_RETURN_URL = "http://my.ule.com/myReturnOrderWapWeb/mobileSelectReturn.do?";

    public static final String ULE_PACKAGE = "com.tom.ule.lifepay";
    public static final String JPush_APP_NAME = "ysh";

    public static final int TYPE_ORDER = 0x00100;

    public static final int notLogin = 0x004;

    public static final String UMENG_EVENT_CREATE_ORDER = "createFlightOrder";
    public static final String UMENG_EVENT_DURATION_ORDER_PAY = "createToPayInterval";

    public static final int FILTER_FLIGHT_TYPE_SINGLE = 1;
    public static final int FILTER_FLIGHT_TYPE_GO = 2;
    public static final int FILTERFLIGHT_TYPE_BACK = 3;

    public static final int FILTER_TYPE_TIME = 1000;
    public static final int FILTER_TYPE_AIRPORT = 1001;
    public static final int FILTER_TYPE_SEAT = 1002;
    public static final int FILTER_TYPE_COMPANY = 1003;

    public static final String FLIGHT_TYPE_SINGLE = "1";
    public static final String FLIGHT_TYPE_GOBACK = "2";

    public static final String AIRLINE_TYPE_DEPART = "1";
    public static final String AIRLINE_TYPE_ARRIVE = "2";

    public static final String NEED_TRAVEL_INVOICE = "1";
    public static final String NO_NEED_TRAVEL_INVOICE = "0";

    public static final String MAIL_TYPE_NO_NEED_TRAVEL_INVOICE = "0";
    public static final String MAIL_TYPE_NEED_TRAVEL_INVOICE = "1";
    public static final String MAIL_TYPE_POST_TRAVEL_INVOICE = "2";
    public static final String MAIL_TYPE_CODE_TRAVEL_INVOICE = "5";

    public static final String INTENT_KEY_FLIGHT_TYPE = "flight_type";
    public static final String INTENT_KEY_FILTER_FLIGHT_TYPE = "filter_flight_type";
    public static final String INTENT_KEY_FILTER_TYPE = "filter_type";
    public static final String INTENT_KEY_FILTER_DATA_TIME = "time_filter";
    public static final String INTENT_KEY_FILTER_DATA_AIRPORT = "airport_filter";
    public static final String INTENT_KEY_FILTER_DATA_SEAT = "seat_filter";
    public static final String INTENT_KEY_FILTER_DATA_COMPANY = "company_filter";
    public static final String INTENT_KEY_FILTER_SEAT_TYPE = "filter_seat_type";
    public static final String INTENT_KEY_FILTER_RESULT_DEPAIRPORT = "result_dep_airport";
    public static final String INTENT_KEY_FILTER_RESULT_ARRAIRPORT = "result_arr_airport";
    public static final String INTENT_KEY_FILTER_RESULT_SEAT = "result_seat";
    public static final String INTENT_KEY_FILTER_RESULT_TIME = "result_time";
    public static final String INTENT_KEY_FILTER_RESULT_COMPANY = "result_company";
    public static final String INTENT_KEY_REGISTER_RESULT = "register_result";
    public static final String INTENT_KEY_FIND_PWD_RESULT = "find_pwd_result";
    public static final String INTENT_KEY_REGISTER_COMING_TYPE = "register_coming_type";
    public static final String INTENT_KEY_LOGIN_COMING_TYPE = "login_coming_type";
    public static final String INTENT_KEY_LOGIN_RESULT_TYPE = "login_result_type";
    public static final String INTENT_KEY_CREATE_ORDER = "create_order";
    public static final String INTENT_KEY_ESC_ORDER_ID = "esc_order_id";
    public static final String INTENT_KEY_POST_CARD_LIST = "post_card_list";
    public static final String INTENT_KEY_POST_PAY_RESULT = "post_pay_result";
    public static final String INTENT_KEY_SET_PWD_CARD = "set_pwd_card";
    public static final String INTENT_KEY_REMOVE_POST_CARD = "remove_post_card";
    public static final String INTENT_KEY_REMOVE_POST_CARD_SUCCESS = "remove_post_card_success";
    public static final String INTENT_KEY_BIND_POST_CARD = "bind_post_card";
    public static final String BACKUPADDRESS_KEY = "backupaddress_key";
    public static final String CAR_INFO_lOCATION_PROVINCE_KEY = "car_info_location_province_key";
    public static final String CAR_INFO_lOCATION_CITY_KEY = "car_info_location_city_key";
    public static final String CAR_INFO_lOCATION_AREA_KEY = "car_info_location_area_key";
    public static final String CAR_INFO_lOCATION_PROVINCE_CODE_KEY = "car_info_location_province_code_key";
    public static final String CAR_INFO_lOCATION_CITY_CODE_KEY = "car_info_location_city_code_key";
    public static final String CAR_INFO_lOCATION_AREA_CODE_KEY = "car_info_location_area_code_key";

    public static final String ORDER_CONTACT_NAME_KEY = "order_contact_name";
    public static final String ORDER_CONTACT_NUM_KEY = "order_contact_num";


    public static final String WEBVIEW_PARAM_URL = "load_url";
    public static final String WEBVIEW_PARAM_NEED_LOGIN = "needLogin";
    public static final String WEBVIEW_PARAM_NEED_TITLE = "needTitle";
    public static final String WEBVIEW_PARAM_TITLE_STRING = "title";
    // oli 酒店 信用卡信息字段
    public static final String HOTEL_CARD_BANK_KEY = "hotel_card_bank_key";
    public static final String HOTEL_CARD_NO_KEY = "hotel_card_no_key";
    public static final String HOTEL_CARD_HOLDER_NAME_KEY = "hotel_card_holder_name_key";
    public static final String HOTEL_CARD_HOLDER_PAPERS_TYPE_KEY = "hotel_card_holder_papers_type_key";
    public static final String HOTEL_CARD_HOLDER_PAPERS_NO_KEY = "hotel_card_holder_papers_no_key";
    public static final String HOTEL_CARD_CVV_KEY = "hotel_card_cvv_key";
    public static final String HOTEL_CARD_VALID_YEAR_KEY = "hotel_card_valid_year_key";
    public static final String HOTEL_CARD_VALID_MONTH_KEY = "hotel_card_valid_month_key";
    public static final String EVENT_GIFT_URL = "http://www.ule.com/event/2014/0704/ule_app/m_index.html";
    public static final int REBATE_REQUEST_CODE = 0x021;
    public static final int REQUEST_CODE_PAY_MONTH = 0x022;
    public static Map<String, String> clientType;

    /*
     * public static final String EVENT_GIFT_URL =
     * "http://www.beta.ule.com/event/2014/0704/ule_app/m_index.html";
     */
    public static List<String> hotelBankList;

    public static void initAirLineIcons(Context context) {

    }

    public class QrCode {
        public static final String QRCODE_DOWNLOAD_URL = "http://mw.ule.com/mobile/downloadUle.do";

        public static final String QRCODE_ACT_LIST_ID = "qrlisting";
        public static final String QRCODE_ACT_STORE_ID = "qrstore";
        public static final String QRCODE_ACT_VOTE = "vote";

        public static final String QRCODE_ACT_KEY = "act";
        public static final String QRCODE_LIST_ID = "listId";
        public static final String QRCODE_STORE_ID = "storeId";
        public static final String QRCODE_WISHERID = "wisherId";
        public static final String QRCODE_ITEMID = "itemId";
        public static final String QRCODE_WISHID = "wishId";
    }

    public class XMPP {
        public static final String MSG_LIST_ID = "listingId";
        public static final String MSG_USER_NAME = "username";
    }

    public class Intents {
        public static final String INTENT_CHAT_JID = "chat_jid";
        public static final String INTENT_CHAT_NAME = "chat_name";
        public static final String INTENT_CHAT_ID = "chat_id";
        public static final String INTENT_CHAT_PRD = "chat_prd";
        public static final String INTENT_CHAT_SPIKE = "chat_spike";
        public static final String USER_NAME_KEY = "user_name";
        public static final String USER_PWD_KEY = "user_pwd";
        public static final String MSG_BODY = "msg_body";
        public static final String MSG_TO = "msg_to";
        public static final String SERVICE_START_TYPE = "start_type";
        public static final String FROM_CHAT_NOTIFICATION = "from_chat_notification";
        public static final String INTENT_LIST_ID = "list_id";
        public static final String INTENT_JID = "jid";
        public static final String INTENT_USER_ID = "user_id";
        public static final String INTENT_UPDATE_FORCE_FLAG = "force_flag";
        public static final String INTENT_UPDATE_URL = "update_url";
        public static final String INTENT_UPDATE_DESCRIBE = "update_des";
        public static final String INTENT_UPDATE_NEED = "need_update";
        public static final String INTENT_UPDATE_SHOW_FAILURE = "show_failure";
        public static final String INTENT_IS_FROM_PUSH = "is_push";
        public static final String INTENT_LAUNCH_ACTION = "launch_action";
        public static final String REQUEST_CODE_KEY = "request_code_key";
        public static final String UNION_PAY_TN_NUMBER_KEY = "tn_number";
        public static final String DECODED_CONTENT_KEY = "decoded_content_key";


        public static final String FAMOUS_ATTENTION_KEY = "famous_attention_key";
        public static final String FAMOUS_ATTENTION = "famous_attention";

        public static final int REQUEST_READULECARDBARCODE = 0x001;
        public static final int REQUEST_READBARCODE = 0x002;
        public static final int REQUEST_GIFT_READBARCODE_FIRST = 0x003;
        public static final int REQUEST_GIFT_READBARCODE_SECOND = 0x004;
        public static final int REQUEST_GIFT_READBARCODE_THIRD = 0x005;
        public static final int REQUEST_GIFT_READBARCODE_FOUTH = 0x006;
        public static final int REQUEST_MORE_READBARCODE = 0x007;
        public static final int REQUEST_CONTACT_FORM_CONTACT = 0x008;
        public static final int REQUEST_UNION_PAY = 0x011;
        public static final int REQUEST_LIFE_INFO = 0x16;
        public static final int FILTER_INFO_CODE = 0X010;
        public static final int FILTER_CITY_CODE = 0X012;
        public static final int FILTER_BRAND_CODE = 0X013;
        public static final int FILTER_PROMOTION_CODE = 0X014;
        public static final int OTHER__PICK = 0x0000015;
        public static final String FILTER_CITY_STRING = "filter_city";
        public static final String FILTER_BRAND_STRING = "filter_brand";
        public static final String FILTER_PROMOTION_STRING = "filter_promotion";
        public static final String REQUEST_LIFE_SCAN_RESULT = "life_scan_result";
        public static final String FILTER_BRAND_INFO = "filter_brand_info";
        public static final String FILTER_BRAND_INFOS = "filter_brand_infos";
        public static final String FILTER_PROMOTION_INFO = "filter_promotion_info";
        public static final String FILTER_PROMOTION_INFOS = "filter_promotion_infos";
        public static final String FILTER_CITY = "filter_city";

        public static final String SEARCH_CLMID = "searchClmid";
        public static final String SEARCH_CATEGORY_ID = "search_category_id";
        public static final String SEARCH_CATEGORY_NAME = "search_category_name";
        public static final String SEARCH_KEY = "searchKey";

        public static final int PIC_REQUEST_CODE = 0X015;
        public static final String PIC_ACTIVITYTYPE = "ActivityType";
        public static final String AVATAR_URL = "Avatar_URL";
        public static final int PIC_PERSONCENTERACTIVITY = 0X016;

        public static final int PIC_TAKE_ALBUM = 0X017;
        public static final int PIC_TAKE_CAMERA = 0X018;

        public static final int INTENT_OPEN_FILE_VALUE = 0X020;
        public static final int INTENT_OPEN_FILE = 0X021;

        public static final String INTENT_INSTALL_PATH = "install_path";
        public static final String INTENT_INSTALL_DESCRIBE = "install_describe";

        public static final String INTENT_JUMP_BUNDLE = "jump_bundle";
        public static final String MODE_TYPE = "modeType";
        public static final String ADDRESSCHOOSE_SUBTYPE_KEY = "addresschoose_subtype_key";
        public static final String ADDRESSCHOOSE_DEFAULTAREA_KEY = "addresschoose_defaultarea_key";
        public static final String ADDRESSCHOOSE_RESULTCODE_KEY = "addresschoose_resultcode_key";
        public static final String CITY_SWITCH_NEWCITY_KEY = "city_switch_newcity_key";
        public static final String INDEX_HOT_CITY_KEY = "index_hot_city_key";
        public static final int REQUEST_CODE_CITYCODE = 3000;
        public static final int MAIN_INDEX_PICK = 0x000001;

        public static final String ORDER_DETAIL_INFO = "order_detail_info";
        public static final String INDEX_FUNCTION_BUNDLE = "index_function_bundle";
        public static final String SEC_KILL_CLOCK_NUM = "sec_kill_clock_num";
        public static final String SEC_KILL_INTENT = "sec_kill_clock_intent";
        public static final String SEC_KILL_CLOCK_FLAG = "sec_kill_clock_flag";

        public class Values {

        }
    }

    public class Preference {
        public static final String OPEN_NOTIFICATION = "open_notification";
        public static final String OPEN_SERVICE = "open_service";
        public static final String OPEN_SYC_CHAT = "open_syc_chat";
        public static final String CLOSE_PUSH = "open_push";
        public static final String UUID = "uuid";
        public static final String SESSION_ID = "session_id";
        public static final String USER_TOKEN = "user_token";
        public static final String USER_NAME = "user_name";
        public static final String REMEMBER_USER = "remember_user";
        public static final String FIRST_START = "first_start";
        public static final String LAST_SUBMIT_KF_MSG_TIME = "last_submit_kf_msg_time";
        public static final String LAST_GET_HOT_CITY_MSG_TIME = "last_get_hot_city_msg_time";
        public static final String LISTID_HISTORY = "list_id_history";
        public static final String HISTORY_TIME = "History_Time";

        // public static final String FAMOUS_BRAND = "famous_brand";
        public static final String FAMOUS_TIME = "famous_time";

        public static final String OLD_VERSION = "old_version";
        public static final String CURRENT_VERSION = "current_version";
        public static final String INDEX_GUIDE_SLIDED = "index_guide";

        public static final String KEYWORD_TIME = "keyword_time";
        public static final String KEYWORD_CONTENT = "keyword_content";

        public static final String DATA_CACHE_SP = "data_cache_sp";
        public static final String DATA_CACHE_ORDER_NUMBER = "cache_order_number";
        public static final String DATA_CACHE_COUPON_NUMBER_UNUSE = "cache_coupon_number_unuse";

        public static final String MOBILE_IS_BIND = "mobile_is_bind";

        public static final String XMPP_EMOJI_CONFIG_TIME = "emoji_time";
        public static final String XMPP_EMOJI_REFESH_START_TIME = "emoji_start_time";

        public static final String CATEGORY_CLICK_TIME = "category_click_time";
        public static final String CITYCHOOSE_KEY = "citychoose_key";// 城市选择
        public static final String SHORT_CUT = "short_cut";

        public static final String ISPUSH = "ISPUSH";
        public static final String SEC_KILL_CLOCK = "sec_kill_clock";
        public static final String SEC_KILL_CLOCK_TEXT = "alarmtext";
    }

    public class Handlers {
        public static final int MSG_XMPP_TASK_ERROR = 0x00001;
        public static final int MSG_XMPP_CONNECT_SUCCESS = 0x00002;
        public static final int NOTIFY_NEW_MESSAGE = 0x00003;
        public static final int SERVICE_START_TYPE_LOGIN = 0x10001;
        public static final int SERVICE_START_TYPE_SEND = 0x10002;
        public static final int FETCH_CHAT_RECORD_SUCCES_NET = 0x20001;
        public static final int FETCH_CHAT_RECORD_FAILURE_NET = 0x20002;
        public static final int FETCH_CHAT_RECORD_SUCCES_DATABASE = 0x20003;
        public static final int FETCH_CHAT_RECORD_FAILURE_DATABASE = 0x20004;
        public static final int NEW_MSG_RECEIVED = 0x30001;
        public static final int KEYWORD_ANIMA_MSG = 0x40001;
        public static final int FETCH_CHAT_RECORD_FROM_NET = 0x50001;
    }

    public class Notifications {
        public static final int CHAT_MSG_NOTIFICATION = 0x01;
    }

    public class Downloads {
        public static final int STATE_START = 1;
        public static final int STATE_PROGRESS = 2;
        public static final int STATE_FINISH = 3;
        public static final int STATE_FAILURE = 4;

        public static final int DOWNLOAD_READ_TIME_OUT = 30000;
        public static final int DOWNLOAD_CONNECT_TIME_OUT = 30000;
    }

    public class Actions {
        public static final String CLASSROOM_INFO = "classroom_info";
        public static final String PARAM_ESCORDER_ID = "escOderId";
        public static final String DIALOG_CLASS_NAME = "com.tom.ule.lifepay.ule.ui.dialog";
        public static final String PARAM_STORE_ID = "storeId";
        public static final String PARAM_CLM_ID = "clmId";
        public static final String PARAM_LIST_ID = "listId";
        public static final String PARAM_PRDINFO = "param_prdinfo";
        public static final String PARAM_PRODUCT_KEY = "moduleKey";
        public static final String PARAM_TITLE_STRING = "title";
        public static final String PARAM_LIST_DATA_TYPE = "listDataType";
        public static final String PARAM_INDEX_KEY = "indexKey";
        public static final String PARAM_INDEX_NAME = "indexName";
        public static final String PARAM_PAGER = "pager";
        public static final String PARAM_MULTE_LIST_ID = "multeListId";
        public static final String PARAM_MULTE_LIST_COUNT = "multeListCount";
        public static final String PARAM_BARCODE = "barCode";
        public static final String PARAM_ORDER_PARAM = "orderParam";
        public static final String MOBILE_RECHARGE_PARAM = "mobile_recharge_param";
        public static final String MOBILE_RECHARGEINFO = "mobile_rechargeinfo";

        public static final String PARAM_INDEX_PAGER = "indexPager";
        public static final String PARAM_URL = "url";
        public static final String PARAM_MSG = "msg";
        public static final String PARAM_PACKAGE_ID = "packageId";// 物流订单包裹id
        public static final String PARAM_COLLECTION_TAB = "collectionTab";// 我的收藏，0心动商品：1喜爱店铺
        public static final String PARAM_ORDER_STATUS_TAB = "orderstatusTab";// 我的订单，0待付款：1代签收：2已完成
        public static final String PARAM_LOGIN_TASK = "loginTask";
        public static final String PARAM_NEED_LOGIN = "needLogin";
        public static final String PARAM_NEED_SHARE = "needShare";
        public static final String PARAM_NEED_TITLE = "needTitle";
        public static final String PARAM_EXIST = "exist";// 手机充值活动是否存在
        public static final String PARAM_ORDER_CONFIRM_INFO = "orderConfirmInfo";
        public static final String MID_PARAM_ORDER_CONFIRM_INFO = "midOrderConfirmInfo";
        public static final String PARAM_ADDRESS = "address";

        public static final String PRDLIST_TYPE = "prdlistType";
        public static final String PRDLIST_TYPE_CATEGORYID = "category";
        public static final String PRDLIST_TYPE_BRAND = "brand";
        public static final String PRDLIST_TYPE_STORE = "store";
        public static final String PRDLIST_TYPE_BRAND_ID = "brandId";
        public static final String PRDLIST_TYPE_BRAND_NAME = "brandName";
        public static final String PRDLIST_TYPE_CATEGORY_ID = "categoryId";
        public static final String PRDLIST_TYPE_CATEGORY_NAME = "categoryName";
        public static final String PRDLIST_TYPE_STORE_ID = "storeId";
        public static final String PRDLIST_TYPE_STORE_NAME = "storeName";

        public static final String MIN_VERSION = "minVersion";
        public static final String MAX_VERSION = "maxVersion";
        public static final String INDEX_CLASSIFY = "classify";

        public static final String MID_AUTUMN_EVENT_CHANNEL = "channel";

        public static final String MID_AUTUMN_EVENT_ADDRESS = "address";

        public static final String SHARE_TITLE = "shareTitle";
        public static final String SHARE_CONTENT = "shareContent";
        public static final String SHARE_IMAGE_URL = "shareImageUrl";
        public static final String SHARE_LINK_URL = "shareLinkUrl";
        public static final String LIFE_PAY_TYPE = "lifepaytype";

        public static final String SEC_KILL_CLOCK_ACTION = "sec_kill_clcok_action";
        public static final String SEC_KILL_TIME_ACTION = "sec_kill_time_action";
        public static final String SEC_KILL_TAG_ACTION = "sec_kill_tag_action";
        public static final String SEC_KILL_BANNER_IMG = "bannerImage";
        public static final String SEC_KILL_BANNER_URL = "bannerUrl";

        public static final String IS_CAN_REPLACE = "1";
    }

    public class FRIEND {
        public static final String FRIEND_BUYER_NOTE = "DAIGOUORDER";
        public static final String PRDLIST_TYPE_FRIEND_PAID = "friend_pay";
    }

    public class ACTIONLOG {
        public static final String PAY_SUCCESS = "4";
        public static final String ULE_CARD = "ULECARD";
        public static final String UNION_PAY = "UNIONPAY";
        public static final String POST_CARD = "POSTCARD";
        public static final String INVITE_PAY = "INVITEPAY";
    }
}