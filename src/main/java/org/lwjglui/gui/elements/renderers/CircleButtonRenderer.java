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

package org.lwjglui.gui.elements.renderers;

import org.lwjglui.core.registry.CoreRegistry;
import org.lwjglui.gui.elements.logic.CircleButton;
import org.lwjglui.gui.logic.UIElement;
import org.lwjglui.gui.renderable.ElementRenderer;
import org.lwjglui.render.Camera;
import org.lwjglui.render.Material;
import org.lwjglui.render.shader.Shader;
import org.lwjglui.util.ColorGrade;
import org.lwjglui.util.ColorName;
import org.lwjglui.util.ColorPalette;

import static org.lwjgl.opengl.GL20.glUseProgram;

/**
 * Created by ben on 18/06/15.
 */
public class CircleButtonRenderer extends ElementRenderer {

    public CircleButtonRenderer() {
        super();
    }

    @Override
    public ElementRenderer initialize() {
        shader = new Shader("circle");
        shader.addUniform("projectionMatrix");
        shader.addUniform("viewMatrix");
        shader.addUniform("modelMatrix");
        shader.addUniform("colorScheme");
        shader.addUniform("centerPoint");
        shader.addUniform("radius");

        material = new Material(ColorPalette.getColor(ColorName.RED, ColorGrade.P900));
        return this;
    }

    @Override
    public void render(UIElement ui) {

        glUseProgram(shader.getProgramID());
        shader.updateUniformColor("colorScheme", material.getColor());
        shader.updateUniformMatrix4f("projectionMatrix", CoreRegistry.get(Camera.class).getOrthoGraphicMatrix());
        shader.updateUniformMatrix4f("viewMatrix", CoreRegistry.get(Camera.class).getTransform().getTransformationMatrix());
        ui.getTransform().updateTransformation();
        shader.updateUniformMatrix4f("modelMatrix", ui.getTransform().getTransformationMatrix());
        shader.updateUniformFloat("radius", ui.getSize().getWidth() / 2);
        shader.updateUniformVector2f("centerPoint", ((CircleButton) ui).centerPoint);

        ui.getElementMesh().getVertexArrayObject().render();

        glUseProgram(0);
    }
}
