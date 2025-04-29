package com.example.celestialspheregeometry.rendering;


import android.content.Context;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.Matrix;

import com.example.celestialspheregeometry.model.sphere.SphereScene;
import com.example.celestialspheregeometry.model.utils.math.Point;
import com.example.celestialspheregeometry.model.utils.math.Vector;
import java.nio.FloatBuffer;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;



public class SphereGLRenderer implements GLSurfaceView.Renderer {

    private final Context context;

    private final float[] viewMatrix = new float[16];
    private final float[] projectionMatrix = new float[16];
    private final float[] VPMatrix = new float[16];
    private final float[] MVPMatrix = new float[16];

    SphereScene sphereScene;


    public SphereGLRenderer(Context context) {
        this.context = context;
    }


    @Override
    public void onSurfaceCreated(GL10 unused, EGLConfig config) {
        GLES20.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
        this.sphereScene = new SphereScene(context);
    }


    @Override
    public void onSurfaceChanged(GL10 unused, int width, int height) {
        GLES20.glViewport(0, 0, width, height);
        Camera.getFrustumProjection(projectionMatrix, width, height);
        Camera.getView(viewMatrix, new Point(0, 0, 0), new Point(0, 0, -1), new Vector(0, 1, 0));
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


    public void drawLoop(int program, FloatBuffer vertexBuffer, float[] MMatrix, int points)
    {
        GLES20.glUseProgram(program);

        GLES20.glEnableVertexAttribArray(0);
        GLES20.glVertexAttribPointer(0, 3, GLES20.GL_FLOAT, false, 0, vertexBuffer);

        Matrix.multiplyMM(MVPMatrix, 0, VPMatrix, 0, MMatrix, 0);

        int VPMatrixLocation = GLES20.glGetUniformLocation(program, "MVPMatrix");
        GLES20.glUniformMatrix4fv(VPMatrixLocation, 1, false, MVPMatrix, 0);

        GLES20.glLineWidth(5.0f);
        GLES20.glDrawArrays(GLES20.GL_LINE_LOOP, 0, points);

        GLES20.glDisableVertexAttribArray(0);
    }
}
