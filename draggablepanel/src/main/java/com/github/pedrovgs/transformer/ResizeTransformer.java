package com.github.pedrovgs.transformer;

import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

/**
 * Transformer extension created to resize the view instead of scale it as the other
 * implementation does.
 *
 * @author Pedro Vicente Gómez Sánchez
 */
public class ResizeTransformer extends Transformer{

    private float lastHeight;

    public ResizeTransformer(View view) {
        super(view);
    }

    @Override
    public void updateWidth(float verticalDragOffset) {
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) getView().getLayoutParams();
        int newWidth = (int) (getOriginalWidth() * (1 - verticalDragOffset / getxScaleFactor()));
        params.width = newWidth;
        setLastLeftPosition((int) (getOriginalWidth()-newWidth));
        getView().setLayoutParams(params);
    }

    @Override
    public void updateHeight(float verticalDragOffset) {
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) getView().getLayoutParams();
        int newHeight = (int) (getOriginalHeight() * (1 - verticalDragOffset / getxScaleFactor()));
        params.height = newHeight;
        lastHeight = newHeight;
        getView().setLayoutParams(params);
    }

    @Override
    public float getViewHeight() {
        return lastHeight==0 ? super.getViewHeight() : lastHeight;
    }

    @Override
    public void updateXPosition(float verticalDragOffset) {
        int left,top,right,bottom;
        left = (int) (getOriginalWidth()-getViewWidth());
        right = getViewRightPosition(verticalDragOffset);
        setLastRightPosition(right);
        top = getView().getTop();
        bottom = getView().getBottom();
        getView().layout(left,top,right,bottom);
        Log.e("DEPURAR","LEFT "+ left+ " - RIGHT "+right);
    }

    @Override
    public void updateYPosition() {
        // ViewDragHelper already changes the Y position.
    }

}
