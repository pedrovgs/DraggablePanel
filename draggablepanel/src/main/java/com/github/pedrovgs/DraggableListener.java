package com.github.pedrovgs;

/**
 * @author Pedro Vicente Gómez Sánchez.
 */
public interface DraggableListener {

    public void onDraggableViewReleased(float xVel, float yVel);

    public void onDraggableViewPositionChanged(int left, int top, int dx, int dy);

}
