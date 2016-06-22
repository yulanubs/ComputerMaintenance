package com.computerdmaintenance.ui.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.os.Process;
import android.provider.MediaStore;
import android.provider.Settings;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

import com.baidu.navisdk.BNaviEngineManager.NaviEngineInitListener;
import com.computerdmaintenance.ComputerMaintenanceApplication;
import com.computerdmaintenance.R;
import com.computerdmaintenance.ui.Action;
import com.computerdmaintenance.ui.Base;
import com.computerdmaintenance.ui.OpenGpsDialog;
import com.computerdmaintenance.ui.OpenGpsDialog.OpenGpsButtonListener;
import com.computerdmaintenance.ui.WGTContainer;
import com.computerdmaintenance.ui.WGTContainer.OnContainerEventListener;
import com.computerdmaintenance.ui.WGTFactory;
import com.computerdmaintenance.ui.component.MrFooter;
import com.computerdmaintenance.ui.component.MrFooter.OnFootTabChooseListener;
import com.computerdmaintenance.ui.wgt.BaseWgt;
import com.computerdmaintenance.ui.wgt.DiagnosisWgt;
import com.computerdmaintenance.ui.wgt.IndexWgt;
import com.computerdmaintenance.ui.wgt.LoginWgt;
import com.computerdmaintenance.ui.wgt.PersonalCenterWgt;
import com.computerdmaintenance.ui.wgt.PrivateMessagWgt;
import com.computerdmaintenance.ui.wgt.SearchWgt;
import com.computerdmaintenance.ui.wgt.event.MrEvent;
import com.computerdmaintenance.ui.wgt.event.MrEventBarCode;
import com.computerdmaintenance.ui.wgt.event.MrEventContactPick;
import com.computerdmaintenance.ui.wgt.event.MrEventNetwork;
import com.loudmaintenance.util.ActivityResultTransfer;
import com.loudmaintenance.util.Consts;
import com.loudmaintenance.util.MrLog;
import com.loudmaintenance.util.OnActivityResultCallBack;

import java.io.Serializable;

//import com.tom.ule.ui.wgt.PersonalCenterNewEx;

/**
 * @ClassName:WorkingActivity <BR>
 * @Describe：<BR>
 * @Author: zhuxunkang
 * @Extends：首页<BR>
 * @Version:1.0
 * @date:2016-3-12 下午9:12:42
 */
@SuppressWarnings({"deprecation"})
public class WorkingActivity extends SlideActivity implements
        OnFootTabChooseListener, OnContainerEventListener,
        OnActivityResultCallBack {
    private static final String TAG = "WorkingActivity";
    public CodeListerner mCodeListerner;
    private ComputerMaintenanceApplication mapp;
    // 个人中心
    // private PersonalCenter personalCenter;
    private boolean canExit = false;// 是否退出，true是，false不是
    private boolean isClickTwice = false;
    private Handler handler;

    @SuppressWarnings("unused")
    private boolean mIsEngineInitSuccess = false;
    @SuppressWarnings("unused")
    private NaviEngineInitListener mNaviEngineInitListener = new NaviEngineInitListener() {
        public void engineInitSuccess() {
            mIsEngineInitSuccess = true;
        }

        public void engineInitStart() {
        }

        public void engineInitFail() {
        }
    };

    public static final boolean isOPen(final Context context) {
        LocationManager locationManager = (LocationManager) context
                .getSystemService(Context.LOCATION_SERVICE);
        boolean gps = locationManager
                .isProviderEnabled(LocationManager.GPS_PROVIDER);
        boolean network = locationManager
                .isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        return gps || network;
    }

    @SuppressWarnings("unused")
    private String getSdcardDir() {
        if (Environment.getExternalStorageState().equalsIgnoreCase(
                Environment.MEDIA_MOUNTED)) {
            return Environment.getExternalStorageDirectory().toString();
        }
        return null;
    }

    public void gotoJump(Action act) {
        jump(act);
    }

    public void imageViewVisibility(int visibility) {
        imageView.setVisibility(visibility);
    }

    public void footerVisibility(int visibility) {
        footer.setVisibility(visibility);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        ActivityResultTransfer.INSTANCE.registerActivityResultCallback(this);
        mapp = (ComputerMaintenanceApplication) getApplication();
        mapp.wk = WorkingActivity.this;
        footer.setVisibility(View.VISIBLE);
        footer.setOnFootTabChooseListener(this);
        container.setOnContainerEventListener(this);
        imageView.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                imageView.setVisibility(View.GONE);
            }
        });

        // BaiduNaviManager.getInstance().initEngine(this, getSdcardDir(),
        // mNaviEngineInitListener, new LBSAuthManagerListener() {
        // @Override
        // public void onAuthResult(int status, String msg) {
        // String str = null;
        // if (0 == status) {
        // str = "key校验成功!";
        // } else {
        // str = "key校验失败, " + msg;
        // Toast.makeText(WorkingActivity.this, str,
        // Toast.LENGTH_LONG).show();
        // }
        //
        // }
        // });
