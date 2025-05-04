package com.example.celestialspheregeometry.model.utils;

import org.joml.Matrix4f;
import org.joml.Vector3f;


public class MathUtils {

    public static void rotateBetweenVecs(Matrix4f matrix, Vector3f from, Vector3f to)
    {
        Vector3f rot = new Vector3f();
        from = new Vector3f(from).normalize();
        to = new Vector3f(to).normalize();

        from.cross(to, rot);

        float angle = (float) Math.toDegrees(Math.acos(from.dot(to)));

        if (rot.length() > 10e-6) {
            matrix.rotate((float)Math.toRadians(angle), rot.normalize());
        }
    }
}
