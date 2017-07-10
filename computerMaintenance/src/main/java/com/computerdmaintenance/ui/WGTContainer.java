package com.computerdmaintenance.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import com.computerdmaintenance.ComputerMaintenanceApplication;
import com.computerdmaintenance.ui.wgt.BaseWgt;
import com.computerdmaintenance.ui.wgt.IndexWgt;
import com.computerdmaintenance.ui.wgt.LoginWgt;
import com.computerdmaintenance.ui.wgt.NetworkErr;
import com.computerdmaintenance.ui.wgt.event.MrEvent;
import com.computerdmaintenance.util.MrLog;

import java.util.Stack;

public class WGTContainer extends RelativeLayout {

    public Action action = null;
    protected WGTFactory wgtfactory = WGTFactory.INSTANCE;
    private Stack<com.computerdmaintenance.ui.wgt.BaseWgt> wgtstack = new Stack<BaseWgt>();
    private BaseWgt currentView = null;
    private ComputerMaintenanceApplication app = null;
    private LayoutParams params =
            new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
    private OnContainerEventListener containerEventListener;

    public WGTContainer(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
        initView(context);
    }

    public WGTContainer(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
        initView(context);
    }

    public WGTContainer(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        // TODO Auto-generated constructor stub
        initView(context);
    }

    public void alertUleEvent(MrEvent event) {
        MrLog.error("alertUleEvent", "event: " + event.Event);
        if (onUleEvent(event)) {
            return;
        }
        dispatchUleEvent(event);
    }

    private void initView(Context context) {
        app = (ComputerMaintenanceApplication) context.getApplicationContext();
    }

    public void onResume() {
        if (currentView != null) {
            currentView.onResume();
        }
    }

    public void onRefreshGetCoupon() {
        if (currentView != null) {
            currentView.onRefreshGetCoupon();
        }
    }

    public BaseWgt switchView(Class<? extends BaseWgt> wgtClass) {
        return switchView(wgtClass.getName());
    }

    public BaseWgt switchView(String wgtName) {
        if (!ComputerMaintenanceApplication.networkAlive && !wgtName.equals(NetworkErr.class.getName())) {
            wgtName = NetworkErr.class.getName();
            switchView(wgtName);
            return null;
        }
        BaseWgt wgt = wgtfactory.createWGT(getContext(), wgtName);
        if (wgt == null) {
            return null;
        }
        if (wgt == currentView || (currentView != null && wgt.getClass().equals(currentView.getClass()) && !isMulti(wgt))) {
            return currentView;
        }
        if (wgt != null) {
            if (wgt.needLogin() && !app.islogin()) {
                goLogin();
                return null;
            }
            if (currentView != null && (currentView.getLifeCycle() & BaseWgt.TRACE) == BaseWgt.TRACE) {
                wgtstack.push(currentView);
            }
            if (wgt instanceof IndexWgt) {
                wgtstack.clear();
            }
            replaceView(wgt);
        }
        return wgt;
    }

    private void goLogin() {
        switchView(LoginWgt.class);
    }

    private void replaceView(BaseWgt wgt) {
        removeAllViews();
        wgtfactory.wgtRemoved(currentView, this);
        addView(wgt, params);
        /*if (uleapp.networkAlive){
            wgt.unShowNetworkErr();
		}else{
			wgt.showNetworkErr();
		}*/
        currentView = wgt;
        wgtfactory.wgtAttached(wgt, this);
        if (containerEventListener != null) {
            containerEventListener.onWgtChange(wgt);
        }
    }

    public void setOnContainerEventListener(OnContainerEventListener l) {
        containerEventListener = l;
    }


    public boolean hasWgt(Class<? extends BaseWgt> wgt) {
        for (int i = 0; i < wgtstack.size(); i++) {
            if (wgt.getName().equals(wgtstack.get(i).getClass().getName())) {
                return true;
            }
        }
        return false;
    }

    private boolean onUleEvent(MrEvent event) {
        MrLog.error("onUleEvent", "event: " + event.Event);
        if (containerEventListener != null) {
            if (containerEventListener.onUleEvent(this, event)) {
                return true;
            }
        }

        if (currentView != null && currentView.onUleEvent(event, currentView, this)) {
            MrLog.error("onUleEvent++", "onUleEvent: " + event.Event);
            return true;
        }

        return false;
    }

    private void dispatchUleEvent(MrEvent event) {
        Stack<BaseWgt> clone = cloneWgtStack();
        for (int i = 0; i < clone.size(); i++) {
            BaseWgt wgt = clone.get(i);
            wgt.onUleEvent(event, wgt, this);
        }
        clone.clear();
        clone = null;
    }

    private Stack<BaseWgt> cloneWgtStack() {
        Stack<BaseWgt> clone = new Stack<BaseWgt>();
        for (int i = 0; i < wgtstack.size(); i++) {
            BaseWgt wgt = wgtstack.get(i);
            clone.add(wgt);
        }
        return clone;
    }

    public boolean back() {
        if (currentView != null && currentView.onBackKeyDown()) {
            return true;
        }
        if (wgtstack.size() > 0) {
            BaseWgt wgt = wgtstack.pop();
            replaceView(wgt);
            return true;
        }
        return false;
    }

    public void onTitleBackClick() {
        if (containerEventListener != null) {
            containerEventListener.onTitleBackClick();
        }
    }

    public void clearStack() {
        wgtstack.clear();
    }

    private boolean isMulti(BaseWgt wgt) {
        return (wgt.getLifeCycle() & BaseWgt.MULTI) == BaseWgt.MULTI;
    }

    public void expireWgt(BaseWgt wgt) {
        wgtstack.remove(wgt);
    }

    public void stepBack() {
        if (!back() && containerEventListener != null) {
            containerEventListener.onBackToEnd();
        }
    }

    public BaseWgt getCurrentWgt() {
        return currentView;
    }

    public interface OnContainerEventListener {
        void onTitleBackClick();

        void onWgtChange(BaseWgt wgt);

        boolean onUleEvent(WGTContainer container, MrEvent event);

        void onBackToEnd();
    }

}
