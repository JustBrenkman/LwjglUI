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

package org.lwjglui.render.shader;

import org.lwjglui.core.registry.CoreRegistry;
import org.lwjglui.math.Vector2f;
import org.lwjglui.math.Vector3f;
import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;

import static org.lwjgl.opengl.GL20.*;

/**
 * Created by ben on 09/05/15.
 * <p>
 * JGUILibrary
 */
public class Shader {

    private HashMap<String, Integer> uniforms;
    private int programID;
    private ArrayList<UpdateUniformListener> updateUniformListeners = new ArrayList<UpdateUniformListener>();

    public Shader() {
        this("name of default shader");
    }

    public Shader(UpdateUniformListener updateUniformListener) {
        this("name of default shader");
        updateUniformListeners.add(updateUniformListener);
    }

    public Shader(String filename, UpdateUniformListener up) {
        this(filename);
        updateUniformListeners.add(up);
    }

    public Shader(String filename) {
        uniforms = new HashMap<String, Integer>();
        programID = glCreateProgram();

        if (programID == 0) {
            CoreRegistry.get(Logger.class).error("Failed to create shader program");
            System.exit(-1);
        }

        CoreRegistry.get(Logger.class).info("Created shader program " + programID);

        CoreRegistry.get(ShaderManager.class).loadShader(this, filename);
    }



    public int getProgramID () {
        return programID;
    }

    public void setProgramID(int programID) {
        this.programID = programID;
    }

    public void unpdateUniformFloat(String name, float value) {
        glUniform1f(uniforms.get(name), value);
    }

    public void unpdateUniformInt(String name, int value) {
        glUniform1i(uniforms.get(name), value);
    }

    //    public void updateUniformMatrix4f(String name, float value) {}
    public void unpdateUniformVector3f(String name, Vector3f value) {
        glUniform3f(uniforms.get(name), value.getX(), value.getY(), value.getZ());
    }

    public void unpdateUniformVector2f(String name, Vector2f value) {
        glUniform2f(uniforms.get(name), value.getX(), value.getY());
    }

    public void addUniform(String name) {
        int uniformLocation = glGetUniformLocation(this.programID, name);

        if (uniformLocation == 0xFFFFFFFF) {
            System.err.println("Could not make uniform location: " + name);
            new Exception().printStackTrace();
            System.exit(-1);
        }

        uniforms.put(name, uniformLocation);
    }

    public void addUpdateUniformListener(UpdateUniformListener updateUniformListener) {
        updateUniformListeners.add(updateUniformListener);
    }

    public void clearUpdateListeners() {
        updateUniformListeners.clear();
    }

    public void updateUniforms() {
        for (UpdateUniformListener up : updateUniformListeners) up.updateUniforms(this);
    }

    public void destroy() {
        glDeleteProgram(programID);
    }
}