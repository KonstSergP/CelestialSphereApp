package com.example.celestialspheregeometry.model.sphere;

import android.content.Context;
import android.os.SystemClock;
import com.example.celestialspheregeometry.rendering.SphereGLRenderer;

import org.joml.Vector3f;

import lombok.Getter;


@Getter
public class SphereScene {

    Sphere sphere;
    float prevTime = (float)(SystemClock.uptimeMillis() % 10000) / 10000;


    public SphereScene() {
        sphere = new Sphere(new Vector3f(0, 0, -6), new Vector3f(1, 1, -0.5f), 2);
    }


    public void update()
    {
        float time = (float)(SystemClock.uptimeMillis() % 10000) / 10000;
        float angle = (time - prevTime) * 360; prevTime = time;
        sphere.rotateAroundMainAxis(angle);
    }


    public void draw(SphereGLRenderer sphereGLRenderer) {
        sphere.draw(sphereGLRenderer);
    }
}
