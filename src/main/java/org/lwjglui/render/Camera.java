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

package org.lwjglui.render;

import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;
import org.lwjglui.core.registry.CoreRegistry;
import org.lwjglui.glfw.Window;
import org.lwjglui.math.Axis;
import org.lwjglui.scene.transform.Transform;
import org.lwjglui.scene.transform.Vector3fMath;

/**
 * Created by ben on 14/05/15.
 * <p>
 * JGUILibrary
 */
public class Camera {
    private Transform transform = new Transform();
    private Transform orthoTransform = new Transform();

    private Matrix4f projectionMatrix = null;
    private Matrix4f orthoGraphicMatrix = null;

    private float fov = 60f;

    private float aspectRatio = (float) CoreRegistry.get(Window.class).getSize().getWidth() / (float) CoreRegistry.get(Window.class).getSize().getHeight();

    float near_plane = 0.1f;

    float far_plane = 800f;

    float y_scale = this.coTangent(this.degreesToRadians(fov / 2f));

    float x_scale = y_scale / aspectRatio;

    float frustum_length = far_plane - near_plane;

    public Camera(Vector3f forward, Vector3f up) {
        this();

        forward.normalise();
        up.normalise();
    }

    public Camera(Vector3f position) {
        this();
        transform.setTranslation(position);
    }

    public Camera() {
        transform.setTranslation(new Vector3f(0, 0, 0));
        orthoTransform.setTranslation(new Vector3f(0, 0, 0));
        projectionMatrix = new Matrix4f();
        orthoGraphicMatrix = Ortho(0, 0, 300, 300, 0, 100);
    }

    public void move(Vector3f dir, float amount) {
//        Vector3f.add(new Vector3f(dir.getX() * amount, dir.getY() * amount, dir.getZ() * amount), transform.getTranslation(), transform.getTranslation());
        getTransform().setTranslation(Vector3fMath.add(getTransform().getTranslation(), Vector3fMath.multiply(dir, amount)));
    }

    public void rotate(float amount, Axis axis) {
        switch (axis) {
            case X_AXIS:
                transform.rotate(new Vector3f(1, 0, 0), amount);
                break;
            case Y_AXIS:
                transform.rotate(new Vector3f(0, 1, 0), amount);
                break;
            case Z_AXIS:
                transform.setRotation(new Vector3f(transform.getRotation().getX(), transform.getRotation().getY(), amount + transform.getRotation().getZ()));
                break;
            case W_AXIS:
                break;
            default:
                break;
        }
    }

    public void rotate(float amount, Vector3f vector3f) {
        transform.rotate(vector3f, amount);
    }


    private float coTangent(float angle) {
        return (float)(1f / Math.tan(angle));
    }

    public float degreesToRadians(float degrees) {
        return degrees * (float)(Math.PI / 180d);
    }

    public void init() {
        projectionMatrix.m00 = x_scale;
        projectionMatrix.m11 = y_scale;
        projectionMatrix.m22 = -((far_plane + near_plane) / frustum_length);
        projectionMatrix.m23 = -1;
        projectionMatrix.m32 = -((2 * near_plane * far_plane) / frustum_length);
        projectionMatrix.m33 = 0;

//        orthoGraphicMatrix = Ortho(0, 0, CoreRegistry.get(Window.class).getSize().getWidth(), CoreRegistry.get(Window.class).getSize().getHeight(), -1, 100);
//        orthoGraphicMatrix = Ortho(98, 102, 12, 8, 0, 100);
        orthoGraphicMatrix = Ortho(0, CoreRegistry.get(Window.class).getSize().getWidth(), CoreRegistry.get(Window.class).getSize().getHeight(), 0, 0, 100);
    }

    public Matrix4f getProjectionMatrix() {
        return projectionMatrix;
    }

    public Matrix4f getOrthoGraphicMatrix() {
        return orthoGraphicMatrix;
    }

    public Transform getTransform() {
        return transform;
    }

    public void updateTransform() {
        transform.updateCameraTransformation();
        orthoTransform.updateCameraTransformation();
    }

    public Matrix4f getViewMatrix() {
        return transform.getModelMatrix();
    }

    public Matrix4f getOrthoTransformation() {
        return orthoTransform.getTransformationMatrix();
//        return orthoTransform.getModelMatrix();
    }

    public static Matrix4f identityMat() {
        Matrix4f mat = new Matrix4f();
        mat.m00 = 1;
        mat.m01 = 0;
        mat.m02 = 0;
        mat.m03 = 0;
        mat.m10 = 0;
        mat.m11 = 1;
        mat.m12 = 0;
        mat.m13 = 0;
        mat.m20 = 0;
        mat.m21 = 0;
        mat.m22 = 1;
        mat.m23 = 0;
        mat.m30 = 0;
        mat.m31 = 0;
        mat.m32 = 0;
        mat.m33 = 1;
        return mat;
    }

    public Matrix4f Ortho(float left, float right, float top, float bottom, float znear, float zfar) {

        Matrix4f mat = identityMat();

        mat.m00 = 2.0f/(right-left);
        mat.m01 = 0.0f;
        mat.m02 = 0.0f;
        mat.m03 = 0.0f;

        mat.m10 = 0.0f;
        mat.m11 = 2.0f/(top-bottom);
        mat.m12 = 0.0f;
        mat.m13 = 0.0f;

        mat.m20 = 0.0f;
        mat.m21 = 0.0f;
        mat.m22 = -2.0f/(zfar-znear);
        mat.m23 = 0.0f;

        mat.m30 = -(right+left)/(right-left);
        mat.m31 = -(top+bottom)/(top-bottom);
        mat.m32 = -(zfar+znear)/(zfar-znear);
        mat.m33 = 1.0f;

        return mat;
    }
}
