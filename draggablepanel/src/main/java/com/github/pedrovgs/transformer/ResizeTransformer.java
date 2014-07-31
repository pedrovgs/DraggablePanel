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
        getView().setLayoutParams(params);
        Log.d("DEPURAR", "NEW WIDTH " + newWidth);
    }

    @Override
    public void updateHeight(float verticalDragOffset) {
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) getView().getLayoutParams();
        int newHeight = (int) (getOriginalHeight() * (1 - verticalDragOffset / getxScaleFactor()));
        params.height = newHeight;
        lastHeight = newHeight;
        getView().setLayoutParams(params);
        Log.e("DEPURAR", "NEW HEIGHT " + newHeight);
    }

    @Override
    public float getViewHeight() {
        return lastHeight==0 ? super.getViewHeight() : lastHeight;
    }
}
