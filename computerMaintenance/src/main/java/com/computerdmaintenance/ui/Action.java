package com.computerdmaintenance.ui;

import android.content.Context;

import com.computerdmaintenance.ui.wgt.event.MrEventAction;
import com.computerdmaintenance.util.MrLog;
import com.mr.cm.common.base.domain.IndexFunctionItem;
import com.mr.cm.common.base.domain.IndexItemInfo;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Action implements Serializable {
    public static final int ACTION_TYPE_OPENURL = 1;
    public static final int ACTION_TYPE_RUN = 2;
    public static final int ACTION_TYPE_RUNWGT = 3;
    public static final String ULE_ACTION = "ULE_Action";
    private static final long serialVersionUID = -6248767504210311030L;
    public MrEventAction uleEventAction;
    /* 类名&参数名|参数值#参数名|参数值 */
    public String actyName;
    public String wgtClass;
    public Map<String, Object> parameters = new HashMap<String, Object>();
    // 优惠券提示信息
    public String mPromtTitle;
    public String mPromtStr;
    public String mCouponType;
    private String TAG = "Action";

    public Action() {
        actyName = "";
        wgtClass = "";
    }

    public Action(Context context, String act, boolean fromPush)
            throws Exception {
        if (fromPush) {
            String newAct = act.replace("**", "&");
            createAction(context, newAct);
        } else {
            createAction(context, act);
        }
    }

    public Action(Context context, String act) throws Exception {
        createAction(context, act);
    }

    public Action(Context context, IndexItemInfo item) throws Exception {
        if (item == null || item.attribute1 == null
                || item.attribute1.equals("")) {
            throw new Exception("IndexItemInfo is empty");
        }
        String packageName = context.getPackageName();
        if (item.attribute1.startsWith(".")) {
            actyName = packageName + item.attribute1;
        } else {
            actyName = packageName + "." + item.attribute1;
        }

        if (item.attribute2 != null && !item.attribute2.equals("")) {
            if (item.attribute2.startsWith(".")) {
                wgtClass = packageName + item.attribute2;
            } else {
                wgtClass = packageName + "." + item.attribute2;
            }
        } else {
            wgtClass = "";
        }

        String[] ps = item.attribute3.split("#");
        for (int i = 0; i < ps.length; i++) {
            int index = ps[i].indexOf("|");
            if (index > 0) {
                String key = ps[i].substring(0, index);
                String value = ps[i].substring(index + 1);
                parameters.put(key, value);
            }
        }
    }

    public Action(Context context, IndexFunctionItem item) throws Exception {
        if (item == null || item.android_action == null
                || item.android_action.equals("")) {
            throw new Exception("IndexFunctionItem is empty");
        }
        String packageName = context.getPackageName();
        String[] android_actionStr = item.android_action.split("&&");
        if (android_actionStr.length == 0) {
            return;
        }
        String actyStr = android_actionStr[0];

        String wgtClassStr = "";
        String parameterStr = "";
        if (android_actionStr.length > 1)
            wgtClassStr = android_actionStr[1];
        if (android_actionStr.length > 2)
            parameterStr = android_actionStr[2];

        if (item.android_action.startsWith(".")) {
            actyName = packageName + actyStr;
        } else {
            actyName = packageName + "." + actyStr;
        }

        if (wgtClassStr != null && !wgtClassStr.equals("")) {
            if (wgtClassStr.startsWith(".")) {
                wgtClass = packageName + wgtClassStr;
            } else {
                wgtClass = packageName + "." + wgtClassStr;
            }
        } else {
            wgtClass = "";
        }

        String[] ps = parameterStr.split("##");
        for (int i = 0; i < ps.length; i++) {
            int index = ps[i].indexOf("::");
            if (index > 0) {
                String key = ps[i].substring(0, index);
                String value = ps[i].substring(index + 2);
                parameters.put(key, value);
            }
        }
    }

    public Action(Context context, IndexItemInfo item, String param)
            throws Exception {
        String packageName = context.getPackageName();
        String[] attr1 = item.attribute1.split("#");
        if (attr1 != null) {
            actyName = packageName + "." + attr1[0];
            if (attr1.length == 2) {
                wgtClass = packageName + "." + attr1[1];
            } else {
                wgtClass = "";
            }
        }

        if (item.attribute2.contains("#")) {
            String[] attr2 = item.attribute2.split("#");
            if (attr2 != null && attr2.length > 0) {
                int size = attr2.length;
                for (int i = 0; i < size; i++) {
                    int index = attr2[i].indexOf("|");
                    if (index > 0) {
                        String key = attr2[i].substring(0, index);
                        String value = attr2[i].substring(index + 1);
                        parameters.put(key, value);
                        MrLog.debug(TAG, "key" + key + "value" + value);
                    }
                }
            }
        } else {
            int index = item.attribute2.indexOf("|");
            if (index > 0) {
                String key = item.attribute2.substring(0, index);
                String value = item.attribute2.substring(index + 1);
                parameters.put(key, value);
                MrLog.debug(TAG, "key" + key + "value" + value);
            }
        }
        mPromtTitle = item.title;
        mPromtStr = item.attribute3;
        mCouponType = item.attribute4;
    }

    private void createAction(Context context, String act) throws Exception {
        String packageName = context.getPackageName(); //
        String[] main = act.split("&");
        if (main.length == 0) {
            throw new Exception();
        }
        String[] className = main[0].split("#");
        if (className.length == 0) {
            throw new Exception();
        }
        if (className[0].startsWith(".")) {
            actyName = packageName + className[0];
        } else {
            actyName = packageName + "." + className[0];
        }

        if (className.length > 1) {
            if (className[1].startsWith(".")) {
                wgtClass = packageName + className[1];
            } else {
                wgtClass = packageName + "." + className[1];
            }
        } else {
            wgtClass = "";
        }
        if (main.length > 1) {
            String[] ps = main[1].split("#");
            for (int i = 0; i < ps.length; i++) {
                int index = ps[i].indexOf("|");
                if (index > 0) {
                    String key = ps[i].substring(0, index);
                    String value = ps[i].substring(index + 1);
                    parameters.put(key, value);
                }
            }
        }
    }
}