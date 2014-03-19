package com.github.pedrovgs;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.AttributeSet;
import android.widget.FrameLayout;

/**
 * @author Pedro Vicente Gómez Sánchez.
 */
public class DraggablePanel extends FrameLayout {

    private Fragment topFragment;
    private Fragment bottomFragment;
    private FragmentManager fragmentManager;
    private int topFragmentHeight;
    private float topFragmentMarginRight;
    private int scaleFactor;
    private float topFragmentMarginBottom;

    public DraggablePanel(Context context) {
        super(context);
        initializeEditMode();
    }

    public DraggablePanel(Context context, AttributeSet attrs) {
        super(context, attrs);
        initializeEditMode();
    }


    public DraggablePanel(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initializeEditMode();
    }

    private void initializeEditMode() {
        if (isInEditMode()) {
            inflate(getContext(), R.layout.draggable_panel, this);
        }
    }


    public void setTopViewHeight(int topFragmentHeight) {
        this.topFragmentHeight = topFragmentHeight;
    }

    public void setTopFragment(Fragment topFragment) {
        this.topFragment = topFragment;
    }

    public void setBottomFragment(Fragment bottomFragment) {
        this.bottomFragment = bottomFragment;
    }

    public void setFragmentManager(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
    }

    public void setScaleFactor(int scaleFactor) {
        this.scaleFactor = scaleFactor;
    }

    public void setTopFragmentMarginRight(float topFragmentMarginRight) {
        this.topFragmentMarginRight = topFragmentMarginRight;
    }

    public void setTopFragmentMarginBottom(float topFragmentMarginBottom) {
        this.topFragmentMarginBottom = topFragmentMarginBottom;
    }

    public void initializeView() {
        checkFragmentConsistency();
        checkSupportFragmentmanagerConsistency();

        inflate(getContext(), R.layout.draggable_panel, this);
        DraggableView draggableView = (DraggableView) findViewById(R.id.draggableView);
        draggableView.setTopViewHeight(topFragmentHeight);
        draggableView.setFragmentManager(fragmentManager);
        draggableView.attachTopFragment(topFragment);
        draggableView.setBackground(getBackground());
        setBackground(null);
        draggableView.setScaleFactor(scaleFactor);
        draggableView.setTopFragmentMarginRight(topFragmentMarginRight);
        draggableView.setTopFragmentMarginBottom(topFragmentMarginBottom);
        draggableView.attachBottomFragment(bottomFragment);
    }

    private void checkSupportFragmentmanagerConsistency() {
        if (fragmentManager == null) {
            throw new IllegalStateException("You have to set the support FragmentManager before initialize DraggablePanel");
        }
    }

    private void checkFragmentConsistency() {
        if (topFragment == null || bottomFragment == null) {
            throw new IllegalStateException("You have to set top and bottom fragment before initialize DraggablePanel");
        }
    }

}
