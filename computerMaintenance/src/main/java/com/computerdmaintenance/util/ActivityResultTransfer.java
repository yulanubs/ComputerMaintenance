package com.computerdmaintenance.util;

import android.content.Intent;

import java.util.Stack;

public enum ActivityResultTransfer {
    INSTANCE;

    private Stack<OnActivityResultCallBack> callBackCache = new Stack<OnActivityResultCallBack>();
    private OnActivityResultCallBack callBack;

    public void registerActivityResultCallback(OnActivityResultCallBack callback) {
        if (this.callBack != null) {
            callBackCache.push(this.callBack);
        }
        this.callBack = callback;
    }

    public void unregisterActivityResultCallback(OnActivityResultCallBack callback) {

        if (this.callBack == callback) {
            this.callBack = null;
            if (callBackCache.size() > 0) {
                this.callBack = callBackCache.pop();
            }
        } else {
            if (callBackCache.size() > 0) {
                callBackCache.remove(callback);
            }
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (callBack != null) {
            callBack.onActivityResult4SingleInstance(requestCode, resultCode, data);
        }
    }

    public void release() {
        callBackCache.clear();
        callBack = null;
    }
}
