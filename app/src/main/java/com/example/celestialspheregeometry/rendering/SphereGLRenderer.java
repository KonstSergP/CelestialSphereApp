package com.example.celestialspheregeometry.rendering;


import android.content.Context;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;

import com.example.celestialspheregeometry.controller.sphere.SphereController;
import com.example.celestialspheregeometry.model.utils.Primitive;
import com.example.celestialspheregeometry.model.utils.Primitive.Uniform;
import com.example.celestialspheregeometry.rendering.shaders.GLProgramManager;
import com.example.celestialspheregeometry.rendering.shaders.GLProgramType;

import org.joml.Matrix4f;
import org.joml.Vector3f;

import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import lombok.Getter;


@Getter
public class SphereGLRenderer implements GLSurfaceView.Renderer {

    public final Context context;
    public final GLProgramManager programManager;
    public final SphereController sphereController;
    public final Camera camera;

    private final Matrix4f viewMatrix = new Matrix4f();
    private final Matrix4f projectionMatrix = new Matrix4f();
    private final Matrix4f VPMatrix = new Matrix4f();
    private final Matrix4f MVPMatrix = new Matrix4f();
    List<Primitive> primitives = new ArrayList<>();


    public SphereGLRenderer(Context context, SphereController sphereController) {
        this.context = context;
        this.sphereController = sphereController;
        this.programManager = new GLProgramManager(context);

        camera = new Camera(new Vector3f(0, 0, 0), new Vector3f(0, 0, -1), new Vector3f(0, 1, 0));
        camera.getViewMatrix(viewMatrix);
    }


    @Override
    public void onSurfaceCreated(GL10 unused, EGLConfig config) {
        GLES20.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
    }


    @Override
    public void onSurfaceChanged(GL10 unused, int width, int height) {
        camera.setWidthHeight(width, height);
        camera.setProjectionMatrix(projectionMatrix);
        projectionMatrix.mul(viewMatrix, VPMatrix);
    }


    @Override
    public void onDrawFrame(GL10 unused) {
        clear(); primitives.clear();
        sphereController.updateScene();
        sphereController.getPrimitives(VPMatrix, primitives);
        drawPrimitives(primitives);
    }


    public void clear() {
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT | GLES20.GL_DEPTH_BUFFER_BIT);
    }




    public void drawPrimitives(List<Primitive> primitives) {
        primitives.forEach(this::drawPrimitive);
    }


    public void drawPrimitive(Primitive primitive)
    {
        enableProgram(primitive.getProgram());

        enableAttributes(primitive.getProgram(), primitive.getAttributes());

        loadUniforms(primitive.getProgram(), primitive.getUniforms());

        draw(primitive.getPrimitiveType(), primitive.getLineWidth(), primitive.getPoints());

        disableAttributes(primitive.getProgram(), primitive.getAttributes());
    }


    private void enableProgram(GLProgramType program) {
        GLES20.glUseProgram(programManager.getProgram(program));
    }


    private void enableAttributes(GLProgramType program, Map<String, FloatBuffer> attributes)
    {
        for (var entry: attributes.entrySet())
        {
            int location = GLES20.glGetAttribLocation(programManager.getProgram(program), entry.getKey());
            GLES20.glEnableVertexAttribArray(location);
            GLES20.glVertexAttribPointer(location, 3, GLES20.GL_FLOAT, false, 0, entry.getValue());
        }
    }


    private void disableAttributes(GLProgramType program, Map<String, FloatBuffer> attributes) {
        for (var entry: attributes.entrySet())
        {
            int location = GLES20.glGetAttribLocation(programManager.getProgram(program), entry.getKey());
            GLES20.glDisableVertexAttribArray(location);
        }
    }


    private void loadUniforms(GLProgramType program, Map<String, Uniform> uniforms) {
        for (var entry: uniforms.entrySet())
        {
            int location = GLES20.glGetUniformLocation(programManager.getProgram(program), entry.getKey());
            switch (entry.getValue().getType())
            {
                case MATRIX4f -> GLES20.glUniformMatrix4fv(location, 1, false, entry.getValue().getArray(), 0);
                case VECTOR4f ->  GLES20.glUniform4fv(location, 1, entry.getValue().getArray(), 0);
            }
        }
    }


    private void draw(int primitiveType, float lineWidth, int points) {
        GLES20.glLineWidth(lineWidth);
        GLES20.glDrawArrays(primitiveType, 0, points);
    }
}
