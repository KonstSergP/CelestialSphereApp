package com.example.celestialspheregeometry.model.sphere.elements.astronomy;


import com.example.celestialspheregeometry.model.sphere.elements.GeometricElement;
import com.example.celestialspheregeometry.model.sphere.elements.geometry.Circle;
import com.example.celestialspheregeometry.model.utils.Primitive;

import org.joml.Matrix4f;
import org.joml.Vector3f;

import java.util.List;


public class SphereCircle implements GeometricElement {

    Circle circle;


    public SphereCircle(Vector3f ort, float shift)
    {
        float radius = (float)(Math.cos(Math.toRadians(shift)));
        float d = (float)(Math.sin(Math.toRadians(shift)));
        circle = new Circle(new Vector3f(ort).mul(d), ort, radius);
    }


    @Override
    public void rotateAroundAxis(Vector3f axis, float angle) {
        circle.rotateAroundAxis(axis, angle);
    }


    @Override
    public void getPrimitives(Matrix4f modelMatrix, List<Primitive> primitives) {
        circle.getPrimitives(modelMatrix, primitives);
    }
}
