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

import android.support.v4.widget.ViewDragHelper;
import android.view.View;

/**
 * ViewDragHelper.Callback implementation used to work with DraggableView to perform the scale
 * effect and other animation when the view is released.
 *
 * @author Pedro Vicente Gómez Sánchez.
 */
class DraggableViewCallback extends ViewDragHelper.Callback {

  private static final int MINIMUM_DX_FOR_HORIZONTAL_DRAG = 5;
  private static final int MINIMUM_DY_FOR_VERTICAL_DRAG = 15;
  private static final float X_MIN_VELOCITY = 1500;
  private static final float Y_MIN_VELOCITY = 1000;

  private DraggableView draggableView;
  private View draggedView;

  /**
   * Main constructor.
   *
   * @param draggableView instance used to apply some animations or visual effects.
   */
  public DraggableViewCallback(DraggableView draggableView, View draggedView) {
    this.draggableView = draggableView;
    this.draggedView = draggedView;
  }

  /**
   * Override method used to apply different scale and alpha effects while the view is being
   * dragged.
   *
   * @param left position.
   * @param top position.
   * @param dx change in X position from the last call.
   * @param dy change in Y position from the last call.
   */
  @Override public void onViewPositionChanged(View changedView, int left, int top, int dx, int dy) {
    if (draggableView.isDragViewAtBottom()) {
      draggableView.changeDragViewViewAlpha();
    } else {
      draggableView.restoreAlpha();
      draggableView.changeDragViewScale();
      draggableView.changeDragViewPosition();
      draggableView.changeSecondViewAlpha();
      draggableView.changeSecondViewPosition();
      draggableView.changeBackgroundAlpha();
    }
  }

  /**
   * Override method used to apply different animations when the dragged view is released. The
   * dragged view is going to be maximized or minimized if the view is above the middle of the
   * custom view and the velocity is greater than a constant value.
   *
   * @param releasedChild the captured child view now being released.
   * @param xVel X velocity of the pointer as it left the screen in pixels per second.
   * @param yVel Y velocity of the pointer as it left the screen in pixels per second.
   */
  @Override public void onViewReleased(View releasedChild, float xVel, float yVel) {
    super.onViewReleased(releasedChild, xVel, yVel);

    if (draggableView.isDragViewAtBottom() && !draggableView.isDragViewAtRight()) {
      triggerOnReleaseActionsWhileHorizontalDrag(xVel);
    } else {
      triggerOnReleaseActionsWhileVerticalDrag(yVel);
    }
  }

  /**
   * Override method used to configure which is going to be the dragged view.
   *
   * @param view child the user is attempting to capture.
   * @param pointerId ID of the pointer attempting the capture,
   * @return true if capture should be allowed, false otherwise.
   */
  @Override public boolean tryCaptureView(View view, int pointerId) {
    return view.equals(draggedView);
  }

  /**
   * Override method used to configure the horizontal drag. Restrict the motion of the dragged
   * child view along the horizontal axis.
   *
   * @param child child view being dragged.
   * @param left attempted motion along the X axis.
   * @param dx proposed change in position for left.
   * @return the new clamped position for left.
   */
  @Override public int clampViewPositionHorizontal(View child, int left, int dx) {
    int newLeft = draggedView.getLeft();
    if ((draggableView.isMinimized() && Math.abs(dx) > MINIMUM_DX_FOR_HORIZONTAL_DRAG) || (
        draggableView.isDragViewAtBottom() && !draggableView.isDragViewAtRight())) {
      newLeft = left;
    }
    return newLeft;
  }

  /**
   * Override method used to configure the vertical drag. Restrict the motion of the dragged child
   * view along the vertical axis.
   *
   * @param child child view being dragged.
   * @param top attempted motion along the Y axis.
   * @param dy proposed change in position for top.
   * @return the new clamped position for top.
   */
  @Override public int clampViewPositionVertical(View child, int top, int dy) {
    int newTop = draggableView.getHeight() - draggableView.getDraggedViewHeightPlusMarginTop();
    if (draggableView.isMinimized() && Math.abs(dy) >= MINIMUM_DY_FOR_VERTICAL_DRAG || (
        !draggableView.isMinimized() && !draggableView.isDragViewAtBottom())) {

      final int topBound = draggableView.getPaddingTop();
      final int bottomBound =
          draggableView.getHeight() - draggableView.getDraggedViewHeightPlusMarginTop()
              - draggedView.getPaddingBottom();

      newTop = Math.min(Math.max(top, topBound), bottomBound);
    }
    return newTop;
  }

  /**
   * Maximize or minimize the DraggableView using the draggableView position and the y axis
   * velocity.
   */
  private void triggerOnReleaseActionsWhileVerticalDrag(float yVel) {
    if (yVel < 0 && yVel <= -Y_MIN_VELOCITY) {
      draggableView.maximize();
    } else if (yVel > 0 && yVel >= Y_MIN_VELOCITY) {
      draggableView.minimize();
    } else {
      if (draggableView.isDragViewAboveTheMiddle()) {
        draggableView.maximize();
      } else {
        draggableView.minimize();
      }
    }
  }

  /**
   * Close the view to the right, to the left or minimize it using the draggableView position and
   * the x axis velocity.
   */
  private void triggerOnReleaseActionsWhileHorizontalDrag(float xVel) {
    if (xVel < 0 && xVel <= -X_MIN_VELOCITY) {
      draggableView.closeToLeft();
    } else if (xVel > 0 && xVel >= X_MIN_VELOCITY) {
      draggableView.closeToRight();
    } else {
      if (draggableView.isNextToLeftBound()) {
        draggableView.closeToLeft();
      } else if (draggableView.isNextToRightBound()) {
        draggableView.closeToRight();
      } else {
        draggableView.minimize();
      }
    }
  }
}
