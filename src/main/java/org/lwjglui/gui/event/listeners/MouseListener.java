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

package org.lwjglui.gui.event.listeners;

import org.lwjglui.math.vector.Vector2f;

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
