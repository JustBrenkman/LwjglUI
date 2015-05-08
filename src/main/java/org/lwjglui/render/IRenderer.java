package org.lwjglui.render;

/**
 * Created by ben on 05/05/15.
 * <p>
 * JGUILibrary
 */
public interface IRenderer {

    public void initialize();

    public void renderImage();

    public void renderLine();

    public void renderRectangle();

    public void renderTriangle();

    public void renderCircle();

    public void renderVBO();

    public void setFrameBuffer();

    public void renderMesh(IRenderable renderable);

    public void renderElement();

    public void clearBuffers();

    public void uploadVBO();

    public void shutDown();

    public void enableScissor(int x, int y, int width, int height);

    public void disableScissor();

    public void setUpMonitoring();

    public void updateMonitoring();

//    public void setInternalResolution();
}
