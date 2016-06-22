package com.computerdmaintenance.ui.component;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * <p>推荐功能</p>
 */
public class InnovativeActivitiesView extends GridView {

    public InnovativeActivitiesView(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
    }

    public InnovativeActivitiesView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        // TODO Auto-generated constructor stub
    }

    public InnovativeActivitiesView(Context context) {
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