package com.computerdmaintenance.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.computerdmaintenance.R;
import com.computerdmaintenance.adapter.ClassKeChengAdapter;
import com.computerdmaintenance.ui.component.FeatureListView;

import com.computerdmaintenance.util.BindView;
import com.computerdmaintenance.util.Consts;
import com.mr.cm.common.base.domain.Business;
import com.mr.cm.common.base.domain.ClassCurriculum;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName:ClassRoomActivity <BR>
 * @Describe：<BR>
 * @Author: zhuxunkang
 * @Extends：<BR>
 * @Version:1.0
 * @date:2016-4-23 下午1:39:01
 */
public class ClassRoomActivity extends BaseActivity
         {
    /***/
    @BindView(id = R.id.ll_context)
    private LinearLayout ll_context;
    /**
     * 课程信息
     */
    private Business maBusiness;
//    /**
//     * 播放器
//     */
//    @BindView(id = R.id.player_view)
//    private MediaPlayerView playerView;
    /**
     * 课程简介布局
     */
    @BindView(id = R.id.ll_class_introduction, click = true)
    private LinearLayout ll_class_introduction;
    /**
     * 课程节数布局
     */
    @BindView(id = R.id.ll_class_curriculum, click = true)
    private LinearLayout ll_class_curriculum;
    /**
     * 课程缓存布局
     */
    @BindView(id = R.id.ll_class_cache, click = true)
    private LinearLayout ll_class_cache;
    /**
     * 课程分享布局
     */
    @BindView(id = R.id.ll_class_share, click = true)
    private LinearLayout ll_class_share;
    /**
     * 课程简介按钮
     */
    @BindView(id = R.id.tv_class_introduction)
    private TextView tv_class_introduction;
    /**
     * 课程节数按钮
     */
    @BindView(id = R.id.tv_class_curriculum)
    private TextView tv_class_curriculum;
    /**
     * 课程缓存按钮
     */
    @BindView(id = R.id.tv_class_cache)
    private TextView tv_class_cache;
    /**
     * 课程分享按钮
     */
    @BindView(id = R.id.tv_class_share)
    private TextView tv_class_share;
    /**
     * 课程简介内容
     */
    @BindView(id = R.id.ll_class_prospectuses)
    private LinearLayout ll_class_prospectuses;
    /**
     * 课程节数内容
     */
    @BindView(id = R.id.ll_curriculum)
    private LinearLayout ll_curriculum;

    /**
     * 课程节数列表
     */
    @BindView(id = R.id.fl_class_curriculum)
    private FeatureListView fl_class_curriculum;
    /**
     * 课程节数适配器
     */
    private ClassKeChengAdapter mKeChengAdapter;
    /**
     * 视频地址
     */
    private String path;
    /**
     * 被选中节数
     */
    private int Position = 0;
    private List<ClassCurriculum> mDatas;
    /**
     * 视频名称
     */
    private String voideTitle = "";
    /**
     * 视频播放模式
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.classroom_activity);
        Intent intent = getIntent();
        maBusiness = (Business) intent
                .getSerializableExtra(Consts.Actions.CLASSROOM_INFO);
        initBindView(ClassRoomActivity.this, this);
        // 初始化view
        intiView();

    }

    /**
     * 方法名：setViewListener<BR>
     * 此方法描述的是： 为View控件绑定事件监听器
     */
    private void setViewListener() {
        // 课程节数
        fl_class_curriculum.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                ClassCurriculum item = mKeChengAdapter.getItem(position);
                if (null != item) {

                    mKeChengAdapter.getmDatas().get(Position).isSelected = false;
                    mKeChengAdapter.getmDatas().get(position).isSelected = true;
                    Position = position;
                    mKeChengAdapter.notifyDataSetChanged();

                    path = item.getUrl();
//                    playerView.onResume();
//                    playerView.play(path, item.getTitle() + "");

                }

            }
        });

    }

    /**
     * 方法名：intiView<BR>
     * 此方法描述的是：初始化view
     */
    private void intiView() {
        mKeChengAdapter = new ClassKeChengAdapter(this,
                R.layout.class_kecheng_list_item, 0, app);
        fl_class_curriculum.setAdapter(mKeChengAdapter);
        initData();

        setViewListener();

    }

    /**
     * 方法名：initData<BR>
     * 此方法描述的是： 初始化数据
     */
    private void initData() {

        mDatas = new ArrayList<ClassCurriculum>();
        mDatas.add(new ClassCurriculum("100", "系统安装前的准备",
                "http://7xpara.com1.z0.glb.clouddn.com/外卖数据交互.mp4", "1", true));
        mDatas.add(new ClassCurriculum("100", "系统安装前的准备",
                "http://7xpara.com1.z0.glb.clouddn.com/第8讲%20了解BaaS.mp4", "2"));
        mDatas.add(new ClassCurriculum(
                "100",
                "系统安装前的准备",
                "http://7xpara.com1.z0.glb.clouddn.com/1.%20%E5%93%8D%E5%BA%94%E5%BC%8F%E5%B8%83%E5%B1%80%E4%BB%8B%E7%BB%8D.mp4",
                "3"));

        mKeChengAdapter.setmDatas(mDatas);
        mKeChengAdapter.notifyDataSetChanged();
        startPlayer("");

    }

    private void startPlayer(String url) {

//        playerView.setPlayerViewCallback(ClassRoomActivity.this);

        // path =
        // "http://7xpara.com1.z0.glb.clouddn.com/1.%20%E5%93%8D%E5%BA%94%E5%BC%8F%E5%B8%83%E5%B1%80%E4%BB%8B%E7%BB%8D.mp4";
        // File file = new File(Environment.getExternalStorageDirectory(),
        // "aa.mp4");
        if (null != mDatas && mDatas.size() > 0) {
            voideTitle = mDatas.get(0).getTitle();
            path = mDatas.get(0).getUrl();

        }
//        playerView.play(path, voideTitle);
        // Love.mp4
        // avitest.avi
        // flvtest.flv
        // mkvtest.mkv
        // rmvbtest.rmvb
        // tstest.ts
        // wmvtest.wmv

        // Log.d("eflake", file.getAbsolutePath());
        // playerView.play("http://maichang.kssws.ks-cdn.com/upload20150716161913.mp4");
    }











    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
