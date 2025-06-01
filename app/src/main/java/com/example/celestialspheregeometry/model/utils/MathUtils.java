package com.example.celestialspheregeometry.model.utils;

import org.joml.Matrix4f;
import org.joml.Vector3f;


public class MathUtils {


    private static final ThreadLocal<Matrix4f> ThreadTmp = new ThreadLocal() {
        @Override
        public Matrix4f initialValue() {
            return new Matrix4f();
        }
    };


    public static void rotateBetweenVecs(Vector3f vector, Vector3f from, Vector3f to) {
        rotateBetweenVecs(ThreadTmp.get().identity(), from, to).transformPosition(vector);
    }

    public static Matrix4f rotateBetweenVecs(Matrix4f matrix, Vector3f from, Vector3f to)
    {
        Vector3f rot = new Vector3f();
        from = new Vector3f(from).normalize();
        to = new Vector3f(to).normalize();

        from.cross(to, rot);

        float angle = (float) Math.acos(from.dot(to));

        if (rot.length() > 10e-6) {
            matrix.rotate(angle, rot.normalize());
        }

        return matrix;
    }
}
