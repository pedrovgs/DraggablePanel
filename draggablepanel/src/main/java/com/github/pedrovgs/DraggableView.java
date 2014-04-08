/*
 * Copyright (C) 2014 Pedro Vicente Gómez Sánchez.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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

    private static final int DEFAULT_SCALE_FACTOR = 2;
    private static final float DEFAULT_TOP_VIEW_HEIGHT = -1;
    private static final int DEFAULT_TOP_FRAGMENT_MARGIN = 30;
    private static final float SLIDE_TOP = 0f;
    private static final float SLIDE_BOTTOM = 1f;
    private static final boolean DEFAULT_ENABLE_HORIZONTAL_ALPHA_EFECT = true;

    private View dragView;
    private View secondView;

    private FragmentManager fragmentManager;
    private ViewDragHelper viewDragHelper;

    private float xScaleFactor = DEFAULT_SCALE_FACTOR;
    private float yScaleFactor = DEFAULT_SCALE_FACTOR;
    private float topFragmentMarginRight = DEFAULT_TOP_FRAGMENT_MARGIN;
    private float topFragmentMarginBottom = DEFAULT_TOP_FRAGMENT_MARGIN;
    private float topViewHeight = DEFAULT_TOP_VIEW_HEIGHT;
    private int dragViewId;
    private int secondViewId;
    private boolean enableHorizontalAlphaEffect;
    private DraggableListener listener;

    private float initialMotionX;
    private float initialMotionY;
    private int lastActionMotionEvent = -1;

    private int lastTopPosition;
    private int lastLeftPosition;


    public DraggableView(Context context) {
        super(context);
        initializeView();
    }

    public DraggableView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initializeAttributes(attrs);
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public DraggableView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initializeAttributes(attrs);
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

    @Override
    public void computeScroll() {
        if (!isInEditMode() && viewDragHelper.continueSettling(true)) {
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    public void maximize() {
        smoothSlideTo(SLIDE_TOP);
        notifyMaximizeToListener();
    }

    public void minimize() {
        smoothSlideTo(SLIDE_BOTTOM);
        notifyMinimizeToListener();
    }

    public void closeToRight() {
        if (viewDragHelper.smoothSlideViewTo(dragView, dragView.getWidth(), getHeight() - dragView.getHeight())) {
            ViewCompat.postInvalidateOnAnimation(this);
            notifyCloseToRightListener();
        }
    }

    public void closeToLeft() {
        if (viewDragHelper.smoothSlideViewTo(dragView, -dragView.getWidth(), getHeight() - dragView.getHeight())) {
            ViewCompat.postInvalidateOnAnimation(this);
            notifyCloseToLeftListener();
        }
    }

    public boolean isMinimized() {
        return isDragViewAtBottom() && isDragViewAtRight();
    }

    public boolean isMaximized() {
        return isDragViewAtTop();
    }


    public void setTopViewHeight(float topFragmentHeight) {
        if (topFragmentHeight > 0) {
            this.topViewHeight = topFragmentHeight;
            LayoutParams layoutParams = (LayoutParams) dragView.getLayoutParams();
            layoutParams.height = (int) topFragmentHeight;
            dragView.setLayoutParams(layoutParams);
        }
    }

    public void setHorizontalAlphaEffectEnabled(boolean enableHorizontalAlphaEffect) {
        this.enableHorizontalAlphaEffect = enableHorizontalAlphaEffect;
    }

    public void setDraggableListener(DraggableListener listener) {
        this.listener = listener;
    }

    public boolean isClosedAtRight() {
        return dragView.getLeft() >= getWidth();
    }

    public boolean isClosedAtLeft() {
        return dragView.getRight() <= 0;
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

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        int topViewHeight = this.topViewHeight == DEFAULT_TOP_VIEW_HEIGHT ? dragView.getMeasuredHeight() : (int) this.topViewHeight;
        dragView.layout(lastLeftPosition, lastTopPosition, lastLeftPosition + dragView.getMeasuredWidth(), lastTopPosition + topViewHeight);
        secondView.layout(0, lastTopPosition + topViewHeight, right, lastTopPosition + bottom);
    }


    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        if (!isInEditMode()) {
            dragView = findViewById(dragViewId);
            secondView = findViewById(secondViewId);
            setTopViewHeight(topViewHeight);
            initializeView();
        }

    }

    void updateLastDragViewPosition(int lastTopPosition, int lastLeftPosition) {
        this.lastTopPosition = lastTopPosition;
        this.lastLeftPosition = lastLeftPosition;
    }

    void changeDragViewPosition() {
        ViewHelper.setPivotX(dragView, dragView.getWidth() - getDragViewMarginRight());
        ViewHelper.setPivotY(dragView, dragView.getHeight() - getDragViewMarginBottom());
    }

    void changeSeondViewPosition() {
        ViewHelper.setY(secondView, dragView.getTop() + dragView.getHeight());
    }


    void changeDragViewScale() {
        ViewHelper.setScaleX(dragView, 1 - getVerticalDragOffset() / xScaleFactor);
        ViewHelper.setScaleY(dragView, 1 - getVerticalDragOffset() / yScaleFactor);
    }

    void changeBackgroundAlpha() {
        Drawable background = getBackground();
        if (background != null) {
            int newAlpha = (int) (100 * (1 - getVerticalDragOffset()));
            background.setAlpha(newAlpha);
        }
    }


    void changeSecondViewAlpha() {
        ViewHelper.setAlpha(secondView, 1 - getVerticalDragOffset());
    }

    void changeDragViewViewAlpha() {
        if (enableHorizontalAlphaEffect) {
            float alpha = 1 - getHorizontalDragOffset();
            if (alpha == 0) {
                alpha = 1;
            }
            ViewHelper.setAlpha(dragView, alpha);
        }
    }

    boolean isHeaderAboveTheMiddle() {
        int viewHeight = getHeight();
        float viewHeaderY = ViewHelper.getY(dragView) + (dragView.getHeight() / 2);
        return viewHeaderY < (viewHeight * 0.5f);
    }

    boolean isNextToLeftBound() {
        return (dragView.getRight() - getDragViewMarginRight()) < getWidth() / 2;
    }

    boolean isNextToRightBound() {
        return (dragView.getLeft() - getDragViewMarginRight()) > getWidth() * 0.25;
    }

    boolean isDragViewAtTop() {
        return dragView.getTop() == 0;
    }

    boolean isDragViewAtRight() {
        return dragView.getRight() == getWidth();
    }

    boolean isDragViewAtBottom() {
        return dragView.getTop() == (getHeight() - dragView.getHeight());
    }

    private boolean isViewHit(View view, int x, int y) {
        int[] viewLocation = new int[2];
        view.getLocationOnScreen(viewLocation);
        int[] parentLocation = new int[2];
        this.getLocationOnScreen(parentLocation);
        int screenX = parentLocation[0] + x;
        int screenY = parentLocation[1] + y;
        return screenX >= viewLocation[0] && screenX < viewLocation[0] + view.getWidth() &&
                screenY >= viewLocation[1] && screenY < viewLocation[1] + view.getHeight();
    }

    private void addFragmentToView(final int viewId, final Fragment fragment) {
        fragmentManager.beginTransaction().replace(viewId, fragment).commit();
    }

    private void initializeView() {
        viewDragHelper = ViewDragHelper.create(this, 1f, new DraggableViewCallback(this, dragView));
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
        this.enableHorizontalAlphaEffect = attributes.getBoolean(R.styleable.draggable_view_enable_minimized_horizontal_alpha_effect, DEFAULT_ENABLE_HORIZONTAL_ALPHA_EFECT);
        attributes.recycle();

    }

    private float getDragViewMarginRight() {
        return topFragmentMarginRight;
    }

    private float getDragViewMarginBottom() {
        return topFragmentMarginBottom;
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

    private void notifyMaximizeToListener() {
        if (listener != null) {
            listener.onMaximized();
        }
    }

    private void notifyMinimizeToListener() {
        if (listener != null) {
            listener.onMinimized();
        }
    }

    private void notifyCloseToRightListener() {
        if (listener != null) {
            listener.onClosedToRight();
        }
    }

    private void notifyCloseToLeftListener() {
        if (listener != null) {
            listener.onClosedToLeft();
        }
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

}