package com.example.celestialspheregeometry.model.sphere.elements;

import com.example.celestialspheregeometry.rendering.SphereGLRenderer;


public interface GeometricElement {
    void draw(SphereGLRenderer sphereGLRenderer, float[] rotationMatrix);
}
