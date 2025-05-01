package com.example.celestialspheregeometry.model.sphere.elements;

import com.example.celestialspheregeometry.rendering.SphereGLRenderer;

import org.joml.Matrix4f;
import org.joml.Vector3f;


public interface GeometricElement {
    void draw(SphereGLRenderer sphereGLRenderer, Matrix4f rotationMatrix);

    void rotateAroundAxis(Vector3f axis, float angle);
}
