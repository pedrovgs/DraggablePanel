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

import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import com.nineoldandroids.view.ViewHelper;

/**
 * Transformer extension created to scale the view instead of resize it as the other
 * implementation does.
 *
 * @author Pedro Vicente Gómez Sánchez
 */
class ScaleTransformer extends Transformer {

    public ScaleTransformer(View view, View parent) {
        super(view, parent);
    }


    @Override
    public void updateWidth(float verticalDragOffset) {
        ViewHelper.setScaleX(getView(), 1 - verticalDragOffset / getxScaleFactor());
    }

    @Override
    public void updateHeight(float verticalDragOffset) {
        ViewHelper.setScaleY(getView(), 1 - verticalDragOffset / getyScaleFactor());
    }

    @Override
    public void updateXPosition(float verticalDragOffset) {
        ViewHelper.setPivotX(getView(), getView().getWidth() - getMarginRight());
    }

    @Override
    public void updateYPosition() {
        ViewHelper.setPivotY(getView(), getView().getHeight() - getMarginBottom());
    }

    @Override
    public boolean isViewAtRight() {
        return getView().getRight() == getParentView().getWidth();
    }

    @Override
    public boolean isViewAtBottom(){
        return getView().getBottom()== getParentView().getHeight();
    }

    @Override
    public boolean isNextToRightBound() {
        return (getView().getLeft() - getMarginRight()) > getParentView().getWidth() * 0.25;
    }

    @Override
    public boolean isNextToLeftBound() {
        return (getView().getRight() - getMarginRight()) < getParentView().getWidth() * 0.25;
    }

    @Override
    public int getHeightPlusMarginTop() {
        return getView().getHeight();
    }

    @Override
    public int getWidthPlusMarginRight() {
        return 0;
    }
}
