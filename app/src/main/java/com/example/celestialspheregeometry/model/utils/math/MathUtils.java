package com.example.celestialspheregeometry.model.utils.math;

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
}

