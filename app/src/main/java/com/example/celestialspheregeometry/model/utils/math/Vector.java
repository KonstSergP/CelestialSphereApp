package com.example.celestialspheregeometry.model.utils.math;


import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class Vector {
    float x;
    float y;
    float z;


    public Vector(float x, float y, float z, boolean normalize)
    {
        this.x = x; this.y = y; this.z = z;
        if (normalize) normalize();
    }

    public Vector(float x, float y, float z)
    {
        this(x, y, z, false);
    }


    public Vector(Vector vector) {
        x = vector.getX(); y = vector.getY(); z = vector.getZ();
    }


    public void normalize() {
        MathUtils.normalize(this);
    }


    public float[] toFloatArray() {
        return new float[]{x, y, z};
    }

    public float[] to4FloatArray() {
        return new float[]{x, y, z, 0};
    }
}
