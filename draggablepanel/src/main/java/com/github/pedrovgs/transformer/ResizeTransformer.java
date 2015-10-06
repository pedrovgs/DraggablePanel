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

  private final RelativeLayout.LayoutParams layoutParams;

  ResizeTransformer(View view, View parent) {
    super(view, parent);
    layoutParams = (RelativeLayout.LayoutParams) view.getLayoutParams();
  }

  /**
   * Changes view scale using view's LayoutParam.
   *
   * @param verticalDragOffset used to calculate the new size.
   */
  @Override public void updateScale(float verticalDragOffset) {
    layoutParams.width = (int) (getOriginalWidth() * (1 - verticalDragOffset / getXScaleFactor()));
    layoutParams.height =
        (int) (getOriginalHeight() * (1 - verticalDragOffset / getYScaleFactor()));

    getView().setLayoutParams(layoutParams);
  }

  /**
   * Changes X view position using layout() method.
   *
   * @param verticalDragOffset used to calculate the new X position.
   */
  @Override public void updatePosition(float verticalDragOffset) {
    int right = getViewRightPosition(verticalDragOffset);
    int left = right - layoutParams.width;
    int top = getView().getTop();
    int bottom = top + layoutParams.height;

    getView().layout(left, top, right, bottom);
  }

  /**
   * @return true if the right position of the view plus the right margin is equals to the parent
   * width.
   */
  @Override public boolean isViewAtRight() {
    return getView().getRight() + getMarginRight() == getParentView().getWidth();
  }

  /**
   * @return true if the bottom position of the view plus the margin right is equals to
   * the parent view height.
   */
  @Override public boolean isViewAtBottom() {
    return getView().getBottom() + getMarginBottom() == getParentView().getHeight();
  }

  /**
   * @return true if the left position of the view is to the right of the seventy five percent of
   * the parent view width.
   */
  @Override public boolean isNextToRightBound() {
    return (getView().getLeft() - getMarginRight()) > getParentView().getWidth() * 0.75;
  }

  /**
   * @return true if the left position of the view is to the left of the twenty five percent of
   * the parent width.
   */
  @Override public boolean isNextToLeftBound() {
    return (getView().getLeft() - getMarginRight()) < getParentView().getWidth() * 0.05;
  }

  /**
   * Uses the Y scale factor to calculate the min possible height.
   */
  @Override public int getMinHeightPlusMargin() {
    return (int) (getOriginalHeight() * (1 - 1 / getYScaleFactor()) + getMarginBottom());
  }

  /**
   * Uses the X scale factor to calculate the min possible width.
   */
  @Override public int getMinWidthPlusMarginRight() {
    return (int) (getOriginalWidth() * (1 - 1 / getXScaleFactor()) + getMarginRight());
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
