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

import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWKeyCallback;
import org.lwjgl.glfw.GLFWvidmode;
import org.lwjglui.core.registry.CoreRegistry;
import org.lwjglui.math.Size;

import java.nio.ByteBuffer;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.glfw.GLFW.GLFW_RESIZABLE;
import static org.lwjgl.glfw.GLFW.glfwWindowHint;
import static org.lwjgl.opengl.GL11.GL_FALSE;
import static org.lwjgl.opengl.GL11.GL_TRUE;
import static org.lwjgl.system.MemoryUtil.*;

/**
 * Created by ben on 04/05/15.
 * <p>
 * JGUILibrary
 */
public class Window {

    // We need to strongly reference callback instances.
    private GLFWErrorCallback errorCallback;
    private GLFWKeyCallback keyCallback;

    // Window referencing handle
    private long windowHandle;

    // Is window visible
    private boolean isVisisble;

    // size of window
    private Size size;

    // Title
    private String title;

    public Window(Size sizeOfWindow, String title) {
        size = sizeOfWindow;
        this.title = title;
    }

    /**
     * Creates window but does not make it visible
     */
    public void createWindow() {

        setWindowHints();

        // Create the window
        setWindowHandle(glfwCreateWindow(size.getWidth(), size.getHeight(), title, NULL, NULL));

        // Check to see if window creation was successful
        if (getWindowHandle() == NULL)
            throw new  RuntimeException("Failed to create a GLFW window");

        // Setup a key callback. It will be called every time a key is pressed, repeated or released.
        glfwSetKeyCallback(getWindowHandle(), keyCallback = new GLFWKeyCallback() {
            @Override
            public void invoke(long window, int key, int scancode, int action, int mods) {
                CoreRegistry.get(WindowManager.class).processInput(window, key, scancode, action, mods);
            }
        });
        // Get the resolution of the primary monitor
        ByteBuffer vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());
        // Center our window
        glfwSetWindowPos(
                getWindowHandle(),
                (GLFWvidmode.width(vidmode) - size.getWidth()) / 2,
                (GLFWvidmode.height(vidmode) - size.getHeight()) / 2
        );

        // Make the OpenGL context current
        glfwMakeContextCurrent(getWindowHandle());
        // Enable v-sync
        glfwSwapInterval(1);

        // Make the window visible
        glfwShowWindow(getWindowHandle());
    }

    private void setWindowHandle(long windowHandle) {
        this.windowHandle = windowHandle;
    }

    public void visible(boolean isVisisble) {
        this.isVisisble = isVisisble;
    }

    public long getWindowHandle() {
        return windowHandle;
    }

    /**
     * Sets the window hints
     */
    private void setWindowHints() {
        //TODO Add a more customizable approach - get rid of setting to visible and such
        glfwDefaultWindowHints();
        glfwWindowHint(GLFW_VISIBLE, GL_FALSE);
        glfwWindowHint(GLFW_RESIZABLE, GL_TRUE);
        glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3);
        glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 3);
        glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);
    }

    public GLFWErrorCallback getErrorCallback() {
        return errorCallback;
    }

    public void setErrorCallback(GLFWErrorCallback errorCallback) {
        this.errorCallback = errorCallback;
    }

    public GLFWKeyCallback getKeyCallback() {
        return keyCallback;
    }

    public void setKeyCallback(GLFWKeyCallback keyCallback) {
        this.keyCallback = keyCallback;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }
}