//		checkGPSState();
    }

    private void checkGPSState() {
        LocationManager mLocationManager = (LocationManager) this
                .getSystemService(Context.LOCATION_SERVICE);
        if (mLocationManager
                .isProviderEnabled(android.location.LocationManager.GPS_PROVIDER)) {
            return;
        }

        if (!isOPen(getApplicationContext())) {
            final OpenGpsDialog dialog = new OpenGpsDialog(WorkingActivity.this);
            dialog.setIClickButtonListener(new OpenGpsButtonListener() {
                @Override
                public void clickOk() {
                    // TODO Auto-generated method stub
                    dialog.dismiss();
                    Intent intent = new Intent(
                            Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                    startActivityForResult(intent, 0);
                }

                @Override
                public void clickCancel() {
                    // TODO Auto-generated method stub
                    dialog.dismiss();
                }
            });
            dialog.show();
        }
    }

    @Override
    public void onIndexTab() {
        // TODO Auto-generated method stub
        // UleMobileLog.onClick(this, "", "底部导航", "主页",
        // uleappcontext.domainUser.userID);
        if (container != null) {
            container.switchView(IndexWgt.class);
        }

    }

    @Override
    public void onCategoryTab() {
        // TODO Auto-generated method stub
        // UleMobileLog.onClick(this, "", "底部导航", "发现",
        // uleappcontext.domainUser.userID);
        if (container != null) {
            container.switchView(SearchWgt.class);
        }

    }

    @Override
    public void onCartTab() {
        //诊断
        if (container != null) {
            container.switchView(DiagnosisWgt.class);
        }


    }

    @Override
    public void onPersonalTab() {
        // TODO Auto-generated method stub
        if (container != null) {
            container.switchView(PersonalCenterWgt.class);
        }

    }

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        if (container != null) {
            container.onResume();
        }
//		if (personalCenter != null) {
//			personalCenter.onResume();
//		}
        if (!isFirst) {
            isFirst = true;
        } else {
            if (isSlied) {
                // UleMobileLog.onPageChange(this,
                // uleappcontext.domainUser.userID,
                // personalCenter.getPageName(),
                // PostLifeApplication.MSGID, "");
                // } else {
                // UleMobileLog.onPageChange(this,
                // uleappcontext.domainUser.userID, container
                // .getCurrentWgt().getPageName(),
                // PostLifeApplication.MSGID, "");
            }
        }
        // footer.setCartCount(PostLifeApplication.cartNumber);
    }

    @Override
    public void onMoreTab() {
        //发现
        if (container != null) {
            container.switchView(SearchWgt.class);
        }
    }

    private void goIndex() {
        //首页
        container.switchView(IndexWgt.class);
        footer.setChoseTab(MrFooter.TAB_TAG_INDEX);
    }

    public int getFootHeight() {
        if (footer != null) {
            return footer.getFootHeight();
        } else {
            return 0;
        }
    }

    @Override
    public void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        WGTFactory.INSTANCE.release();
        ActivityResultTransfer.INSTANCE.unregisterActivityResultCallback(this);
        Process.killProcess(Process.myPid());
    }

    @Override
    protected boolean goByAction() {
        // TODO Auto-generated method stub
        return super.goByAction();
    }

    @Override
    protected void normalLaunch() {
        // TODO Auto-generated method stub
        goIndex();
    }

    @Override
    public void onTitleBackClick() {
        // TODO Auto-generated method stub
        if (!container.back()) {
            finish();
        }
    }

    @Override
    public void onWgtChange(BaseWgt wgt) {
        // TODO Auto-generated method stub
//		if (wgt.getClass().equals(IndexWgt.class)) {
//			footer.setChoseTab(UleFooter.TAB_TAG_INDEX);
//		} else if (wgt.getClass().equals(Search.class)) {
//			footer.setChoseTab(UleFooter.TAB_TAG_SEARCH);
//		} else if (wgt.getClass().equals(PersonalCenter.class)) {
//			footer.setChoseTab(UleFooter.TAB_TAG_PERSONAL);
//		} else if (wgt.getClass().equals(Activities.class)) {
//			footer.setChoseTab(UleFooter.TAB_TAG_CATEGORY);
//		} else if (wgt.getClass().equals(Cart.class)) {
//			footer.setChoseTab(UleFooter.TAB_TAG_CART);
//		} else if (wgt.getClass().equals(RouteActivity.class)) {
//			footer.setChoseTab(UleFooter.TAB_TAG_ROUTE);
//		} else {
//			footer.setChoseTab(UleFooter.TAB_TAG_OTHER);
//		}
    }


    public void setPersonPromot(int visibility) {
        footer.setPersonPromot(visibility);
    }

    public void setGetCouponPhone() {
        container.onRefreshGetCoupon();
    }

    // public void dismissCartNumber() {
    // PostLifeApplication.cartNumber = 0;
    // footer.setCartCount(0);
    // }

