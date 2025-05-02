package com.example.celestialspheregeometry.rendering;


import android.content.Context;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;

import com.example.celestialspheregeometry.controller.sphere.SphereController;
import com.example.celestialspheregeometry.model.sphere.SphereScene;

import org.joml.Matrix4f;
import org.joml.Vector3f;

import java.nio.FloatBuffer;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import lombok.Getter;


@Getter
public class SphereGLRenderer implements GLSurfaceView.Renderer {

    public final Context context;
    public final SphereController sphereController;

    private final Matrix4f viewMatrix = new Matrix4f();
    private final Matrix4f projectionMatrix = new Matrix4f();
    private final Matrix4f VPMatrix = new Matrix4f();
    private final Matrix4f MVPMatrix = new Matrix4f();
    float[] tmpFloatArray = new float[16];

    SphereScene sphereScene;


    public SphereGLRenderer(Context context, SphereController sphereController) {
        this.context = context;
        this.sphereController = sphereController;
    }


    @Override
    public void onSurfaceCreated(GL10 unused, EGLConfig config) {
        GLES20.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);

        this.sphereScene = new SphereScene(context);
        sphereController.setSphereScene(sphereScene);
    }


    @Override
    public void onSurfaceChanged(GL10 unused, int width, int height) {
        GLES20.glViewport(0, 0, width, height);
        Camera.getProjection(projectionMatrix, width, height);
        Camera.getView(viewMatrix, new Vector3f(0, 0, 0), new Vector3f(0, 0, -1), new Vector3f(0, 1, 0));
        Camera.updateViewProjMatrix(VPMatrix, viewMatrix, projectionMatrix);
    }


    @Override
    public void onDrawFrame(GL10 unused) {
        clear();
        sphereScene.update();
        sphereScene.draw(this);
    }


    public void clear() {
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT | GLES20.GL_DEPTH_BUFFER_BIT);
    }


    public void drawLoop(int program, FloatBuffer vertexBuffer, Matrix4f MMatrix, int points)
    {
        GLES20.glUseProgram(program);

        int positionLocation = GLES20.glGetAttribLocation(program, "vPosition");
        GLES20.glEnableVertexAttribArray(positionLocation);
        GLES20.glVertexAttribPointer(positionLocation, 3, GLES20.GL_FLOAT, false, 0, vertexBuffer);

        VPMatrix.mul(MMatrix, MVPMatrix);

        int MVPMatrixLocation = GLES20.glGetUniformLocation(program, "MVPMatrix");
        GLES20.glUniformMatrix4fv(MVPMatrixLocation, 1, false, MVPMatrix.get(tmpFloatArray), 0);

        GLES20.glLineWidth(5.0f);
        GLES20.glDrawArrays(GLES20.GL_LINE_LOOP, 0, points);

        GLES20.glDisableVertexAttribArray(0);
    }
}
