package com.example.celestialspheregeometry.model.sphere.elements;

import com.example.celestialspheregeometry.model.utils.Primitive;

import org.joml.Matrix4f;
import org.joml.Vector3f;

import java.util.List;


public interface GeometricElement {
    void rotateAroundAxis(Vector3f axis, float angle);

    void getPrimitives(Matrix4f modelMatrix, List<Primitive> primitives);
}
