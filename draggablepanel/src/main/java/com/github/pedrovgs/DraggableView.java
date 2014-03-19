package com.github.pedrovgs;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import com.nineoldandroids.view.ViewHelper;

/**
 * Class created to extends a ViewGroup and simulate the YoutubeLayoutComponent
 *
 * @author "Pedro Vicente Gómez Sánchez"
 */
class DraggableView extends RelativeLayout {

    /*
     * Constants
     */

    private static final String LOGTAG = "DraggableView";

    private static final int DEFAULT_SCALE_FACTOR = 2;
    private static final int DRAG_VIEW_MARGIN_RIGHT = 30;
    private static final int DRAG_VIEW_MARGIN_BOTTOM = 30;
    private static final float SLIDE_TOP = 0f;
    private static final float SLIDE_BOTTOM = 1f;
    private static final int MINIMUM_DY_FOR_VERTICAL_DRAG = 10;
    private static final int MINIMUN_DX_FOR_HORIZONTAL_DRAG = 20;


    /*
     * Attributes
     */

    private ViewGroup dragView;
    private ViewGroup secondView;

    private FragmentManager fragmentManager;
    private ViewDragHelper viewDragHelper;

    private int lastActionMotionEvent = -1;

    private int scaleFactor = DEFAULT_SCALE_FACTOR;


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

    public void setFragmentManager(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
    }

    public void attachTopFragment(Fragment topFragment) {
        addFragmentToView(R.id.dragView, topFragment);
    }

    public void attachBottomFragment(Fragment bottomFragment) {
        addFragmentToView(R.id.secondView, bottomFragment);
    }

    public void setScaleFactor(int scaleFactor) {
        this.scaleFactor = scaleFactor;
    }

    private void addFragmentToView(final int viewId, final Fragment fragment) {
        fragmentManager.beginTransaction().replace(viewId, fragment).commit();
    }

    private void initializeView() {
        viewDragHelper = ViewDragHelper.create(this, 1f, new DragPanelCallback());
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        if (!isInEditMode()) {
            dragView = (ViewGroup) findViewById(R.id.dragView);
            secondView = (ViewGroup) findViewById(R.id.secondView);
        }
    }


