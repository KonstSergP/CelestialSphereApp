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

    private GLProgramType program;

    private int points;

    private int primitiveType;

    private float lineWidth;

    private Map<String, FloatBuffer> attributes;

    private Map<String, Uniform> uniforms;


    @Getter
    @Setter
    @AllArgsConstructor
    public static class Uniform
    {
        UniformType type;

        float[] array;


        public Uniform(UniformType type)
        {
            this.type = type;
            switch (type)
            {
                case MATRIX4f -> array = new float[16];
                case VECTOR4f -> array = new float[4];
            }
        }
    }


    public enum UniformType
    {
        MATRIX4f,
        VECTOR4f
    }

}
