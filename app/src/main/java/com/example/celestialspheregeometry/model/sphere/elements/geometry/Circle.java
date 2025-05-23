package com.example.celestialspheregeometry.model.sphere.elements.geometry;

import android.opengl.GLES20;

import com.example.celestialspheregeometry.model.sphere.elements.GeometricElement;
import com.example.celestialspheregeometry.model.utils.MathUtils;
import com.example.celestialspheregeometry.model.utils.Primitive;
import com.example.celestialspheregeometry.model.utils.Primitive.*;
import com.example.celestialspheregeometry.rendering.shaders.GLProgramType;
import com.example.celestialspheregeometry.model.utils.BufferUtils;

import org.joml.Matrix4f;
import org.joml.Vector3f;

import java.nio.FloatBuffer;
import java.util.List;
import java.util.Map;
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

    public Primitive primitive = new Primitive();

    public static Vector3f DEFAULT_ORT = new Vector3f(0, 1,0);


    public Circle(Vector3f center, Vector3f ort, float radius) {
        this.center = new Vector3f(center); this.ort = new Vector3f(ort); this.radius = radius;

        primitive.setProgram(GLProgramType.DEFAULT);
        primitive.setPrimitiveType(GLES20.GL_LINE_LOOP);
        primitive.setUniforms(Map.of("MVPMatrix", new Uniform(UniformType.MATRIX4f)));
        primitive.setPoints(360);
        primitive.setLineWidth(5.0f);

        generateVertices();
        setModelMatrix();

        primitive.setAttributes(Map.of("vPosition", vertexBuffer));
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


    void setModelMatrix()
    {
        modelMatrix.setTranslation(center);
        MathUtils.rotateBetweenVecs(modelMatrix, DEFAULT_ORT, ort);
    }


    @Override
    public void getPrimitives(Matrix4f outerMatrix, List<Primitive> primitives) {
        outerMatrix.mul(modelMatrix, resModelMatrix);
        resModelMatrix.get(primitive.getUniforms().get("MVPMatrix").getArray());
        primitives.add(primitive);
    }
}
