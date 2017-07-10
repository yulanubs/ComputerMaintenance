package com.computerdmaintenance.ui.wgt;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.text.Editable;
import android.text.Selection;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.computerdmaintenance.R;
import com.computerdmaintenance.util.BindView;
import com.computerdmaintenance.util.CircleImageTool;
import com.mr.cm.common.base.domain.UserInfo;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

import java.util.Map;

/**
 * @ClassName: PersonalCenterWgt<BR>
 * @Describe：个人中心<BR>
 * @Author: zhuxunkang
 * @Extends：<BR>
 * @Version:1.0
 * @date:2016-4-25 下午3:16:39
 */
public class PersonalCenterWgt extends BaseWgt implements OnClickListener {
    /**
     * 用户名
     */
    @BindView(id = R.id.tv_username, click = true)
    private TextView tv_username;
    /**
     * 等级
     */
    @BindView(id = R.id.tv_userRank, click = true)
    private TextView tv_userRank;
    /**
     * 积分
     */
    @BindView(id = R.id.tv_userGold, click = true)
    private TextView tv_userGold;
    /**
     * 头像
     */
    @BindView(id = R.id.personal_login_button)
    private ImageView personal_login_button;


    private UserInfo domainUser;

    public PersonalCenterWgt(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        // TODO Auto-generated constructor stub
    }

    public PersonalCenterWgt(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
    }

    public PersonalCenterWgt(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
    }

    @Override
    protected void initView(Context context) {
        /* TODO Auto-generated method stub */
        View mView = inflate(context, R.layout.personalcenter_layout, this);
        init();
        initBindView(PersonalCenterWgt.this, mView);
        setData();

    }

    /**
     * 设置数据
     */
    private void setData() {
        domainUser = appcontext.domainUser;
        if (null != domainUser) {
            tv_username.setText(domainUser.userByname);
            tv_userRank.setText(domainUser.userRank);
            tv_userGold.setText(domainUser.userGold);
            ImageLoader.getInstance().displayImage( domainUser.userIconUrl, personal_login_button, appcontext.option, new SimpleImageLoadingListener() {

                @Override
                public void onLoadingFailed(String imageUri, View view,
                                            FailReason failReason) {
                    personal_login_button.setImageResource(R.drawable.personal_avatar_unlogin_normal);
                }

                @Override
                public void onLoadingComplete(String imageUri, View view,
                                              Bitmap loadedImage) {
                    Bitmap circleBitmap = CircleImageTool.getCircleBitmap(loadedImage, mContext);
                    personal_login_button.setImageBitmap(circleBitmap);
                }
            });
        }
    }

    @Override
    public void onBringToFront() {

    }

    private void init() {
        mContext = getContext();

    }


    @Override
    public void onResume() {
        onBringToFront();
    }


    @Override
    public void onSentToBack() {
        // TODO Auto-generated method stub
        clearViewFocus();
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
        return View.INVISIBLE;
    }

    @Override
    public View setRightView() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int getLifeCycle() {
        // TODO Auto-generated method stub
        return SINGLE | TRACE;
    }

    @Override
    public boolean needLogin() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    protected String getTitleString() {
        // TODO Auto-generated method stub
        return "个人中心";
    }

    @Override
    protected boolean needTitle() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    protected void startFromAction(Map<String, Object> params) {
        // TODO Auto-generated method stub

    }

    @Override
    public String getPageName() {
        // TODO Auto-generated method stub
        return "USERCENTER";
    }

    /**
     * 显示软键盘
     *
     * @param editText
     */
    private void showKeyboard(EditText editText) {
        editText.requestFocus();
        InputMethodManager imm = (InputMethodManager) getContext()
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.showSoftInput(editText, 0);
        }
        moveCursor(editText);
    }

    /**
     * 隐藏软键盘
     */
    public void cancelKeyboard() {
        InputMethodManager imm = (InputMethodManager) getContext()
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        View vFocus = ((Activity) getContext()).getCurrentFocus();
        if (imm != null && vFocus != null) {
            imm.hideSoftInputFromWindow(vFocus.getWindowToken(), 0);
        }
        this.clearFocus();
    }

    /**
     * 光标移动到最后
     *
     * @param editText
     */
    private void moveCursor(EditText editText) {
        Editable etext = editText.getText();
        if (etext.length() < 1) {
        } else {
            Selection.setSelection(etext, etext.length());
        }
    }

    @Override
    public boolean dispatchKeyEventPreIme(KeyEvent event) {
        clearViewFocus();
        return super.dispatchKeyEventPreIme(event);
    }

    private void clearViewFocus() {
        View fadeView = ((Activity) getContext()).getCurrentFocus();
        if (fadeView != null)
            fadeView.clearFocus();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_username:

                break;
        }
    }

    @Override
    public void hideFooter() {
        // TODO Auto-generated method stub

    }
}
