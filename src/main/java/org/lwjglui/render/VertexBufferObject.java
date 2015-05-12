/*
 * Copyright (c) 2015.
 *  All rights reserved.
 *
 *  Redistribution and use in source and binary forms, with or without
 *  modification, are permitted provided that the following conditions are met:
 *
 *  1. Redistributions of source code must retain the above copyright notice, this
 *     list of conditions and the following disclaimer.
 *  2. Redistributions in binary form must reproduce the above copright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *     and/or other materials provided with the distribution.
 *
 *  THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 *  ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 *  WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 *  DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
 *  ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 *  (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 *  LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 *  ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 *  (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 * The views and conclusions contained in the software and documentation are those
 * of the authors and should not be interpreted as representing official policies,
 * either expressed or implied, of the FreeBSD Project.
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
        FloatBuffer floatBuffer = Utils.createFlippedBuffer(vertexArray.toArray(new Vertex[vertexArray.size()]));
//        FloatBuffer floatBuffer = Utils.createFlippedBuffer((Vertex[])vertexArray.toArray());
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
        glBufferData(GL_ELEMENT_ARRAY_BUFFER, Utils.createFlippedBuffer(intBuffer), GL_STATIC_DRAW);

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
