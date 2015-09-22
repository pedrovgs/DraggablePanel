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

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

import com.github.pedrovgs.transformer.Transformer;
import com.github.pedrovgs.transformer.TransformerFactory;
import com.nineoldandroids.view.ViewHelper;

/**
 * Class created to extends a ViewGroup and simulate the YoutubeLayoutComponent
 *
 * @author Pedro Vicente Gómez Sánchez
 */
public class DraggableView extends RelativeLayout{

	private static final int DEFAULT_SCALE_FACTOR = 2;
	private static final int DEFAULT_TOP_VIEW_MARGIN = 30;
	private static final int DEFAULT_TOP_VIEW_HEIGHT = -1;
	private static final float SLIDE_TOP = 0f;
	private static final float SLIDE_BOTTOM = 1f;
	private static final float MIN_SLIDE_OFFSET = 0.1f;
	private static final boolean DEFAULT_ENABLE_HORIZONTAL_ALPHA_EFFECT = true;
	private static final boolean DEFAULT_ENABLE_CLICK_TO_MAXIMIZE = false;
	private static final boolean DEFAULT_ENABLE_CLICK_TO_MINIMIZE = false;
	private static final boolean DEFAULT_ENABLE_TOUCH_LISTENER = true;
	private static final int MIN_SLIDING_DISTANCE_ON_CLICK = 10;
	private static final int ONE_HUNDRED = 100;
	private static final float SENSITIVITY = 1f;
	private static final boolean DEFAULT_TOP_VIEW_RESIZE = false;
	private static final int INVALID_POINTER = -1;

	private int activePointerId = INVALID_POINTER;
	private float lastTouchActionDownXPosition;

	private View dragView;
	private View secondView;
	private TypedArray attributes;

	private FragmentManager fragmentManager;
	private ViewDragHelper viewDragHelper;
	private Transformer transformer;

	private boolean enableHorizontalAlphaEffect;
	private boolean topViewResize;
	private boolean enableClickToMaximize;
	private boolean enableClickToMinimize;
	private boolean touchEnabled;

	private DraggableListener listener;


	public DraggableView(Context context){
		super(context);
	}


	public DraggableView(Context context, AttributeSet attrs){
		super(context, attrs);
		initializeAttributes(attrs);
	}


	public DraggableView(Context context, AttributeSet attrs, int defStyle){
		super(context, attrs, defStyle);
		initializeAttributes(attrs);
	}


	/**
	 * Return if user can maximize minimized view on click.
	 */
	public boolean isClickToMaximizeEnabled(){
		return enableClickToMaximize;
	}


	/**
	 * Enable or disable click to maximize view when dragged view is minimized
	 * If your content have a touch/click listener (like YoutubePlayer), you
	 * need disable it to active this feature.
	 *
	 * @param enableClickToMaximize to enable or disable the click.
	 */
	public void setClickToMaximizeEnabled(boolean enableClickToMaximize){
		this.enableClickToMaximize = enableClickToMaximize;
	}


	/**
	 * Return if user can minimize maximized view on click.
	 */
	public boolean isClickToMinimizeEnabled(){
		return enableClickToMinimize;
	}


	/**
	 * Enable or disable click to minimize view when dragged view is maximized
	 * If your content have a touch/click listener (like YoutubePlayer), you
	 * need disable it to active this feature.
	 *
	 * @param enableClickToMinimize to enable or disable the click.
	 */
	public void setClickToMinimizeEnabled(boolean enableClickToMinimize){
		this.enableClickToMinimize = enableClickToMinimize;
	}


	/**
	 * Return if touch listener is enable or disable
	 */
	private boolean isTouchEnabled(){
		return this.touchEnabled;
	}


	/**
	 * Enable or disable the touch listener
	 *
	 * @param touchEnabled to enable or disable the touch event.
	 */
	public void setTouchEnabled(boolean touchEnabled){
		this.touchEnabled = touchEnabled;
	}


