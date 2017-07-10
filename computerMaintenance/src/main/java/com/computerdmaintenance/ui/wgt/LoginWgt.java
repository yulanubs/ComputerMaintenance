package com.computerdmaintenance.ui.wgt;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.text.Editable;
import android.text.InputType;
import android.text.Selection;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.benframework.utils.UtilsToast;
import com.computerdmaintenance.R;
import com.computerdmaintenance.nohttp.AllModuleReqeust;
import com.computerdmaintenance.nohttp.CallServer;
import com.computerdmaintenance.nohttp.HttpCallBack;
import com.computerdmaintenance.ui.WGTContainer;
import com.computerdmaintenance.ui.view.StateButton;
import com.computerdmaintenance.ui.view.StateButton.OnStateChangeListener;
import com.computerdmaintenance.ui.wgt.event.MrEvent;
import com.computerdmaintenance.util.Constants;
import com.computerdmaintenance.util.MrEventWebLogin;
import com.computerdmaintenance.util.MrLog;
import com.computerdmaintenance.util.UtilTools;
import com.mr.cm.common.base.domain.FuncationInfo;
import com.mr.cm.common.base.domain.UserViewModle;
import com.yolanda.nohttp.Request;
import com.yolanda.nohttp.Response;

import java.util.Map;

public class LoginWgt extends BaseWgt implements View.OnClickListener {
    public static final String DES = "Login";
    public static String secretKey = "6fd4b7f4";
    Handler handler = new Handler();
    private Button loginButton;
    private EditText username;
    private EditText userpwd;
    private StateButton pwdStateBtn;
    private TextView findPwd;
    private TextView goRegister;
    private TextView loginByQQ;
    private TextView login_wb_btn;
    private String isrememberMe;
    private String user;
    private String password;
    private boolean isBack = false;
    private HttpCallBack<FuncationInfo> serverBeanCallBack = new HttpCallBack<FuncationInfo>() {
        @Override
        public void onSucceed(int what, Response<FuncationInfo> response) {
            if (what == Constants.What.WHAT_INDEX_FUNCTION) {// 处理登录结果
                FuncationInfo info = response.get();
                if (null != info) {

                    if (info.returnCode.equals("0000")) {

                        //跳转到个人中心
                        UtilsToast.myToast(mContext, info.toString());


                    } else {
                        appcontext.openToast(mContext, info.returnMessage);
                    }
                }
            }
        }

        @Override
        public void onFailed(int what, String url, Object tag, Exception exception, int responseCode, long networkMillis) {
        }
    };

    public LoginWgt(Context context) {
        super(context);
        // TODO Auto-generated constructor stub

    }

    public LoginWgt(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub

    }

    public LoginWgt(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        // TODO Auto-generated constructor stub

    }

    @Override
    protected void initView(Context context) {
        inflate(context, R.layout.wgt_login, this);

        loginButton = (Button) findViewById(R.id.login_login);
        username = (EditText) findViewById(R.id.user);
        userpwd = (EditText) findViewById(R.id.pw);
        pwdStateBtn = (StateButton) findViewById(R.id.login_pwd_show_check);
        pwdStateBtn.setDrawable(new int[]{R.drawable.login_key_off,
                R.drawable.login_key_on});
        pwdStateBtn.setState(false);

        findPwd = (TextView) findViewById(R.id.login_find_pwd);
        findPwd.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
        goRegister = (TextView) findViewById(R.id.login_register);
        goRegister.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
        loginByQQ = (TextView) findViewById(R.id.login_wx);
        login_wb_btn = (TextView) findViewById(R.id.login_wb_btn);

        Drawable draw = getResources().getDrawable(R.drawable.ic_image_qq);
        draw.setBounds(0, 0, UtilTools.dip2Px(context, 55),
                UtilTools.dip2Px(context, 55));
        loginByQQ.setCompoundDrawables(null, draw, null, null);

        Drawable drawcxy = getResources().getDrawable(R.drawable.ic_image_wb);
        drawcxy.setBounds(0, 0, UtilTools.dip2Px(context, 55),
                UtilTools.dip2Px(context, 55));
        login_wb_btn.setCompoundDrawables(null, drawcxy, null, null);

        initdata();
        setViewListener();
    }

