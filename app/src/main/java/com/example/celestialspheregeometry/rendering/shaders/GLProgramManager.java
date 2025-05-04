package com.example.celestialspheregeometry.rendering.shaders;

import android.content.Context;
import android.opengl.GLES20;

import java.util.HashMap;
import java.util.Map;
import com.example.celestialspheregeometry.rendering.shaders.ShaderManager.ShaderType;


public class GLProgramManager {

    public final ShaderManager shaderManager;
    public final Map<GLProgramType, Integer> shaders = new HashMap<>();


    public GLProgramManager(Context context) {
        shaderManager = new ShaderManager(context);
    }


    public int getProgram(GLProgramType type)
    {
        Integer program = shaders.get(type);
        if (program != null) return program;

        program = buildProgram(type);
        shaders.put(type, program);
        return program;
    }


    public int buildProgram(GLProgramType type) {

        int vertexShader = shaderManager.getShader(ShaderType.VERTEX, type.getVertexShader());
        int fragmentShader = shaderManager.getShader(ShaderType.FRAGMENT, type.getFragmentShader());

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
