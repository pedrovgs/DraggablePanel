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
 * view. The most basic one is going to scale the view and the most complex used with VideoView is
 * going to change the size of the view.
 * <p/>
 * The view used in this class has to be contained by a RelativeLayout.
 * <p/>
 * This class also provide information about the size of the view and the position because
 * different Transformer implementations could change the size of the view but not the position,
 * like ScaleTransformer does.
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

  public float getXScaleFactor() {
    return xScaleFactor;
  }

  public void setXScaleFactor(float xScaleFactor) {
    this.xScaleFactor = xScaleFactor;
  }

  public float getYScaleFactor() {
    return yScaleFactor;
  }

  public void setYScaleFactor(float yScaleFactor) {
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

  /**
   * Change view height using the LayoutParams of the view.
   *
   * @param viewHeight to change..
   */
  public void setViewHeight(float viewHeight) {
    this.viewHeight = viewHeight;
    if (viewHeight > 0f) {
      originalHeight = viewHeight;
      RelativeLayout.LayoutParams layoutParams =
          (RelativeLayout.LayoutParams) view.getLayoutParams();
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

  protected View getView() {
    return view;
  }

  protected View getParentView() {
    return parent;
  }

  public abstract void updateXPosition(float verticalDragOffset);

  public abstract void updateYPosition(float verticalDragOffset);

  public abstract void updateWidth(float verticalDragOffset);

  public abstract void updateHeight(float verticalDragOffset);

  /**
   * @return height of the view before it has change the size.
   */
  public float getOriginalHeight() {
    if (originalHeight == 0) {
      originalHeight = viewHeight < 0 ? view.getMeasuredHeight() : viewHeight;
    }
    return originalHeight;
  }

  /**
   * @return width of the view before it has change the size.
   */
  public float getOriginalWidth() {
    if (originalWidth == 0) {
      originalWidth = view.getMeasuredWidth();
    }
    return originalWidth;
  }

  /**
   * @return current width of the view.
   */
  public int getViewWidth() {
    return getView().getMeasuredWidth();
  }

  public boolean isViewAtTop() {
    return view.getTop() == 0;
  }

  public boolean isAboveTheMiddle() {
    int parentHeight = parent.getHeight();
    float viewYPosition = ViewHelper.getY(view) + (view.getHeight() * 0.5f);
    return viewYPosition < (parentHeight * 0.5);
  }

  public abstract boolean isViewAtRight();

  public abstract boolean isViewAtBottom();

  public abstract boolean isNextToRightBound();

  public abstract boolean isNextToLeftBound();

  /**
   * @return min possible height, after apply the transformation, plus the margin right.
   */
  public abstract int getMinHeightPlusMargin();

  /**
   * @return min possible width, after apply the transformation.
   */
  public abstract int getMinWidth();

  protected boolean isEqualsIntegerFloat(float floatValue, int integerValue){
      int delta = Math.round(floatValue) - integerValue;
      return Math.abs(delta) <= 1;
  }
}
