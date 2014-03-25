package com.github.pedrovgs;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.AttributeSet;
import android.widget.FrameLayout;

/**
 * @author Pedro Vicente Gómez Sánchez.
 */
public class DraggablePanel extends FrameLayout {

    private static final float DEFAULT_TOP_FRAGMENT_HEIGHT = 200;
    private static final float DEFAULT_SCALE_FACTOR = 2;
    private static final float DEFAULT_TOP_FRAGMENT_MARGIN = 0;

    private DraggableView draggableView;
    private Fragment topFragment;
    private Fragment bottomFragment;
    private FragmentManager fragmentManager;
    private float topFragmentHeight;
    private float topFragmentMarginRight;
    private float scaleFactor;
    private float topFragmentMarginBottom;

    private DraggableListener draggableListener;

    public DraggablePanel(Context context) {
        super(context);
        initializeEditMode();
    }

    public DraggablePanel(Context context, AttributeSet attrs) {
        super(context, attrs);
        initializeAttrs(attrs);
        initializeEditMode();
    }


    public DraggablePanel(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initializeAttrs(attrs);
        initializeEditMode();
    }

    private void initializeAttrs(AttributeSet attrs) {
        TypedArray attributes = getContext().obtainStyledAttributes(attrs, R.styleable.draggable_panel);
        this.topFragmentHeight = attributes.getDimension(R.styleable.draggable_panel_top_fragment_height, DEFAULT_TOP_FRAGMENT_HEIGHT);
        this.scaleFactor = attributes.getFloat(R.styleable.draggable_panel_scale_factor, DEFAULT_SCALE_FACTOR);
        this.topFragmentMarginRight = attributes.getDimension(R.styleable.draggable_panel_top_fragment_margin_right, DEFAULT_TOP_FRAGMENT_MARGIN);
        this.topFragmentMarginBottom = attributes.getDimension(R.styleable.draggable_panel_top_fragment_margin_bottom, DEFAULT_TOP_FRAGMENT_MARGIN);
    }

    private void initializeEditMode() {
        if (isInEditMode()) {
            inflate(getContext(), R.layout.draggable_panel, this);
        }
    }


    public void setTopViewHeight(float topFragmentHeight) {
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

    public void setDraggableListener(DraggableListener draggableListener) {
        this.draggableListener = draggableListener;
    }

    public void closeToLeft() {
        draggableView.closeToLeft();
    }

    public void closeToRight() {
        draggableView.closeToRight();
    }

    public void maximize() {
        draggableView.maximize();
    }

    public void minimize() {
        draggableView.minimize();
    }

    /**
     * TODO we have to remove this mehtod and use initializeView as part of creation flow.
     */
    public void initializeView() {
        checkFragmentConsistency();
        checkSupportFragmentmanagerConsistency();

        inflate(getContext(), R.layout.draggable_panel, this);
        draggableView = (DraggableView) findViewById(R.id.draggableView);
        draggableView.setTopViewHeight(topFragmentHeight);
        draggableView.setFragmentManager(fragmentManager);
        draggableView.attachTopFragment(topFragment);

        draggableView.setBackground(getBackground());
        setBackground(null);

        draggableView.setTopViewScaleFactor(scaleFactor);
        draggableView.setTopViewMarginRight(topFragmentMarginRight);
        draggableView.setTopViewMarginBottom(topFragmentMarginBottom);
        draggableView.attachBottomFragment(bottomFragment);

        draggableView.setDraggableListener(draggableListener);
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
