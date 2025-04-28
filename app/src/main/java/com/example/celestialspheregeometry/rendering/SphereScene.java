package com.example.celestialspheregeometry.rendering;

import android.content.Context;
import android.opengl.Matrix;
import android.os.SystemClock;

import com.example.celestialspheregeometry.model.sphere.Sphere;
import com.example.celestialspheregeometry.model.utils.math.Point;
import com.example.celestialspheregeometry.model.utils.math.Vector;


public class SphereScene {

    Sphere sphere;
    float prevTime = (float)(SystemClock.uptimeMillis() % 10000) / 10000;


    public SphereScene(Context context)
    {
        sphere = new Sphere(context, new Point(0, 0, -6), new Vector(1, 1, -0.5f, true), 2);
    }


    public void update()
    {
        float time = (float)(SystemClock.uptimeMillis() % 10000) / 10000;
        float angle = (time - prevTime) * 360; prevTime = time;
        sphere.rotateAroundAxis(sphere.getRotationAxis(), angle);
    }


    public void draw(SphereGLRenderer sphereGLRenderer) {
        sphere.draw(sphereGLRenderer);
    }
}
