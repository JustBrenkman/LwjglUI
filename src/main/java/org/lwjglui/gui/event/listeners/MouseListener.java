/*
 * Copyright (c) 2015.
 */

package org.lwjglui.gui.event.listeners;

import org.lwjglui.math.Vector2f;

/**
 * Created by ben on 06/05/15.
 * Mouse listener, interface class for mouse related events
 * JGUILibrary
 */
public interface MouseListener {

    /**
     * Event listener for mouse moving
     * @param vector2i - change to a Vector2i
     */
    public void onMouseMove(Vector2f vector2i);

    /**
     * When mouse button is clicked
     * @param button - int of button (ID)
     */
    public void onMouseButtonClick(int button);

    /**
     * When mouse button is double clicked
     * @param button - int of button (ID)
     */
    public void onMouseDoubleClick(int button);

    /**
     * When mouse button is triple clicked
     * @param button - int of button (ID)
     */
    public void onMouseTripleClick(int button);

    /**
     * When mouse enters and hovers over element
     */
    public void onMouseHover();

    /**
     * When the mouse enters the region of the element
     */
    public void onMouseEnter();

    /**
     * When the mouse leaves the elements region
     */
    public void onMouseLeave();
}
