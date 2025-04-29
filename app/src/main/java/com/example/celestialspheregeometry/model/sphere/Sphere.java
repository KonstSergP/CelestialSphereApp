package com.example.celestialspheregeometry.model.sphere;

import android.content.Context;
import android.opengl.Matrix;

import com.example.celestialspheregeometry.model.sphere.elements.astronomy.SphereCircle;
import com.example.celestialspheregeometry.model.sphere.elements.GeometricElement;
import com.example.celestialspheregeometry.model.utils.math.MathUtils;
import com.example.celestialspheregeometry.model.utils.math.Point;
import com.example.celestialspheregeometry.model.utils.math.Vector;
import com.example.celestialspheregeometry.rendering.SphereGLRenderer;
import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class Sphere {

    Point center;
    Vector rotationAxis;
    float radius;
    public final List<GeometricElement> elements = new ArrayList<>();
    public final Context context;

    public float[] tmpMatrix = new float[16];
    public float[] modelMatrix = new float[16];
    public float[] rotationMatrix = new float[16];
    public float[] resModelMatrix = new float[16];


    public Sphere(Context context, Point center, Vector axis, float r) {
        this.center = center; this.rotationAxis = axis; this.radius = r;
        this.context = context;

        Matrix.setIdentityM(modelMatrix, 0);
        Matrix.translateM(modelMatrix, 0, getCenter().getX(), getCenter().getY(), getCenter().getZ());
        Matrix.scaleM(modelMatrix, 0, radius, radius, radius);

        Matrix.setIdentityM(rotationMatrix, 0);
        MathUtils.rotateBetweenVecs(rotationMatrix, new Vector(0, 1, 0), rotationAxis);

        createMeridians(6);
        createParallels(5);
    }


    public void rotateAroundMainAxis(float angle) {
        Matrix.rotateM(rotationMatrix, 0, angle, 0, 1, 0);
    }

    public void rotateAroundAxis(Vector axis, float angle)
    {
        MathUtils.rotateV(rotationAxis, angle, axis.getX(), axis.getY(), axis.getZ());
        Matrix.setRotateM(tmpMatrix, 0, angle, axis.getX(), axis.getY(), axis.getZ());
        Matrix.multiplyMM(rotationMatrix, 0, tmpMatrix, 0, rotationMatrix, 0);
    }


    public void draw(SphereGLRenderer sphereGLRenderer) {
        Matrix.multiplyMM(resModelMatrix, 0, modelMatrix, 0, rotationMatrix, 0);
        for (GeometricElement circle: elements) {
            circle.draw(sphereGLRenderer, resModelMatrix);
        }
    }


    public void createMeridians(int k) {
        float step = 180f / k;
        float[] rotMatrix = new float[16];
        for (int i = 0; i < k; i++) {
            float[] vec = new float[]{0, 0, -1, 0};
            float angle = i*step;
            System.out.println(angle);
            Matrix.setRotateM(rotMatrix, 0, angle, 0, 1, 0);
            Matrix.multiplyMV(vec, 0, rotMatrix, 0, vec, 0);
            elements.add(new SphereCircle(context, new Vector(vec[0], vec[1], vec[2]), 0));
        }
    }


    public void createParallels(int k) {
        for (float shift = 180f*k/(k+1) - 90; shift > -90f; shift -= 180f/(k+1)) {
            elements.add(new SphereCircle(context, new Vector(0, 1, 0), shift));
        }
    }
}
