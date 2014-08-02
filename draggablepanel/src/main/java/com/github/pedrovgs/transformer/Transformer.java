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
public class Transformer {

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

    public Transformer(View view,View parent) {
        this.view = view;
        this.parent = parent;
    }

    public void setXScaleFactor(float xScaleFactor) {
        this.xScaleFactor = xScaleFactor;
    }

    public void setyScaleFactor(float yScaleFactor) {
        this.yScaleFactor = yScaleFactor;
    }

    protected View getView() {
        return view;
    }

    protected View getParentView(){
        return parent;
    }

    protected float getxScaleFactor() {
        return xScaleFactor;
    }

    protected float getyScaleFactor() {
        return yScaleFactor;
    }

    public void setMarginRight(float marginRight) {
        this.marginRight = marginRight;
    }

    public void setMarginBottom(float marginBottom) {
        this.marginBottom = marginBottom;
    }

    public float getMarginRight() {
        return marginRight;
    }

    public float getMarginBottom() {
        return marginBottom;
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

    public float getViewHeight() {
        return viewHeight < 0f ? view.getMeasuredHeight() : viewHeight;
    }

    public void setLastTopPosition(int lastTopPosition) {
        this.lastTopPosition = lastTopPosition;
    }

    public void setLastLeftPosition(int lastLeftPosition) {
        this.lastLeftPosition = lastLeftPosition;
    }

    public void setLastRightPosition(int lastRightPosition) {
        this.lastRightPosition = lastRightPosition;
    }

    public int getLastRightPosition(){
        if(lastRightPosition<=0){
            lastRightPosition = view.getMeasuredWidth();
        }
        return lastRightPosition;
    }

    public int getLastTopPosition() {
        return lastTopPosition;
    }

    public int getLastLeftPosition() {
        return lastLeftPosition;
    }

    public void updateXPosition(float verticalDragOffset) {
        ViewHelper.setPivotX(view, view.getWidth() - getMarginRight());
    }

    public void updateYPosition() {
        ViewHelper.setPivotY(view, view.getHeight() - getMarginBottom());
    }

    public void updateWidth(float verticalDragOffset) {
        ViewHelper.setScaleX(view, 1 - verticalDragOffset / getxScaleFactor());
    }

    public void updateHeight(float verticalDragOffset) {
        ViewHelper.setScaleY(view, 1 - verticalDragOffset / getyScaleFactor());
    }

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

    public int getViewRightPosition(float verticalDragOffset) {
        return (int) (getOriginalWidth()-(marginRight*verticalDragOffset));
    }

    public boolean isViewAtRight() {
        return view.getRight() == parent.getWidth();
    }

    public boolean isViewAtTop() {
        return view.getTop() == 0;
    }

    public boolean isViewAtBottom() {
        return view.getBottom()== parent.getHeight();
    }

    public boolean isNextToRightBound() {
        return (view.getLeft() - getMarginRight()) > parent.getWidth() * 0.25;
    }

    public boolean isNextToLeftBound() {
        return (view.getRight() - getMarginRight()) < parent.getWidth() * 0.25;
    }

    public boolean isAboveTheMiddle() {
        int parentHeight = parent.getHeight();
        float viewYPosition = ViewHelper.getY(view) + (view.getHeight() * 0.5f);
        return viewYPosition < (parentHeight * 0.5);
    }

    public int getHeightPlusMarginTop() {
        return view.getHeight();
    }

    public int getWidthPlusMarginRight() {
        return 0;
    }
}
