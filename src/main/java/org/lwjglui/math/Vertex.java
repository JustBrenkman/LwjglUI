/*
 * Copyright (c) 2015.
 */

package org.lwjglui.math;

/**
 * Created by ben on 08/05/15.
 * <p>
 * JGUILibrary
 */
public class Vertex {

    private Vector3f position;
    private Vector3f normal;
    private Vector3f tangent;

    private Vector2f textCoord;

    public static final int size = 11;

    public Vertex(Vector3f position) {
        this(position, new Vector2f(0, 0));
    }

    public Vertex(Vector3f position, Vector2f textCoord) {
        this(position, textCoord, new Vector3f(0, 0, 0));
    }

    public Vertex(Vector3f position, Vector2f textCoord, Vector3f normal) {
        this(position, textCoord, normal, new Vector3f(0, 0, 0));
    }

    public Vertex(Vector3f position, Vector2f textCoord, Vector3f normal, Vector3f tangent) {
        this.position = position;
        this.normal = normal;
        this.tangent = tangent;
        this.textCoord = textCoord;
    }

    public Vector3f getPosition() {
        return position;
    }

    public void setPosition(Vector3f position) {
        this.position = position;
    }

    public Vector3f getNormal() {
        return normal;
    }

    public void setNormal(Vector3f normal) {
        this.normal = normal;
    }

    public Vector3f getTangent() {
        return tangent;
    }

    public void setTangent(Vector3f tangent) {
        this.tangent = tangent;
    }

    public Vector2f getTextCoord() {
        return textCoord;
    }

    public void setTextCoord(Vector2f textCoord) {
        this.textCoord = textCoord;
    }
}
