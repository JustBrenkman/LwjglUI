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

package org.lwjglui.gui.elements.logic;

import org.lwjglui.gui.logic.UIElement;
import org.lwjglui.gui.renderable.ElementRenderers;
import org.lwjglui.math.Size;
import org.lwjglui.math.vector.Vector2f;
import org.lwjglui.util.ColorPalette;

import java.awt.*;

/**
 * Created by ben on 20/05/15.
 * <p>
 * JGUILibrary
 */
public class Button extends UIElement {

    public Vector2f centerPoint;

    private Color hover = ColorPalette.getColor(ColorPalette.ColorName.RED, ColorPalette.ColorGrade.P500);
    private Color off = ColorPalette.getColor(ColorPalette.ColorName.RED, ColorPalette.ColorGrade.P900);

    public Button(float x, float y, int width, int height) {
        this(x, y, new Size(width, height));
    }

    public Button(float x, float y, Size size) {
        super(x, y, size);
        centerPoint = new Vector2f(x + ((float) size.getWidth() / 2.0f), y + ((float) size.getHeight() / 2.0f));
    }

    @Override
    public void initializeMesh() {
        getElementMesh().addBoxToMesh(0, 0, getSize().getWidth(), getSize().getHeight(), 0, 0, 1, 1);
        getElementMesh().compile();
    }

    @Override
    public void initializePhysics() {
        addToPhysics();
    }

    @Override
    public void initializeListeners() {
        addMouseEnterListener(() -> ElementRenderers.getElementRenderer(Button.class).material.color = hover);
        addMouseLeaveListener(() -> ElementRenderers.getElementRenderer(Button.class).material.color = off);
    }
}
