package com.computerdmaintenance.ui.wgt;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.computerdmaintenance.ComputerMaintenanceApplication;
import com.computerdmaintenance.R;
import com.computerdmaintenance.config.ConstData;
import com.computerdmaintenance.ui.WGTContainer;
import com.computerdmaintenance.ui.activity.WorkingActivity;
import com.computerdmaintenance.ui.component.MrHeader;
import com.computerdmaintenance.ui.component.MrHeader.OnBackClickListener;
import com.computerdmaintenance.ui.wgt.event.MrEvent;
import com.computerdmaintenance.ui.wgt.event.MrEventAction;
import com.loudmaintenance.util.BindView;
import com.loudmaintenance.util.Consts;
import com.loudmaintenance.util.RegularExpressions;
import com.loudmaintenance.util.UtilTools;
import com.mr.cm.common.base.domain.ResultViewModle;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class BaseWgt extends FrameLayout implements
        OnBackClickListener, OnClickListener {
    public static final int SINGLE = 0x00;
    public static final int MULTI = 0x01;
    public static final int UNTRACE = 0x00;
    public static final int TRACE = 0x02;
    public Context mContext;
    public Toast mToast;
    protected WGTContainer container;
    protected MrHeader header;

    protected Map<String, Object> launchParams = new HashMap<String, Object>();

    protected ComputerMaintenanceApplication appcontext;
    protected int titleHight;
    WorkingActivity mw;

    public BaseWgt(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
        init(context);
    }

    public BaseWgt(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
        init(context);
    }

    public BaseWgt(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        // TODO Auto-generated constructor stub
        init(context);
    }

    /**
     * @param currentClass 当前类，一般为Activity或Fragment
     * @param view         待绑定控件的直接或间接父控件
     */
    public static void initBindView(OnClickListener currentClass, View view) {
        // 通过反射获取到全部属性，反射的字段可能是一个类（静态）字段或实例字段
        Field[] fields = currentClass.getClass().getDeclaredFields();
        if (fields != null && fields.length > 0) {
            for (Field field : fields) {
                // 返回BindView类型的注解内容
                BindView bindView = field.getAnnotation(BindView.class);
                if (bindView != null) {
                    int viewId = bindView.id();
                    boolean clickLis = bindView.click();
                    try {
                        field.setAccessible(true);
                        if (clickLis) {
                            view.findViewById(viewId).setOnClickListener(
                                    currentClass);
                        }
                        // 将currentClass的field赋值为sourceView.findViewById(viewId)
                        field.set(currentClass, view.findViewById(viewId));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    private void init(Context context) {
        mContext = context;
        setBackgroundColor(Color.WHITE);
        titleHight = UtilTools.dip2Px(context, 50);
        appcontext = (ComputerMaintenanceApplication) context
                .getApplicationContext();
        initView(context);
        if (needTitle()) {
            addTitle(context);
        }
    }

    /**
     * 方法名：checkPwdCh<BR>
     * 此方法描述的是：
     *
     * @param cellPhoneNr
     * @return boolean
     */
    public boolean checkPwdCh(String cellPhoneNr) {
        String reg = RegularExpressions.a_password;
        return UtilTools.startCheck(reg, cellPhoneNr);
    }

    private void addTitle(Context context) {
        // titleHight = UtilTools.dip2Px(context, 50);
        header = new MrHeader(context);
        LayoutParams headerLayoutParams = new LayoutParams(
                LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        headerLayoutParams.gravity = Gravity.TOP;
        addView(header, headerLayoutParams);

        header.setLeftBackVisible(leftBackVisibility());
        header.setOnBackClickListener(this);
        header.setBottomVisible(View.VISIBLE);
        header.setTitleText(getTitleString());
        /*
         * if (uleapp.networkAlive){ unShowNetworkErr(); }else{
		 * showNetworkErr(); }
		 */
        View right = setRightView();
        if (right != null) {
            header.setRightView(right);
        }
    }

    @Override
    public void onBackClick() {
        // TODO Auto-generated method stub
        container.onTitleBackClick();
    }

    /**
     * 初始化WGT
     *
     * @param context
     */
    protected abstract void initView(Context context);

    public abstract void onBringToFront();

    /**
     * 显示底部菜单
     */
    public abstract void onSentToBack();

    public abstract void onAddToStack();

    public abstract void onDestory();

    public abstract void onDispose();

    /**
     * 隐藏底部菜单
     */
    public abstract void hideFooter();

    /**
     * Title是否显示左边返回按钮
     *
     * @return
     */
    public abstract int leftBackVisibility();

    /**
     * 设置Title右边自定义按钮，返回null为不设置
     *
     * @return
     */
    public abstract View setRightView();

    /**
     * 返回WGT的生命形式
     *
     * @return
     */
    public abstract int getLifeCycle();

    /**
     * 该WGT是否需要登录
     *
     * @return
     */
    public abstract boolean needLogin();

    /**
     * 返回Title名称
     *
     * @return
     */
    protected abstract String getTitleString();

    /**
     * 该WGT是否需要Title
     *
     * @return
     */
    protected abstract boolean needTitle();

    public boolean onUleEvent(MrEvent event, BaseWgt wgt, WGTContainer container) {
        switch (event.Event) {
            case MrEvent.EVENT_NETWORK_ERR:
                // showNetworkErr();
                break;

            case MrEvent.EVENT_NETWORK_SUCCESS:
                // unShowNetworkErr();
                refereshData();
                break;
        }
        return false;
    }

    public void showNetworkErr() {
        if (header != null) {
            header.showNetworkErr();
        }
    }

    public void unShowNetworkErr() {
        if (header != null) {
            header.dismissNetworkErr();
        }
    }

    public boolean onBackKeyDown() {
        return false;
    }

    public void setContainer(WGTContainer c) {
        container = c;
    }

    public void removeContainer() {
        container = null;
    }

    public SharedPreferences getSharedPreferences() {
        SharedPreferences sp = getContext().getSharedPreferences(
                ComputerMaintenanceApplication.MR_PREFERENCES,
                Context.MODE_PRIVATE);
        return sp;
    }

    public void setLaunchParams(Map<String, Object> params) {
        launchParams = params;
        startFromAction(params);
    }

    public void changeTitleText(String title) {
        header.setTitleText(title);
    }

    public void hideTitle(int visibility) {
        header.setVisibility(visibility);
    }

    public void changeRightView(View view) {
        if (view != null) {
            header.setRightView(view);
        }
    }

    public void hideRightView(int visibility) {
        header.setRightVisible(visibility);
    }

    protected abstract void startFromAction(Map<String, Object> params);

    protected void requestSwitchView(MrEventAction event) {
        if (container != null) {
            container.alertUleEvent(event);
        }
    }

    public void onReferesh() {

    }

    public void onResume() {

    }

    public void onRefreshGetCoupon() {

    }

    /**
     * 隐藏键盘
     */
    public void hideSoftKeyword() {
        InputMethodManager inputManager = (InputMethodManager) getContext()
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        View fadeView = ((Activity) getContext()).getCurrentFocus();
        if (inputManager != null && fadeView != null) {
            inputManager.hideSoftInputFromWindow(fadeView.getWindowToken(),
                    InputMethodManager.HIDE_NOT_ALWAYS);
            fadeView.clearFocus();
        }
        clearFocus();
    }

    /**
     * 调用接口时，在Success()中，当不满足情况时，调用此方法统一处理
     *
     * @param result
     * @return true 已处理，否者false
     */
    public boolean handleResult(ResultViewModle result) {
        if (result != null) {
            if (result.returnCode.equals(ConstData.ERR_0023)) {
                // 登陆失败，重新登陆
                ((ComputerMaintenanceApplication) getContext()
                        .getApplicationContext()).LogOut();
                // container.switchView(Login.class);
                ((ComputerMaintenanceApplication) getContext()
                        .getApplicationContext()).openToast(getContext(),
                        result.returnMessage);
                return true;
            }
        }
        return false;
    }

    public abstract String getPageName();

    protected void refereshData() {

    }

    protected String getCacheData(String key, long dprecated) {
        SharedPreferences sp = getContext().getSharedPreferences(
                Consts.Preference.DATA_CACHE_SP, Context.MODE_PRIVATE);
        long time = sp.getLong(key + "_time", 0);
        long now = System.currentTimeMillis();
        if (now - time > dprecated) {
            sp.edit().remove(key).commit();
            return null;
        } else {
            return sp.getString(key, null);
        }
    }

    protected void saveCache(String key, String value) {
        SharedPreferences sp = getContext().getSharedPreferences(
                Consts.Preference.DATA_CACHE_SP, Context.MODE_PRIVATE);
        long now = System.currentTimeMillis();
        sp.edit().putString(key, value).putLong(key + "_time", now).commit();
    }

    protected void removeCache(String key) {
        getContext()
                .getSharedPreferences(Consts.Preference.DATA_CACHE_SP,
                        Context.MODE_PRIVATE).edit().remove(key).commit();
    }

    public void showToast(Context context, String text_String) {
        if (mToast != null) {
            // mToast.cancel();
        } else {
            mToast = new Toast(context);
            mToast.setGravity(Gravity.CENTER, 0, 0);
            mToast.setDuration(Toast.LENGTH_SHORT);
        }
        TextView textView = new TextView(context);
        textView.setText(text_String);
        textView.setTextColor(Color.WHITE);
        textView.setTextSize(25);
        textView.setBackgroundResource(R.drawable.rectangle);
        textView.setPadding(50, 5, 50, 5);
        mToast.setView(textView);
        mToast.show();
    }

    public void goActy(Class<?> acty, Bundle data) {
        Intent intent = new Intent(getContext(), acty);
        if (data != null) {
            intent.putExtras(data);
        }
        getContext().startActivity(intent);
    }

    /**
     * 显示底部菜单
     */
    protected void showFooter(int show) {
        WorkingActivity workingActivity = (WorkingActivity) getContext();
        if (workingActivity != null) {
            workingActivity.footerVisibility(show);
        }
    }

    /**
     * 判断参数是否为空
     *
     * @param iProvinceName
     * @return
     */
    protected boolean isNullParameter(String iProvinceName) {
        return null != iProvinceName && !iProvinceName.equals("null")
                && !iProvinceName.equals("");
    }

    /**
     * 方法名：isNumeric<BR>
     * 此方法描述的是：验证是否是数字
     *
     * @param str
     * @return boolean
     */
    public boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("^\\d*\\.{0,2}\\d{0,2}$");
        Matcher isNum = pattern.matcher(str);
        return isNum.matches();
    }

    /**
     * 判断对象是否为空
     *
     * @param obj
     * @return
     */
    public boolean isNullObj(Object obj) {

        return null != obj;

    }

    /**
     * 方法名：<BR>
     * 此方法描述的是：拨打电话
     *
     * @param mobile
     */
    public void tallPhone(String mobile) {
        Intent intent = new Intent("android.intent.action.CALL",
                Uri.parse("tel:" + mobile));
        mContext.startActivity(intent);
    }


    /**
     * 方法名：startCheck<BR>
     * 此方法描述的是： 正则验证方法
     *
     * @param reg
     * @param string
     * @return boolean
     */
    public boolean startCheck(String reg, String string) {
        boolean tem = false;

        Pattern pattern = Pattern.compile(reg);
        Matcher matcher = pattern.matcher(string);

        tem = matcher.matches();
        return tem;
    }
}
