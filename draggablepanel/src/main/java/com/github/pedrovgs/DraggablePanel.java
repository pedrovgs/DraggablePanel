package com.github.pedrovgs;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

/**
 * @author Pedro Vicente Gómez Sánchez.
 */
public class DraggablePanel extends RelativeLayout {

    public DraggablePanel(Context context) {
        super(context);
        initializeView();
    }

    public DraggablePanel(Context context, AttributeSet attrs) {
        super(context, attrs);
        initializeView();
    }

    public DraggablePanel(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initializeView();
    }

    private void initializeView() {
        inflate(getContext(), R.layout.draggable_panel, this);
    }


}
