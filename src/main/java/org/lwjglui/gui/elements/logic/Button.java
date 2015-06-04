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

import org.lwjglui.gui.event.listeners.MouseListener;
import org.lwjglui.gui.logic.IUIElemntLogic;
import org.lwjglui.gui.logic.UIElement;
import org.lwjglui.math.vector.Vector2f;

/**
 * Created by ben on 20/05/15.
 * <p>
 * JGUILibrary
 */
public class Button extends UIElement implements IUIElemntLogic {

    public Button() {
        super();
    }

    @Override
    public void initializeMesh() {
        getElementMesh().addBoxToMesh(0, 0, 0.5f, 0.5f, 0, 0, 1, 1);
        getElementMesh().addTriangleToMesh(-1, -1, 0, 0, -1, 0);
        getElementMesh().compile();
    }

    @Override
    public void initializePhysics() {
        addToPhysics();
    }

    @Override
    public void initializeListeners() {
        addMouseListener(new MouseListener() {
            @Override
            public void onMouseMove(Vector2f vector2i) {

            }

            @Override
            public void onMouseButtonClick(int button) {

            }

            @Override
            public void onMouseDoubleClick(int button) {

            }

            @Override
            public void onMouseTripleClick(int button) {

            }

            @Override
            public void onMouseHover() {

            }

            @Override
            public void onMouseEnter() {

            }

            @Override
            public void onMouseLeave() {

            }
        });
    }
}
