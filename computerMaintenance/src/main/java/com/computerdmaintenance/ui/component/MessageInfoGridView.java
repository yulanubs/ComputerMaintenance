package com.computerdmaintenance.ui.component;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * @ClassName: MessageInfoGridView<BR>
 * @Describe：私信View<BR>
 * @Author: 朱勋康
 * @Extends：ListView<BR>
 * @Version:1.0
 * @date:2016-4-17 下午12:51:27
 */
public class MessageInfoGridView extends ListView {

    public MessageInfoGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
    }

    public MessageInfoGridView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        // TODO Auto-generated constructor stub
    }

    public MessageInfoGridView(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
    }

    /**
     * 设置不滚动
     */
    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}