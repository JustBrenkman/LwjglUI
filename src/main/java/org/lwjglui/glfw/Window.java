package org.lwjglui.glfw;

import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWKeyCallback;
import org.lwjgl.glfw.GLFWvidmode;
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
                if ( key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE )
                    glfwSetWindowShouldClose(window, GL_TRUE); // We will detect this in our rendering loop
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
}
