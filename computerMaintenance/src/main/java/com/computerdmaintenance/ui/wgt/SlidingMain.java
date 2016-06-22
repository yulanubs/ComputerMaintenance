package com.computerdmaintenance.ui.wgt;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;

import com.computerdmaintenance.ui.component.SlidingView;
import com.computerdmaintenance.ui.component.SlidingView.OnSlidingViewScrollChangedLinstener;

public class SlidingMain extends RelativeLayout {
    private SlidingView mSlidingView;
    private View mLeftView;
    private View mRightView;
    private boolean hasLeftView = false;
    private boolean hasRightView = false;

    public SlidingMain(Context context) {
        super(context);
    }

    public SlidingMain(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SlidingMain(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void addViews(View left, View center, View right) {
        setLeftView(left);
        setRightView(right);
        setCenterView(center);
    }

    public void setLeftView(View view) {
        if (view == mLeftView) {
            return;
        }
        if (mLeftView != null) {
            removeView(mLeftView);
        }
        if (mSlidingView != null) {
            mSlidingView.setLeftView(view);
        }
        if (view == null) {
            hasLeftView = false;
            return;
        }
        hasLeftView = true;
        @SuppressWarnings("deprecation")
        LayoutParams behindParams = new LayoutParams(LayoutParams.WRAP_CONTENT,
                LayoutParams.FILL_PARENT);
        behindParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        addView(view, behindParams);
        mLeftView = view;
    }

    public void setRightView(View view) {
        if (view == mRightView) {
            return;
        }
        if (mRightView != null) {
            removeView(mRightView);
        }
        if (mSlidingView != null) {
            mSlidingView.setRightView(view);
        }
        if (view == null) {
            hasRightView = false;
            return;
        }
        hasRightView = true;
        @SuppressWarnings("deprecation")
        LayoutParams behindParams = new LayoutParams(LayoutParams.WRAP_CONTENT,
                LayoutParams.FILL_PARENT);
        behindParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);

        addView(view, behindParams);
        mRightView = view;
    }

    public void setCenterView(View view) {
        @SuppressWarnings("deprecation")
        LayoutParams aboveParams = new LayoutParams(LayoutParams.FILL_PARENT,
                LayoutParams.FILL_PARENT);
        mSlidingView = new SlidingView(getContext());
        addView(mSlidingView, aboveParams);
        mSlidingView.setView(view);
        mSlidingView.invalidate();
        mSlidingView.setLeftView(mLeftView);
        mSlidingView.setRightView(mRightView);
    }

    public SlidingView getSlidingView() {
        return mSlidingView;
    }

    public void showLeftView() {
        if (hasLeftView) {
            mSlidingView.showLeftView();
        }
    }

    public void showRightView() {
        if (hasRightView) {
            mSlidingView.showRightView();
        }
    }

    public void setOnSlidingViewLinstener(
            OnSlidingViewScrollChangedLinstener l) {
        if (mSlidingView != null)
            mSlidingView.setOnSlidingViewScrollChangedLinstener(l);
    }
}