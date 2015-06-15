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

package org.lwjglui.gui;

import org.jbox2d.collision.AABB;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.World;
import org.lwjglui.core.registry.CoreRegistry;
import org.lwjglui.glfw.Mouse;
import org.lwjglui.gui.logic.UIElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by ben on 06/05/15.
 * <p>
 * GUIManager
 */
public class GUIManager {

    public World world;
    public Vec2 gravity;
    public AABB aabb;

    public Vec2 lowerBound = new Vec2(0, 0);
    public Vec2 upperBound = new Vec2(0, 0);

    public UIElement lastElement, currentElement;

    boolean hasFound = false;

    private Logger logger = LoggerFactory.getLogger(GUIManager.class);

    public GUIManager() {
        gravity = new Vec2(0, -9.81f);
        world = new World(gravity);
        CoreRegistry.put(World.class, world);

        aabb = new AABB(lowerBound, upperBound);

        world.setContactListener(Mouse.getInstance());
    }

    public void stepMouseInteraction() {

        lowerBound.set(Mouse.getMouseTransform().getTranslation().getX() - 0.001f, Mouse.getMouseYInWorld() - 0.0001f);
        upperBound.set(Mouse.getMouseTransform().getTranslation().getX() + 0.001f, Mouse.getMouseYInWorld() + 0.001f);

        aabb.set(new AABB(lowerBound, upperBound));

        hasFound = false;

        world.queryAABB(fixture -> {
            currentElement = UIElement.getFromPhysicsWorld(fixture.getBody());
//            if (fixture.testPoint(new Vec2(Mouse.getMouseTransform().getTranslation().getX(), Mouse.getMouseTransform().getTranslation().getY()))) {
            if (currentElement != null)
                currentElement.processMouseHit(true);
//            }


            lastElement = currentElement;
            hasFound = true;
            return false;
        }, aabb);

        if (!hasFound && lastElement != null) {
            lastElement.processMouseHit(false);
        }



//        logger.info("" + hasFound);
    }
}
