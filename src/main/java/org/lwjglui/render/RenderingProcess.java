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

import org.lwjglui.core.registry.CoreRegistry;
import org.lwjglui.gui.elements.logic.Button;
import org.lwjglui.gui.logic.UIElement;
import org.lwjglui.gui.elements.renderers.ButtonRenderer;
import org.lwjglui.gui.renderable.ElementRenderers;
import org.lwjglui.render.mesh.VertexArrayObject;
import org.lwjglui.render.shader.Shader;
import org.lwjglui.render.mesh.MeshBuilder;

/**
 * Created by ben on 15/05/15.
 * <p>
 * JGUILibrary
 */
public class RenderingProcess {

    public VertexArrayObject vertexArrayObject;
    Shader newShader;
    Texture newTexture;
    Camera camera;

    Button button;

    public void initialize() {

        camera = new Camera();

        CoreRegistry.put(Camera.class, camera);

        camera.init();
        camera.getTransform().updateTransformation();
//        camera.move(Axis.toVector(Axis.Z_AXIS), 1);
        camera.getTransform().updateCameraTransfomationWithQuaternion();

        vertexArrayObject = MeshBuilder.createBox(1, 1);
//        vertexArrayObject = new VertexArrayObject();
//        vertexArrayObject.getVertexBufferObject().getVertexArray()
//                .addVertex(new Vertex(new Vector3f(0, 0, -1), new Vector2f(0, 0)))
//                .addVertex(new Vertex(new Vector3f(1, 0, -1), new Vector2f(1, 0)))
//                .addVertex(new Vertex(new Vector3f(1, 1, -1), new Vector2f(1, 1)))
//                .addVertex(new Vertex(new Vector3f(0, 1, -1), new Vector2f(0, 1)));
//        vertexArrayObject.getVertexBufferObject().addIndex(0).addIndex(2).addIndex(3).addIndex(0).addIndex(1).addIndex(2);
//        vertexArrayObject.getVertexBufferObject().addIndex(0).addIndex(1).addIndex(2).addIndex(0).addIndex(2).addIndex(3);
//        vertexArrayObject.compile();

        newShader = new Shader("hello");
        newShader.addUniform("projectionMatrix");
        newShader.addUniform("viewMatrix");
        newTexture = new Texture("bricks.png");

        newShader.addUniform("text");

        button = new Button();
        ElementRenderers.setElementRenderer(Button.class, new ButtonRenderer().initialize());
    }

    public void render() {
        processInput();
//        glUseProgram(newShader.getProgramID());
//        glActiveTexture(GL_TEXTURE0);
//        glBindTexture(GL_TEXTURE_2D, newTexture.getTextureID());
//        newShader.updateUniformInt("text", 0);
//        newShader.updateUniformMatrix4f("projectionMatrix", camera.getProjectionMatrix());
//        newShader.updateUniformMatrix4f("viewMatrix", camera.getTransform().getTransformationMatrix());
//        vertexArrayObject.render();
//        glBindTexture(GL_TEXTURE_2D, 0);
//        glUseProgram(0);

        renderElement(button);
    }

    public void processInput() {

    }

    public void renderElement(UIElement element) {
        ElementRenderers.getElementRenderer(element.getClass()).render(element);
    }

    public void destory() {
        vertexArrayObject.destroy();
    }
}
