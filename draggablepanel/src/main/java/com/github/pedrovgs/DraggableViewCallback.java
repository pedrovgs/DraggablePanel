package com.github.pedrovgs;

import android.support.v4.widget.ViewDragHelper;
import android.view.View;

/**
 * @author Pedro Vicente Gómez Sánchez.
 */
public class DraggableViewCallback extends ViewDragHelper.Callback {

    private static final int MINIMUM_DY_FOR_VERTICAL_DRAG = 10;
    private static final int MINIMUN_DX_FOR_HORIZONTAL_DRAG = 20;


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
    public void onViewReleased(View releasedChild, float xvel, float yvel) {
        super.onViewReleased(releasedChild, xvel, yvel);
        if (!draggableView.isDragViewAtBottom()) {
            if (draggableView.isHeaderAboveTheMiddle()) {
                draggableView.maximize();
            } else {
                draggableView.minimize();
            }
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

    @Override
    public boolean tryCaptureView(View view, int pointerId) {
        return view == capturedView;
    }

    @Override
    public int clampViewPositionHorizontal(View child, int left, int dx) {
        int newLeft = 0;
        if (((draggableView.isMinimized() && (dx > MINIMUN_DX_FOR_HORIZONTAL_DRAG || dx < -MINIMUN_DX_FOR_HORIZONTAL_DRAG))) || (draggableView.isDragViewAtBottom() && !draggableView.isDragViewAtRight())) {
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
