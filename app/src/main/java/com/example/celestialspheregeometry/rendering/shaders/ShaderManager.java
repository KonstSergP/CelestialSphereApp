package com.example.celestialspheregeometry.rendering.shaders;

import static android.opengl.GLES20.glGetShaderInfoLog;
import static android.opengl.GLES20.glGetShaderSource;

import android.content.Context;
import android.opengl.GLES20;
import android.util.Pair;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import lombok.Getter;


public class ShaderManager {

    public final String SHADER_DIRECTORY = "shaders";
    public final Context context;
    public final Map<Pair<ShaderType, String>, Integer> shaders = new HashMap<>();


    public ShaderManager(Context context) {
        this.context = context;
    }


    public int getShader(ShaderType type, String name)
    {
        Pair<ShaderType, String> key = new Pair<>(type, name);
        Integer shader = shaders.get(key);
        if (shader != null) return shader;

        shader = buildShader(type, name);
        shaders.put(key, shader);
        return shader;
    }


    public String loadShader(ShaderType type, String name) {
        StringBuilder shaderSource = new StringBuilder();
        try {
            InputStream inputStream = context.getAssets().open(SHADER_DIRECTORY+"/"+type.getDirectory()+"/"+name+".glsl");
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = reader.readLine()) != null) {
                shaderSource.append(line).append("\n");
            }
            reader.close();
        } catch (IOException e) {
            throw new RuntimeException("Could not load shader: " + name, e);
        }
        return shaderSource.toString();
    }


    public int buildShader(ShaderType type, String name) {
        int shader = GLES20.glCreateShader(type.getGLType());
        String shaderCode = loadShader(type, name);

        GLES20.glShaderSource(shader, shaderCode);
        GLES20.glCompileShader(shader);

        int[] compileStatus = new int[1];
        GLES20.glGetShaderiv(shader, GLES20.GL_COMPILE_STATUS, compileStatus, 0);
        if (compileStatus[0] == 0) {
            throw new RuntimeException("Ошибка компиляции шейдера: " + glGetShaderInfoLog(shader) + glGetShaderSource(shader) + shader);
        }
        return shader;
    }


    @Getter
    public enum ShaderType {
        VERTEX("vertex", GLES20.GL_VERTEX_SHADER),
        FRAGMENT("fragment", GLES20.GL_FRAGMENT_SHADER);

        ShaderType(String dir, int gltype) {
            this.directory = dir;
            this.GLType = gltype;
        }

        final String directory;
        final int GLType;
    }
}
