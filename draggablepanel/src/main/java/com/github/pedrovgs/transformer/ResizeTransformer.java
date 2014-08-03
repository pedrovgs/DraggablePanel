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
 * implementation does.
 *
 * @author Pedro Vicente Gómez Sánchez
 */
class ResizeTransformer extends Transformer {

    private float lastHeight;

    ResizeTransformer(View view, View parent) {
        super(view, parent);
    }

    @Override
    public void updateWidth(float verticalDragOffset) {
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) getView().getLayoutParams();
        int newWidth = (int) (getOriginalWidth() * (1 - verticalDragOffset / getxScaleFactor()));
        params.width = newWidth;
        setLastLeftPosition((int) (getOriginalWidth() - newWidth));
        getView().setLayoutParams(params);
    }

    @Override
    public void updateHeight(float verticalDragOffset) {
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) getView().getLayoutParams();
        int newHeight = (int) (getOriginalHeight() * (1 - verticalDragOffset / getyScaleFactor()));
        params.height = newHeight;
        lastHeight = newHeight;
        getView().setLayoutParams(params);
    }

    @Override
    public int getViewRightPosition(float verticalDragOffset) {
        return (int) ((getOriginalWidth()) - getMarginRight() * verticalDragOffset);
    }

    @Override
    public float getViewHeight() {
        return lastHeight == 0 ? super.getViewHeight() : lastHeight;
    }

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

    @Override
    public void updateYPosition() {
        // ViewDragHelper already changes the Y position.
    }

    @Override
    public boolean isViewAtRight() {
        return getView().getRight() + getMarginRight() == getParentView().getWidth();
    }

    @Override
    public boolean isViewAtBottom() {
        return getView().getBottom() + getMarginBottom() >= getParentView().getHeight();
    }

    @Override
    public boolean isNextToRightBound() {
        return (getView().getLeft() - getMarginRight()) > getParentView().getWidth() * 0.75;
    }

    @Override
    public boolean isNextToLeftBound() {
        return (getView().getLeft() - getMarginRight()) < getParentView().getWidth() * 0.25;
    }

    @Override
    public int getHeightPlusMarginTop() {
        return (int) ((getOriginalHeight() / getyScaleFactor()) + getMarginBottom());
    }

    @Override
    public int getWidthPlusMarginRight() {
        return (int) ((getOriginalWidth() / getxScaleFactor()) + getMarginRight());
    }

    @Override
    public int getMinWidth() {
        return (int) (getOriginalWidth() / getxScaleFactor());
    }

}
