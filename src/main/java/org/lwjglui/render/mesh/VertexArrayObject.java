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

package org.lwjglui.render.mesh;

import org.lwjglui.core.registry.CoreRegistry;
import org.slf4j.Logger;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL30.glBindVertexArray;
import static org.lwjgl.opengl.GL30.glDeleteVertexArrays;
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

        CoreRegistry.get(Logger.class).info("Compiled Vertex Array Object");
    }

    @Deprecated
    public void render() {
        glBindVertexArray(vaoID);
        vertexBufferObject.bindIBO();
        glDrawElements(GL_TRIANGLES, vertexBufferObject.getIndex().size(), GL_UNSIGNED_INT, 0);
        vertexBufferObject.unBind();
        glBindVertexArray(0);
    }

    public VertexBufferObject getVertexBufferObject() {
        return vertexBufferObject;
    }

    public void setVertexBufferObject(VertexBufferObject vertexBufferObject) {
        this.vertexBufferObject = vertexBufferObject;
    }

    public void destroy() {
        glDeleteVertexArrays(vaoID);
        vertexBufferObject.destroy();
    }
}
