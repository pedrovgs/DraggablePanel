package com.github.pedrovgs;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;
import com.nineoldandroids.view.ViewHelper;

/**
 * Class created to extends a ViewGroup and simulate the YoutubeLayoutComponent
 *
 * @author "Pedro Vicente Gómez Sánchez"
 */
public class DraggableView extends RelativeLayout {

    /*
     * Constants
     */
    private static final String LOGTAG = "DraggableView";

    public static final int SCALE_FACTOR = 2;
    private static final int DRAG_VIEW_MARGIN_RIGHT = 30;
    private static final int DRAG_VIEW_MARGIN_BOTTOM = 30;
    private static final boolean RIGHT_DIRECTION = true;


    /*
     * Attributes
     */

    private View dragView;
    private View secondView;

    private ViewDragHelper dragHelper;

    /*
     * Constructors
     */

    public DraggableView(Context context) {
        super(context);
        initializeView();
    }

    public DraggableView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initializeView();
    }


    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public DraggableView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initializeView();
    }

    private void initializeView() {
        dragHelper = ViewDragHelper.create(this, 1f, new DragHelperCallback());
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        dragView = findViewById(R.id.dragView);
        secondView = findViewById(R.id.secondView);
    }


    @Override
    public void computeScroll() {
        if (dragHelper.continueSettling(true)) {
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    private void changeDragViewXPosition() {
        ViewHelper.setPivotX(dragView, dragView.getWidth() * getDirection() - getDragViewMarginRight());
        ViewHelper.setPivotY(dragView, dragView.getHeight() - getDragViewMarginBottom());
    }

    private float getDirection() {
        return RIGHT_DIRECTION ? 1 : 0;
    }

    private int getDragViewMarginRight() {
        return RIGHT_DIRECTION ? DRAG_VIEW_MARGIN_RIGHT : -DRAG_VIEW_MARGIN_RIGHT;
    }

    private int getDragViewMarginBottom() {
        return DRAG_VIEW_MARGIN_BOTTOM;
    }

    private void changeDragViewScale() {
        ViewHelper.setScaleX(dragView, 1 - getVerticalDragOffset() / SCALE_FACTOR);
        ViewHelper.setScaleY(dragView, 1 - getVerticalDragOffset() / SCALE_FACTOR);
    }

    private void changeBackgroundAlpha() {
        Drawable background = getBackground();
        if (background != null) {
            int newAlpha = (int) (100 * (1 - getVerticalDragOffset()));
            background.setAlpha(newAlpha);
        }
    }


    private void changeSecondViewAlpha() {
        ViewHelper.setAlpha(secondView, 1 - getVerticalDragOffset());
    }

    private float getVerticalDragOffset() {
        return dragView.getTop() / getDragRange();
    }

    private float getDragRange() {
        return getHeight() - dragView.getHeight();
    }

    /*
     * DragHelperCallback
     */

    private class DragHelperCallback extends ViewDragHelper.Callback {
        @Override
        public void onViewPositionChanged(View changedView, int left, int top, int dx, int dy) {
            changeDragViewScale();
            changeDragViewXPosition();
            changeSecondViewAlpha();
            changeBackgroundAlpha();
            invalidate();
        }

        @Override
        public boolean tryCaptureView(View view, int pointerId) {
            return view == dragView;
        }

        @Override
        public int clampViewPositionHorizontal(View child, int left, int dx) {
            return 0;
        }


        @Override
        public int clampViewPositionVertical(View child, int top, int dy) {
            final int topBound = getPaddingTop();
            final int bottomBound = getHeight() - child.getHeight() - child.getPaddingBottom();

            final int newTop = Math.min(Math.max(top, topBound), bottomBound);
            return newTop;
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        final int action = MotionEventCompat.getActionMasked(ev);
        if (action == MotionEvent.ACTION_CANCEL || action == MotionEvent.ACTION_UP) {
            dragHelper.cancel();
            return false;
        }
        return dragHelper.shouldInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        dragHelper.processTouchEvent(ev);
        return true;
    }

}