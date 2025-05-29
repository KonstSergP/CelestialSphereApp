package com.example.celestialspheregeometry.rendering;

import android.opengl.GLES20;

import org.joml.Matrix4f;
import org.joml.Vector3f;

import lombok.Getter;


@Getter
public class Camera {

    private int width, height;
    private final Vector3f eye, viewCenter, up;


    Camera(Vector3f eye, Vector3f viewCenter, Vector3f up) {
        this.eye = new Vector3f(eye); this.viewCenter = new Vector3f(viewCenter); this.up = new Vector3f(up);
    }


    public void setWidthHeight(int width, int height)
    {
        this.width = width;
        this.height = height;
        GLES20.glViewport(0, 0, width, height);
    }


    public void setProjectionMatrix(Matrix4f projectionMatrix) {
        float aspectRatio = (float) width / height;
        projectionMatrix.identity().perspective((float)Math.toRadians(120), aspectRatio, 0.1f, 100f);
    }


    public void setView(Vector3f eye, Vector3f viewCenter, Vector3f up) {
        this.eye.set(eye); this.viewCenter.set(viewCenter); this.up.set(up);
    }


    public void setViewMatrix(Matrix4f viewMatrix) {
        viewMatrix.setLookAt(eye, viewCenter, up);
    }
}
