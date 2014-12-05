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

/**
 * Transformer extension created to resize the view instead of scale it as the other
 * implementation does. This implementation is based on change the LayoutParams.
 *
 * @author Pedro Vicente Gómez Sánchez
 */
class ResizeTransformer extends Transformer {

    private float lastHeight;

    ResizeTransformer(View view, View parent) {
        super(view, parent);
    }

    /**
     * Changes view width using view's LayoutParam.
     *
     * @param verticalDragOffset used to calculate the new width.
     */
    @Override
    public void updateWidth(float verticalDragOffset) {
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) getView().getLayoutParams();
        int newWidth = (int) (getOriginalWidth() * (1 - verticalDragOffset / getXScaleFactor()));
        params.width = newWidth;
        setLastLeftPosition((int) (getOriginalWidth() - newWidth));
        getView().setLayoutParams(params);
    }

    /**
     * Changes view height using view's LayoutParam.
     *
     * @param verticalDragOffset used to calculate the new width.
     */
    @Override
    public void updateHeight(float verticalDragOffset) {
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) getView().getLayoutParams();
        int newHeight = (int) (getOriginalHeight() * (1 - verticalDragOffset / getYScaleFactor()));
        params.height = newHeight;
        lastHeight = newHeight;
        getView().setLayoutParams(params);
    }

    /**
     * @return last updated height or the view height if the view hasn't been changed yet.
     */
    @Override
    public float getViewHeight() {
        return lastHeight == 0 ? super.getViewHeight() : lastHeight;
    }

    /**
     * Changes X view position using layout() method.
     *
     * @param verticalDragOffset used to calculate the new X position.
     */
    @Override
    public void updateXPosition(float verticalDragOffset) {
        int left, top, right, bottom;
        left = (int) (getOriginalWidth() - getViewWidth());
        right = getViewRightPosition(verticalDragOffset);
        setLastRightPosition(right);
        top = getView().getTop();
        bottom = getView().getBottom();
        getView().layout(left, top, right, bottom);
    }

    /**
     * Empty implementation. ViewDragHelper already changes the Y position.
     */
    @Override
    public void updateYPosition(float verticalDragOffset) {
        // Empty
    }

    /**
     * @return true if the right position of the view plus the right margin is equals to the parent
     * width.
     */
    @Override
    public boolean isViewAtRight() {
        return isEqualsIntegerFloat(getView().getRight() + getMarginRight(), getParentView().getWidth());
    }

    /**
     * @return true if the bottom position of the view plus the margin right is equals to
     * the parent view height.
     */
    @Override
    public boolean isViewAtBottom() {
        return isEqualsIntegerFloat(getView().getBottom() + getMarginBottom(), getParentView().getHeight());
    }

    /**
     * @return true if the left position of the view is to the right of the seventy five percent of
     * the parent view width.
     */
    @Override
    public boolean isNextToRightBound() {
        return (getView().getLeft() - getMarginRight()) > getParentView().getWidth() * 0.75;
    }

    /**
     * @return true if the left position of the view is to the left of the twenty five percent of
     * the parent width.
     */
    @Override
    public boolean isNextToLeftBound() {
        return (getView().getLeft() - getMarginRight()) < getParentView().getWidth() * 0.25;
    }

    /**
     * Uses the Y scale factor to calculate the min possible height.
     */
    @Override
    public int getMinHeightPlusMargin() {
        return (int) ((getOriginalHeight() / getYScaleFactor()) + getMarginBottom());
    }

    /**
     * Uses the X scale factor to calculate the min possible width.
     */
    @Override
    public int getMinWidth() {
        return (int) (getOriginalWidth() / getXScaleFactor());
    }

    /**
     * Calculate the current view right position for a given verticalDragOffset.
     *
     * @param verticalDragOffset used to calculate the new right position.
     */
    private int getViewRightPosition(float verticalDragOffset) {
        return (int) ((getOriginalWidth()) - getMarginRight() * verticalDragOffset);
    }
}
