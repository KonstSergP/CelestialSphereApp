package com.example.celestialspheregeometry.controller.sphere;

import com.example.celestialspheregeometry.model.sphere.SphereScene;
import com.example.celestialspheregeometry.model.utils.Primitive;
import com.example.celestialspheregeometry.rendering.SphereGLRenderer;

import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.joml.Vector4f;

import java.util.List;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class SphereController {

    private SphereScene sphereScene;
    private SphereGLRenderer sphereRenderer;
    private SphereRotationController sphereRotationController;

    int width, height;


    public SphereController(SphereScene sphereScene) {
        this.sphereScene = sphereScene;
        sphereRotationController = new SphereRotationController(sphereScene);
    }


    public void setRenderer(SphereGLRenderer sphereGLRenderer) {
        this.sphereRenderer = sphereGLRenderer;
    }



    public void setWidthHeight(int width, int height) {
        this.width = width; this.height = height;
    }


    public void scaleSphere(float scale) {
        sphereScene.getSphere().scale(scale);
    }


    public void updateScene() {
        sphereScene.update();
    }


    public void getPrimitives(Matrix4f VPMatrix, List<Primitive> primitives) {
        sphereScene.getPrimitives(VPMatrix, primitives);
    }



    public void handleTap(float x, float y)
    {
        x = (2.0f * x) / width - 1.0f;
        y = 1.0f - (2.0f * y) / height;
        float z_near = -1.0f;
        float z_far = 1.0f;
        Vector4f near = new Vector4f(x, y, z_near, 1), far = new Vector4f(x, y, z_far, 1);
        Matrix4f proj = new Matrix4f(sphereRenderer.getProjectionMatrix()).invert();
        Matrix4f view = new Matrix4f(sphereRenderer.getViewMatrix()).invert();

        proj.transform(near); proj.transform(far);

        near.div(near.w); far.div(far.w);

        view.transform(near); view.transform(far);
        var near3 = near.xyz(new Vector3f());
        var far3 = far.xyz(new Vector3f());

        sphereScene.findIntersection(near3, far3);
    }


    public void handleScroll(float distanceX, float distanceY) {
        sphereRotationController.handleScroll(distanceX, distanceY);
    }
}
