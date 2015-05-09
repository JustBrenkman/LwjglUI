/*
 * Copyright (c) 2015.
 */

package org.lwjglui.render;

import org.lwjglui.math.Vertex;
import org.lwjglui.util.Utils;

import java.nio.FloatBuffer;
import java.util.ArrayList;

import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL20.*;

/**
 * Created by ben on 08/05/15.
 * <p>
 * JGUILibrary
 */
public class VertexBufferObject {

    private int vboID;
    private int iboID;

    private int size;

    private VertexArray vertexArray;
    private ArrayList<Integer> indecies = new ArrayList<Integer>();

    /**
     * Generates a new VertexArray list
     */
    public VertexBufferObject() {
        vertexArray = new VertexArray();
    }

    /**
     * Size of buffer
     * @return - size
     */
    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public VertexBufferObject addIndex(int i) {
        indecies.add(i);
        return this;
    }

    public void addIndecies(Integer... i) {
        for (Integer integer : i) {
            addIndex(integer);
        }
    }

    /**
     * Destroy's buffers from openGL
     */
    public void destroy() {
        glDeleteBuffers(vboID);
        glDeleteBuffers(iboID);
    }

    public VertexArray getVertexArray() {
        return vertexArray;
    }

    public void setVertexArray(VertexArray vertexArray) {
        this.vertexArray = vertexArray;
    }

    /**
     * generates a float buffer, vbo and uploads the buffer to the vbo. sets the vertex attribute pointers in the vao
     */
    public void compile() {
        FloatBuffer floatBuffer = Utils.CreateFlippedBuffer(vertexArray.toArray(new Vertex[vertexArray.size()]));
//        FloatBuffer floatBuffer = Utils.CreateFlippedBuffer((Vertex[])vertexArray.toArray());
        size = vertexArray.size();

        int[] intBuffer = new int[indecies.size()];

        for (int p = 0; p < intBuffer.length; p++) {
            intBuffer[p] = indecies.get(p);
        }

        vboID = glGenBuffers();

        glBindBuffer(GL_ARRAY_BUFFER, vboID);
        glBufferData(GL_ARRAY_BUFFER, floatBuffer, GL_STATIC_DRAW);

        glVertexAttribPointer(0, 3, GL_FLOAT, false, Vertex.size * 4, 0);
        glVertexAttribPointer(1, 2, GL_FLOAT, false, Vertex.size * 4, 12);
        glVertexAttribPointer(2, 3, GL_FLOAT, false, Vertex.size * 4, 20);
        glVertexAttribPointer(3, 3, GL_FLOAT, false, Vertex.size * 4, 32);

        glEnableVertexAttribArray(0);

        iboID = glGenBuffers();

        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, iboID);
        glBufferData(GL_ELEMENT_ARRAY_BUFFER, Utils.CreateFlippedBuffer(intBuffer), GL_STATIC_DRAW);

        glBindBuffer(GL_ARRAY_BUFFER, 0);
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
    }

    @Deprecated
    public void bindIBO() {
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, iboID);
    }

    @Deprecated
    public void unBind() {
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
    }
}
