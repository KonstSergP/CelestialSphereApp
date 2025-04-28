package com.example.celestialspheregeometry.model.sphere.elements.geometry;

import android.content.Context;
import android.opengl.Matrix;

import com.example.celestialspheregeometry.model.sphere.elements.GeometricElement;
import com.example.celestialspheregeometry.model.utils.math.MathUtils;
import com.example.celestialspheregeometry.model.utils.math.Point;
import com.example.celestialspheregeometry.model.utils.math.Vector;
import com.example.celestialspheregeometry.rendering.SphereGLRenderer;
import com.example.celestialspheregeometry.rendering.shaders.ProgramBuilder;
import com.example.celestialspheregeometry.model.utils.BufferUtils;
import java.nio.FloatBuffer;
import lombok.Getter;



@Getter
public class Circle implements GeometricElement {

    public int program;
    public FloatBuffer vertexBuffer;
    public float[] modelMatrix = new float[16];

    public float[] rotationMatrix = new float[16];
    public float[] rotatedOrt = new float[4];

    public float[] resModelMatrix = new float[16];

    Point center;
    Vector ort;
    float radius;


    public Circle(Context context, Point center, Vector ort, float radius) {
        program = ProgramBuilder.buildProgram(context, "Circle");
        this.center = center; this.ort = ort; this.radius = radius;
        generateVertices();
        generateModelMatrix();
    }


    public void rotateAroundAxis(Vector axis, float angle) {
        Matrix.setRotateM(rotationMatrix, 0, angle, axis.getX(), axis.getY(), axis.getZ());
        Matrix.multiplyMM(modelMatrix, 0, rotationMatrix, 0, modelMatrix, 0);
        Matrix.multiplyMV(rotatedOrt, 0, rotationMatrix, 0, axis.to4FloatArray(), 0);
        ort = new Vector(rotatedOrt[0], rotatedOrt[1], rotatedOrt[2]);
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
        Matrix.setIdentityM(modelMatrix, 0);
        Matrix.translateM(modelMatrix, 0, center.getX(), center.getY(), center.getZ());

        MathUtils.rotateBetweenVecs(modelMatrix, new Vector(0, 1, 0), ort);
    }


    public void draw(SphereGLRenderer sphereGLRenderer, float[] sphereMatrix) {
        Matrix.multiplyMM(resModelMatrix, 0, sphereMatrix, 0, modelMatrix, 0);
        sphereGLRenderer.drawLoop(program, vertexBuffer, resModelMatrix, 360);
    }
}
