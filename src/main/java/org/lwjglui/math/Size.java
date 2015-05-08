package org.lwjglui.math;

/**
 * Created by ben on 04/05/15.
 * <p>
 * JGUILibrary
 */
public class Size {
    private int width, height;

    public Size(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public Size() {
        this.width = 0;
        this.height = 0;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
