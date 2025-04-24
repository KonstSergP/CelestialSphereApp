package com.example.celestialspheregeometry.rendering;

import android.opengl.Matrix;

import com.example.celestialspheregeometry.model.utils.math.Point;
import com.example.celestialspheregeometry.model.utils.math.Vector;

public class Camera {

    public static void getFrustumProjection(float[] projectionMatrix, int width, int height) {
        float aspectRatio = (float) width / height;
        Matrix.frustumM(projectionMatrix, 0, -aspectRatio, aspectRatio, -1f, 1f, 1f, 10f);
    }


    public static void getView(float[] viewMatrix, Point eye, Point viewCenter, Vector up)
    {
        Matrix.setLookAtM(viewMatrix, 0,
                eye.getX(), eye.getY(), eye.getZ(),
                viewCenter.getX(), viewCenter.getY(),
                viewCenter.getZ(), up.getX(), up.getY(), up.getZ());
    }

    public static void updateViewProjMatrix(float[] VPMatrix, float[] viewMatrix, float[] projectionMatrix) {
        Matrix.multiplyMM(VPMatrix, 0, projectionMatrix, 0, viewMatrix, 0);
    }
}
