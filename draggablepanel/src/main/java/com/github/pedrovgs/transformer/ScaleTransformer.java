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

import com.nineoldandroids.view.ViewHelper;

/**
 * Transformer extension created to scale the view instead of resize it as the other
 * implementation does. This implementation is based on Nineoldanroids library to scale
 * the view.
 *
 * @author Pedro Vicente Gómez Sánchez
 */
class ScaleTransformer extends Transformer {

    ScaleTransformer(View view, View parent) {
        super(view, parent);
    }

    /**
     * Uses Nineoldandroids to change the scale in the X axis.
     *
     * @param verticalDragOffset used to calculate the new scale.
     */
    @Override
    public void updateWidth(float verticalDragOffset) {
        ViewHelper.setScaleX(getView(), 1 - verticalDragOffset / getXScaleFactor());
    }

    /**
     * Uses Nineoldandroids to change the scale in the Y axis.
     *
     * @param verticalDragOffset used to calculate the new scale.
     */
    @Override
    public void updateHeight(float verticalDragOffset) {
        ViewHelper.setScaleY(getView(), 1 - verticalDragOffset / getYScaleFactor());
    }

    /**
     * Uses Nineoldandroids to change the X position of the view.
     *
     * @param verticalDragOffset used to calculate the new X position.
     */
    @Override
    public void updateXPosition(float verticalDragOffset) {
        ViewHelper.setPivotX(getView(), getView().getWidth() - getMarginRight());
    }

    /**
     * Uses Nineoldandroids to change the Y position of the view.
     *
     * @param verticalDragOffset used to calculate the new Y position.
     */
    @Override
    public void updateYPosition(float verticalDragOffset) {
        ViewHelper.setPivotY(getView(), getView().getHeight() - getMarginBottom());
    }

    /**
     * @return true if the right corner of the view matches with the parent view width.
     */
    @Override
    public boolean isViewAtRight() {
        return getView().getRight() == getParentView().getWidth();
    }

    /**
     * @return true if the bottom corner of the view matches with the parent view height.
     */
    @Override
    public boolean isViewAtBottom() {
        return getView().getBottom() == getParentView().getHeight();
    }

    /**
     * @return true if the left position of the view is to the right of the twenty five percent of
     * the parent width.
     */
    @Override
    public boolean isNextToRightBound() {
        return (getView().getLeft() - getMarginRight()) > getParentView().getWidth() * 0.25;
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
     * @return min view height taking into account the configured margin.
     */
    @Override
    public int getMinHeightPlusMargin() {
        return getView().getHeight();
    }

    /**
     * @return min view width.
     */
    @Override
    public int getMinWidth() {
        return (int) getOriginalWidth();
    }

    @Override
    public int getLastLeftPosition() {
        return 0;
    }

}
