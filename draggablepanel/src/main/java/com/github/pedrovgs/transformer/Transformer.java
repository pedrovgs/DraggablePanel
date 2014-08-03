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
package com.github.pedrovgs.transformer;

import android.view.View;
import android.widget.RelativeLayout;

import com.nineoldandroids.view.ViewHelper;

/**
 * Abstract class created to be implemented by different classes are going to change the size of a
 * view. The most basic one is going to scale the view and the most complex used with VideoView or
 * the YouTubePlayer is going to change the size of the view.
 * <p/>
 * The view used in this class has to be contained by a RelativeLayout.
 *
 * @author Pedro Vicente Gómez Sánchez
 */
public abstract class Transformer {

    private final View view;
    private final View parent;
    private float viewHeight;
    private float xScaleFactor;
    private float yScaleFactor;
    private float marginRight;
    private float marginBottom;
    private int lastTopPosition;
    private int lastLeftPosition;
    private int lastRightPosition;
    private float originalHeight;
    private float originalWidth;

    public Transformer(View view, View parent) {
        this.view = view;
        this.parent = parent;
    }

    public void setXScaleFactor(float xScaleFactor) {
        this.xScaleFactor = xScaleFactor;
    }

    public View getView() {
        return view;
    }

    public View getParentView() {
        return parent;
    }

    public float getxScaleFactor() {
        return xScaleFactor;
    }

    public float getyScaleFactor() {
        return yScaleFactor;
    }

    public void setyScaleFactor(float yScaleFactor) {
        this.yScaleFactor = yScaleFactor;
    }

    public float getMarginRight() {
        return marginRight;
    }

    public void setMarginRight(float marginRight) {
        this.marginRight = marginRight;
    }

    public float getMarginBottom() {
        return marginBottom;
    }

    public void setMarginBottom(float marginBottom) {
        this.marginBottom = marginBottom;
    }

    public float getViewHeight() {
        return viewHeight < 0f ? view.getMeasuredHeight() : viewHeight;
    }

    public void setViewHeight(float viewHeight) {
        this.viewHeight = viewHeight;
        if (viewHeight > 0f) {
            originalHeight = viewHeight;
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) view.getLayoutParams();
            layoutParams.height = (int) viewHeight;
            view.setLayoutParams(layoutParams);
        }
    }

    public int getLastRightPosition() {
        if (lastRightPosition <= 0) {
            lastRightPosition = view.getMeasuredWidth();
        }
        return lastRightPosition;
    }

    public void setLastRightPosition(int lastRightPosition) {
        this.lastRightPosition = lastRightPosition;
    }

    public int getLastTopPosition() {
        return lastTopPosition;
    }

    public void setLastTopPosition(int lastTopPosition) {
        this.lastTopPosition = lastTopPosition;
    }

    public int getLastLeftPosition() {
        return lastLeftPosition;
    }

    public void setLastLeftPosition(int lastLeftPosition) {
        this.lastLeftPosition = lastLeftPosition;
    }

    public abstract void updateXPosition(float verticalDragOffset);

    public abstract void updateYPosition();

    public abstract void updateWidth(float verticalDragOffset);

    public abstract void updateHeight(float verticalDragOffset);

    public float getOriginalHeight() {
        if (originalHeight == 0) {
            originalHeight = viewHeight < 0 ? view.getMeasuredHeight() : viewHeight;
        }
        return originalHeight;
    }

    public float getOriginalWidth() {
        if (originalWidth == 0) {
            originalWidth = view.getMeasuredWidth();
        }
        return originalWidth;
    }

    public int getViewWidth() {
        return getView().getMeasuredWidth();
    }

    public abstract int getViewRightPosition(float verticalDragOffset);

    public abstract boolean isViewAtRight();

    public boolean isViewAtTop() {
        return view.getTop() == 0;
    }

    public abstract boolean isViewAtBottom();

    public abstract boolean isNextToRightBound();

    public abstract boolean isNextToLeftBound();

    public boolean isAboveTheMiddle() {
        int parentHeight = parent.getHeight();
        float viewYPosition = ViewHelper.getY(view) + (view.getHeight() * 0.5f);
        return viewYPosition < (parentHeight * 0.5);
    }

    public abstract int getHeightPlusMarginTop();

    public abstract int getWidthPlusMarginRight();

    public abstract int getMinWidth();
}
