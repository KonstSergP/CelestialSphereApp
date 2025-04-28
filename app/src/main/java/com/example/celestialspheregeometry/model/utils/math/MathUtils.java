package com.example.celestialspheregeometry.model.utils.math;

import android.opengl.Matrix;

public class MathUtils {

    public static void cross(float[] first, float[] second, float[] dest) {
        dest[0] = first[1] * second[2] - first[2] * second[1];
        dest[1] = first[2] * second[0] - first[0] * second[2];
        dest[2] = first[0] * second[1] - first[1] * second[0];
    }

    public static float norm(float[] vec) {
        return (float) Math.sqrt(vec[0]*vec[0] + vec[1]*vec[1] + vec[2]*vec[2]);
    }

    public static float norm(Vector vec)
    {
        return (float) Math.sqrt(vec.getX()*vec.getX() + vec.getY()*vec.getY() + vec.getZ()*vec.getZ());
    }

    public static void normalize(float[] vec) {
        float sum = norm(vec);
        vec[0] /= sum;
        vec[1] /= sum;
        vec[2] /= sum;
    }

    public static void normalize(Vector vec)
    {
        float sum = norm(vec);
        vec.setX(vec.getX() / sum);
        vec.setY(vec.getY() / sum);
        vec.setZ(vec.getZ() / sum);
    }

    public static float dot(float[] l, float[] r) {
        return l[0]*r[0] + l[1]*r[1] + l[2]*r[2];
    }


    public static void rotateBetweenVecs(float[] matrix, Vector from, Vector to)
    {
        float[] rot = new float[3];
        float[] fromArr = from.toFloatArray();
        float[] toArr = to.toFloatArray();
        MathUtils.normalize(fromArr);
        MathUtils.normalize(toArr);

        MathUtils.cross(fromArr, toArr, rot);

        float angle = (float) Math.toDegrees(Math.acos(MathUtils.dot(fromArr, toArr)));

        if (MathUtils.norm(rot) > 10e-6) {
            Matrix.rotateM(matrix, 0, angle, rot[0], rot[1], rot[2]);
        }
    }


    public static void rotateV(float[] vec, float a, float x, float y, float z) {
        float[] tmp = new float[16];
        Matrix.setRotateM(tmp, 0, a, x, y, z);
        Matrix.multiplyMV(vec, 0, tmp, 0, vec, 0);
    }

    public static void rotateV(Vector vec, float a, float x, float y, float z) {
        float[] tmpVec = vec.to4FloatArray();
        rotateV(tmpVec, a, x, y, z);
        vec.setX(tmpVec[0]); vec.setY(tmpVec[1]); vec.setZ(tmpVec[2]);
    }
}
