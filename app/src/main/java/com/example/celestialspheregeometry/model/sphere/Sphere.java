package com.example.celestialspheregeometry.model.sphere;

import android.content.Context;

import com.example.celestialspheregeometry.model.sphere.elements.astronomy.SphereCircle;
import com.example.celestialspheregeometry.model.sphere.elements.GeometricElement;
import com.example.celestialspheregeometry.model.utils.MathUtils;
import com.example.celestialspheregeometry.rendering.SphereGLRenderer;

import org.joml.Matrix4f;
import org.joml.Vector3f;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class Sphere {

    public Vector3f center;
    public Vector3f rotationAxis;
    public float radius;
    public final List<GeometricElement> elements = new ArrayList<>();

    public Matrix4f tmpMatrix = new Matrix4f();
    public Matrix4f modelMatrix = new Matrix4f();
    public Matrix4f rotationMatrix = new Matrix4f();
    public Matrix4f resModelMatrix = new Matrix4f();


    public Sphere(Vector3f center, Vector3f axis, float r) {
        this.center = center; this.rotationAxis = axis; this.radius = r;

        modelMatrix.translate(center).scale(radius);

        MathUtils.rotateBetweenVecs(rotationMatrix, new Vector3f(0, 1, 0), rotationAxis);

        createMeridians(6);
        createParallels(5);
    }


    public void rotateAroundMainAxis(float angle) {
        rotationMatrix.rotate((float)Math.toRadians(angle), new Vector3f(0, 1, 0));
    }


    public void rotateAroundAxis(Vector3f axis, float angle)
    {
        rotationAxis.rotateAxis((float)Math.toRadians(angle), axis.x, axis.y, axis.z);
        tmpMatrix.identity().rotate((float)Math.toRadians(angle), axis);
        tmpMatrix.mul(rotationMatrix, rotationMatrix);
    }


    public void draw(SphereGLRenderer sphereGLRenderer) {
        modelMatrix.mul(rotationMatrix, resModelMatrix);
        for (GeometricElement circle: elements) {
            circle.draw(sphereGLRenderer, resModelMatrix);
        }
    }


    public void createMeridians(int k) {
        float step = 180f / k;
        Matrix4f rotMatrix = new Matrix4f();
        for (int i = 0; i < k; i++) {
            Vector3f vec = new Vector3f(0, 0, -1);
            rotMatrix.transformPosition(vec);
            elements.add(new SphereCircle(vec, 0));
            rotMatrix.rotate((float)Math.toRadians(step), new Vector3f(0, 1, 0));
        }
    }


    public void createParallels(int k) {
        for (float shift = 180f*k/(k+1) - 90; shift > -90f; shift -= 180f/(k+1)) {
            elements.add(new SphereCircle(new Vector3f(0, 1, 0), shift));
        }
    }

    public void scale(float scale) {
        modelMatrix.scale(scale);
    }
}
