package com.example.celestialspheregeometry.model.sphere.elements.geometry.circle;


import com.example.celestialspheregeometry.model.sphere.elements.GeometricElement;
import com.example.celestialspheregeometry.model.utils.MathUtils;

import org.joml.Matrix4f;
import org.joml.Vector3f;
import lombok.Getter;


@Getter
public class Circle extends GeometricElement {

    public Matrix4f modelMatrix = new Matrix4f();
    public Matrix4f rotationMatrix = new Matrix4f();

    public Vector3f center;
    public Vector3f ort;
    public float radius;

    public static Vector3f DEFAULT_ORT = new Vector3f(0, 1,0);


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
}
