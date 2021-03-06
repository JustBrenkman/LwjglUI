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

package org.lwjglui.gui.renderable;

import org.lwjglui.core.registry.CoreRegistry;
import org.lwjglui.gui.layout.AABB;
import org.lwjglui.gui.logic.ElementPhysics;
import org.lwjglui.math.Vertex;
import org.lwjglui.math.vector.Vector2f;
import org.lwjglui.math.vector.Vector3f;
import org.lwjglui.render.mesh.VertexArrayObject;
import org.slf4j.Logger;

/**
 * Created by ben on 26/05/15.
 */
public class ElementMesh {

    private VertexArrayObject vertexArrayObject;

    private AABB aabb;

    private ElementPhysics elementPhysics;

    public ElementMesh() {
        vertexArrayObject = new VertexArrayObject();
    }

    public void addVertex() {
        addVertex(new Vertex(new Vector3f(0, 0, 0)));
    }

    public void addVertex(Vertex vertex) {
        vertexArrayObject.getVertexBufferObject().getVertexArray().add(vertex);
    }

    public void compile() {
        vertexArrayObject.compile();
    }

    public void computeAABB() {
        if (aabb == null)
            aabb = new AABB();

        boolean first = true;

        for (Vertex v : vertexArrayObject.getVertexBufferObject().getVertexArray()) {

            if (first) {
                aabb.setMaxX(v.getPosition().getX());
                aabb.setMinX(v.getPosition().getX());
                aabb.setMaxY(v.getPosition().getY());
                aabb.setMinY(v.getPosition().getY());
            }

            if ((v.getPosition().getX()) > aabb.getMaxX())
                aabb.setMaxX(v.getPosition().getX());

            if ((v.getPosition().getX()) < aabb.getMinX())
                aabb.setMinX(v.getPosition().getX());

            if ((v.getPosition().getY()) > aabb.getMaxY())
                aabb.setMaxY(v.getPosition().getY());

            if ((v.getPosition().getY()) < aabb.getMinY())
                aabb.setMinY(v.getPosition().getY());

            first = false;
        }

        CoreRegistry.get(Logger.class).info("AABB max X: " + aabb.getMaxX());
        CoreRegistry.get(Logger.class).info("AABB max Y: " + aabb.getMaxY());
        CoreRegistry.get(Logger.class).info("AABB min X: " + aabb.getMinX());
        CoreRegistry.get(Logger.class).info("AABB min Y: " + aabb.getMinY());
    }

    public VertexArrayObject getVertexArrayObject() {
        return vertexArrayObject;
    }

    public void setVertexArrayObject(VertexArrayObject vertexArrayObject) {
        this.vertexArrayObject = vertexArrayObject;
    }

    public void addBoxToMesh(float x, float y, float width, float height) {

        int i = getVertexArrayObject().getVertexBufferObject().getVertexArray().size();

        addVertex(new Vertex(new Vector3f(x, y, -1)));
        addVertex(new Vertex(new Vector3f(width + x, y, -1)));
        addVertex(new Vertex(new Vector3f(width + x, height + y, -1)));
        addVertex(new Vertex(new Vector3f(x, height + y, -1)));

        getVertexArrayObject().getVertexBufferObject().addIndex(i);
        getVertexArrayObject().getVertexBufferObject().addIndex(i + 1);
        getVertexArrayObject().getVertexBufferObject().addIndex(i + 2);
        getVertexArrayObject().getVertexBufferObject().addIndex(i);
        getVertexArrayObject().getVertexBufferObject().addIndex(i + 2);
        getVertexArrayObject().getVertexBufferObject().addIndex(i + 3);
    }

    public void addBoxToMesh(float x, float y, float width, float height, float uv0, float uv1, float uv2, float uv3) {
        int i = getVertexArrayObject().getVertexBufferObject().getVertexArray().size();

        // TODO figure out the proper texture coordinates
        addVertex(new Vertex(new Vector3f(x, y, -1), new Vector2f(uv0, uv1)));
        addVertex(new Vertex(new Vector3f(width + x, y, -1), new Vector2f(uv1, uv2)));
        addVertex(new Vertex(new Vector3f(width + x, height + y, -1), new Vector2f(uv2, uv3)));
        addVertex(new Vertex(new Vector3f(x, height + y, -1), new Vector2f(uv3, uv1)));

        getVertexArrayObject().getVertexBufferObject().addIndex(i);
        getVertexArrayObject().getVertexBufferObject().addIndex(i + 1);
        getVertexArrayObject().getVertexBufferObject().addIndex(i + 2);
        getVertexArrayObject().getVertexBufferObject().addIndex(i);
        getVertexArrayObject().getVertexBufferObject().addIndex(i + 2);
        getVertexArrayObject().getVertexBufferObject().addIndex(i + 3);
    }

    public void addTriangleToMesh(float p1x, float p1y, float p2x, float p2y, float p3x, float p3y) {
        int i = getVertexArrayObject().getVertexBufferObject().getVertexArray().size();

        addVertex(new Vertex(new Vector3f(p1x, p1y, -1)));
        addVertex(new Vertex(new Vector3f(p2x, p2y, -1)));
        addVertex(new Vertex(new Vector3f(p3x, p3y, -1)));

        getVertexArrayObject().getVertexBufferObject().addIndex(i);
        getVertexArrayObject().getVertexBufferObject().addIndex(i + 1);
        getVertexArrayObject().getVertexBufferObject().addIndex(i + 2);
    }

    public void addTriangleToMesh(float p1x, float p1y, float p2x, float p2y, float p3x, float p3y, float uv0, float uv1, float uv2, float uv3, float uv4, float uv5) {
        int i = getVertexArrayObject().getVertexBufferObject().getVertexArray().size();

        addVertex(new Vertex(new Vector3f(p1x, p1y, 0), new Vector2f(uv0, uv1)));
        addVertex(new Vertex(new Vector3f(p2x, p2y, 0), new Vector2f(uv2, uv3)));
        addVertex(new Vertex(new Vector3f(p3x, p3y, 0), new Vector2f(uv4, uv5)));

        getVertexArrayObject().getVertexBufferObject().addIndex(i);
        getVertexArrayObject().getVertexBufferObject().addIndex(i + 1);
        getVertexArrayObject().getVertexBufferObject().addIndex(i + 2);
    }

    public void addVertexToMesh(Vertex vertex) {
        addVertex(vertex);
    }

    public void addIndex(int i) {
        getVertexArrayObject().getVertexBufferObject().addIndex(i);
    }

    public void addVerteciesToMesh(Vertex... vertexes) {
        for (Vertex v : vertexes)
            addVertex(v);
    }

    public AABB getAABB() {
        return aabb;
    }
}
