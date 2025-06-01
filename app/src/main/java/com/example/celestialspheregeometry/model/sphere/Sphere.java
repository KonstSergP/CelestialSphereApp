package com.example.celestialspheregeometry.model.sphere;

import com.example.celestialspheregeometry.model.sphere.elements.sphere.SphereCircle;
import com.example.celestialspheregeometry.model.sphere.elements.GeometricElement;
import com.example.celestialspheregeometry.model.utils.MathUtils;
import com.example.celestialspheregeometry.model.utils.Primitive;

import org.joml.Matrix4f;
import org.joml.Vector3f;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class Sphere {

    private Vector3f center;
    private Vector3f rotationAxis;
    private float radius;
    private final List<GeometricElement> elements = new ArrayList<>();

    private final Matrix4f tmpMatrix = new Matrix4f();
    private final Matrix4f modelMatrix = new Matrix4f();
    private final Matrix4f rotationMatrix = new Matrix4f();
    private final Matrix4f resModelMatrix = new Matrix4f();

    public static final Vector3f UP = new Vector3f(0, 1, 0);


    public Sphere(Vector3f center, Vector3f axis, float r) {
        this.center = center; this.rotationAxis = axis; this.radius = r;

        modelMatrix.translate(center).scale(radius);

        MathUtils.rotateBetweenVecs(rotationMatrix, UP, rotationAxis);

        createMeridians(6);
        createParallels(5);
    }


    public void rotateAroundMainAxis(float angle) {
        rotationMatrix.rotate((float)Math.toRadians(angle), UP);
    }


    public void rotateAroundAxis(Vector3f axis, float angle)
    {
        rotationAxis.rotateAxis((float)Math.toRadians(angle), axis.x, axis.y, axis.z);
        tmpMatrix.identity().rotate((float)Math.toRadians(angle), axis);
        tmpMatrix.mul(rotationMatrix, rotationMatrix);
    }


    public void createMeridians(int k) {
        float step = 180f / k;
        Matrix4f rotMatrix = new Matrix4f();
        for (int i = 0; i < k; i++) {
            Vector3f vec = new Vector3f(0, 0, -1);
            rotMatrix.transformPosition(vec);
            elements.add(new SphereCircle(vec, 0));
            rotMatrix.rotate((float)Math.toRadians(step), UP);
        }
    }


    public void createParallels(int k) {
        for (float shift = 180f*k/(k+1) - 90; shift > -90f; shift -= 180f/(k+1)) {
            elements.add(new SphereCircle(UP, shift));
        }
    }


    public void scale(float scale) {
        modelMatrix.scale(scale);
    }


    public void getPrimitives(Matrix4f outerMatrix, List<Primitive> primitives) {
        modelMatrix.mul(rotationMatrix, resModelMatrix);
        outerMatrix.mul(resModelMatrix, resModelMatrix);
        elements.forEach(element -> element.getPrimitives(resModelMatrix, primitives));
    }


    public GeometricElement getIntersectedElement(Vector3f first, Vector3f second) {
        var matrix = modelMatrix.mul(rotationMatrix, new Matrix4f()).invert();
        var firstLocal = matrix.transformPosition(new Vector3f(first));
        var secondLocal = matrix.transformPosition(new Vector3f(second));

        float d2 = 1e9f; GeometricElement el = null;
        for (var element: elements)
        {
            float d3 = element.distanceToLine(firstLocal, secondLocal);
            if (d3 < d2) {d2 = d3; el=element;}
        }
        if (d2 < 0.05) return el;
        return null;
    }
}