	/**
	 * Slide the view based on scroll of the nav drawer.
	 * "setEnableTouch" user prevents click to expand while the drawer is moving, it will be
	 * set to false when the @slideOffset is bigger than MIN_SLIDE_OFFSET.
	 * When the slideOffset is bigger than 0.1 and dragView isn't close, set the dragView
	 * to minimized.
	 * It's only possible to maximize the view when @slideOffset is equals to 0.0,
	 * in other words, closed.
	 *
	 * @param slideOffset    Value between 0 and 1, represent the value of slide:
	 *                       0.0 is equal to close drawer and 1.0 equals open drawer.
	 * @param drawerPosition Represent the position of nav drawer on X axis.
	 * @param width          Width of nav drawer
	 */
	public void slideHorizontally(float slideOffset, float drawerPosition, int width){
		if(slideOffset > MIN_SLIDE_OFFSET && !isClosed() && isMaximized()){
			minimize();
		}
		setTouchEnabled(slideOffset <= MIN_SLIDE_OFFSET);
		ViewHelper.setX(this, width - Math.abs(drawerPosition));
	}


	/**
	 * Configure the horizontal scale factor applied when the view is dragged to the bottom of the
	 * custom view.
	 */
	public void setXTopViewScaleFactor(float xScaleFactor){
		transformer.setXScaleFactor(xScaleFactor);
	}


	/**
	 * Configure the vertical scale factor applied when the view is dragged to the bottom of the
	 * custom view.
	 */
	public void setYTopViewScaleFactor(float yScaleFactor){
		transformer.setYScaleFactor(yScaleFactor);
	}


	/**
	 * Configure the dragged view margin right applied when the dragged view is minimized.
	 *
	 * @param topFragmentMarginRight in pixels.
	 */
	public void setTopViewMarginRight(int topFragmentMarginRight){
		transformer.setMarginRight(topFragmentMarginRight);
	}


	/**
	 * Configure the dragView margin bottom applied when the dragView is minimized.
	 */
	public void setTopViewMarginBottom(int topFragmentMarginBottom){
		transformer.setMarginBottom(topFragmentMarginBottom);
	}


	/**
	 * Configure the dragged view height.
	 *
	 * @param topFragmentHeight in pixels
	 */
	public void setTopViewHeight(int topFragmentHeight){
		transformer.setViewHeight(topFragmentHeight);
	}


	/**
	 * Configure the disabling of the alpha effect applied when the dragView is dragged horizontally.
	 */
	public void setHorizontalAlphaEffectEnabled(boolean enableHorizontalAlphaEffect){
		this.enableHorizontalAlphaEffect = enableHorizontalAlphaEffect;
	}


	/**
	 * Configure the DraggableListener notified when the view is minimized, maximized, closed to the
	 * right or closed to the left.
	 */
	public void setDraggableListener(DraggableListener listener){
		this.listener = listener;
	}


	/**
	 * Configure DraggableView to resize top view instead of scale it.
	 */
	public void setTopViewResize(boolean topViewResize){
		this.topViewResize = topViewResize;
	}


	/**
	 * To ensure the animation is going to work this method has been override to call
	 * postInvalidateOnAnimation if the view is not settled yet.
	 */
	@Override
	public void computeScroll(){
		if(!isInEditMode() && viewDragHelper.continueSettling(true)){
			ViewCompat.postInvalidateOnAnimation(this);
		}
	}


	/**
	 * Maximize the custom view applying an animation to return the view to the initial position.
	 */
	public void maximize(){
		smoothSlideTo(SLIDE_TOP);
		notifyMaximizeToListener();
	}


	/**
	 * Minimize the custom view applying an animation to put the top fragment on the bottom right
	 * corner of the screen.
	 */
	public void minimize(){
		smoothSlideTo(SLIDE_BOTTOM);
		notifyMinimizeToListener();
	}


	/**
	 * Close the custom view applying an animation to close the view to the right side of the screen.
	 */
	public void closeToRight(){
		DisplayMetrics dm = getResources().getDisplayMetrics();
		int maxSize = Math.max(dm.widthPixels, dm.heightPixels);
		if(viewDragHelper.smoothSlideViewTo(
			dragView, transformer.getOriginalWidth() + maxSize, getHeight() - transformer.getMinHeightPlusMargin()
		)){
			ViewCompat.postInvalidateOnAnimation(this);
			notifyCloseToRightListener();
		}
	}


