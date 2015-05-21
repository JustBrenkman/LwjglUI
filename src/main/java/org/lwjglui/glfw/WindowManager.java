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

package org.lwjglui.glfw;

import org.lwjglui.core.registry.CoreRegistry;
import org.lwjglui.gui.event.UIEventManager;
import org.lwjglui.math.Size;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.GL_TRUE;

/**
 * Created by ben on 15/05/15.
 * <p>
 * JGUILibrary
 */
public class WindowManager {

    private HashMap<String, Window> windows = new HashMap<>();

    private Logger logger = LoggerFactory.getLogger(WindowManager.class);

    public WindowManager() {
    }

    public Window createWindow(String name, Size size) {
        Window window = new Window(size, name);
        window.createWindow();
        windows.put(name, window);
        return window;
    }

    public void registerWindow(Window window, String name) {
        if (windows.get(name) != null) {
            windows.put(name, window);
        }
    }

    public Window getWindow(String name) {
        return windows.get(name);
    }

    public void update(int delta) {

    }

    public void processInput(long window, int key, int scancode, int action, int mods) {
        if ( key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE )
            glfwSetWindowShouldClose(window, GL_TRUE); // We will detect this in our rendering loop
        CoreRegistry.get(UIEventManager.class).processMouseInputs();
//        logger.info("processed input: " + key);
    }
}
