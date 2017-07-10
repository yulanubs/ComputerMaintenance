package com.computerdmaintenance.ui.wgt;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.drawable.AnimationDrawable;
import android.text.Editable;
import android.text.Selection;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AlphaAnimation;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.computerdmaintenance.R;
import com.computerdmaintenance.ui.view.DiagnosisMonitorView;
import com.computerdmaintenance.ui.view.DigitalGroupView;
import com.computerdmaintenance.ui.view.HeartbeatView;
import com.computerdmaintenance.util.BindView;
import com.computerdmaintenance.util.ValueUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 诊断页面
 */
public class DiagnosisWgt extends BaseWgt implements OnClickListener {
    private HeartbeatView mHeartbeatView;
    private DigitalGroupView mDigiResult;
    private TextView mTextUnit,mTextToast;
    private List<HeartbeatEntity> mData = new ArrayList<>();
    private Button btn_Diagnosis;
    private  EditText et_search;
    private   String keyWord="";
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
        mHeartbeatView = (HeartbeatView) findViewById(R.id.heartbeat);
        mDigiResult = (DigitalGroupView) findViewById(R.id.digi_heartbeat_result);
        mTextUnit = (TextView) findViewById(R.id.text_unit);
        mTextToast = (TextView) findViewById(R.id.tv_toast);
        btn_Diagnosis = (Button) findViewById(R.id.btn_Diagnosis);
        et_search = (EditText) findViewById(R.id.et_search);

        btn_Diagnosis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 keyWord = et_search.getText().toString().trim();
                if (ValueUtils.isStrEmpty(keyWord)){
                    appcontext.openToast("亲,关键字不能为空!");
                    return;
                }
                hideSoftKeyword();
                mHeartbeatView.startAnim();
                mTextToast.setText("云端诊断中...");
                mTextToast.setVisibility(VISIBLE);
                hideResult();
            }
        });
        mHeartbeatView.setHeartBeatAnimListener(new HeartbeatView.HeartBeatAnimImpl() {
            @Override
            public void onAnimFinished() {
                mTextToast.setText("云端诊断成功");
                int randomNum = (int) (50 + Math.random() * 50);
                HeartbeatEntity e = new HeartbeatEntity();
                e.date = "2016-06-27";
                e.datum = String.valueOf(randomNum);
                mData.add(0, e);
//                mAdapter.notifyItemInserted(0);
//                mHeartbeatRecycler.scrollToPosition(0);

                showResult();
                mDigiResult.setDigits(randomNum);
            }
        });

        setData();

    }
    private void showResult() {
        mTextUnit.setVisibility(View.VISIBLE);
        mDigiResult.setVisibility(View.VISIBLE);
    }
    class HeartbeatEntity {
        String date;
        String datum;
    }
    private void hideResult() {
        AlphaAnimation mHiddenAction = new AlphaAnimation(1f, 0f);
        mHiddenAction.setDuration(400);

        mTextUnit.setAnimation(mHiddenAction);
        mDigiResult.setAnimation(mHiddenAction);

        mTextUnit.setVisibility(View.GONE);
        mDigiResult.setVisibility(View.GONE);
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
