/*
 * Copyright (c) 2015.
 */

package org.lwjglui.render;

import org.lwjglui.math.Vector3f;
import org.lwjglui.math.Vertex;

import java.util.ArrayList;
import java.util.Collection;
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
