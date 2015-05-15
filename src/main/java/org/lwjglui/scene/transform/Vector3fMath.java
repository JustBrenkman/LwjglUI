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

package org.lwjglui.scene.transform;

import org.lwjgl.util.vector.Vector3f;

/**
 * Created by ben on 15/05/15.
 * <p>
 * JGUILibrary
 */
public class Vector3fMath {
    public static float lenght(Vector3f vector3f) {
        return vector3f.length();
    }

    public static float max(Vector3f vector3f) {
        return Math.max(vector3f.getX(), Math.max(vector3f.getY(), vector3f.getZ()));
    }

    public static Vector3f cross(Vector3f vector3f0, Vector3f vector3f1) {
        float x_ = vector3f0.getY() * vector3f1.getZ() - vector3f0.getZ() * vector3f1.getY();
        float y_ = vector3f0.getZ() * vector3f1.getX() - vector3f0.getX() * vector3f1.getZ();
        float z_ = vector3f0.getX() * vector3f1.getY() - vector3f0.getY() * vector3f1.getX();

        return new Vector3f(x_, y_, z_);
    }

    public static Vector3f rotate(Vector3f vector3f, Vector3f axis, float angle) {
        float sinAngle = (float)Math.sin(-angle);
        float cosAngle = (float)Math.cos(-angle);

        return Vector3fMath.cross(vector3f, Vector3fMath.add(Vector3fMath.multiply(axis, sinAngle), (Vector3fMath.add(Vector3fMath.multiply(vector3f, cosAngle), Vector3fMath.multiply(axis, Vector3fMath.dot(vector3f, Vector3fMath.multiply(axis, 1 - cosAngle)))))));
    }

    public static Vector3f rotate(Vector3f vector3f, Quaternion rotation) {
        Quaternion conjugate = rotation.conjugate();

        Quaternion w = rotation.multiply(vector3f).multiply(conjugate);

        return new Vector3f(w.getX(), w.getY(), w.getZ());
    }

    public static float dot(Vector3f vector3f, Vector3f r) {
        return vector3f.getX() * r.getX() + vector3f.getY() * r.getY() + vector3f.getZ() * r.getZ();
    }

    public static Vector3f multiply(Vector3f vector3f0, Vector3f vector3f1) {
        return new Vector3f(vector3f0.getX() * vector3f1.getX(), vector3f0.getY() * vector3f1.getY(), vector3f0.getZ() * vector3f1.getZ());
    }

    public static Vector3f multiply(Vector3f vector3f0, float r) {
        return new Vector3f(vector3f0.getX() * r, vector3f0.getY() * r, vector3f0.getZ() * r);
    }

    public static Vector3f divide(Vector3f vector3f0, Vector3f vector3f1) {
        return new Vector3f(vector3f0.getX() / vector3f1.getX(), vector3f0.getY() / vector3f1.getY(), vector3f0.getZ() / vector3f1.getZ());
    }

    public static Vector3f divide(Vector3f vector3f0, float r) {
        return new Vector3f(vector3f0.getX() / r, vector3f0.getY() / r, vector3f0.getZ() / r);
    }

    public static Vector3f subtract(Vector3f vector3f0, Vector3f vector3f1) {
        return new Vector3f(vector3f0.getX() - vector3f1.getX(), vector3f0.getY() - vector3f1.getY(), vector3f0.getZ() - vector3f1.getZ());
    }

    public static Vector3f subtract(Vector3f vector3f0, float r) {
        return new Vector3f(vector3f0.getX() - r, vector3f0.getY() - r, vector3f0.getZ() - r);
    }

    public static Vector3f add(Vector3f vector3f0, Vector3f vector3f1) {
        return new Vector3f(vector3f0.getX() + vector3f1.getX(), vector3f0.getY() + vector3f1.getY(), vector3f0.getZ() + vector3f1.getZ());
    }

    public static Vector3f add(Vector3f vector3f0, float r) {
        return new Vector3f(vector3f0.getX() + r, vector3f0.getY() + r, vector3f0.getZ() + r);
    }
}
