package com.example.celestialspheregeometry.model.sphere.elements;

import android.content.Context;
import android.opengl.Matrix;

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

    Point center;
    Vector ort;
    float radius;


    public Circle(Context context, Point center, Vector ort, float radius) {
        program = ProgramBuilder.buildProgram(context, "Circle");
        this.center = center; this.ort = ort; this.radius = radius;
        generateVertices();
    }


    public void setOrt(Vector vec)
    {
        this.ort = vec;
        generateVertices();
    }


    public void generateVertices()
    {
        float[] vertices = new float[360 * 3];
        float[] tmp = new float[4];
        float[] rot = new float[3];
        float[] rotMatrix = new float[16];
        float[] perpendicular = ort.toFloatArray();
        MathUtils.normalize(perpendicular);
        float[] ver = {0, 1, 0};

        MathUtils.cross(ver, perpendicular, rot);

        float angle = (float) Math.toDegrees(Math.acos(MathUtils.dot(ver, perpendicular)));


        if (MathUtils.norm(rot) < 10e-6) {
            Matrix.setIdentityM(rotMatrix, 0);
        } else {
            Matrix.setRotateM(rotMatrix, 0, angle, rot[0], rot[1], rot[2]);
        }

        for (int i = 0; i < 360; i++) {
            double ang = Math.toRadians(i);
            tmp[0] = (float) Math.cos(ang) * radius;
            tmp[1] = 0;
            tmp[2] = (float) Math.sin(ang) * radius;
            tmp[3] = 0;

            Matrix.multiplyMV(tmp, 0, rotMatrix, 0, tmp, 0);

            vertices[i*3] = center.getX() + tmp[0];
            vertices[i*3 + 1] = center.getY() + tmp[1];
            vertices[i*3 + 2] = center.getZ() + tmp[2];
        }

        vertexBuffer = BufferUtils.convertToFloatBuffer(vertices);
    }


    public void draw(SphereGLRenderer sphereGLRenderer, float[] rotationMatrix) {
        sphereGLRenderer.drawLoop(program, vertexBuffer, rotationMatrix, 360);
    }
}
