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

import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

/**
 * Created by ben on 15/05/15.
 * <p>
 * JGUILibrary
 */
public class Quaternion {
    private float x, y, z, w;

    public Quaternion() {
        this(0, 0, 0, 1);
    }

    public Quaternion(float x, float y, float z, float w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }

    public Quaternion(Vector3f axis, float angle) {
        float sinHalfAngle = (float) Math.sin(angle / 2);
        float cosHalfAngle = (float) Math.cos(angle / 2);

        this.x = axis.getX() * sinHalfAngle;
        this.y = axis.getY() * sinHalfAngle;
        this.z = axis.getZ() * sinHalfAngle;
        this.w = cosHalfAngle;
    }

    //From Ken Shoemake's "Quaternion Calculus and Fast Animation" article
    public Quaternion(Matrix4f rot)
    {
        float trace = rot.m00 + rot.m11 + rot.m22;

        if(trace > 0)
        {
            float s = 0.5f / (float)Math.sqrt(trace+ 1.0f);
            w = 0.25f / s;
            x = (rot.m12 - rot.m21) * s;
            y = (rot.m20 - rot.m02) * s;
            z = (rot.m01 - rot.m10) * s;
        }
        else
        {
            if(rot.m00 > rot.m11 && rot.m00 > rot.m22)
            {
                float s = 2.0f * (float)Math.sqrt(1.0f + rot.m00 - rot.m11 - rot.m22);
                w = (rot.m12 - rot.m21) / s;
                x = 0.25f * s;
                y = (rot.m10 + rot.m01) / s;
                z = (rot.m20 + rot.m02) / s;
            }
            else if(rot.m11 > rot.m22)
            {
                float s = 2.0f * (float)Math.sqrt(1.0f + rot.m11 - rot.m00 - rot.m22);
                w = (rot.m20 - rot.m02) / s;
                x = (rot.m10 + rot.m01) / s;
                y = 0.25f * s;
                z = (rot.m21 + rot.m12) / s;
            }
            else
            {
                float s = 2.0f * (float)Math.sqrt(1.0f + rot.m22 - rot.m00 - rot.m11);
                w = (rot.m01 - rot.m10 ) / s;
                x = (rot.m20 + rot.m02 ) / s;
                y = (rot.m12 + rot.m21 ) / s;
                z = 0.25f * s;
            }
        }

        float length = (float)Math.sqrt(x * x + y * y + z * z + w * w);
        x /= length;
        y /= length;
        z /= length;
        w /= length;
    }

    public float length() {
        return (float)Math.sqrt(x * x + y * y + z * z + w * w);
    }

    public Quaternion normalize() {
        float lenght = length();

        return new Quaternion(x / lenght, y / lenght, z / lenght, w / lenght);
    }

    public Quaternion conjugate() {
        return new Quaternion(-x, -y, -z, w);
    }

    public Quaternion multiply(float param0) {
        return new Quaternion(x * param0, y * param0, z * param0, w * param0);
    }

    public Quaternion multiply(Quaternion quaternion) {
        float w_ = w * quaternion.getW() - x * quaternion.getX() - y * quaternion.getY() - z * quaternion.getZ();
        float x_ = x * quaternion.getW() + w * quaternion.getX() + y * quaternion.getZ() - z * quaternion.getY();
        float y_ = y * quaternion.getW() + w * quaternion.getY() + z * quaternion.getX() - x * quaternion.getZ();
        float z_ = z * quaternion.getW() + w * quaternion.getZ() + x * quaternion.getY() - y * quaternion.getX();

        return new Quaternion(x_, y_, z_, w_);
    }

    public Quaternion multiply(Vector3f r)
    {
        float w_ = -x * r.getX() - y * r.getY() - z * r.getZ();
        float x_ =  w * r.getX() + y * r.getZ() - z * r.getY();
        float y_ =  w * r.getY() + z * r.getX() - x * r.getZ();
        float z_ =  w * r.getZ() + x * r.getY() - y * r.getX();

        return new Quaternion(x_, y_, z_, w_);
    }

    public Quaternion subtract(Quaternion quaternion) {
        return new Quaternion(x - quaternion.getX(), y - quaternion.getY(), z - quaternion.getZ(), w - quaternion.getW());
    }

    public Quaternion add(Quaternion quaternion) {
        return new Quaternion(x + quaternion.getX(), y + quaternion.getY(), z + quaternion.getZ(), w + quaternion.getW());
    }