	/**
	 * Close the custom view applying an animation to close the view to the left side of the screen.
	 */
	public void closeToLeft(){
		DisplayMetrics dm = getResources().getDisplayMetrics();
		int maxSize = Math.max(dm.widthPixels, dm.heightPixels);
		if(viewDragHelper.smoothSlideViewTo(
			dragView, -(transformer.getOriginalWidth() + maxSize), getHeight() - transformer.getMinHeightPlusMargin()
		)){
			ViewCompat.postInvalidateOnAnimation(this);
			notifyCloseToLeftListener();
		}
	}
	//	public  void closeToLeft(boolean internal){
	//		if(viewDragHelper.smoothSlideViewTo(
	//			dragView, -transformer.getOriginalWidth(), getHeight() - transformer.getMinHeightPlusMargin()
	//		)){
	//			ViewCompat.postInvalidateOnAnimation(this);
	//			if(notifyCloseToLeftListener();
	//		}
	//	}
	//


	/**
	 * Checks if the top view is minimized.
	 *
	 * @return true if the view is minimized.
	 */
	public boolean isMinimized(){
		return isDragViewAtBottom() && isDragViewAtRight();
	}


	/**
	 * Checks if the top view is maximized.
	 *
	 * @return true if the view is maximized.
	 */
	public boolean isMaximized(){
		return isDragViewAtTop();
	}


	/**
	 * Checks if the top view closed at the right place.
	 *
	 * @return true if the view is closed at right.
	 */
	public boolean isClosedAtRight(){
		return dragView.getLeft() >= getWidth();
	}


	/**
	 * Checks if the top view is closed at the left place.
	 *
	 * @return true if the view is closed at left.
	 */
	public boolean isClosedAtLeft(){
		return dragView.getRight() <= 0;
	}


	/**
	 * Checks if the top view is closed at the right or left place.
	 *
	 * @return true if the view is closed.
	 */
	public boolean isClosed(){
		return isClosedAtLeft() || isClosedAtRight();
	}