//	private void refreshCartNumbers(MrEvent event) {
//		footer.showCarItems(((UleEventShoppingCart) event).total);
//	}

    @Override
    public void onBackToEnd() {
        // TODO Auto-generated method stub
        finish();
    }

    @SuppressLint("HandlerLeak")
    @Override
    public void onBackPressed() {
        // TODO Auto-generated method stub
        if (isSlied) {
            restore();
            return;
        }
        if (container.back()) {

        } else {
            if (!canExit) {
                Toast.makeText(WorkingActivity.this, R.string.exit_prompt,
                        Toast.LENGTH_SHORT).show();
                canExit = true;
                if (handler == null)
                    handler = new Handler() {
                        @Override
                        public void handleMessage(Message msg) {
                            // TODO Auto-generated method stub
                            super.handleMessage(msg);
                            canExit = false;
                            isClickTwice = false;
                        }
                    };
                handler.sendEmptyMessageDelayed(0, 2500);
            } else {
                if (isClickTwice)
                    super.onBackPressed();
            }
        }
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        if (keyCode == KeyEvent.KEYCODE_BACK && canExit) {
            isClickTwice = true;
        }
        return super.onKeyUp(keyCode, event);
    }

    @Override
    public void onActivityResult4SingleInstance(int requestCode,
                                                int resultCode, Intent data) {
        // TODO Auto-generated method stub
        switch (requestCode) {
            case Consts.Intents.REQUEST_CONTACT_FORM_CONTACT:
                MrEventContactPick contactPick = new MrEventContactPick(data);
                container.alertUleEvent(contactPick);
                break;
            case Consts.Intents.REQUEST_MORE_READBARCODE:
                if (resultCode == RESULT_OK) {
//				onBarcode(data);

                }
                break;
            case Consts.Intents.REQUEST_READULECARDBARCODE: // 扫描ULE 卡条形码
                dispatchBarCodeResult(requestCode, data);
                break;
            default:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);
//		if (ShareSdk.mTencent != null) {
//			ShareSdk.mTencent.onActivityResult(requestCode, resultCode, data);
//		}


        // 扫描二维码/条码回传
        if (requestCode == Consts.Intents.REQUEST_MORE_READBARCODE && resultCode == RESULT_OK) {
            if (data != null) {

                String result = data.getStringExtra(Consts.Intents.DECODED_CONTENT_KEY);
                if (null != result && !result.equals("")) {
//                    UtilsToast.myToast(getApplicationContext(), result);
                    mCodeListerner.getCode(result);

                }
            }
        }


    }

    private void dispatchBarCodeResult(int requestCode, Intent data) {
        MrEvent barcodeResult = new MrEventBarCode(requestCode,
                data.getStringExtra("SCAN_RESULT"));
        container.alertUleEvent(barcodeResult);
    }

    protected void pageSwitch(Class<? extends Base> clsName, Bundle bn) {
        if (this.isFinishing()) {
            return;
        }
        Intent i = new Intent(this, clsName);
        if (bn != null) {
            i.putExtras(bn);
        }
        startActivity(i);
    }

    @SuppressWarnings("unused")
    private boolean isAblumPicture(Uri uri) {
        boolean isAlbum = false;
        String path = getPath(uri);
        if (!TextUtils.isEmpty(path)) {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            Bitmap bitmap = BitmapFactory.decodeFile(path, options);
            isAlbum = options.outHeight > 0;
        }
        return isAlbum;
    }

    private String getPath(Uri uri) {
        String path = uri.getPath();
        String[] projection = {MediaStore.Images.Media.DATA};
        Cursor cursor = managedQuery(uri, projection, null, null, null);
        if (cursor == null) {
            return path;
        }
        int column_index = cursor
                .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        path = cursor.getString(column_index);
        return path;
    }

