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

import org.jbox2d.callbacks.ContactImpulse;
import org.jbox2d.callbacks.ContactListener;
import org.jbox2d.collision.Manifold;
import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.*;
import org.jbox2d.dynamics.contacts.Contact;
import org.lwjgl.glfw.GLFWCursorPosCallback;
import org.lwjglui.core.registry.CoreRegistry;
import org.lwjglui.gui.logic.UIElement;
import org.lwjglui.scene.transform.Transform;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.lwjgl.glfw.GLFW.*;

/**
 * Created by ben on 04/06/15.
 */
public class Mouse implements ContactListener {
    private static final Mouse mouse = new Mouse();

    private Transform transform;

    public Transform getTransform() {
        return transform;
    }

    public static Transform getMouseTransform() {
        return mouse.getTransform();
    }

    public GLFWCursorPosCallback glfwCursorPosCallback;

    /**
     * Body definition for Box2D physics
     */
    private static BodyDef bodyDef;
    private static Body body;

    /**
     * Polygon shape for collision detection
     */
    private static PolygonShape shape;

    private Logger logger = LoggerFactory.getLogger(Mouse.class);

    private void createMouse() {
        transform = new Transform();
        addToPhysics();
        glfwCursorPosCallback = new GLFWCursorPosCallback() {
            @Override
            public void invoke(long window, double x, double y) {
                getMouseTransform().getTranslation().setX((float) x);
                getMouseTransform().getTranslation().setY((float) y);
//                logger.info("Mouse X: " + x + ", Mouse Y: " + y);
            }
        };

        glfwSetCursorPosCallback(CoreRegistry.get(Window.class).getWindowHandle(), glfwCursorPosCallback);
    }

    public static void create() {
        mouse.createMouse();
    }

    public static float getMouseYInWorld() {
        return CoreRegistry.get(Window.class).getSize().getHeight() - mouse.getTransform().getTranslation().getY();
    }

    public static void addToPhysics() {
        bodyDef = new BodyDef();
        bodyDef.type = BodyType.KINEMATIC;
        bodyDef.position.set(mouse.getTransform().getTranslation().getX(), mouse.getTransform().getTranslation().getY());

        body = CoreRegistry.get(World.class).createBody(bodyDef);

        shape = new PolygonShape();
        shape.setAsBox(1f, 1f);
        body.createFixture(shape, 0.0f);
    }

    public static Mouse getInstance() {
        return mouse;
    }

    @Override
    public void beginContact(Contact contact) {
//        Fixture f1 = contact.getFixtureA();
//        Fixture f2 = contact.getFixtureB();
//
//        if (f1.getBody() == body) {
//            UIElement.getFromPhysicsWorld(f2.getBody()).processMouseHit(true);
//        } else {
//            UIElement.getFromPhysicsWorld(f1.getBody()).processMouseHit(true);
//        }
//
//        logger.info("Yeah");
    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
