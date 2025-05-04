package com.example.celestialspheregeometry.rendering.shaders;

import lombok.Getter;


@Getter
public enum GLProgramType {

    DEFAULT("default", "default");


    GLProgramType(String vertexShader, String fragmentShader)
    {
        this.vertexShader = vertexShader;
        this.fragmentShader = fragmentShader;
    }


    final String vertexShader;
    final String fragmentShader;
}
