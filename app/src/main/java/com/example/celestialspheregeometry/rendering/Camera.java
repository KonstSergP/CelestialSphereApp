package com.example.celestialspheregeometry.rendering;

import org.joml.Matrix4f;
import org.joml.Vector3f;


public class Camera {

    public static void getProjection(Matrix4f projectionMatrix, int width, int height) {
        float aspectRatio = (float) width / height;
        projectionMatrix.identity().perspective((float)Math.toRadians(120), aspectRatio, 0.1f, 100f);
    }


    public static void getView(Matrix4f viewMatrix, Vector3f eye, Vector3f viewCenter, Vector3f up) {
        viewMatrix.setLookAt(eye, viewCenter, up);
    }


    public static void updateViewProjMatrix(Matrix4f VPMatrix, Matrix4f viewMatrix, Matrix4f projectionMatrix) {
        projectionMatrix.mul(viewMatrix, VPMatrix);
    }
}
