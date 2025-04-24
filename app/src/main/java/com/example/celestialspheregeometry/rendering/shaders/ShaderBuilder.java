package com.example.celestialspheregeometry.rendering.shaders;

import static android.opengl.GLES20.glGetShaderInfoLog;
import static android.opengl.GLES20.glGetShaderSource;

import android.content.Context;
import android.opengl.GLES20;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


public class ShaderBuilder {

    public static String loadShader(Context context, String filename) {
        StringBuilder shaderSource = new StringBuilder();
        try {
            InputStream inputStream = context.getAssets().open("shaders/" + filename + ".glsl");
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = reader.readLine()) != null) {
                shaderSource.append(line).append("\n");
            }
            reader.close();
        } catch (IOException e) {
            throw new RuntimeException("Could not load shader: " + filename, e);
        }
        return shaderSource.toString();
    }

    static int buildShader(int type, String shaderCode) {
        int shader = GLES20.glCreateShader(type);

        GLES20.glShaderSource(shader, shaderCode);
        GLES20.glCompileShader(shader);

        int[] compileStatus = new int[1];
        GLES20.glGetShaderiv(shader, GLES20.GL_COMPILE_STATUS, compileStatus, 0);
        if (compileStatus[0] == 0) {
            throw new RuntimeException("Ошибка компиляции шейдера: " + glGetShaderInfoLog(shader) + glGetShaderSource(shader) + shader);
        }
        return shader;
    }
}
