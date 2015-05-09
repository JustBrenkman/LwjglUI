/*
 * Copyright (c) 2015.
 */

package org.lwjglui.render;

import org.lwjglui.math.Vertex;

import java.util.Iterator;

import static org.lwjgl.opengl.GL11.GL_TRIANGLES;
import static org.lwjgl.opengl.GL11.GL_UNSIGNED_INT;
import static org.lwjgl.opengl.GL11.glDrawElements;
import static org.lwjgl.opengl.GL30.glBindVertexArray;
import static org.lwjgl.opengl.GL30.glGenVertexArrays;

/**
 * Created by ben on 08/05/15.
 * <p>
 * JGUILibrary
 */
public class VertexArrayObject {

    private VertexBufferObject vertexBufferObject = new VertexBufferObject();
    private int vaoID;

    public VertexArrayObject() {

    }

    public void compile() {

        // creates vertex array ID
        vaoID = glGenVertexArrays();
        glBindVertexArray(vaoID);

        // compiles vertex buffer object
        vertexBufferObject.compile();

        // unbinds array
        glBindVertexArray(0);
    }

    @Deprecated
    public void render() {
        glBindVertexArray(vaoID);
        vertexBufferObject.bindIBO();
        glDrawElements(GL_TRIANGLES, vertexBufferObject.getSize(), GL_UNSIGNED_INT, 0);
        glBindVertexArray(0);
        vertexBufferObject.unBind();
    }

    public VertexBufferObject getVertexBufferObject() {
        return vertexBufferObject;
    }

    public void setVertexBufferObject(VertexBufferObject vertexBufferObject) {
        this.vertexBufferObject = vertexBufferObject;
    }
}