	/**
	 * Override method to intercept only touch events over the drag view and to cancel the drag when
	 * the action associated to the MotionEvent is equals to ACTION_CANCEL or ACTION_UP.
	 *
	 * @param ev captured.
	 * @return true if the view is going to process the touch event or false if not.
	 */
	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev){
		if(!isEnabled()){
			return false;
		}
		switch(MotionEventCompat.getActionMasked(ev) & MotionEventCompat.ACTION_MASK){
			case MotionEvent.ACTION_CANCEL:
			case MotionEvent.ACTION_UP:
				viewDragHelper.cancel();
				return false;
			case MotionEvent.ACTION_DOWN:
				int index = MotionEventCompat.getActionIndex(ev);
				activePointerId = MotionEventCompat.getPointerId(ev, index);
				if(activePointerId == INVALID_POINTER){
					return false;
				}
				break;
			default:
				break;
		}
		boolean interceptTap =
			viewDragHelper.isViewUnder(dragView, (int) ev.getX(), (int) ev.getY());
		return viewDragHelper.shouldInterceptTouchEvent(ev) || interceptTap;
	}


	/**
	 * Override method to dispatch touch event to the dragged view.
	 *
	 * @param ev captured.
	 * @return true if the touch event is realized over the drag or second view.
	 */
	@Override
	public boolean onTouchEvent(MotionEvent ev){
		int actionMasked = MotionEventCompat.getActionMasked(ev);
		if((actionMasked & MotionEventCompat.ACTION_MASK) == MotionEvent.ACTION_DOWN){
			activePointerId = MotionEventCompat.getPointerId(ev, actionMasked);
		}
		if(activePointerId == INVALID_POINTER){
			return false;
		}
		viewDragHelper.processTouchEvent(ev);
		if(isClosed()){
			return false;
		}
		boolean isDragViewHit = isViewHit(dragView, (int) ev.getX(), (int) ev.getY());
		boolean isSecondViewHit = isViewHit(secondView, (int) ev.getX(), (int) ev.getY());
		analyzeTouchToMaximizeIfNeeded(ev, isDragViewHit);
		if(isMaximized()){
			dragView.dispatchTouchEvent(ev);
		} else{
			dragView.dispatchTouchEvent(cloneMotionEventWithAction(ev, MotionEvent.ACTION_CANCEL));
		}
		return isDragViewHit || isSecondViewHit;
	}


	private void analyzeTouchToMaximizeIfNeeded(MotionEvent ev, boolean isDragViewHit){
		switch(ev.getAction()){
			case MotionEvent.ACTION_DOWN:
				lastTouchActionDownXPosition = ev.getX();
				break;
			case MotionEvent.ACTION_UP:
				float clickOffset = ev.getX() - lastTouchActionDownXPosition;
				if(shouldMaximizeOnClick(ev, clickOffset, isDragViewHit)){
					if(isMinimized() && isClickToMaximizeEnabled()){
						maximize();
					} else if(isMaximized() && isClickToMinimizeEnabled()){
						minimize();
					}
				}
				break;
			default:
				break;
		}
	}


	public boolean shouldMaximizeOnClick(MotionEvent ev, float deltaX, boolean isDragViewHit){
		return (Math.abs(deltaX) < MIN_SLIDING_DISTANCE_ON_CLICK) && ev.getAction() != MotionEvent.ACTION_MOVE && isDragViewHit;
	}


	/**
	 * Clone given motion event and set specified action. This method is useful, when we want to
	 * cancel event propagation in child views by sending event with {@link
	 * android.view.MotionEvent#ACTION_CANCEL}
	 * action.
	 *
	 * @param event  event to clone
	 * @param action new action
	 * @return cloned motion event
	 */
	private MotionEvent cloneMotionEventWithAction(MotionEvent event, int action){
		return MotionEvent.obtain(
			event.getDownTime(), event.getEventTime(), action, event.getX(), event.getY(), event.getMetaState()
		);
	}


	/**
	 * Override method to configure the dragged view and secondView layout properly.
	 */
	@Override
	protected void onLayout(boolean changed, int left, int top, int right, int bottom){
		if(isInEditMode()){
			super.onLayout(changed, left, top, right, bottom);
		} else if(isDragViewAtTop()){
			dragView.layout(left, top, right, transformer.getOriginalHeight());
			secondView.layout(left, transformer.getOriginalHeight(), right, bottom);
			ViewHelper.setY(dragView, top);
			ViewHelper.setY(secondView, transformer.getOriginalHeight());
		} else{
			//      secondView.layout(left, transformer.getOriginalHeight(), right, bottom);
			if(isClosedAtLeft()){
			} else if(isClosedAtRight()){
			} else{
				smoothSlideTo(SLIDE_BOTTOM);
			}
		}
	}


	/**
	 * Override method to map dragged view, secondView to view objects, to configure dragged
	 * view height and to initialize DragViewHelper.
	 */
	@Override
	protected void onFinishInflate(){
		super.onFinishInflate();
		if(!isInEditMode()){
			mapGUI(attributes);
			initializeTransformer(attributes);
			attributes.recycle();
			initializeViewDragHelper();
		}
	}


	private void mapGUI(TypedArray attributes){
		int dragViewId =
			attributes.getResourceId(R.styleable.draggable_view_top_view_id, R.id.drag_view);
		int secondViewId =
			attributes.getResourceId(R.styleable.draggable_view_bottom_view_id, R.id.second_view);
		dragView = findViewById(dragViewId);
		secondView = findViewById(secondViewId);
	}


	/**
	 * Configure the FragmentManager used to attach top and bottom Fragments to the view. The
	 * FragmentManager is going to be provided only by DraggablePanel view.
	 */
	void setFragmentManager(FragmentManager fragmentManager){
		this.fragmentManager = fragmentManager;
	}


	/**
	 * Attach one fragment to the dragged view.
	 *
	 * @param topFragment to be attached.
	 */
	void attachTopFragment(Fragment topFragment){
		addFragmentToView(R.id.drag_view, topFragment);
	}


	/**
	 * Attach one fragment to the secondView.
	 *
	 * @param bottomFragment to be attached.
	 */
	void attachBottomFragment(Fragment bottomFragment){
		addFragmentToView(R.id.second_view, bottomFragment);
	}


	/**
	 * Modify dragged view pivot based on the dragged view vertical position to simulate a horizontal
	 * displacement while the view is dragged.
	 */
	void changeDragViewPosition(){
		transformer.updatePosition(getVerticalDragOffset());
	}


	/**
	 * Modify secondView position to be always below dragged view.
	 */
	void changeSecondViewPosition(){
		ViewHelper.setY(secondView, dragView.getBottom());
	}


	/**
	 * Modify dragged view scale based on the dragged view vertical position and the scale factor.
	 */
	void changeDragViewScale(){
		transformer.updateScale(getVerticalDragOffset());
	}


	/**
	 * Modify the background alpha if has been configured to applying an alpha effect when the view
	 * is dragged.
	 */
	void changeBackgroundAlpha(){
		Drawable background = getBackground();
		if(background != null){
			int newAlpha = (int) (ONE_HUNDRED * (1 - getVerticalDragOffset()));
			background.setAlpha(newAlpha);
		}
	}


	/**
	 * Modify the second view alpha based on dragged view vertical position.
	 */
	void changeSecondViewAlpha(){
		ViewHelper.setAlpha(secondView, 1 - getVerticalDragOffset());
	}


	/**
	 * Modify dragged view alpha based on the horizontal position while the view is being
	 * horizontally dragged.
	 */
	void changeDragViewViewAlpha(){
		if(enableHorizontalAlphaEffect){
			float alpha = 1 - getHorizontalDragOffset();
			if(alpha == 0){
				alpha = 1;
			}
			ViewHelper.setAlpha(dragView, alpha);
		}
	}


	/**
	 * Restore view alpha to 1
	 */
	void restoreAlpha(){
		if(enableHorizontalAlphaEffect && ViewHelper.getAlpha(dragView) < 1){
			ViewHelper.setAlpha(dragView, 1);
		}
	}


	/**
	 * Check if dragged view is above the middle of the custom view.
	 *
	 * @return true if dragged view is above the middle of the custom view or false if is below.
	 */
	boolean isDragViewAboveTheMiddle(){
		return transformer.isAboveTheMiddle();
	}


	/**
	 * Check if dragged view is next to the left bound.
	 *
	 * @return true if dragged view right position is behind the right half of the custom view.
	 */
	boolean isNextToLeftBound(){
		return transformer.isNextToLeftBound();
	}


	/**
	 * Check if dragged view is next to the right bound.
	 *
	 * @return true if dragged view left position is behind the left quarter of the custom view.
	 */
	boolean isNextToRightBound(){
		return transformer.isNextToRightBound();
	}


	/**
	 * Check if dragged view is at the top of the custom view.
	 *
	 * @return true if dragged view top position is equals to zero.
	 */
	boolean isDragViewAtTop(){
		return transformer.isViewAtTop();
	}


	/**
	 * Check if dragged view is at the right of the custom view.
	 *
	 * @return true if dragged view right position is equals to custom view width.
	 */
	boolean isDragViewAtRight(){
		return transformer.isViewAtRight();
	}


	/**
	 * Check if dragged view is at the bottom of the custom view.
	 *
	 * @return true if dragged view bottom position is equals to custom view height.
	 */
	boolean isDragViewAtBottom(){
		return transformer.isViewAtBottom();
	}


	/**
	 * Calculate if one position is above any view.
	 *
	 * @param view to analyze.
	 * @param x    position.
	 * @param y    position.
	 * @return true if x and y positions are below the view.
	 */
	private boolean isViewHit(View view, int x, int y){
		int[] viewLocation = new int[2];
		view.getLocationOnScreen(viewLocation);
		int[] parentLocation = new int[2];
		this.getLocationOnScreen(parentLocation);
		int screenX = parentLocation[0] + x;
		int screenY = parentLocation[1] + y;
		return screenX >= viewLocation[0] && screenX < viewLocation[0] + view.getWidth() && screenY >= viewLocation[1] && screenY < viewLocation[1] + view
			.getHeight();
	}


	/**
	 * Use FragmentManager to attach one fragment to one view using the viewId.
	 *
	 * @param viewId   used to obtain the view.
	 * @param fragment to be attached.
	 */
	private void addFragmentToView(final int viewId, final Fragment fragment){
		fragmentManager.beginTransaction().replace(viewId, fragment).commit();
	}


	/**
	 * Initialize the viewDragHelper.
	 */
	private void initializeViewDragHelper(){
		viewDragHelper =
			ViewDragHelper.create(this, SENSITIVITY, new DraggableViewCallback(this, dragView));
	}


	/**
	 * Initialize Transformer with a scalable or change width/height implementation.
	 */
	private void initializeTransformer(TypedArray attributes){
		topViewResize =
			attributes.getBoolean(R.styleable.draggable_view_top_view_resize, DEFAULT_TOP_VIEW_RESIZE);
		TransformerFactory transformerFactory = new TransformerFactory();
		transformer = transformerFactory.getTransformer(topViewResize, dragView, this);
		transformer.setViewHeight(
			attributes.getDimensionPixelSize(
				R.styleable.draggable_view_top_view_height, DEFAULT_TOP_VIEW_HEIGHT
			)
		);
		transformer.setXScaleFactor(
			attributes.getFloat(
				R.styleable.draggable_view_top_view_x_scale_factor, DEFAULT_SCALE_FACTOR
			)
		);
		transformer.setYScaleFactor(
			attributes.getFloat(
				R.styleable.draggable_view_top_view_y_scale_factor, DEFAULT_SCALE_FACTOR
			)
		);
		transformer.setMarginRight(
			attributes.getDimensionPixelSize(
				R.styleable.draggable_view_top_view_margin_right, DEFAULT_TOP_VIEW_MARGIN
			)
		);
		transformer.setMarginBottom(
			attributes.getDimensionPixelSize(
				R.styleable.draggable_view_top_view_margin_bottom, DEFAULT_TOP_VIEW_MARGIN
			)
		);
	}


	/**
	 * Initialize XML attributes.
	 *
	 * @param attrs to be analyzed.
	 */
	private void initializeAttributes(AttributeSet attrs){
		TypedArray attributes =
			getContext().obtainStyledAttributes(attrs, R.styleable.draggable_view);
		this.enableHorizontalAlphaEffect = attributes.getBoolean(
			R.styleable.draggable_view_enable_minimized_horizontal_alpha_effect, DEFAULT_ENABLE_HORIZONTAL_ALPHA_EFFECT
		);
		this.enableClickToMaximize = attributes.getBoolean(
			R.styleable.draggable_view_enable_click_to_maximize_view, DEFAULT_ENABLE_CLICK_TO_MAXIMIZE
		);
		this.enableClickToMinimize = attributes.getBoolean(
			R.styleable.draggable_view_enable_click_to_minimize_view, DEFAULT_ENABLE_CLICK_TO_MINIMIZE
		);
		this.attributes = attributes;
	}


	/**
	 * Realize an smooth slide to an slide offset passed as argument. This method is the base of
	 * maximize, minimize and close methods.
	 *
	 * @param slideOffset to apply
	 * @return true if the view is slided.
	 */
	private boolean smoothSlideTo(float slideOffset){
		final int topBound = getPaddingTop();
		int x = (int) (slideOffset * (getWidth() - transformer.getMinWidthPlusMarginRight()));
		int y = (int) (topBound + slideOffset * getVerticalDragRange());
		if(viewDragHelper.smoothSlideViewTo(dragView, x, y)){
			ViewCompat.postInvalidateOnAnimation(this);
			return true;
		}
		return false;
	}


	/**
	 * @return configured dragged view margin right configured.
	 */
	private int getDragViewMarginRight(){
		return transformer.getMarginRight();
	}


	/**
	 * @return configured dragged view margin bottom.
	 */
	private int getDragViewMarginBottom(){
		return transformer.getMarginBottom();
	}


	/**
	 * Calculate the dragged view left position normalized between 1 and 0.
	 *
	 * @return absolute value between the dragged view  left position divided by custon view width
	 */
	private float getHorizontalDragOffset(){
		return (float) Math.abs(dragView.getLeft()) / (float) getWidth();
	}


	/**
	 * Calculate the dragged view  top position normalized between 1 and 0.
	 *
	 * @return dragged view top divided by vertical drag range.
	 */
	private float getVerticalDragOffset(){
		return dragView.getTop() / getVerticalDragRange();
	}


	/**
	 * Calculate the vertical drag range between the custom view and dragged view.
	 *
	 * @return the difference between the custom view height and the dragged view height.
	 */
	private float getVerticalDragRange(){
		return getHeight() - transformer.getMinHeightPlusMargin();
	}


	/**
	 * Notify te view is maximized to the DraggableListener
	 */
	private void notifyMaximizeToListener(){
		if(listener != null){
			listener.onMaximized();
		}
	}


	/**
	 * Notify te view is minimized to the DraggableListener
	 */
	private void notifyMinimizeToListener(){
		if(listener != null){
			listener.onMinimized();
		}
	}


	/**
	 * Notify te view is closed to the right to the DraggableListener
	 */
	private void notifyCloseToRightListener(){
		if(listener != null){
			listener.onClosedToRight();
		}
	}


	/**
	 * Notify te view is closed to the left to the DraggableListener
	 */
	private void notifyCloseToLeftListener(){
		if(listener != null){
			listener.onClosedToLeft();
		}
	}


	public int getDraggedViewHeightPlusMarginTop(){
		return transformer.getMinHeightPlusMargin();
	}
}
