package com.example.celestialspheregeometry.model.sphere.elements.astronomy;

import android.content.Context;

import com.example.celestialspheregeometry.model.sphere.elements.GeometricElement;
import com.example.celestialspheregeometry.model.sphere.elements.geometry.Circle;
import com.example.celestialspheregeometry.model.utils.math.Point;
import com.example.celestialspheregeometry.model.utils.math.Vector;
import com.example.celestialspheregeometry.rendering.SphereGLRenderer;


public class SphereCircle implements GeometricElement {

    Circle circle;


    public SphereCircle(Context context, Vector ort, float shift)
    {
        float radius = (float)(Math.cos(Math.toRadians(shift)));
        float d = (float)(Math.sin(Math.toRadians(shift)));
        circle = new Circle(context, new Point(ort.getX() * d, ort.getY() * d, ort.getZ() * d), ort, radius);
    }


    @Override
    public void draw(SphereGLRenderer sphereGLRenderer, float[] rotationMatrix) {
        circle.draw(sphereGLRenderer, rotationMatrix);
    }


    @Override
    public void rotateAroundAxis(Vector axis, float angle) {
        circle.rotateAroundAxis(axis, angle);
    }
}
