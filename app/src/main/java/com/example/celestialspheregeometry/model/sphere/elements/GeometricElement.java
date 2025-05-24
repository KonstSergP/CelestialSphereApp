package com.example.celestialspheregeometry.model.sphere.elements;

import com.example.celestialspheregeometry.model.utils.Primitive;

import org.joml.Matrix4f;
import org.joml.Vector3f;

import java.util.List;


public abstract class GeometricElement {

    public ElementRenderStrategy renderStrategy;


    public abstract void rotateAroundAxis(Vector3f axis, float angle);

    public void getPrimitives(Matrix4f modelMatrix, List<Primitive> primitives) {
        renderStrategy.getPrimitives(modelMatrix, primitives);
    }
}
