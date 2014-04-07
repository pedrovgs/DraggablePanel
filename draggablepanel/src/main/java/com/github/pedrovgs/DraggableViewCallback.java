package com.github.pedrovgs;

import android.support.v4.widget.ViewDragHelper;
import android.util.Log;
import android.view.View;

/**
 * @author Pedro Vicente Gómez Sánchez.
 */
public class DraggableViewCallback extends ViewDragHelper.Callback {

    private static final int MINIMUN_DX_FOR_HORIZONTAL_DRAG = 25;
    private static final int MINIMUM_DY_FOR_VERTICAL_DRAG = 15;
    private static final float X_MIN_VELOCITY = 1300;
    private static final float Y_MIN_VELOCITY = 1300;


    private DraggableView draggableView;
    private View capturedView;

    public DraggableViewCallback(DraggableView draggableView, View capturedView) {
        this.draggableView = draggableView;
        this.capturedView = capturedView;
    }

    @Override
    public void onViewPositionChanged(View changedView, int left, int top, int dx, int dy) {
        if (draggableView.isDragViewAtBottom()) {
            draggableView.changeDragViewViewAlpha();
        } else {
            draggableView.changeDragViewScale();
            draggableView.changeDragViewPosition();
            draggableView.changeSecondViewAlpha();
            draggableView.changeSeondViewPosition();
            draggableView.changeBackgroundAlpha();
        }
        draggableView.invalidate();
    }


    @Override
    public void onViewReleased(View releasedChild, float xVel, float yVel) {
        super.onViewReleased(releasedChild, xVel, yVel);
        Log.e("DEPURAR", "X-->" + xVel);
        Log.e("DEPURAR", "Y-->" + yVel);

        if (draggableView.isDragViewAtBottom() && !draggableView.isDragViewAtRight()) {
            if (xVel < 0 && xVel <= X_MIN_VELOCITY) {
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
        } else {
            if (yVel < 0 && yVel <= -Y_MIN_VELOCITY) {
                draggableView.maximize();
            } else if (yVel > 0 && yVel >= Y_MIN_VELOCITY) {
                draggableView.minimize();
            } else {
                if (draggableView.isHeaderAboveTheMiddle()) {
                    draggableView.maximize();
                } else {
                    draggableView.minimize();
                }
            }
        }
    }

    @Override
    public boolean tryCaptureView(View view, int pointerId) {
        return view == capturedView;
    }

    @Override
    public int clampViewPositionHorizontal(View child, int left, int dx) {
        int newLeft = 0;
        if ((draggableView.isMinimized() && Math.abs(dx) > MINIMUN_DX_FOR_HORIZONTAL_DRAG) || (draggableView.isDragViewAtBottom() && !draggableView.isDragViewAtRight())) {
            newLeft = left;
        }
        return newLeft;
    }


    @Override
    public int clampViewPositionVertical(View child, int top, int dy) {
        int newTop = draggableView.getHeight() - capturedView.getHeight();
        if (draggableView.isMinimized() && (dy >= MINIMUM_DY_FOR_VERTICAL_DRAG || dy >= -MINIMUM_DY_FOR_VERTICAL_DRAG) || (!draggableView.isMinimized() && !draggableView.isDragViewAtBottom())) {
            final int topBound = draggableView.getPaddingTop();
            final int bottomBound = draggableView.getHeight() - child.getHeight() - child.getPaddingBottom();

            newTop = Math.min(Math.max(top, topBound), bottomBound);
        }
        return newTop;
    }

}
