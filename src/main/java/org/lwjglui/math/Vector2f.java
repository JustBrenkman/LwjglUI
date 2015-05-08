package org.lwjglui.math;

/**
 * Created by ben on 04/05/15.
 * <p>
 * JGUILibrary
 */
public class Vector2f {

    private float x, y;

    public Vector2f(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public Vector2f() {
        this.x = 0;
        this.y = 0;
    }

    // Getters and Setters

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    // Extra Util methods
    public Vector2f toVector2f() {
        return new Vector2f(x, y);
    }
}
