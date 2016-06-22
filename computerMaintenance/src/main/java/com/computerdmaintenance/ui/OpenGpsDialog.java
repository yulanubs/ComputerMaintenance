package com.computerdmaintenance.ui;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.computerdmaintenance.R;

public class OpenGpsDialog extends AlertDialog implements
        OnClickListener {

    private TextView open_gps_ok;
    private TextView open_gps_cancle;

    private OpenGpsButtonListener mIClickButtonListener;

    public OpenGpsDialog(Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);

        setContentView(R.layout.open_gps_dialog);

        open_gps_ok = (TextView) findViewById(R.id.open_gps_ok);
        open_gps_cancle = (TextView) findViewById(R.id.open_gps_cancle);
        initEvent();
    }

    private void initEvent() {
        open_gps_ok.setOnClickListener(this);
        open_gps_cancle.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        if (v == open_gps_ok) {
            if (mIClickButtonListener != null) {
                mIClickButtonListener.clickOk();
            }
        } else if (v == open_gps_cancle) {
            if (mIClickButtonListener != null) {
                mIClickButtonListener.clickCancel();
            }

        }
    }

    public void setIClickButtonListener(OpenGpsButtonListener lisenter) {
        this.mIClickButtonListener = lisenter;
    }

    public interface OpenGpsButtonListener {
        void clickOk();

        void clickCancel();
    }

}