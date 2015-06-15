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

import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.dynamics.*;
import org.lwjglui.core.registry.CoreRegistry;
import org.lwjglui.gui.event.EventListenHandler;
import org.lwjglui.gui.event.listeners.MouseListener;
import org.lwjglui.gui.renderable.ElementMesh;
import org.lwjglui.math.Size;
import org.lwjglui.scene.transform.Transform;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by ben on 21/05/15.
 */
public abstract class UIElement extends EventListenHandler {

    /**
     * Transformation class, holds transformation matrix
     */
    private Transform transform;

    /**
     * Size of the element
     * TODO get rid of this to make, to reserve control to layout managers
     */
    private Size size;

    /**
     * Body definition for Box2D physics
     */
    private BodyDef bodyDef;
    private Body body;

    /**
     * Polygon shape for collision detection
     */
    private PolygonShape shape;

    /**
     * A map of all the element meshes, is separate from the ui logic and rendering classes
     */
    public static HashMap<UIElement, ElementMesh> elementMeshes = new HashMap<>();

    /**
     * A hasp map to help with the identification from the physics world to the rendering world
     */
    public static HashMap<Body, UIElement> physicsToRender = new HashMap<>();

    /**
     * An array list of all the mouse listeners for entering, hovering, clicking etc.
     */
    public ArrayList<MouseListener> mouseListeners = new ArrayList<>();

    /**
     * Constructor for UIElement must have extended classes call super();
     */
    public UIElement(int x, int y) {
        this(0, 0, new Size(x, y));
    }

    public UIElement(float x, float y, Size size) {
        this.size = size;
        transform = new Transform();
        transform.getTranslation().set(x, y);
        init();
    }

    @Deprecated
    /**
     * Computes and adds to physics for mouse integration
     * Must be called after mesh has changed / created
     */
    public void addToPhysics() {

        getElementMesh().computeAABB();
        float xCenter = getElementMesh().getAABB().getWidth() / 2f;
        float yCenter = getElementMesh().getAABB().getHeight() / 2f;

        System.out.println(xCenter);
        System.out.println(yCenter);

        bodyDef = new BodyDef();
        bodyDef.type = BodyType.KINEMATIC;
        bodyDef.position.set(transform.getTranslation().getX() + xCenter, transform.getTranslation().getY() + (yCenter));
        System.out.println(transform.getTranslation().getX() + " :" + transform.getTranslation().getY());

        body = CoreRegistry.get(World.class).createBody(bodyDef);

        shape = new PolygonShape();
        shape.setAsBox(xCenter, yCenter);
        body.createFixture(shape, 0.0f);

        physicsToRender.put(body, this);
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

    public static UIElement getFromPhysicsWorld(Body body) {
        return physicsToRender.get(body);
    }

    /**
     * Adds mouse listener
     * @param listener mouse listener
     */
    public void addMouseListener(MouseListener listener) {
        mouseListeners.add(listener);
    }

    /***************************************************************************************************
     * *                                                                                              **
     * *                                                                                              **
     * *                    Abstract classes for automation of Element creation                       **
     * *                                                                                              **
     * *                                                                                              **
     * *************************************************************************************************
     */

    /**
     * private method should be called by constructor
     */
    private void init() {
        initializeMesh();
        initializeListeners();
        initializePhysics();
    }

    /**
     * creates and compiles mesh
     */
    public abstract void initializeMesh();

    /**
     * adds mesh to physics
     */
    public abstract void initializePhysics();

    /**
     * allows for the addition of listeners
     */
    public abstract void initializeListeners();

    /*******************************************************************************************************
     *
     *******************************************************************************************************/

    public void processMouseHit(boolean b) {
        if (b) {
            triggerOnMouseEnter(0, 0);
        } else {
            triggerOnMouseLeave();
        }
    }

    public void setTranslation(float x, float y) {
        transform.getTranslation().set(x, y);
        body.getPosition().set(x, y);
    }
}
