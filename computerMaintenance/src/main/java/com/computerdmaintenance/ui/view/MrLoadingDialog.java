package com.computerdmaintenance.ui.view;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.computerdmaintenance.R;

public class MrLoadingDialog extends AlertDialog {
    public AnimationDrawable animationDrawable;
    protected String Message;

    private ImageView imageView;
    private TextView textView;


    public MrLoadingDialog(Context context, int theme, String message) {
        super(context, theme);
        Message = message;
    }

    public MrLoadingDialog(Context context, String message) {
        super(context);
        Message = message;
    }

	/*@Override
    protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.uledialogloading);
		

		ImageView imageView = (ImageView) findViewById(R.id.loading_bar);
		imageView.setBackgroundResource(R.anim.loadinganim_40_40);
		animationDrawable = (AnimationDrawable) imageView.getBackground();

		// animationDrawable.setOneShot(false);
		// animationDrawable.stop();
		// animationDrawable.start();
		// handler.postDelayed(runnable, 50);
	}*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.flight_toastbackground);

        imageView = (ImageView) findViewById(R.id.customertoast_icon);
        imageView.setBackgroundResource(R.anim.loadinganim_40_40);
        animationDrawable = (AnimationDrawable) imageView.getBackground();

        // animationDrawable.setOneShot(false);
        // animationDrawable.stop();
        // animationDrawable.start();
        // handler.postDelayed(runnable, 50);

        //add by xqq
        setCanceledOnTouchOutside(false);
    }

	
	/*public Handler handler = new Handler();
    public Runnable runnable = new Runnable() {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			animationDrawable.setOneShot(false);
			animationDrawable.stop();
			animationDrawable.start();
		}
	};*/

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        animationDrawable.start();
        super.onWindowFocusChanged(hasFocus);
    }

    public void setMsg(String message2) {
        // TODO Auto-generated method stub
        textView.setText(Message);
        textView.postInvalidate();
    }

    public void setMsg(int icon) {
        // TODO Auto-generated method stub
        imageView.setBackgroundResource(icon);
        imageView.postInvalidate();
    }
}