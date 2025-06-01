package com.example.celestialspheregeometry.model.sphere.elements.geometry.circle;


import com.example.celestialspheregeometry.model.sphere.elements.GeometricElement;
import com.example.celestialspheregeometry.model.utils.MathUtils;

import org.joml.Matrix4f;
import org.joml.Vector3f;
import lombok.Getter;


@Getter
public class Circle extends GeometricElement {

    private final Matrix4f modelMatrix = new Matrix4f();
    private final Matrix4f rotationMatrix = new Matrix4f();

    private Vector3f center;
    private Vector3f ort;
    private float radius;

    public static final Vector3f DEFAULT_ORT = new Vector3f(0, 1,0);


    public Circle(Vector3f center, Vector3f ort, float radius) {
        this.center = new Vector3f(center); this.ort = new Vector3f(ort); this.radius = radius;
        setModelMatrix();
        renderStrategy = new DefaultCircleRenderStrategy(this);
    }


    @Override
    public void rotateAroundAxis(Vector3f axis, float angle) {
        ort.rotateAxis((float)Math.toRadians(angle), axis.x, axis.y, axis.z);
        rotationMatrix.identity().rotate((float)Math.toRadians(angle), axis);
        rotationMatrix.mul(modelMatrix, modelMatrix);
    }


    void setModelMatrix()
    {
        modelMatrix.setTranslation(center).scale(radius);
        MathUtils.rotateBetweenVecs(modelMatrix, DEFAULT_ORT, ort);
    }


    @Override
    public float distanceToLine(Vector3f start, Vector3f end) {
        var matrix = new Matrix4f(modelMatrix).invert();
        var firstLocal = matrix.transformPosition(new Vector3f(start));
        var secondLocal = matrix.transformPosition(new Vector3f(end));
        var s = new Vector3f(secondLocal).sub(firstLocal);
        var m0 = new Vector3f();
        var first = new Vector3f();

        float mind = 10e9f;
        for (int i = 0; i < 360; i++) {
            double ang = Math.toRadians(i);
            m0.set((float) Math.cos(ang), 0, (float) Math.sin(ang));
            float d = first.set(firstLocal).sub(m0).cross(s).length() / s.length();
            if (d < mind) mind = d;
        }

        return mind * radius;
    }
}
