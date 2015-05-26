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

package org.lwjglui.gui.logic;

import org.lwjglui.gui.renderable.ElementMesh;
import org.lwjglui.math.Size;
import org.lwjglui.scene.transform.Transform;

import java.util.HashMap;

/**
 * Created by ben on 21/05/15.
 */
public class UIElement {

    /**
     * Transformation class, holds transformation matrix
     */
    private Transform transform;

    /**
     * Size of the element
     */
    private Size size;

    /**
     * A map of all the element meshes, is separate from the ui logic and rendering classes
     */
    public static HashMap<UIElement, ElementMesh> elementMeshes = new HashMap<>();

    public UIElement() {
//        setElementMesh(this, new ElementMesh());
    }

    /**
     * retrieves the elements mesh, checks if element
     * @param element - element
     * @return - returns the mesh associated with the element
     */
    public static ElementMesh getElementMesh(UIElement element) {
        ElementMesh elementMesh = elementMeshes.get(element);

        if (elementMesh == null) {
            elementMesh = new ElementMesh();
            elementMeshes.put(element, elementMesh);
        }

        return elementMesh;
    }

    /**
     * Sets the elements mesh
     * @param element - element associated
     * @param elementMesh - mesh to set as elements mesh
     */
    public static void setElementMesh(UIElement element, ElementMesh elementMesh) {
        elementMeshes.put(element, elementMesh);
    }

    public Transform getTransform() {
        return transform;
    }

    public void setTransform(Transform transform) {
        this.transform = transform;
    }

    /**
     * Helper method for making retrieval of meshes faster to extended classes
     * @return - returns mesh of the element that called this method
     */
    public ElementMesh getElementMesh() {
        return getElementMesh(this);
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }
}
