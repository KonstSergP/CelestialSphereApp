package com.example.celestialspheregeometry.rendering.shaders;

import android.content.Context;
import android.opengl.GLES20;


public class ProgramBuilder {

    public static int buildProgram(Context context, String name) {

        String vertexShaderCode = ShaderBuilder.loadShader(context, name+"_vertex");
        String fragmentShaderCode = ShaderBuilder.loadShader(context, name+"_fragment");

        int vertexShader = ShaderBuilder.buildShader(GLES20.GL_VERTEX_SHADER, vertexShaderCode);
        int fragmentShader = ShaderBuilder.buildShader(GLES20.GL_FRAGMENT_SHADER, fragmentShaderCode);

        int program = GLES20.glCreateProgram();
        GLES20.glAttachShader(program, vertexShader);
        GLES20.glAttachShader(program, fragmentShader);
        GLES20.glLinkProgram(program);

        int[] linkStatus = new int[1];
        GLES20.glGetProgramiv(program, GLES20.GL_LINK_STATUS, linkStatus, 0);
        if (linkStatus[0] == 0) {
            throw new RuntimeException("Ошибка линковки программы: " + GLES20.glGetProgramInfoLog(program));
        }

        return program;
    }
}
