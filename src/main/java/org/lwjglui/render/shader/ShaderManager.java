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
import org.lwjglui.util.PathManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.ref.WeakReference;
import java.util.ArrayList;

import static org.lwjgl.opengl.GL20.*;

/**
 * Created by ben on 07/05/15.
 * Class for managing the shaders
 * JGUILibrary
 */
public class ShaderManager {

    private Logger logger = LoggerFactory.getLogger(ShaderManager.class);
    String pathToContaningFolder;
    private ArrayList<WeakReference<Shader>> shaders = new ArrayList<>();

    public ShaderManager() {
        pathToContaningFolder = PathManager.getLocationPath() + "/src/main/resources/shaders/";
    }

    public void loadShader(Shader shader, String filename) {
        logger.info("Loading shader from file: " + pathToContaningFolder + filename);
        attachShaderProgram(shader, loadShaderSource(pathToContaningFolder + filename, ShaderType.VERTEX), ShaderType.VERTEX);
        logger.debug("Successfully loaded and compiled vertex shader source");
        attachShaderProgram(shader, loadShaderSource(pathToContaningFolder + filename, ShaderType.FRAGMENT), ShaderType.FRAGMENT);
        logger.debug("Successfully loaded and compiled fragment shader source");
        compileShader(shader);
        logger.debug("Successfully validated and compiled shader program");
        shaders.add(new WeakReference<>(shader));
    }

    private String loadShaderSource(String filename, ShaderType type) {

        String extension = "glsl";

        switch (type) {
            case VERTEX:
                extension = ".vert";
                break;

            case FRAGMENT:
                extension = ".frag";
                break;
        }

        StringBuilder shaderSource = new StringBuilder();
        BufferedReader shaderReader = null;
        final String INCLUDE_DIRECTIVE = "#include";

        try
        {
            shaderReader = new BufferedReader(new FileReader(filename + extension));
            String line;

            while((line = shaderReader.readLine()) != null)
            {
//                if(line.startsWith(INCLUDE_DIRECTIVE))
//                {
//                    shaderSource.append(LoadShader(line.substring(INCLUDE_DIRECTIVE.length() + 2, line.length() - 1)));
//                }
//                else
                    shaderSource.append(line).append("\n");
            }

            shaderReader.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
            System.exit(1);
        }


        return shaderSource.toString();
    }

    public void attachShaderProgram(Shader program, String shaderSource, ShaderType type) {
        int shader = glCreateShader(type.getValue());

        if (shader == 0) {
            System.err.println("Shader creation failed: Could not find valid memory location when adding shader");
            System.exit(1);
        }

        glShaderSource(shader, shaderSource);
        glCompileShader(shader);

        if (glGetShaderi(shader, GL_COMPILE_STATUS) == 0) {
            System.err.println(glGetShaderInfoLog(shader, 1024));
            System.exit(1);

        }

        glAttachShader(program.getProgramID(), shader);
    }

    public void compileShader(Shader shader) {
        glLinkProgram(shader.getProgramID());

        if(glGetProgrami(shader.getProgramID(), GL_LINK_STATUS) == 0)
        {
            System.err.println(glGetProgramInfoLog(shader.getProgramID(), 1024));
            System.exit(1);
        }

        glValidateProgram(shader.getProgramID());

        if(glGetProgrami(shader.getProgramID(), GL_VALIDATE_STATUS) == 0)
        {
            System.err.println(glGetProgramInfoLog(shader.getProgramID(), 1024));
            System.exit(1);
        }
    }

    public void destroyAllShaders() {

    }
}
