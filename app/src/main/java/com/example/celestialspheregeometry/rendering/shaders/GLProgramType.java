package com.example.celestialspheregeometry.rendering.shaders;

import lombok.Getter;


@Getter
public enum GLProgramType {

    MINIMAL("MVPMultiplication", "white"),
    COLORED("MVPMultiplication", "colored");


    GLProgramType(String vertexShader, String fragmentShader)
    {
        this.vertexShader = vertexShader;
        this.fragmentShader = fragmentShader;
    }


    private final String vertexShader;
    private final String fragmentShader;
}
