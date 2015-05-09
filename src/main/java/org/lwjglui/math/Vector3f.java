/*
 * Copyright (c) 2015.
 */

package org.lwjglui.math;

/**
 * Created by ben on 08/05/15.
 * <p>
 * JGUILibrary
 */
public class Vector3f {

    // x, y, z variables for the vector
    private float x, y, z;

    public Vector3f(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vector3f(float[] xyzArray) {
        if (xyzArray.length == 3) {
            this.x = xyzArray[0];
            this.y = xyzArray[1];
            this.z = xyzArray[2];
        } else {
            this.x = 0;
            this.y = 0;
            this.z = 0;
        }
    }

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

    public float getZ() {
        return z;
    }

    public void setZ(float z) {
        this.z = z;
    }

    public float length() {
        return (float)Math.sqrt(x * x + y * y + z * z);
    }

    public float max() {
        return Math.max(x, Math.max(y, z));
    }
}
