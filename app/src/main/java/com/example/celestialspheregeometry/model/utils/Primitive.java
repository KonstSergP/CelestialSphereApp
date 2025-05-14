package com.example.celestialspheregeometry.model.utils;


import com.example.celestialspheregeometry.rendering.shaders.GLProgramType;

import org.joml.Matrix4f;

import java.nio.FloatBuffer;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class Primitive {

    GLProgramType program;

    int points;

    Map<String, FloatBuffer> attributes;

    Map<String, Uniform> uniforms;


    @Getter
    @Setter
    @AllArgsConstructor
    public static class Uniform
    {
        UniformType type;

        float[] array;

    }


    public enum UniformType
    {
        MATRIX4f,
        VECTOR4f
    }

}
