package com.computerdmaintenance.ui.component;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.provider.Settings;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.computerdmaintenance.R;

public class MrHeader extends FrameLayout implements View.OnClickListener {

    public LinearLayout backView;
    public TextView titleView;
    private View layerBack;
    private LinearLayout rightCustomLayout;
    @SuppressWarnings("unused")
    private RelativeLayout networkErrLayout;
    private Button checkNetworkErr;
    private LayoutParams mainLayoutParams =
            new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
    private OnBackClickListener _l;
    private View bottomView;

    public MrHeader(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
        init(context);
    }

    public MrHeader(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
        init(context);
    }

    public MrHeader(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        // TODO Auto-generated constructor stub
        init(context);
    }

    private void init(Context context) {
        layerBack = inflate(context, R.layout.commponet_head_main, null);

        backView = (LinearLayout) layerBack.findViewById(R.id.header_left_back);
        titleView = (TextView) layerBack.findViewById(R.id.header_title);
        rightCustomLayout = (LinearLayout) layerBack.findViewById(R.id.header_right_custom_layout);
        networkErrLayout = (RelativeLayout) layerBack.findViewById(R.id.header_network);
        checkNetworkErr = (Button) layerBack.findViewById(R.id.header_network_err_go_detail);

        ViewGroup.LayoutParams titleLayoutParams = titleView.getLayoutParams();
        titleLayoutParams.width = context.getResources().getDisplayMetrics().widthPixels / 2;
        titleLayoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        titleView.setLayoutParams(titleLayoutParams);

        backView.setOnClickListener(this);
        checkNetworkErr.setOnClickListener(this);
        //setBackgroundResource(R.color.title_background_color);
        addView(layerBack, mainLayoutParams);
        bottomView = layerBack.findViewById(R.id.header_divider);
        bottomView.setVisibility(View.GONE);
    }

    public void setBottomVisible(int visibility) {
        bottomView.setVisibility(visibility);
    }

    public void addCustomView(View view) {
        addView(view);
    }

    public void setHeaderBg(int id) {
        layerBack.setBackgroundResource(id);
    }

    public void setHeaderAlpha(int i) {
        layerBack.getBackground().setAlpha(i);
    }

    public void addLayer(View v) {
        addView(v, mainLayoutParams);
    }

    public void removeLayer(View v) {
        removeView(v);
    }

    public void setTitleText(String title) {
        titleView.setText(title);
    }

    public void setLeftBackVisible(int visibility) {
        backView.setVisibility(visibility);
    }

    public void setRightVisible(int visibility) {
        rightCustomLayout.setVisibility(visibility);
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        if (v == backView) {
            if (_l != null) {
                _l.onBackClick();
            }
        } else if (v == checkNetworkErr) {
            checkNetworkDetail();
        }

    }

    private void checkNetworkDetail() {
        if (android.os.Build.VERSION.SDK_INT > 10) {
            Intent intent = new Intent(Settings.ACTION_SETTINGS);
            getContext().startActivity(intent);
        } else {
            Intent intent = new Intent(Settings.ACTION_WIRELESS_SETTINGS);
            getContext().startActivity(intent);
        }

    }

    public void setOnBackClickListener(OnBackClickListener l) {
        this._l = l;
    }

    public void setRightView(View v) {
        @SuppressWarnings("deprecation")
        LinearLayout.LayoutParams params =
                new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.FILL_PARENT);
        rightCustomLayout.addView(v, params);
    }

    public void showNetworkErr() {
//		networkErrLayout.setVisibility(View.VISIBLE);
    }

    public void dismissNetworkErr() {
//		networkErrLayout.setVisibility(View.GONE);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // TODO Auto-generated method stub
        return true;
    }

    public interface OnBackClickListener {
        void onBackClick();
    }

}
