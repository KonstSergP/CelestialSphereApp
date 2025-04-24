package com.example.celestialspheregeometry.model.sphere;

import android.content.Context;
import android.opengl.Matrix;

import com.example.celestialspheregeometry.model.sphere.elements.Circle;
import com.example.celestialspheregeometry.model.sphere.elements.GeometricElement;
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


    public Sphere(Context context, Point center, Vector axis, float r) {
        this.center = center; this.rotationAxis = axis; this.radius = r;
        this.context = context;
        createMeridians();
        createParallels();
    }


    public void draw(SphereGLRenderer sphereGLRenderer, float[] rotationMatrix) {
        for (GeometricElement circle: elements) {
            circle.draw(sphereGLRenderer, rotationMatrix);
        }
    }


    public void createMeridians() {
        int k = 6;
        float step = 180f / k;
        for (int i = 0; i < k; i++) {
            float[] vec = new float[]{-rotationAxis.getY(), rotationAxis.getX(), 0, 0};
            float[] rotMatrix = new float[16];
            float angle = i*step;
            System.out.println(angle);
            Matrix.setRotateM(rotMatrix, 0, angle, rotationAxis.getX(), rotationAxis.getY(), rotationAxis.getZ());
            Matrix.multiplyMV(vec, 0, rotMatrix, 0, vec, 0);
            elements.add(new Circle(context, new Point(center), new Vector(vec[0], vec[1], vec[2]), radius));
        }
    }


    public void createParallels() {
        int k = 5;
        for (float a = 180f*k/(k+1) - 90; a > -90f; a -= 180f/(k+1)) {
            float r = (float)(radius*Math.cos(Math.toRadians(a)));
            float d = (float)(radius*Math.sin(Math.toRadians(a)));
            elements.add(new Circle(context,
                                    new Point(center.getX() + rotationAxis.getX() * d,
                                            center.getY() + rotationAxis.getY() * d,
                                            center.getZ() + rotationAxis.getZ() * d),
                                    new Vector(rotationAxis), r));
        }
    }
}
