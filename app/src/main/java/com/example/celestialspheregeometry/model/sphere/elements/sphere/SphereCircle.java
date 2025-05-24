package com.example.celestialspheregeometry.model.sphere.elements.sphere;


import static java.lang.Math.cos;
import static java.lang.Math.sin;

import com.example.celestialspheregeometry.model.sphere.elements.geometry.circle.Circle;

import org.joml.Vector3f;


public class SphereCircle extends Circle {


    public SphereCircle(Vector3f ort, float shift)
    {
        super(new Vector3f(ort).mul((float)sin(Math.toRadians(shift))),
                ort,
                (float)cos(Math.toRadians(shift)));
    }
}
