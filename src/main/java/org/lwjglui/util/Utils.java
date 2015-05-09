/*
 * Copyright (c) 2015.
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

import com.sun.deploy.util.ArrayUtil;
import org.lwjgl.BufferUtils;
import org.lwjglui.math.Vertex;

public class Utils
{
    public static FloatBuffer CreateFloatBuffer(int size)
    {
        return BufferUtils.createFloatBuffer(size);
    }

    public static IntBuffer CreateIntBuffer(int size)
    {
        return BufferUtils.createIntBuffer(size);
    }

    public static ByteBuffer CreateByteBuffer(int size)
    {
        return BufferUtils.createByteBuffer(size);
    }

    public static IntBuffer CreateFlippedBuffer(int... values)
    {
        IntBuffer buffer = CreateIntBuffer(values.length);
        buffer.put(values);
        buffer.flip();

        return buffer;
    }

    public static FloatBuffer CreateFlippedBuffer(Vertex[] vertices)
    {
        FloatBuffer buffer = CreateFloatBuffer(vertices.length * Vertex.size);

        for(int i = 0; i < vertices.length; i++)
        {
            buffer.put(vertices[i].getPosition().getX());
            buffer.put(vertices[i].getPosition().getY());
            buffer.put(vertices[i].getPosition().getZ());
            buffer.put(vertices[i].getTextCoord().getX());
            buffer.put(vertices[i].getTextCoord().getY());
            buffer.put(vertices[i].getNormal().getX());
            buffer.put(vertices[i].getNormal().getY());
            buffer.put(vertices[i].getNormal().getZ());
            buffer.put(vertices[i].getTangent().getX());
            buffer.put(vertices[i].getTangent().getY());
            buffer.put(vertices[i].getTangent().getZ());
        }

        buffer.flip();

        return buffer;
    }

//    public static FloatBuffer CreateFlippedBuffer(Matrix4f value)
//    {
//        FloatBuffer buffer = CreateFloatBuffer(4 * 4);
//
//        for(int i = 0; i < 4; i++)
//            for(int j = 0; j < 4; j++)
//                buffer.put(value.Get(i, j));
//
//        buffer.flip();
//
//        return buffer;
//    }

    public static String[] RemoveEmptyStrings(String[] data)
    {
        ArrayList<String> result = new ArrayList<String>();

        for(int i = 0; i < data.length; i++)
            if(!data[i].equals(""))
                result.add(data[i]);

        String[] res = new String[result.size()];
        result.toArray(res);

        return res;
    }

    public static int[] ToIntArray(Integer[] data)
    {
        int[] result = new int[data.length];

        for(int i = 0; i < data.length; i++)
            result[i] = data[i].intValue();

        return result;
    }
}
