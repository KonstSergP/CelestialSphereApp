package com.example.celestialspheregeometry.model.sphere.elements.astronomy;

import android.content.Context;

import com.example.celestialspheregeometry.model.sphere.elements.GeometricElement;
import com.example.celestialspheregeometry.model.sphere.elements.geometry.Circle;
import com.example.celestialspheregeometry.rendering.SphereGLRenderer;

import org.joml.Matrix4f;
import org.joml.Vector3f;


public class SphereCircle implements GeometricElement {

    Circle circle;


    public SphereCircle(Vector3f ort, float shift)
    {
        float radius = (float)(Math.cos(Math.toRadians(shift)));
        float d = (float)(Math.sin(Math.toRadians(shift)));
        circle = new Circle(new Vector3f(ort).mul(d), ort, radius);
    }


    @Override
    public void draw(SphereGLRenderer sphereGLRenderer, Matrix4f rotationMatrix) {
        circle.draw(sphereGLRenderer, rotationMatrix);
    }


    @Override
    public void rotateAroundAxis(Vector3f axis, float angle) {
        circle.rotateAroundAxis(axis, angle);
    }
}
