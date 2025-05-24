package com.example.celestialspheregeometry.model.sphere;

import android.os.SystemClock;

import com.example.celestialspheregeometry.model.utils.Primitive;

import org.joml.Matrix4f;
import org.joml.Vector3f;

import java.util.List;

import lombok.Getter;


@Getter
public class SphereScene {

    private Sphere sphere;
    private float prevTime = (float)(SystemClock.uptimeMillis() % 10000) / 10000;


    public SphereScene() {
        sphere = new Sphere(new Vector3f(0, 0, -6), new Vector3f(1, 1, -0.5f), 2);
    }


    public void update()
    {
        float time = (float)(SystemClock.uptimeMillis() % 10000) / 10000;
        float angle = (time - prevTime) * 360; prevTime = time;
        sphere.rotateAroundMainAxis(angle);
    }


    public void getPrimitives(Matrix4f modelMatrix, List<Primitive> primitives) {
        sphere.getPrimitives(modelMatrix, primitives);
    }
}