    public Matrix4f toRotationMatrix()
    {
        Matrix4f matrix = new Matrix4f();
        matrix.m00 = 1.0f - 2.0f * ( this.getY() * this.getY() + this.getZ() * this.getZ() );
        matrix.m01 = 2.0f * (this.getX() * this.getY() + this.getZ() * this.getW());
        matrix.m02 = 2.0f * (this.getX() * this.getZ() - this.getY() * this.getW());
        matrix.m03 = 0.0f;

        // Second row
        matrix.m10 = 2.0f * ( this.getX() * this.getY() - this.getZ() * this.getW() );
        matrix.m11 = 1.0f - 2.0f * ( this.getX() * this.getX() + this.getZ() * this.getZ() );
        matrix.m12 = 2.0f * (this.getZ() * this.getY() + this.getX() * this.getW() );
        matrix.m13 = 0.0f;

        // Third row
        matrix.m20 = 2.0f * ( this.getX() * this.getZ() + this.getY() * this.getW() );
        matrix.m21 = 2.0f * ( this.getY() * this.getZ() - this.getX() * this.getW() );
        matrix.m22 = 1.0f - 2.0f * ( this.getX() * this.getX() + this.getY() * this.getY() );
        matrix.m23 = 0.0f;

        // Fourth row
        matrix.m30 = 0;
        matrix.m31 = 0;
        matrix.m32 = 0;
        matrix.m33 = 1.0f;

        return matrix;
    }

    public float dot(Quaternion r)
    {
        return x * r.getX() + y * r.getY() + z * r.getZ() + w * r.getW();
    }

    public Quaternion nlerp(Quaternion dest, float lerpFactor, boolean shortest)
    {
        Quaternion correctedDest = dest;

        if(shortest && this.dot(dest) < 0)
            correctedDest = new Quaternion(-dest.getX(), -dest.getY(), -dest.getZ(), -dest.getW());

        return correctedDest.subtract(this).multiply(lerpFactor).add(this).normalize();
    }

    public Quaternion slerp(Quaternion dest, float lerpFactor, boolean shortest)
    {
        final float EPSILON = 1e3f;

        float cos = this.dot(dest);
        Quaternion correctedDest = dest;

        if(shortest && cos < 0)
        {
            cos = -cos;
            correctedDest = new Quaternion(-dest.getX(), -dest.getY(), -dest.getZ(), -dest.getW());
        }

        if(Math.abs(cos) >= 1 - EPSILON)
            return nlerp(correctedDest, lerpFactor, false);

        float sin = (float)Math.sqrt(1.0f - cos * cos);
        float angle = (float)Math.atan2(sin, cos);
        float invSin =  1.0f/sin;

        float srcFactor = (float)Math.sin((1.0f - lerpFactor) * angle) * invSin;
        float destFactor = (float)Math.sin((lerpFactor) * angle) * invSin;

        return this.multiply(srcFactor).add(correctedDest.multiply(destFactor));
    }

    public Vector3f getForward() {
        return Vector3fMath.rotate(new Vector3f(0, 0, 1), this);
    }

    public Vector3f getBack() {
        return Vector3fMath.rotate(new Vector3f(0, 0, -1), this);
    }

    public Vector3f getUp() {
        return Vector3fMath.rotate(new Vector3f(0, 1, 0), this);
    }

    public Vector3f getDown() {
        return Vector3fMath.rotate(new Vector3f(0, -1, 0), this);
    }

    public Vector3f getRight() {
        return Vector3fMath.rotate(new Vector3f(1, 0, 0), this);
    }

    public Vector3f getLeft() {
        return Vector3fMath.rotate(new Vector3f(-1, 0, 0), this);
    }

    public Quaternion set(float x, float y, float z, float w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;

        return this;
    }

    public Quaternion set(Quaternion r) {
        set(r.getX(), r.getY(), r.getZ(), r.getW());
        return this;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getZ() {
        return z;
    }

    public void setZ(float z) {
        this.z = z;
    }

    public float getW() {
        return w;
    }

    public void setW(float w) {
        this.w = w;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Quaternion)) return false;

        Quaternion that = (Quaternion) o;

        if (Float.compare(that.w, w) != 0) return false;
        if (Float.compare(that.x, x) != 0) return false;
        if (Float.compare(that.y, y) != 0) return false;
        if (Float.compare(that.z, z) != 0) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (x != +0.0f ? Float.floatToIntBits(x) : 0);
        result = 31 * result + (y != +0.0f ? Float.floatToIntBits(y) : 0);
        result = 31 * result + (z != +0.0f ? Float.floatToIntBits(z) : 0);
        result = 31 * result + (w != +0.0f ? Float.floatToIntBits(w) : 0);
        return result;
    }

}
