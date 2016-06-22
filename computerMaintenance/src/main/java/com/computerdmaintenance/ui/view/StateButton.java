package com.computerdmaintenance.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import com.computerdmaintenance.R;

/**
 * @ClassName:StateButton <BR>
 * @Describe：自定义按钮<BR>
 * @Author: zhuxunkang
 * @Extends：<BR>
 * @Version:1.0
 * @date:2016-3-12 下午4:40:06
 */

public class StateButton extends TextView implements View.OnClickListener {

    private boolean currentState;
    private OnStateChangeListener _l;
    private int[] drawables = {R.drawable.switch_off, R.drawable.switch_on};

    public StateButton(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
        initView(context);
    }

    public StateButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
        initView(context);
    }

    public StateButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        // TODO Auto-generated constructor stub
        initView(context);
    }

    private void initView(Context context) {
        setOnClickListener(this);
        setBackgroundResource(drawables[0]);
    }

    public void setOnStateChangeListener(OnStateChangeListener l) {
        _l = l;
    }

    public void setDrawable(int[] drawables) {
        this.drawables = drawables;
    }

    public boolean getState() {
        return currentState;
    }

    public void setState(boolean state) {
        currentState = state;
        changeState();
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        currentState = !currentState;
        changeState();
        if (_l != null) {
            _l.onStateChange(this, currentState);
        }
    }

    private void changeState() {
        if (currentState) {
            setBackgroundResource(drawables[1]);
        } else {
            setBackgroundResource(drawables[0]);
        }
    }

    public interface OnStateChangeListener {
        void onStateChange(StateButton v, boolean state);
    }

}