    private void initdata() {
        isBack = false;

        user = appcontext.getSharedPreferences("isAlreadyLogin");
        isrememberMe = appcontext.getSharedPreferences("isrememberMe");

        if (isrememberMe != null && !isrememberMe.equals("")
                && isrememberMe.equals("rememberMe")) {
            username.setText(user);
            userpwd.setText(password);
        }
    }

    @SuppressWarnings("unused")
    private boolean validateEmail() {
        user = username.getText().toString();
        return user != null && user.indexOf("@") > 0;
    }

    @SuppressWarnings("unused")
    private boolean validateTelNum() {
        user = username.getText().toString();
        return user != null && user.length() == 11;
    }

    @SuppressWarnings("unused")
    private boolean validatePwd() {
        password = userpwd.getText().toString().trim();
        return password != null && password.length() >= 6;
    }

    private void setViewListener() {
        loginButton.setOnClickListener(this);
        goRegister.setOnClickListener(this);
        findPwd.setOnClickListener(this);
        loginByQQ.setOnClickListener(this);

        pwdStateBtn.setOnStateChangeListener(new OnStateChangeListener() {

            @Override
            public void onStateChange(StateButton v, boolean state) {
                // TODO Auto-generated method stub
                if (state) {
                    userpwd.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                } else {
                    userpwd.setInputType(InputType.TYPE_CLASS_TEXT
                            | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }

                moveCursor();
            }
        });

    }

    private void moveCursor() {
        if (userpwd == null) {
            return;
        }
        Editable etext = userpwd.getText();
        if (etext.length() < 1) {
        } else {
            Selection.setSelection(etext, etext.length());
        }
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    private void login() {
        user = username.getText().toString();
        password = userpwd.getText().toString();
        if (checkInput(user, password)) {
//			ObjectRequest request = new ObjectRequest(appcontext.config.SERVER_MR_VPS,Constants.Api.LOGIN,RequestMethod.POST);
            Request<UserViewModle> mRequest = new AllModuleReqeust<UserViewModle>(appcontext.config.SERVER_MR_VPS + Constants.Api.LOGIN, UserViewModle.class);
            mRequest.add("name", user);
            mRequest.add("password", password);
            CallServer.getInstance().add(mContext, mRequest, new HttpCallBack<UserViewModle>() {

                @Override
                public void onSucceed(int what, Response<UserViewModle> response) {

                    if (what == Constants.What.WHAT_LOGIN) {// 处理登录结果
                        UserViewModle info = response.get();
                        if (null != info) {

                            if (info.returnCode.equals("0000")) {
                                loginSuccess(info, true);

                                //跳转到个人中心
                                UtilsToast.myToast(mContext, appcontext.domainUser.userByname + "userToken" + appcontext.domainUser.userToken);
                                //UtilsToast.myToast(mContext, info.UserInfo.userByname);


                            } else {
                                appcontext.openToast(mContext, info.returnMessage);
                            }
                        }
                    }


                }

                @Override
                public void onFailed(int what, String url, Object tag,
                                     Exception exception, int responseCode,
                                     long networkMillis) {
                    // TODO Auto-generated method stub

                }
            }, Constants.What.WHAT_LOGIN, false, false, true);

        }

    }

    private void loginSuccess(UserViewModle item, boolean isLoginByMobile) {
        cancelKeyboard(username);
        cancelKeyboard(userpwd);
        appcontext.loginSuccess(item.UserInfo);
        rememberInfo();


    }

    private void rememberInfo() {
        MrLog.error("LOGIN", "rmb");
        if (validateEmail() || validateTelNum()) {
            rememberMe();
        }
    }

    private void rememberMe() {
        appcontext.setSharedPreferences("isrememberMe", "rememberMe");
        appcontext.setSharedPreferences("isAlreadyLogin", username.getText()
                .toString());
        appcontext.saveLogin(appcontext.domainUser);
    }

    /**
     * 方法名：<BR>
     * 此方法描述的是：检查手机号跟密码是否为空
     *
     * @param mobile
     * @param password
     * @return boolean
     */
    private boolean checkInput(String mobile, String password) {
        if (TextUtils.isEmpty(mobile) || TextUtils.isEmpty(password)) {
            appcontext.openToast(appcontext, "请输入手机号和密码");
            return false;
        }

        if (password == null || password.equals("")) {
            appcontext.openToast(
                    appcontext,
                    getResources().getString(
                            R.string.login_pls_input_confirm_pwd_first));
            return false;
        }
        if (password.length() < 6) {
            appcontext.openToast(appcontext, "亲，密码长度过短！");
            return false;
        }
        if (password.length() > 20) {
            appcontext.openToast(appcontext, "亲，密码长度过长！");
            return false;
        }
        if (checkPwdCh(password)) {
            appcontext.openToast(appcontext, "亲，密码不能包含中文哦！");
            return false;
        }
        if (password.contains(" ")) {
            appcontext.openToast(appcontext, "亲，密码不能包含空格符哦！");
            return false;
        }
        return true;

    }

    public void cancelKeyboard(EditText editText) {
        InputMethodManager imm = (InputMethodManager) getContext()
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
        }
        this.clearFocus();
    }

    @Override
    public boolean onUleEvent(MrEvent event, BaseWgt wgt, WGTContainer container) {
        // TODO Auto-generated method stub
        switch (event.Event) {
            case MrEvent.EVENT_WGT_DISPOSE:
                releaseResource();
                break;
        }
        return super.onUleEvent(event, wgt, container);
    }

    public void releaseResource() {

    }

    @Override
    public void onBringToFront() {
        // TODO Auto-generated method stub

    }

    @Override
    public void onSentToBack() {
        // TODO Auto-generated method stub

    }

    @Override
    public void onAddToStack() {
        // TODO Auto-generated method stub

    }

    @Override
    public void onDestory() {
        // TODO Auto-generated method stub

    }

    @Override
    public void onDispose() {
        // TODO Auto-generated method stub

    }

    @Override
    public int leftBackVisibility() {
        // TODO Auto-generated method stub
        return View.GONE;
    }

    @Override
    public View setRightView() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int getLifeCycle() {
        // TODO Auto-generated method stub
        return BaseWgt.SINGLE | BaseWgt.UNTRACE;
    }

    @Override
    public boolean needLogin() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    protected String getTitleString() {
        // TODO Auto-generated method stub
        return getResources().getString(R.string.login_login);
    }

    /**
     * 查询该微信是否已关联用户
     */

    @Override
    protected boolean needTitle() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    protected void startFromAction(Map<String, Object> params) {
        // TODO Auto-generated method stub
    }

    /**
     * 获取对应ule账号信息
     */

    @Override
    public void onClick(View v) {
        if (v == loginButton) {
            login();
//			getFunctions();
        } else if (v == goRegister) {
            if (container != null) {
                container.switchView(RegisterWgt.class);
            }
        } else if (v == findPwd) {
            if (container != null) {
                container.switchView(FindLoginPwdWgt.class);
            }
        }
    }

    /**
     * 获取功能分类信息
     */
    private void getFunctions() {
        Request<FuncationInfo> mRequest = new AllModuleReqeust<FuncationInfo>(appcontext.config.SERVER_MR_VPS + Constants.Api.INDEX_FUNCATIONS, FuncationInfo.class);
        CallServer.getInstance().add(mContext, mRequest, serverBeanCallBack, Constants.What.WHAT_INDEX_FUNCTION, true, false, true);
    }

    @Override
    public String getPageName() {
        // TODO Auto-generated method stub
        return "LOGIN";
    }

	/*
     * @Override public void onBackClick() { // TODO Auto-generated method stub
	 * notLoginBack(); super.onBackClick(); }
	 */

    @Override
    public boolean onBackKeyDown() {
        // TODO Auto-generated method stub
        if (!isBack) {
            if (notLoginBack()) {
                return true;
            }
        }
        return super.onBackKeyDown();
    }

    private boolean notLoginBack() {
        isBack = true;
        if (container != null) {
            container.stepBack();
            container.alertUleEvent(new MrEventWebLogin("false"));
            return true;
        }
        return false;
    }

    @Override
    public void hideFooter() {
        // TODO Auto-generated method stub

    }

}