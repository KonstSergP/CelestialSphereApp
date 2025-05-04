package com.example.celestialspheregeometry.model.sphere.elements.geometry;

import com.example.celestialspheregeometry.model.sphere.elements.GeometricElement;
import com.example.celestialspheregeometry.model.utils.MathUtils;
import com.example.celestialspheregeometry.rendering.SphereGLRenderer;
import com.example.celestialspheregeometry.rendering.shaders.GLProgramType;
import com.example.celestialspheregeometry.model.utils.BufferUtils;

import org.joml.Matrix4f;
import org.joml.Vector3f;

import java.nio.FloatBuffer;
import lombok.Getter;



@Getter
public class Circle implements GeometricElement {

    public GLProgramType program;
    public FloatBuffer vertexBuffer;

    public Matrix4f modelMatrix = new Matrix4f();
    public Matrix4f rotationMatrix = new Matrix4f();
    public Matrix4f resModelMatrix = new Matrix4f();

    public Vector3f center;
    public Vector3f ort;
    public float radius;


    public Circle(Vector3f center, Vector3f ort, float radius) {
        program = GLProgramType.DEFAULT;
        this.center = center; this.ort = ort; this.radius = radius;
        generateVertices();
        generateModelMatrix();
    }


    public void rotateAroundAxis(Vector3f axis, float angle) {
        ort.rotateAxis((float)Math.toRadians(angle), axis.x, axis.y, axis.z);
        rotationMatrix.identity().rotate((float)Math.toRadians(angle), axis);
        rotationMatrix.mul(modelMatrix, modelMatrix);
    }


    public void generateVertices()
    {
        float[] vertices = new float[360 * 3];

        for (int i = 0; i < 360; i++) {
            double ang = Math.toRadians(i);
            vertices[i*3] = (float) Math.cos(ang) * radius;
            vertices[i*3 + 1] = 0;
            vertices[i*3 + 2] = (float) Math.sin(ang) * radius;
        }

        vertexBuffer = BufferUtils.convertToFloatBuffer(vertices);
    }


    void generateModelMatrix()
    {
        modelMatrix.setTranslation(center);
        MathUtils.rotateBetweenVecs(modelMatrix, new Vector3f(0, 1, 0), ort);
    }


    public void draw(SphereGLRenderer sphereGLRenderer, Matrix4f sphereMatrix) {
        sphereMatrix.mul(modelMatrix, resModelMatrix);
        sphereGLRenderer.drawLoop(program, vertexBuffer, resModelMatrix, 360);
    }
}
