package com.computerdmaintenance.ui.activity;

import android.app.AlertDialog;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.computerdmaintenance.ComputerMaintenanceApplication;
import com.computerdmaintenance.R;
import com.computerdmaintenance.ui.Action;
import com.computerdmaintenance.ui.Base;
import com.computerdmaintenance.ui.WGTContainer;
import com.computerdmaintenance.ui.component.MrFooter;
import com.computerdmaintenance.ui.wgt.BaseWgt;
import com.computerdmaintenance.ui.wgt.SlidingMain;
import com.loudmaintenance.util.Consts;
import com.loudmaintenance.util.MrLog;
import com.loudmaintenance.util.UtilTools;

public abstract class SlideActivity extends Base {

    public static int D;
    protected WGTContainer container;
    protected SlidingMain mainSlider;
    protected MrFooter footer;
    protected boolean isSlied;
    protected ImageView imageView;
    protected ComputerMaintenanceApplication appcontext;
    private int screenWidth;
    private LinearLayout left;
    private LinearLayout right;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acty_new_main_layout);
        mainSlider = (SlidingMain) findViewById(R.id.main_slider);
        appcontext = (ComputerMaintenanceApplication) getApplicationContext();
        screenWidth = getResources().getDisplayMetrics().widthPixels;
        int screenWidthDip = UtilTools.px2dip(this, screenWidth);
        double s = 265d / (double) screenWidthDip;
        if (s < 0.84) {
            s = 0.84;
        }
        D = (int) (screenWidth * s);

        setSlideViews();
        if (!goByAction()) {
            normalLaunch();
        }
        setImmerseLayout();
    }

    protected void setSlideViews() {
        View center = LayoutInflater.from(this).inflate(
                R.layout.view_slide_center_layout, null);
        container = (WGTContainer) center.findViewById(R.id.main_container);
        footer = (MrFooter) center.findViewById(R.id.main_footer);
        footer.setVisibility(View.GONE);

        imageView = (ImageView) center
                .findViewById(R.id.main_mobile_recharge_guide);

		/*Bitmap newBitmap = UtilTools.createSizeImage(BitmapFactory
                .decodeResource(getResources(), R.drawable.mobile_recharge_guide),
				UtilTools.getDisplayWidth(uleappcontext), UtilTools
						.getDisplayHeight(uleappcontext));
		imageView.setImageBitmap(newBitmap);*/

        left = new LinearLayout(this);
        right = new LinearLayout(this);

        mainSlider.addViews(left, center, right);
    }

    public boolean slide(View v, boolean slideDirection) {
        if (slideDirection) {
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(D,
                    LinearLayout.LayoutParams.MATCH_PARENT);
            left.removeAllViews();
            left.addView(v, params);
            mainSlider.postDelayed(new Runnable() {

                @Override
                public void run() {
                    // TODO Auto-generated method stub
                    mainSlider.showLeftView();
                }
            }, 100);
        } else {
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    screenWidth - 3 * (screenWidth - D) / 2,
                    LinearLayout.LayoutParams.MATCH_PARENT);
            right.removeAllViews();
            right.addView(v, params);
            mainSlider.postDelayed(new Runnable() {

                @Override
                public void run() {
                    // TODO Auto-generated method stub
                    mainSlider.showRightView();
                }
            }, 100);
        }
        isSlied = true;
        return isSlied;
    }

    public boolean restore() {
        mainSlider.getSlidingView().closeLeftView();
        mainSlider.getSlidingView().closeRightView();
        isSlied = false;
        return !isSlied;
    }

    @Override
    public void onBackPressed() {
        // TODO Auto-generated method stub
        if (isSlied) {
            restore();
            return;
        }
        if (container.back()) {

        } else {
            super.onBackPressed();
        }
    }

    protected abstract void normalLaunch();

    @Override
    protected void jumpInner(Action act) {
        // TODO Auto-generated method stub
        MrLog.debug("SlideActivity", "jumpInner INTENT_LAUNCH_ACTION");
        if (act.wgtClass.equals(Consts.Actions.DIALOG_CLASS_NAME)) {
            normalLaunch();
            String msg = (String) act.parameters.get(Consts.Actions.PARAM_MSG);
            if (msg != null && !msg.equals("")) {
                new AlertDialog.Builder(this).setTitle(R.string.hello).setMessage(msg)
                        .setNeutralButton(R.string.confirm, null).create()
                        .show();
            }
        } else {
            BaseWgt wgt = container.switchView(act.wgtClass);
            if (wgt != null) {
                wgt.setLaunchParams(act.parameters);
            }
            if (isSlied) {
                restore();
            }
        }
    }

    protected void setImmerseLayout() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = getWindow();
            window.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        // TODO Auto-generated method stub
        return super.dispatchTouchEvent(ev);
    }

    public WGTContainer getContainer() {
        return container;
    }
}