//        playerView.onResume();
    }

    @Override
    protected void onDestroy() {

        Log.d("lixp", "VideoPlayerActivity ....onDestroy()......");
        super.onDestroy();

//        playerView.onDestroy();
    }

    @Override
    protected void onPause() {

        super.onPause();
//        playerView.onPause();
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.ll_class_introduction:
                ll_class_prospectuses.setVisibility(View.VISIBLE);
                ll_curriculum.setVisibility(View.GONE);
                ll_class_share.setBackgroundColor(0x00000000);
                ll_class_cache.setBackgroundColor(0x00000000);
                ll_class_introduction.setBackgroundColor(0xff337fb3);
                ll_class_curriculum.setBackgroundColor(0x00000000);

                break;
            case R.id.ll_class_curriculum:
                ll_class_prospectuses.setVisibility(View.GONE);
                ll_curriculum.setVisibility(View.VISIBLE);
                ll_class_share.setBackgroundColor(0x00000000);
                ll_class_cache.setBackgroundColor(0x00000000);
                ll_class_introduction.setBackgroundColor(0x00000000);
                ll_class_curriculum.setBackgroundColor(0xff337fb3);
                break;
            case R.id.ll_class_cache:
                ll_class_prospectuses.setVisibility(View.GONE);
                ll_curriculum.setVisibility(View.GONE);
                ll_class_cache.setBackgroundColor(0xff337fb3);
                ll_class_share.setBackgroundColor(0x00000000);
                ll_class_introduction.setBackgroundColor(0x00000000);
                ll_class_curriculum.setBackgroundColor(0x00000000);

                break;
            case R.id.ll_class_share:
                ll_class_prospectuses.setVisibility(View.GONE);
                ll_curriculum.setVisibility(View.GONE);
                ll_class_share.setBackgroundColor(0xff337fb3);
                ll_class_cache.setBackgroundColor(0x00000000);
                ll_class_introduction.setBackgroundColor(0x00000000);
                ll_class_curriculum.setBackgroundColor(0x00000000);
                break;

            default:
                break;
        }

    }






}
