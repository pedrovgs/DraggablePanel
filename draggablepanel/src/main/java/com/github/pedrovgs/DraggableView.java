package com.github.pedrovgs;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
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
public class DraggableView extends RelativeLayout {

    /*
     * Constants
     */

    private static final String LOGTAG = "DraggableView";

    private static final int DEFAULT_SCALE_FACTOR = 2;
    private static final float DEFAULT_TOP_VIEW_HEIGHT = 200;
    private static final int DEFAULT_TOP_FRAGMENT_MARGIN = 30;
    private static final float SLIDE_TOP = 0f;
    private static final float SLIDE_BOTTOM = 1f;
    private static final int MINIMUM_DY_FOR_VERTICAL_DRAG = 10;
    private static final int MINIMUN_DX_FOR_HORIZONTAL_DRAG = 20;


    /*
     * Attributes
     */

    private View dragView;
    private View secondView;

    private FragmentManager fragmentManager;
    private ViewDragHelper viewDragHelper;

    private DraggableListener listener;

    private int lastActionMotionEvent = -1;

    private float xScaleFactor = DEFAULT_SCALE_FACTOR;
    private float yScaleFactor = DEFAULT_SCALE_FACTOR;
    private float topViewHeight;
    private float topFragmentMarginRight = DEFAULT_TOP_FRAGMENT_MARGIN;
    private float topFragmentMarginBottom = DEFAULT_TOP_FRAGMENT_MARGIN;
    private float initialMotionX;
    private float initialMotionY;
    private int dragViewId;
    private int secondViewId;


    /*
     * Constructors
     */

    public DraggableView(Context context) {
        super(context);
        initializeView();
    }

    public DraggableView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initializeAttributes(attrs);
        initializeView();
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public DraggableView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initializeAttributes(attrs);
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

    public void setXTopViewScaleFactor(float xScaleFactor) {
        this.xScaleFactor = xScaleFactor;
    }

    public void setYTopViewScaleFactor(float yScaleFactor) {
        this.yScaleFactor = yScaleFactor;
    }

    public void setTopViewMarginRight(float topFragmentMarginRight) {
        this.topFragmentMarginRight = topFragmentMarginRight;
    }

    public void setTopViewMarginBottom(float topFragmentMarginBottom) {
        this.topFragmentMarginBottom = topFragmentMarginBottom;
    }

    private void addFragmentToView(final int viewId, final Fragment fragment) {
        fragmentManager.beginTransaction().replace(viewId, fragment).commit();
    }

    private void initializeView() {
        viewDragHelper = ViewDragHelper.create(this, 1f, new DragPanelCallback());
    }

