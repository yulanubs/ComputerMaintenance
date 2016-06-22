package com.computerdmaintenance.ui.wgt;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.drawable.AnimationDrawable;
import android.text.Editable;
import android.text.Selection;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.computerdmaintenance.R;
import com.computerdmaintenance.ui.view.DiagnosisMonitorView;
import com.loudmaintenance.util.BindView;

import java.util.Map;

/**
 * 诊断页面
 */
public class DiagnosisWgt extends BaseWgt implements OnClickListener {
    public AnimationDrawable animationDrawable;
    //    /**
//     * 中心盾牌
//     */
//    @BindView(id = R.id.iv_diagnosis)
//    private ImageView iv_diagnosis;
    @BindView(id = R.id.dmv_DiagnosticIndicator)
    private DiagnosisMonitorView dmv_DiagnosticIndicator;


    public DiagnosisWgt(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        // TODO Auto-generated constructor stub
    }

    public DiagnosisWgt(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
    }

    public DiagnosisWgt(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
    }

    @Override
    protected void initView(Context context) {
        /* TODO Auto-generated method stub */
        init();
        View mView = inflate(context, R.layout.diagnosiswgt_layout, this);
        initBindView(DiagnosisWgt.this, mView);
//        iv_diagnosis.setBackgroundResource(R.anim.diagnosis_animation);
//        animationDrawable = (AnimationDrawable) iv_diagnosis.getBackground();
//        animationDrawable.start();
        setData();

    }

    /**
     * 设置数据
     */
    private void setData() {

    }

    @Override
    public void onBringToFront() {

    }

    private void init() {
        mContext = getContext();

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {

        super.onConfigurationChanged(newConfig);


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
        return "诊断";
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
        return "DIAGNOSISWGT";
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

        }

    }

    @Override
    public void hideFooter() {
        // TODO Auto-generated method stub

    }
}
