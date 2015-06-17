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

package org.lwjglui.core;

/**
 * Created by ben on 04/05/15.
 * <p/>
 * JGUILibrary
 */

import org.lwjgl.BufferUtils;
import org.lwjgl.Sys;
import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;
import org.lwjglui.core.registry.CoreRegistry;
import org.lwjglui.glfw.Mouse;
import org.lwjglui.glfw.Window;
import org.lwjglui.glfw.WindowManager;
import org.lwjglui.gui.GUIManager;
import org.lwjglui.gui.event.UIEventManager;
import org.lwjglui.math.Size;
import org.lwjglui.render.Camera;
import org.lwjglui.render.RenderingProcess;
import org.lwjglui.render.shader.ShaderManager;
import org.lwjglui.util.ColorPalette;
import org.lwjglui.util.PathManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.nio.IntBuffer;

import static org.lwjgl.glfw.Callbacks.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

public class Core {

    // We need to strongly reference callback instances.
    private GLFWErrorCallback errorCallback;
    private GLFWKeyCallback   keyCallback;

    // logger for Class Core.class
    private static Logger logger = LoggerFactory.getLogger(Core.class);

    // The window handle
    private long window;

    // TODO manage window creation and handling better
    public Window windowR;

    RenderingProcess rp = new RenderingProcess();

    IntBuffer width = BufferUtils.createIntBuffer(1);
    IntBuffer height = BufferUtils.createIntBuffer(1);

    public void run() {
//        System.out.println("Hello LWJGL " + Sys.getVersion() + "!");

        try {
            init();
            loop();

            glfwDestroyWindow(windowR.getWindowHandle());
            windowR.getKeyCallback().release();
        } finally {
            glfwTerminate();
            errorCallback.release();
        }
    }

    public void init() {
        glfwSetErrorCallback(errorCallback = errorCallbackPrint(System.err));

        if (glfwInit() != GL11.GL_TRUE)
            throw new IllegalStateException("Cannot Initialize GLFW");

        windowR = new Window(new Size(300, 300), "LWJGL Window Class test");
        windowR.createWindow();
        CoreRegistry.put(Window.class, windowR);
        Mouse.create();
    }

    private void loop() {
        // This line is critical for LWJGL's interoperation with GLFW's
        // OpenGL context, or any context that is managed externally.
        // LWJGL detects the context that is current in the current thread,
        // creates the ContextCapabilities instance and makes the OpenGL
        // bindings available for use.
        GLContext.createFromCurrent();
        System.out.println("Version " + glGetString(GL_VERSION));
        logger.info("LWJGL: {} / {}", Sys.getVersion(), Sys.getVersion());
        logger.info("GL_VENDOR: {}", GL11.glGetString(GL11.GL_VENDOR));
        logger.info("GL_RENDERER: {}", GL11.glGetString(GL11.GL_RENDERER));
        logger.info("GL_VERSION: {}", GL11.glGetString(GL11.GL_VERSION));
        logger.info("SHADING_LANGUAGE VERSION: {}", GL11.glGetString(GL20.GL_SHADING_LANGUAGE_VERSION));

        Color backColor = new Color(0x3F51B5);

        // Set the clear color
        glClearColor(backColor.getRed() / 255.0f, backColor.getGreen() / 255.0f, backColor.getBlue() / 255.0f, backColor.getAlpha() / 255.0f);


        glEnable(GL_TEXTURE_2D);
        glEnable(GL_DEPTH_TEST);
        glEnable(GL_CULL_FACE);
        glCullFace(GL_BACK);
        glEnable (GL_BLEND);
        glBlendFunc (GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);

        initRendering();

        // Run the rendering loop until the user has attempted to close
        // the window or has pressed the ESCAPE key.
        while ( glfwWindowShouldClose(windowR.getWindowHandle()) == GL_FALSE ) {
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); // clear the framebuffer
            CoreRegistry.get(GUIManager.class).stepMouseInteraction();
            CoreRegistry.get(Camera.class).updateTransform();

            CoreRegistry.get(GUIManager.class).world.step(1f / 60f, 6, 2);

            glfwGetWindowSize(windowR.getWindowHandle(), width, height);

            windowR.getSize().setWidth(width.get(0));
            windowR.getSize().setHeight(height.get(0));

            render();
            glfwSwapBuffers(windowR.getWindowHandle()); // swap the color buffers

            // Poll for window event. The key callback above will only be
            // invoked during this call.
            glfwPollEvents();
        }
    }

    private void initRendering() {
        rp.initialize();
    }

    public void render() {
        rp.render();
    }

    public static void main(String[] args) {
        System.setProperty("java.library.path", "/home/ben/Documents/Programs/Terasology/LwjglUI/src/main/resources/libs/lwjgl/native");
        System.setProperty("org.slf4j.simpleLogger.defaultLogLevel", "debug");
        CoreRegistry.put(Core.class, new Core());
        CoreRegistry.put(Logger.class, logger);
        PathManager.initialize();
        CoreRegistry.put(PathManager.class, PathManager.getInstance());
        CoreRegistry.put(ShaderManager.class, new ShaderManager());
        CoreRegistry.put(WindowManager.class, new WindowManager());
        CoreRegistry.put(UIEventManager.class, new UIEventManager());
        CoreRegistry.put(GUIManager.class, new GUIManager());
        ColorPalette.initialize();
        CoreRegistry.get(Core.class).run();
        CoreRegistry.get(Core.class).cleanUp();
    }

    public void cleanUp() {
        CoreRegistry.get(ShaderManager.class).destroyAllShaders();
        logger.info("Destroyed all shaders");

        logger.info("Destroyed vao");
        rp.destory();
    }
}
