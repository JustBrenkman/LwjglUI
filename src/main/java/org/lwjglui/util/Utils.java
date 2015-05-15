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

package org.lwjglui.util;

/**
 * Created by ben on 08/05/15.
 * <p>
 * JGUILibrary
 */
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;

import org.lwjgl.BufferUtils;
import org.lwjglui.math.Vertex;

public class Utils
{
    public static FloatBuffer createFloatBuffer(int size)
    {
        return BufferUtils.createFloatBuffer(size);
    }

    public static IntBuffer createIntBuffer(int size)
    {
        return BufferUtils.createIntBuffer(size);
    }

    public static ByteBuffer createByteBuffer(int size)
    {
        return BufferUtils.createByteBuffer(size);
    }

    public static IntBuffer createFlippedBuffer(int... values)
    {
        IntBuffer buffer = createIntBuffer(values.length);
        buffer.put(values);
        buffer.flip();

        return buffer;
    }

    public static FloatBuffer createFlippedBuffer(Vertex[] vertices)
    {
        FloatBuffer buffer = createFloatBuffer(vertices.length * Vertex.size);

        for (Vertex vertice : vertices) {
            buffer.put(vertice.getPosition().getX());
            buffer.put(vertice.getPosition().getY());
            buffer.put(vertice.getPosition().getZ());
            buffer.put(vertice.getTextCoord().getX());
            buffer.put(vertice.getTextCoord().getY());
            buffer.put(vertice.getNormal().getX());
            buffer.put(vertice.getNormal().getY());
            buffer.put(vertice.getNormal().getZ());
            buffer.put(vertice.getTangent().getX());
            buffer.put(vertice.getTangent().getY());
            buffer.put(vertice.getTangent().getZ());
        }

        buffer.flip();

        return buffer;
    }

//    public static FloatBuffer createFlippedBuffer(Matrix4f value)
//    {
//        FloatBuffer buffer = createFloatBuffer(4 * 4);
//
//        for(int i = 0; i < 4; i++)
//            for(int j = 0; j < 4; j++)
//                buffer.put(value.Get(i, j));
//
//        buffer.flip();
//
//        return buffer;
//    }

    public static String[] removeEmptyStrings(String[] data)
    {
        ArrayList<String> result = new ArrayList<String>();

        for(int i = 0; i < data.length; i++)
            if(!data[i].equals(""))
                result.add(data[i]);

        String[] res = new String[result.size()];
        result.toArray(res);

        return res;
    }

    public static int[] toIntArray(Integer[] data)
    {
        int[] result = new int[data.length];

        for(int i = 0; i < data.length; i++)
            result[i] = data[i].intValue();

        return result;
    }
}
