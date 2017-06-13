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

import android.view.MotionEvent;

/**
 * Listener created to be notified when some drag actions are performed over DraggablePanel or
 * DraggableView instances.
 *
 * @author Pedro Vicente Gómez Sánchez.
 */
public interface DraggableListener {

  /**
   * Called when the view is maximized.
   */
  void onMaximized();

  /**
   * Called when the view is minimized.
   */
  void onMinimized();

  /**
   * Called when the view is closed to the left.
   */
  void onClosedToLeft();

  /**
   * Called when the view is closed to the right.
   */
  void onClosedToRight();

  /**
   * Called when the view is being touched at present
   */

  void onTouchListener(MotionEvent ev);

  /**
   * Called when the view is maximized with click instead of drag
   */

  void onClickedToMaximize();

  /**
   * Called when the view is minimized with click instead of drag
   */

  void onClickedToMinimize();

  /**
   * Called when the view is being dragged to either minimize or maximize
   */

  void onSmoothSlide();
}
