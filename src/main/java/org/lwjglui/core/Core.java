/*
 * Copyright (c) 2015.
 */

package org.lwjglui.core;

/**
 * Created by ben on 04/05/15.
 * <p/>
 * JGUILibrary
 */

import org.lwjgl.Sys;
import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;
import org.lwjglui.core.registry.CoreRegistry;
import org.lwjglui.glfw.Window;
import org.lwjglui.math.Size;
import org.lwjglui.math.Vector3f;
import org.lwjglui.math.Vertex;
import org.lwjglui.render.VertexArrayObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

    public Window windowR;

    public VertexArrayObject vertexArrayObject;

    public void run() {
        System.out.println("Hello LWJGL " + Sys.getVersion() + "!");

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

//        glfwDefaultWindowHints();
//        glfwWindowHint(GLFW_VISIBLE, GL_FALSE);
//        glfwWindowHint(GLFW_RESIZABLE, GL_TRUE);
//
//        int width = 300;
//        int height = 300;
//
//        window = glfwCreateWindow(width, height, "Sup LWJGL 3", NULL, NULL);
//        if (window == NULL)
//            throw new  RuntimeException("Failed to create a GLFW window");
//
//        // Setup a key callback. It will be called every time a key is pressed, repeated or released.
//        glfwSetKeyCallback(window, keyCallback = new GLFWKeyCallback() {
//            @Override
//            public void invoke(long window, int key, int scancode, int action, int mods) {
//                if ( key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE )
//                    glfwSetWindowShouldClose(window, GL_TRUE); // We will detect this in our rendering loop
//            }
//        });
//
//        // Get the resolution of the primary monitor
//        ByteBuffer vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());
//        // Center our window
//        glfwSetWindowPos(
//                window,
//                (GLFWvidmode.width(vidmode) - width) / 2,
//                (GLFWvidmode.height(vidmode) - height) / 2
//        );
//
//        // Make the OpenGL context current
//        glfwMakeContextCurrent(window);
//        // Enable v-sync
//        glfwSwapInterval(1);
//
//        // Make the window visible
//        glfwShowWindow(window);

        windowR = new Window(new Size(300, 300), "LWJGL Window Class test");
        windowR.createWindow();
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

        // Set the clear color
        glClearColor(1.0f, 0.0f, 0.0f, 0.0f);

        initRendering();

        // Run the rendering loop until the user has attempted to close
        // the window or has pressed the ESCAPE key.
        while ( glfwWindowShouldClose(windowR.getWindowHandle()) == GL_FALSE ) {
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); // clear the framebuffer
            render();
            glfwSwapBuffers(windowR.getWindowHandle()); // swap the color buffers

            // Poll for window event. The key callback above will only be
            // invoked during this call.
            glfwPollEvents();
        }
    }

    private void initRendering() {
        vertexArrayObject = new VertexArrayObject();
        vertexArrayObject.getVertexBufferObject().getVertexArray().addVertex(new Vertex(new Vector3f(0, 0, 0))).addVertex(new Vertex(new Vector3f(1, 0, 0))).addVertex(new Vertex(new Vector3f(1, 1, 0)));
        vertexArrayObject.getVertexBufferObject().addIndex(0).addIndex(1).addIndex(2);
        vertexArrayObject.compile();
    }

    public void render() {
        vertexArrayObject.render();
    }

    public static void main(String[] args) {
        System.setProperty("java.library.path", "/home/ben/Documents/Programs/Terasology/LwjglUI/src/main/resources/libs/lwjgl/native");
        CoreRegistry.put(Core.class, new Core());
        CoreRegistry.get(Core.class).run();
    }
}
