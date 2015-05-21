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

package org.lwjglui.render.mesh;

import org.lwjglui.math.Vertex;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by ben on 08/05/15.
 * <p>
 * JGUILibrary
 */
public class VertexArray extends ArrayList<Vertex> {

    public VertexArray(int initialCapacity) {
        super(initialCapacity);
    }

    public VertexArray() {
        super();
    }

//    public VertexArray(Collection<? extends Vertex> c) {
//        super(c);
//    }

    /**
     * Wrapper method for adding a vertex
     * @param vertex
     */
    public VertexArray addVertex(Vertex vertex) {
        this.add(vertex);
        return this;
    }

    /**
     * Wrapper method for adding a vertex
     * @param i - position
     * @param vertex - vertex
     */
    public void addVertex(int i, Vertex vertex) {
        this.add(i, vertex);
    }

    /**
     * Wrapper method for removing vertex
     * @param i - index of vertex to remove
     */
    public void removeVertex(int i) {
        this.remove(i);
    }

    /**
     * Wrapper method for removing vertex
     * @param vertex - vertex to be removed
     */
    public void removeVertex(Vertex vertex) {
        this.remove(vertex);
    }

    /**
     * Wrapper method for returning vertex iterator
     * @return
     */
    public Iterator<Vertex> getVertexIterator() {
        return this.iterator();
    }
}