    private void initializeAttributes(AttributeSet attrs) {
        TypedArray attributes = getContext().obtainStyledAttributes(attrs, R.styleable.draggable_view);
        this.dragViewId = attributes.getResourceId(R.styleable.draggable_view_top_view_id, R.id.dragView);
        this.secondViewId = attributes.getResourceId(R.styleable.draggable_view_bottom_view_id, R.id.secondView);
        this.topViewHeight = attributes.getDimension(R.styleable.draggable_view_top_view_height, DEFAULT_TOP_VIEW_HEIGHT);
        this.xScaleFactor = attributes.getFloat(R.styleable.draggable_view_top_view_x_scale_factor, DEFAULT_SCALE_FACTOR);
        this.yScaleFactor = attributes.getFloat(R.styleable.draggable_view_top_view_y_scale_factor, DEFAULT_SCALE_FACTOR);
        this.topFragmentMarginRight = attributes.getDimension(R.styleable.draggable_view_top_view_margin_right, DEFAULT_TOP_FRAGMENT_MARGIN);
        this.topFragmentMarginBottom = attributes.getDimension(R.styleable.draggable_view_top_view_margin_bottom, DEFAULT_TOP_FRAGMENT_MARGIN);

    }


    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        if (!isInEditMode()) {
            dragView = findViewById(dragViewId);
            secondView = findViewById(secondViewId);
            setTopViewHeight(topViewHeight);
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

    private float getDragViewMarginRight() {
        return topFragmentMarginRight;
    }

    private float getDragViewMarginBottom() {
        return topFragmentMarginBottom;
    }

    private void changeDragViewScale() {
        ViewHelper.setScaleX(dragView, 1 - getVerticalDragOffset() / xScaleFactor);
        ViewHelper.setScaleY(dragView, 1 - getVerticalDragOffset() / yScaleFactor);
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
        float alpha = 1 - getHorizontalDragOffset();
        if (alpha == 0) {
            alpha = 1;
        }
        ViewHelper.setAlpha(dragView, alpha);
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

    public void closeToRight() {
        if (viewDragHelper.smoothSlideViewTo(dragView, dragView.getWidth(), getHeight() - dragView.getHeight())) {
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    public void closeToLeft() {
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
        return dragView.getRight() == getWidth();
    }

    private boolean isDragViewAtBottom() {
        return dragView.getTop() == (getHeight() - dragView.getHeight());
    }

    public void setTopViewHeight(float topFragmentHeight) {
        this.topViewHeight = topFragmentHeight;
        LayoutParams layoutParams = (LayoutParams) dragView.getLayoutParams();
        layoutParams.height = (int) topFragmentHeight;
        dragView.setLayoutParams(layoutParams);
    }


    private void notifyPositionChangedToListener(int left, int top, int dx, int dy) {
        if (listener != null) {
            listener.onDraggableViewPositionChanged(left, top, dx, dy);
        }
    }

    private void notifyViewReleasedToListener(float xvel, float yvel) {
        if (listener != null) {
            listener.onDraggableViewReleased(xvel, yvel);
        }
    }

    public void setDraggableListener(DraggableListener listener) {
        this.listener = listener;
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
            notifyPositionChangedToListener(left, top, dx, dy);
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
            notifyViewReleasedToListener(xvel, yvel);
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
            int newTop = getHeight() - dragView.getHeight();
            if (isMinimized() && (dy >= MINIMUM_DY_FOR_VERTICAL_DRAG || dy >= -MINIMUM_DY_FOR_VERTICAL_DRAG) || (!isMinimized() && !isDragViewAtBottom())) {
                final int topBound = getPaddingTop();
                final int bottomBound = getHeight() - child.getHeight() - child.getPaddingBottom();

                newTop = Math.min(Math.max(top, topBound), bottomBound);
            }
            return newTop;
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        final int action = MotionEventCompat.getActionMasked(ev);

        if ((action != MotionEvent.ACTION_DOWN)) {
            viewDragHelper.cancel();
            return super.onInterceptTouchEvent(ev);
        }

        if (action == MotionEvent.ACTION_CANCEL || action == MotionEvent.ACTION_UP) {
            viewDragHelper.cancel();
            return false;
        }

        final float x = ev.getX();
        final float y = ev.getY();
        boolean interceptTap = false;

        switch (action) {
            case MotionEvent.ACTION_DOWN: {
                initialMotionX = x;
                initialMotionY = y;
                interceptTap = viewDragHelper.isViewUnder(dragView, (int) x, (int) y);
                break;
            }

            case MotionEvent.ACTION_MOVE: {
                final float adx = Math.abs(x - initialMotionX);
                final float ady = Math.abs(y - initialMotionY);
                final int slop = viewDragHelper.getTouchSlop();
                if (ady > slop && adx > ady) {
                    viewDragHelper.cancel();
                    return false;
                }
            }
        }

        return viewDragHelper.shouldInterceptTouchEvent(ev) || interceptTap;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        viewDragHelper.processTouchEvent(ev);
        int action = ev.getAction();

        final float x = ev.getX();
        final float y = ev.getY();

        boolean isHeaderViewUnder = viewDragHelper.isViewUnder(dragView, (int) x, (int) y);
        boolean isHeaderViewHit = isViewHit(dragView, (int) x, (int) y);
        boolean isDescViewHit = isViewHit(secondView, (int) x, (int) y);

        if (dragView instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) dragView;
            for (int i = 0; i < viewGroup.getChildCount(); i++) {
                viewGroup.getChildAt(i).dispatchTouchEvent(ev);
            }
        } else if (action == MotionEvent.ACTION_UP && lastActionMotionEvent != MotionEvent.ACTION_MOVE) {
            dragView.performClick();
        }
        lastActionMotionEvent = action;
        return isHeaderViewUnder && isHeaderViewHit || isDescViewHit;
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