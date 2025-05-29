package com.example.celestialspheregeometry.model.sphere;

import android.os.SystemClock;
import android.util.Pair;

import com.example.celestialspheregeometry.model.sphere.elements.GeometricElement;
import com.example.celestialspheregeometry.model.utils.Primitive;

import org.joml.GeometryUtils;
import org.joml.Matrix4f;
import org.joml.Vector3f;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class SphereScene {

    private Sphere sphere;
    private float prevTime = (float)(SystemClock.uptimeMillis() % 10000) / 10000;
    private boolean rotationEnabled = true;
    private float rotationSpeedFactor = 1.0f;


    public static float getCurrentRotation() {return (float)(SystemClock.uptimeMillis() % 10000) / 10000;}


    public SphereScene() {
        sphere = new Sphere(new Vector3f(0, 0, -6), new Vector3f(1, 1, -0.5f), 4);
    }


    public void setRotationEnabled(boolean rotationEnabled) {
        if (rotationEnabled) prevTime = getCurrentRotation();
        this.rotationEnabled = rotationEnabled;
    }

    public void update()
    {
        if (!rotationEnabled) {
            prevTime = getCurrentRotation();
            return;
        }

        float time = getCurrentRotation();
        float deltaTime = time - prevTime;
        float angle = deltaTime * 360 * rotationSpeedFactor;
        prevTime = time;
        sphere.rotateAroundMainAxis(angle);
    }


    public void getPrimitives(Matrix4f modelMatrix, List<Primitive> primitives) {
        sphere.getPrimitives(modelMatrix, primitives);
    }

    public void findIntersection(Vector3f first, Vector3f second) {
        sphere.findIntersection(first, second);
    }
}
