package com.example.celestialspheregeometry.model.sphere.elements.geometry.circle;

import android.opengl.GLES20;

import com.example.celestialspheregeometry.model.sphere.elements.ElementRenderStrategy;
import com.example.celestialspheregeometry.model.utils.BufferUtils;
import com.example.celestialspheregeometry.model.utils.Primitive;
import com.example.celestialspheregeometry.rendering.shaders.GLProgramType;

import org.joml.Matrix4f;

import java.nio.FloatBuffer;
import java.util.List;
import java.util.Map;


public class DefaultCircleRenderStrategy implements ElementRenderStrategy {

    private final Circle circle;

    private FloatBuffer vertexBuffer;
    private final Primitive primitive = new Primitive();
    private final Matrix4f resModelMatrix = new Matrix4f();


    public DefaultCircleRenderStrategy(Circle circle) {
        this.circle = circle;

        generateVertices();

        primitive.setProgram(GLProgramType.DEFAULT);
        primitive.setPrimitiveType(GLES20.GL_LINE_LOOP);
        primitive.setUniforms(Map.of("MVPMatrix", new Primitive.Uniform(Primitive.UniformType.MATRIX4f)));
        primitive.setPoints(360);
        primitive.setLineWidth(5.0f);
        primitive.setAttributes(Map.of("vPosition", vertexBuffer));
    }


    public void generateVertices()
    {
        float[] vertices = new float[360 * 3];

        for (int i = 0; i < 360; i++) {
            double ang = Math.toRadians(i);
            vertices[i*3] = (float) Math.cos(ang);
            vertices[i*3 + 1] = 0;
            vertices[i*3 + 2] = (float) Math.sin(ang);
        }

        vertexBuffer = BufferUtils.convertToFloatBuffer(vertices);
    }


    @Override
    public void getPrimitives(Matrix4f outerMatrix, List<Primitive> primitives) {
        outerMatrix.mul(circle.getModelMatrix(), resModelMatrix);
        resModelMatrix.get(primitive.getUniforms().get("MVPMatrix").getArray());
        primitives.add(primitive);
    }
}
