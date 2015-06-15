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

package org.lwjglui.gui.event;

import org.lwjglui.gui.event.listeners.*;

import java.util.ArrayList;

/**
 * Created by ben on 05/06/15.
 */
public class EventListenHandler {

    /**
     * Array list of mouse button listeners
     */
    public ArrayList<MouseButtonListener> mouseButtonListeners = new ArrayList<>();

    /**
     * Array list of mouse button listeners
     */
    public ArrayList<MouseClickListener> mouseClickListeners = new ArrayList<>();

    /**
     * Array list of mouse button listeners
     */
    public ArrayList<MouseEnterListener> mouseEnterListeners = new ArrayList<>();

    /**
     * Array list of mouse button listeners
     */
    public ArrayList<MouseLeaveListener> mouseLeaveListeners = new ArrayList<>();

    /**
     * Array list of mouse button listeners
     */
    public ArrayList<MouseScrollListener> mouseScrollListeners = new ArrayList<>();


    /***********************************************************************************************************
     **                                                                                                       **
     **                                                                                                       **
     **                                       Event Handler Methods                                           **
     **                                                                                                       **
     **                                                                                                       **
     ***********************************************************************************************************/

    public void processesMouseInput(long windowHandle, int x, int y) {

    }

    public void triggerOnMouseEnter(int x, int y) {
        for (MouseEnterListener l : mouseEnterListeners)
            l.onMouseEnter();
    }

    public void triggerOnMouseLeave() {
        for (MouseLeaveListener l : mouseLeaveListeners)
            l.onMouseLeave();
    }

    public boolean addMouseEnterListener(MouseEnterListener mouseEnterListener) {
        return mouseEnterListeners.add(mouseEnterListener);
    }

    public boolean addMouseLeaveListener(MouseLeaveListener mouseLeaveListener) {
        return mouseLeaveListeners.add(mouseLeaveListener);
    }
}
