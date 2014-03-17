package com.github.pedrovgs;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.util.Log;
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

    private static final int SCALE_FACTOR = 2;
    private static final int DRAG_VIEW_MARGIN_RIGHT = 30;
    private static final int DRAG_VIEW_MARGIN_BOTTOM = 30;
    private static final boolean RIGHT_DIRECTION = true;
    private static final float SLIDE_TOP = 0f;
    private static final float SLIDE_BOTTOM = 1f;
    private static final int MINIMUM_DY_FOR_VERTICAL_DRAG = 10;
    private static final int MINIMUN_DX_FOR_HORIZONTAL_DRAG = 10;


    /*
     * Attributes
     */

    private View dragView;
    private View secondView;

    private ViewDragHelper viewDragHelper;

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
        viewDragHelper = ViewDragHelper.create(this, 1f, new DragPanelCallback());
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        dragView = findViewById(R.id.dragView);
        secondView = findViewById(R.id.secondView);
    }


    @Override
    public void computeScroll() {
        if (viewDragHelper.continueSettling(true)) {
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
        return dragView.getTop() / getVierticalDragRange();
    }

    private float getVierticalDragRange() {
        return getHeight() - dragView.getHeight();
    }

    private boolean isHeaderAboveTheMiddle() {
        Log.d(LOGTAG, "isHeaderAboveTheMiddle");
        int viewHeight = getHeight();
        float viewHeaderY = ViewHelper.getY(dragView) + (dragView.getHeight() / 2);
        return viewHeaderY < (viewHeight * 0.5f);
    }

    public void maximize() {
        Log.d(LOGTAG, "maximize");
        smoothSlideTo(SLIDE_TOP);
    }

    public void minimize() {
        Log.d(LOGTAG, "minimize");
        smoothSlideTo(SLIDE_BOTTOM);
    }


    private boolean smoothSlideTo(float slideOffset) {
        final int topBound = getPaddingTop();
        int y = (int) (topBound + slideOffset * getVierticalDragRange());

        if (viewDragHelper.smoothSlideViewTo(dragView, dragView.getLeft(), y)) {
            ViewCompat.postInvalidateOnAnimation(this);
            return true;
        }
        return false;
    }

    private boolean isMinimized() {
        return isDragViewAtBottom() && isDragViewAtRight();
    }

    private boolean isMaximized() {
        return isDragViewAtTop();
    }

    private boolean isDragViewAtTop() {
        return dragView.getTop() == 0;
    }

    private boolean isDragViewAtRight() {
        return dragView.getRight() >= (getWidth() - MINIMUN_DX_FOR_HORIZONTAL_DRAG);
    }

    private boolean isDragViewAtBottom() {
        Log.e("DEPURAR", "TOP " + dragView.getTop());
        Log.e("DEPURAR", "HEIGHT - HEIGHT " + (getHeight() - dragView.getHeight()));
        return dragView.getTop() == (getHeight() - dragView.getHeight());
    }

    /*
     * DragPanelCallback
     */

    private class DragPanelCallback extends ViewDragHelper.Callback {

        @Override
        public void onViewPositionChanged(View changedView, int left, int top, int dx, int dy) {
            if (isDragViewAtBottom()) {
                super.onViewPositionChanged(changedView, left, top, dx, dy);
            } else {
                changeDragViewScale();
                changeDragViewXPosition();
                changeSecondViewAlpha();
                changeBackgroundAlpha();
            }
            invalidate();
        }

        @Override
        public void onViewReleased(View releasedChild, float xvel, float yvel) {
            super.onViewReleased(releasedChild, xvel, yvel);
            /*if (isHeaderAboveTheMiddle()) {
                maximize();
            } else {
                minimize();
            }*/
        }

        @Override
        public boolean tryCaptureView(View view, int pointerId) {
            return view == dragView;
        }

        @Override
        public int clampViewPositionHorizontal(View child, int left, int dx) {
            int newLeft = 0;
            if (((isMinimized() && (dx > MINIMUN_DX_FOR_HORIZONTAL_DRAG || dx < -MINIMUN_DX_FOR_HORIZONTAL_DRAG))) || (isDragViewAtBottom())) {
                Log.e("DEPURAR", "-> LEFT " + left);
                Log.e("DEPURAR", "-> dx " + dx);
                newLeft = left;
            }
            return newLeft;
        }


        @Override
        public int clampViewPositionVertical(View child, int top, int dy) {
            int newTop = 0;
            if (isMinimized() && dy >= MINIMUM_DY_FOR_VERTICAL_DRAG || (!isMinimized() && !isDragViewAtBottom())) {
                final int topBound = getPaddingTop();
                final int bottomBound = getHeight() - child.getHeight() - child.getPaddingBottom();

                newTop = Math.min(Math.max(top, topBound), bottomBound);
            } else {
                newTop = getHeight() - dragView.getHeight();
            }
            return newTop;
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        final int action = MotionEventCompat.getActionMasked(ev);
        if (action == MotionEvent.ACTION_CANCEL || action == MotionEvent.ACTION_UP) {
            viewDragHelper.cancel();
            return false;
        }
        return viewDragHelper.shouldInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        viewDragHelper.processTouchEvent(ev);
        return true;
    }

}