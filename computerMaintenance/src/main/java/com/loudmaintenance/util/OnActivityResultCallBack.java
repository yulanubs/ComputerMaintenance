package com.loudmaintenance.util;

import android.content.Intent;

public interface OnActivityResultCallBack {
    void onActivityResult4SingleInstance(int requestCode, int resultCode, Intent data);
}
