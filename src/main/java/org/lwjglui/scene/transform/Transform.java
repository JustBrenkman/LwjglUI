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
public class Transform {

    private Vector3f translation = null;

    private Vector3f scale = new Vector3f(1, 1, 1);

    private Vector3f rotation = new Vector3f(0, 0, 0);

    private Quaternion q_rotation;

    private Matrix4f modelMatrix;

    public Transform() {
        modelMatrix = new Matrix4f();
        translation = new Vector3f(0, 0, 0);
        q_rotation = new Quaternion(0, 0, 0, 1);
    }

    public Transform(Vector3f translation) {
        this();
        this.translation = translation;
    }

    public Transform(Vector3f translation, Vector3f scale) {
        this();
        this.translation = translation;
        this.scale = scale;
    }

    public Vector3f getTranslation() {
        return translation;
    }

    public void setTranslation(Vector3f translation) {
        this.translation = translation;
    }

    public Vector3f getScale() {
        return scale;
    }

    public void setScale(Vector3f scale) {
        this.scale = scale;
    }

    public void setScale(float scale) {
        this.scale = new Vector3f(scale, scale, scale);
    }

    public Vector3f getRotation() {
        return rotation;
    }

    public void setRotation(Vector3f rotation) {
        this.rotation = rotation;
    }

    public Quaternion getQ_rotation() {
        return q_rotation;
    }

    public void setQ_rotation(Quaternion q_rotation) {
        this.q_rotation = q_rotation;
    }

    public void updateTransformation() {
        modelMatrix = new Matrix4f();
        Matrix4f.scale(scale, modelMatrix, modelMatrix);
        Matrix4f.translate(translation, modelMatrix, modelMatrix);
        Matrix4f.rotate(rotation.getX(), new Vector3f(0, 0, 1), modelMatrix, modelMatrix);
        Matrix4f.rotate(rotation.getY(), new Vector3f(0, 1, 0), modelMatrix, modelMatrix);
        Matrix4f.rotate(rotation.getZ(), new Vector3f(1, 0, 0), modelMatrix, modelMatrix);
    }

    @Deprecated
    public void updateCameraTransformation() {
//        modelMatrix = new Matrix4f();
//        Matrix4f.scale(scale, modelMatrix, modelMatrix);
//
//        float eX = (float) Math.atan2(-2 * (q_rotation.getY() * q_rotation.getZ() - q_rotation.getW() * q_rotation.getX()), q_rotation.getW() * q_rotation.getW() - q_rotation.getX() * q_rotation.getX() - q_rotation.getY() * q_rotation.getY() + q_rotation.getZ() * q_rotation.getZ());
//        float eY = (float) Math.asin(2 * (q_rotation.getX() * q_rotation.getZ() + q_rotation.getW() * q_rotation.getY()));
//        float eZ = (float) Math.atan2(-2 * (q_rotation.getX() * q_rotation.getY() - q_rotation.getW() * q_rotation.getZ()), q_rotation.getW() * q_rotation.getW() + q_rotation.getX() * q_rotation.getX() - q_rotation.getY() * q_rotation.getY() - q_rotation.getZ() * q_rotation.getZ());
//
//        Matrix4f.rotate(eX, new Vector3f(0, 0, 1), modelMatrix, modelMatrix);
//        Matrix4f.rotate(eY, new Vector3f(0, 1, 0), modelMatrix, modelMatrix);
//        Matrix4f.rotate(eZ, new Vector3f(1, 0, 0), modelMatrix, modelMatrix);
//        Matrix4f.translate(translation, modelMatrix, modelMatrix);
        updateCameraTransfomationWithQuaternion();
    }

    public void updateCameraTransfomationWithQuaternion() {
        modelMatrix = new Matrix4f();
        Matrix4f cameraRotatoin = q_rotation.conjugate().toRotationMatrix();
        Vector3f cameraPos = Vector3fMath.multiply(translation, -1);
        Matrix4f cameraTranslation = new Matrix4f().translate(translation);
        Matrix4f.mul(modelMatrix, Matrix4f.mul(cameraRotatoin, cameraTranslation, cameraRotatoin), modelMatrix);
    }

    public Matrix4f getModelMatrix() {
        return modelMatrix;
    }

    public void setModelMatrix(Matrix4f modelMatrix) {
        this.modelMatrix = modelMatrix;
    }

    public void rotate(Vector3f axis, float amount) {
        q_rotation = new Quaternion(axis, amount).multiply(q_rotation).normalize();
    }

    // TODO make main getModelMatrix;
    public Matrix4f getTransformationMatrix() {
        Matrix4f translationMatrix = new Matrix4f();
        translationMatrix.translate(getTranslation());
        Matrix4f rotationMatrix = q_rotation.toRotationMatrix();
        Matrix4f scaleMatrix = new Matrix4f().scale(getScale());

        return Matrix4f.mul(translationMatrix, Matrix4f.mul(rotationMatrix, scaleMatrix, rotationMatrix), translationMatrix);
    }
}
