package com.computerdmaintenance.ui.view;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

/**
 * @ClassName: DiagnosisMonitorView<BR>
 * @Describe：诊断显示<BR>
 * @Author: zhuxunkang
 * @Extends：<BR>
 * @Version:1.0
 * @date:2016-4-25 下午3:38:08
 */
public class DiagnosisMonitorView extends View {
    /**
     * 上下文
     */
    private Context mContext;

    public DiagnosisMonitorView(Context context, AttributeSet attrs,
                                int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        init();

    }

    public DiagnosisMonitorView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
        // TODO Auto-generated constructor stub
    }

    public DiagnosisMonitorView(Context context) {
        this(context, null);
        // TODO Auto-generated constructor stub
    }

    /**
     * 方法名：init<BR>
     * 此方法描述的是： 初始化
     */
    private void init() {
        // TODO Auto-generated method stub

    }

    /**
     * 绘制图形
     */
    @Override
    protected void onDraw(Canvas canvas) {


        canvas.drawColor(0xff12b7f5);
        super.onDraw(canvas);
    }

}
