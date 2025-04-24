package com.example.celestialspheregeometry.rendering;

import android.content.Context;
import android.opengl.Matrix;
import android.os.SystemClock;

import com.example.celestialspheregeometry.model.sphere.Sphere;
import com.example.celestialspheregeometry.model.utils.math.Point;
import com.example.celestialspheregeometry.model.utils.math.Vector;


public class SphereScene {

    Sphere sphere;

    float[] rotationMatrix = new float[16];

    public SphereScene(Context context)
    {
        sphere = new Sphere(context, new Point(0, 0, -4), new Vector(1, 1, 1, true), 2);
    }

    public void update()
    {
        float time = (float)(SystemClock.uptimeMillis() % 10000) / 10000;
        float angle = time * 360;

        Matrix.setIdentityM(rotationMatrix, 0);
        Matrix.translateM(rotationMatrix, 0, sphere.getCenter().getX(), sphere.getCenter().getY(), sphere.getCenter().getZ());
        Matrix.rotateM(rotationMatrix, 0, angle, sphere.getRotationAxis().getX(), sphere.getRotationAxis().getY(), sphere.getRotationAxis().getZ());
        Matrix.translateM(rotationMatrix, 0, -sphere.getCenter().getX(), -sphere.getCenter().getY(), -sphere.getCenter().getZ());

    }

    public void draw(SphereGLRenderer sphereGLRenderer)
    {
        sphere.draw(sphereGLRenderer, rotationMatrix);
    }
}
