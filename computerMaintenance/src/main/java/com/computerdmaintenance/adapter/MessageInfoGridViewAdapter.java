package com.computerdmaintenance.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;

import com.computerdmaintenance.ComputerMaintenanceApplication;
import com.computerdmaintenance.R;
import com.computerdmaintenance.util.CircleImageTool;
import com.computerdmaintenance.util.ViewHolder;
import com.mr.cm.common.base.domain.MessageInfo;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

public class MessageInfoGridViewAdapter extends CommonAdapter<MessageInfo> {

    public MessageInfoGridViewAdapter(Context context, int layoutId,
                                      int radian, ComputerMaintenanceApplication app) {
        super(context, layoutId, radian, app);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void convert(ViewHolder holder, MessageInfo ben) {
        holder.setText(R.id.tv_user_name, ben.getUser_name())
                .setText(R.id.tv_new_chat, ben.getDescribe())
                .setText(R.id.tv_chat_time, ben.getChat_time())
        ;
        final ImageView mImageView = holder.getView(R.id.iv_userphoto);
        ImageLoader.getInstance().displayImage(app.config.IMAGE_SERVER + ben.getImgurl(), mImageView, app.option, new SimpleImageLoadingListener() {

            @Override
            public void onLoadingFailed(String imageUri, View view,
                                        FailReason failReason) {
                mImageView.setImageResource(R.drawable.plugin_camera_no_pictures);
            }

            @Override
            public void onLoadingComplete(String imageUri, View view,
                                          Bitmap loadedImage) {
                Bitmap circleBitmap = CircleImageTool.getCircleBitmap(loadedImage, mContext);
                mImageView.setImageBitmap(circleBitmap);
            }
        });

    }

}