    @Override
    public void computeScroll() {
        if (!isInEditMode() && viewDragHelper.continueSettling(true)) {
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }


    private void changeDragViewPosition() {
        ViewHelper.setPivotX(dragView, dragView.getWidth() - getDragViewMarginRight());
        ViewHelper.setPivotY(dragView, dragView.getHeight() - getDragViewMarginBottom());
    }

    private void changeSeondViewPosition() {
        ViewHelper.setY(secondView, dragView.getTop() + dragView.getHeight());
    }

    private int getDragViewMarginRight() {
        return DRAG_VIEW_MARGIN_RIGHT;
    }

    private int getDragViewMarginBottom() {
        return DRAG_VIEW_MARGIN_BOTTOM;
    }

    private void changeDragViewScale() {
        ViewHelper.setScaleX(dragView, 1 - getVerticalDragOffset() / scaleFactor);
        ViewHelper.setScaleY(dragView, 1 - getVerticalDragOffset() / scaleFactor);
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

    private void changeDragViewViewAlpha() {
        ViewHelper.setAlpha(dragView, 1 - getHorizontalDragOffset());
    }

    private float getHorizontalDragOffset() {
        return (float) Math.abs(dragView.getLeft()) / (float) getWidth();
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

        if (viewDragHelper.smoothSlideViewTo(dragView, 0, y)) {
            ViewCompat.postInvalidateOnAnimation(this);
            return true;
        }
        return false;
    }

    private void closeToRight() {
        if (viewDragHelper.smoothSlideViewTo(dragView, dragView.getWidth(), getHeight() - dragView.getHeight())) {
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    private void closeToLeft() {
        if (viewDragHelper.smoothSlideViewTo(dragView, -dragView.getWidth(), getHeight() - dragView.getHeight())) {
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    private boolean isMinimized() {
        return isDragViewAtBottom() && isDragViewAtRight();
    }

    private boolean isMaximized() {
        return isDragViewAtTop();
    }

    private boolean isNextToLeftBound() {
        return (dragView.getRight() - getDragViewMarginRight()) < getWidth() / 2;
    }

    private boolean isNextToRightBound() {
        return (dragView.getLeft() - getDragViewMarginRight()) > getWidth() * 0.25;
    }

    private boolean isDragViewAtTop() {
        return dragView.getTop() == 0;
    }

    private boolean isDragViewAtRight() {
        return dragView.getRight() >= (getWidth() - MINIMUN_DX_FOR_HORIZONTAL_DRAG);
    }

    private boolean isDragViewAtBottom() {
        return dragView.getTop() == (getHeight() - dragView.getHeight());
    }

    public void setTopViewHeight(final int topFragmentHeight) {
        LayoutParams layoutParams = (LayoutParams) dragView.getLayoutParams();
        layoutParams.height = topFragmentHeight;
        dragView.setLayoutParams(layoutParams);
    }

    /*
     * DragPanelCallback
     */

    private class DragPanelCallback extends ViewDragHelper.Callback {

        @Override
        public void onViewPositionChanged(View changedView, int left, int top, int dx, int dy) {
            if (isDragViewAtBottom()) {
                changeDragViewViewAlpha();
            } else {
                changeDragViewScale();
                changeDragViewPosition();
                changeSecondViewAlpha();
                changeSeondViewPosition();
                changeBackgroundAlpha();
            }
            invalidate();
        }

        @Override
        public void onViewReleased(View releasedChild, float xvel, float yvel) {
            super.onViewReleased(releasedChild, xvel, yvel);
            if (!isDragViewAtBottom()) {
                if (isHeaderAboveTheMiddle()) {
                    maximize();
                } else {
                    minimize();
                }
            } else {
                if (isNextToLeftBound()) {
                    closeToLeft();
                } else if (isNextToRightBound()) {
                    closeToRight();
                } else {
                    minimize();
                }
            }
        }

        @Override
        public boolean tryCaptureView(View view, int pointerId) {
            return view == dragView;
        }

        @Override
        public int clampViewPositionHorizontal(View child, int left, int dx) {
            int newLeft = 0;
            if (((isMinimized() && (dx > MINIMUN_DX_FOR_HORIZONTAL_DRAG || dx < -MINIMUN_DX_FOR_HORIZONTAL_DRAG))) || (isDragViewAtBottom())) {
                newLeft = left;
            }
            return newLeft;
        }


        @Override
        public int clampViewPositionVertical(View child, int top, int dy) {
            int newTop;
            if (isMinimized() && (dy >= MINIMUM_DY_FOR_VERTICAL_DRAG || dy >= -MINIMUM_DY_FOR_VERTICAL_DRAG) || (!isMinimized() && !isDragViewAtBottom())) {
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
        Log.d(LOGTAG, "onInterceptTouchEvent " + action);

        if ((action != MotionEvent.ACTION_DOWN)) {
            Log.d(LOGTAG, "ACTION_DOWN");
            viewDragHelper.cancel();
            return super.onInterceptTouchEvent(ev);
        }

        if (action == MotionEvent.ACTION_CANCEL || action == MotionEvent.ACTION_UP) {
            Log.d(LOGTAG, "ACTION_CANCEL || ACTION_UP");
            viewDragHelper.cancel();
            return false;
        }

        final float x = ev.getX();
        final float y = ev.getY();
        boolean interceptTap = false;

        switch (action) {
            case MotionEvent.ACTION_DOWN:
                interceptTap = viewDragHelper.isViewUnder(dragView, (int) x, (int) y);
                break;
            case MotionEvent.ACTION_MOVE:
                interceptTap = false;
        }

        return viewDragHelper.shouldInterceptTouchEvent(ev) || interceptTap;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        Log.d(LOGTAG, "onTouchEvent");
        viewDragHelper.processTouchEvent(ev);
        boolean dragViewTouched = isViewHit(dragView, (int) ev.getX(), (int) ev.getY()) && ev.getAction() == MotionEvent.ACTION_UP && lastActionMotionEvent != MotionEvent.ACTION_MOVE;
        if (dragViewTouched) {
            propagateTouchEventToChildViews(dragView);
            return false;
        }
        boolean secondViewTouched = isViewHit(secondView, (int) ev.getX(), (int) ev.getY()) && ev.getAction() == MotionEvent.ACTION_UP && lastActionMotionEvent != MotionEvent.ACTION_MOVE;
        if (secondViewTouched && !dragViewTouched) {
            propagateTouchEventToChildViews(secondView);
        }
        lastActionMotionEvent = ev.getAction();
        return true;
    }

    private void propagateTouchEventToChildViews(ViewGroup viewGroup) {
        for (int i = 0; i < viewGroup.getChildCount(); i++) {
            View view = viewGroup.getChildAt(i);
            view.performClick();
        }
    }

    private boolean isViewHit(View view, int x, int y) {
        Log.d(LOGTAG, "isViewHit");
        int[] viewLocation = new int[2];
        view.getLocationOnScreen(viewLocation);
        int[] parentLocation = new int[2];
        this.getLocationOnScreen(parentLocation);
        int screenX = parentLocation[0] + x;
        int screenY = parentLocation[1] + y;
        return screenX >= viewLocation[0] && screenX < viewLocation[0] + view.getWidth() &&
                screenY >= viewLocation[1] && screenY < viewLocation[1] + view.getHeight();
    }


}