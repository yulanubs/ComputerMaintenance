package com.computerdmaintenance.ui.component;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * @ClassName:Search_List_ItemView <BR>
 * @Describe：发现View<BR>
 * @Author: 朱勋康
 * @Extends：<BR>
 * @Version:1.0
 * @date:2016-4-16 下午8:28:24
 */
public class Search_List_ItemView extends GridView {

    public Search_List_ItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
    }

    public Search_List_ItemView(Context context, AttributeSet attrs,
                                int defStyle) {
        super(context, attrs, defStyle);
        // TODO Auto-generated constructor stub
    }

    public Search_List_ItemView(Context context) {
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