//	private void onBarcode(Intent data) {
//		if (data == null) {
//			return;
//		}
//		String codeDetail = data
//				.getStringExtra(com.google.zxing.client.android.Intents.Scan.RESULT);
//		if (codeDetail == null || codeDetail.equals("")) {
//			return;
//		}
//		QrCodeHelper.processBarcode(this, codeDetail);
//	}


    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        MrLog.debug(TAG, "onNewIntent");
        if (intent.hasExtra(Consts.Intents.INTENT_LAUNCH_ACTION)) {
            MrLog.debug(TAG, "onNewIntent INTENT_LAUNCH_ACTION");
            Serializable obj = intent
                    .getSerializableExtra(Consts.Intents.INTENT_LAUNCH_ACTION);
            if (obj instanceof Action) {
                if (isSlied) {
                    restore();
                }
                MrLog.debug(TAG, "onNewIntent ACTION");
                jumpInner((Action) obj);
            }
        }
    }

    @Override
    protected void notifyNetworkChange(boolean connected) {
        // TODO Auto-generated method stub
        MrEventNetwork e = new MrEventNetwork(connected);
        if (container != null) {
            container.alertUleEvent(e);
        }
    }

    @Override
    public void onRouteTab() {
        if (appcontext.islogin()) {

            container.switchView(PrivateMessagWgt.class);
        } else {
            container.switchView(LoginWgt.class);
        }
    }

    @Override
    public boolean onUleEvent(WGTContainer container, MrEvent event) {
        // TODO Auto-generated method stub
        return false;
    }

    public void setmCodeListerner(CodeListerner mCodeListerner) {
        this.mCodeListerner = mCodeListerner;
    }

    public interface CodeListerner {
        void getCode(String code);

    }
}