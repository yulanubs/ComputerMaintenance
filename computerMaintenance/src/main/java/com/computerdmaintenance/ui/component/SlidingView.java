package com.computerdmaintenance.ui.component;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Scroller;

public class SlidingView extends ViewGroup {
    public static final int STATE_CENTER = 0;
    public static final int STATE_LEFT = 1;
    public static final int STATE_RIGHT = 2;
    private static final int SNAP_VELOCITY = 1000;
    public boolean mIsBeingDragged;
    public boolean viewPageOnTouch = false;
    private FrameLayout mContainer;
    private Scroller mScroller;
    private VelocityTracker mVelocityTracker;
    private int mTouchSlop;
    private int currentState;
    private float mLastMotionX;
    private float mLastMotionY;
    private View leftView;
    private View rightView;
    private boolean hasLeftView;
    private boolean hasRightView;
    private boolean leftViewSlideOpenEnable;
    private boolean leftViewSlideCloseEnable;
    private boolean rightViewSlideOpenEnable;

    // private static final String TAG = "SlidingView";
    private boolean rightViewSlideCloseEnable;
    private OnSlidingViewScrollChangedLinstener linstener;

    public SlidingView(Context context) {
        super(context);
        init();
    }

    public SlidingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SlidingView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mContainer.measure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        final int width = r - l;
        final int height = b - t;
        mContainer.layout(0, 0, width, height);
    }

    private void init() {
        mContainer = new FrameLayout(getContext());
        mContainer.setBackgroundColor(Color.WHITE);
        mScroller = new Scroller(getContext());
        mTouchSlop = ViewConfiguration.get(getContext()).getScaledTouchSlop();
        @SuppressWarnings("deprecation")
        LayoutParams layoutParams = new LayoutParams(LayoutParams.FILL_PARENT,
                LayoutParams.FILL_PARENT);
        super.addView(mContainer, layoutParams);
    }

    public void setRightViewSlideOpenEnable(boolean enable) {
        rightViewSlideOpenEnable = enable;
    }

    public void setRightViewSlideCloseEnable(boolean enable) {
        rightViewSlideCloseEnable = enable;
    }

    public void setLeftViewSlideOpenEnable(boolean enable) {
        leftViewSlideOpenEnable = enable;
    }

    public void setLeftViewSlideCloseEnable(boolean enable) {
        leftViewSlideCloseEnable = enable;
    }

    public void setOnSlidingViewScrollChangedLinstener(
            OnSlidingViewScrollChangedLinstener l) {
        linstener = l;
    }

    public int getCurrentState() {
        return currentState;
    }

    public void setView(View v) {
        if (mContainer.getChildCount() > 0) {
            mContainer.removeAllViews();
        }
        @SuppressWarnings("deprecation")
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(
                LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT);
        mContainer.addView(v, layoutParams);
    }

    @Override
    public void scrollTo(int x, int y) {
        super.scrollTo(x, y);
        postInvalidate();
    }

    @Override
    public void computeScroll() {
        if (!mScroller.isFinished()) {
            if (mScroller.computeScrollOffset()) {
                int oldX = getScrollX();
                int oldY = getScrollY();
                int x = mScroller.getCurrX();
                int y = mScroller.getCurrY();
                if (oldX != x || oldY != y) {
                    scrollTo(x, y);
                }
                // Keep on drawing until the animation has finished.
                invalidate();
            } else {
                clearChildrenCache();
            }
        } else {
            clearChildrenCache();
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        /*if(!mIsBeingDragged){
            return true;
		}*/

        final int action = ev.getAction();
        final float x = ev.getX();
        final float y = ev.getY();

        switch (action) {
            case MotionEvent.ACTION_DOWN:
                mLastMotionX = x;
                mLastMotionY = y;
            /*if (viewPageOnTouch) {
				mIsBeingDragged = true;
				viewPageOnTouch = false;
			} else {*/
                mIsBeingDragged = currentState != STATE_CENTER;
                // }

                // Log.d(TAG, "onInterceptTouchEvent: ACTION_DOWN");
                break;

            case MotionEvent.ACTION_MOVE:
                // Log.d(TAG, "onInterceptTouchEvent: ACTION_MOVE");
                final float dx = x - mLastMotionX;

                final float xDiff = Math.abs(dx);
                final float yDiff = Math.abs(y - mLastMotionY);
                if (xDiff > mTouchSlop && xDiff > yDiff) {
                    // mIsBeingDragged = true;
                    mLastMotionX = x;
                }
                break;

        }
        return mIsBeingDragged;
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent ev) {

		/*if(!mIsBeingDragged){
			return true;
		}*/

        if (mVelocityTracker == null) {
            mVelocityTracker = VelocityTracker.obtain();
        }
        mVelocityTracker.addMovement(ev);

        final int action = ev.getAction();
        final float x = ev.getX();
        final float y = ev.getY();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                // Log.d(TAG, "onTouchEvent: ACTION_DOWN");
                if (mIsBeingDragged) {
                    if (!mScroller.isFinished()) {
                        mScroller.abortAnimation();
                    }
                    mLastMotionX = x;
                    mLastMotionY = y;
                    if (getScrollX() == -getLeftViewWidth()
                            && mLastMotionX < getLeftViewWidth()) {
                        return false;
                    }

                    if (getScrollX() == getRightViewWidth()
                            && mLastMotionX > getLeftViewWidth()) {
                        return false;
                    }
                }

                break;
            case MotionEvent.ACTION_MOVE:
                // Log.d(TAG, "onTouchEvent: ACTION_MOVE");
                if (mIsBeingDragged) {
                    enableChildrenCache();

                    switch (currentState) {
                        case STATE_CENTER:
                            handleCenterState(ev);
                            break;

                        case STATE_LEFT:
                            handleLeftState(ev);
                            break;

                        case STATE_RIGHT:
                            handleRightState(ev);
                            break;
                    }
                }
                break;
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:
                // Log.d(TAG, "onTouchEvent: ACTION_UP");
                if (mIsBeingDragged) {
                    final VelocityTracker velocityTracker = mVelocityTracker;
                    velocityTracker.computeCurrentVelocity(1000);
                    int velocityX = (int) velocityTracker.getXVelocity();
                    velocityX = 0;
                    // Log.e("ad", "velocityX == " + velocityX);
                    int oldScrollX = getScrollX();
                    int dx = 0;
                    if (oldScrollX < 0) {
                        if (oldScrollX < -getLeftViewWidth() / 2
                                || velocityX > SNAP_VELOCITY) {
                            dx = -getLeftViewWidth() - oldScrollX;
                            int oldState = currentState;
                            currentState = STATE_LEFT;
                            if (linstener != null && oldState == STATE_CENTER) {
                                linstener.OnLeftViewOpen();
                            }
                        } else if (oldScrollX >= -getLeftViewWidth() / 2
                                || velocityX < -SNAP_VELOCITY) {
                            dx = -oldScrollX;
                            int oldState = currentState;
                            currentState = STATE_CENTER;
                            if (linstener != null && oldState == STATE_LEFT) {
                                linstener.OnLeftViewClose();
                            }
                        }
                    } else {
                        if (oldScrollX > getRightViewWidth() / 2
                                || velocityX < -SNAP_VELOCITY) {
                            dx = getRightViewWidth() - oldScrollX;
                            int oldState = currentState;
                            currentState = STATE_RIGHT;
                            if (linstener != null && oldState == STATE_CENTER) {
                                linstener.OnRightViewOpen();
                            }
                        } else if (oldScrollX <= getRightViewWidth() / 2
                                || velocityX > SNAP_VELOCITY) {
                            dx = -oldScrollX;
                            int oldState = currentState;
                            currentState = STATE_CENTER;
                            if (linstener != null && oldState == STATE_RIGHT) {
                                linstener.OnRightViewClose();
                            }
                        }
                    }

                    smoothScrollTo(dx);
                    clearChildrenCache();

                }

                break;

        }
        if (mVelocityTracker != null) {
            mVelocityTracker.recycle();
            mVelocityTracker = null;
        }

        return true;
    }

    private void handleCenterState(MotionEvent ev) {
        // Log.d(TAG, "handleCenterState");
        float x = ev.getX();
        final float deltaX = mLastMotionX - x;
        final float alreadyScrollX = getScrollX();
        float scrollX = deltaX + alreadyScrollX;
        if (scrollX > 0 && rightViewSlideOpenEnable) {
            float bound = getRightViewWidth();
            if (scrollX > bound) {
                scrollX = bound;
            }
        } else if (scrollX < 0 && leftViewSlideOpenEnable) {
            float bound = -getLeftViewWidth();
            if (scrollX < bound) {
                scrollX = bound;
            }
        } else {
            return;
        }
        scrollTo((int) scrollX, getScrollY());
    }

    private void handleLeftState(MotionEvent ev) {
        // Log.d(TAG, "handleLeftState");
        float x = ev.getX();
        final float deltaX = mLastMotionX - x;
        final float alreadyScrollX = getScrollX();
        float scrollX = deltaX + alreadyScrollX;
        if (deltaX > 0 && leftViewSlideCloseEnable) {
            float bound = 0;
            if (scrollX > bound) {
                scrollX = bound;
            }
        } else if (deltaX < 0) {
            float bound = -getLeftViewWidth();
            if (scrollX < bound) {
                scrollX = bound;
            }
        } else {
            return;
        }
        scrollTo((int) scrollX, getScrollY());
    }

    private void handleRightState(MotionEvent ev) {
        // Log.d(TAG, "handleRightState");
        float x = ev.getX();
        final float deltaX = mLastMotionX - x;
        final float alreadyScrollX = getScrollX();
        float scrollX = deltaX + alreadyScrollX;
        if (deltaX < 0 && rightViewSlideCloseEnable) {
            float bound = 0;
            if (scrollX < bound) {
                scrollX = bound;
            }
        } else if (deltaX > 0) {
            float bound = getRightViewWidth();
            if (scrollX > bound) {
                scrollX = bound;
            }
        } else {
            return;
        }
        scrollTo((int) scrollX, getScrollY());
    }

    private int getLeftViewWidth() {
        if (leftView == null) {
            return 0;
        }
        return leftView.getWidth();
    }

    private int getRightViewWidth() {
        if (rightView == null) {
            return 0;
        }
        return rightView.getWidth();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    public View getRightView() {
        return rightView;
    }

    public void setRightView(View mRightView) {
        if (mRightView != null) {
            hasRightView = true;
            rightViewSlideOpenEnable = true;
            rightViewSlideCloseEnable = true;
        } else {
            hasRightView = false;
            rightViewSlideOpenEnable = false;
            rightViewSlideCloseEnable = false;
        }
        this.rightView = mRightView;
    }

    public View getLeftView() {
        return leftView;
    }

    public void setLeftView(View mLeftView) {
        if (mLeftView != null) {
            hasLeftView = true;
            leftViewSlideOpenEnable = true;
            leftViewSlideCloseEnable = true;
        } else {
            hasLeftView = false;
            leftViewSlideOpenEnable = false;
            leftViewSlideCloseEnable = false;
        }
        this.leftView = mLeftView;
    }

    void toggle() {
        int leftWidth = leftView.getWidth();
        int oldScrollX = getScrollX();
        if (oldScrollX == 0) {
            smoothScrollTo(-leftWidth);
        } else if (oldScrollX == -leftWidth) {
            smoothScrollTo(leftWidth);
        }
    }

    public void showLeftView() {
        if (!hasLeftView) {
            return;
        }
        int leftWidth = leftView.getWidth();
        int oldScrollX = getScrollX();
        if (oldScrollX == 0) {
            smoothScrollTo(-leftWidth);
            currentState = STATE_LEFT;
            if (linstener != null) {
                linstener.OnLeftViewOpen();
            }
        }
    }

    public void showRightView() {
        if (!hasRightView) {
            return;
        }
        int rightWidth = rightView.getWidth();
        int oldScrollX = getScrollX();
        if (oldScrollX == 0) {
            smoothScrollTo(rightWidth);
            currentState = STATE_RIGHT;
            if (linstener != null) {
                linstener.OnRightViewOpen();
            }
        }
    }

    public void closeLeftView() {
        if (!hasLeftView) {
            return;
        }
        int leftWidth = leftView.getWidth();
        int oldScrollX = getScrollX();
        if (oldScrollX == -leftWidth) {
            smoothScrollTo(leftWidth);
            currentState = STATE_CENTER;
            if (linstener != null) {
                linstener.OnLeftViewClose();
            }
        }
    }

    public void closeRightView() {
        if (!hasRightView) {
            return;
        }
        int rightWidth = rightView.getWidth();
        int oldScrollX = getScrollX();
        if (oldScrollX == rightWidth) {
            smoothScrollTo(-rightWidth);
            currentState = STATE_CENTER;
            if (linstener != null) {
                linstener.OnRightViewClose();
            }
        }
    }

    void smoothScrollTo(int dx) {
        int duration = 500;
        int oldScrollX = getScrollX();
        mScroller.startScroll(oldScrollX, getScrollY(), dx, getScrollY(),
                duration);
        invalidate();
    }

    void enableChildrenCache() {
        final int count = getChildCount();
        for (int i = 0; i < count; i++) {
            final View layout = getChildAt(i);
            layout.setDrawingCacheEnabled(true);
        }
    }

    void clearChildrenCache() {
        final int count = getChildCount();
        for (int i = 0; i < count; i++) {
            final View layout = getChildAt(i);
            layout.setDrawingCacheEnabled(false);
        }
    }

    public interface OnSlidingViewScrollChangedLinstener {
        void OnLeftViewOpen();

        void OnLeftViewClose();

        void OnRightViewOpen();

        void OnRightViewClose();
    }
}