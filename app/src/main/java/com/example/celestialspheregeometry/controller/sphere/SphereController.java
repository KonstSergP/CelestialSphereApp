package com.example.celestialspheregeometry.controller.sphere;

import com.example.celestialspheregeometry.model.sphere.SphereScene;
import com.example.celestialspheregeometry.model.utils.Primitive;

import org.joml.Matrix4f;
import org.joml.Vector3f;

import java.util.List;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class SphereController {

    private final SphereScene sphereScene;

    private final Vector3f tmp1 = new Vector3f(), tmp2 = new Vector3f();


    public SphereController(SphereScene sphereScene) {
        this.sphereScene = sphereScene;
    }


    public void scaleSphere(float scale) {
        sphereScene.getSphere().scale(scale);
    }


    public void handleScroll(float distanceX, float distanceY) {
        float swipeLength = (float) Math.sqrt(distanceX * distanceX + distanceY * distanceY);
        distanceX /= swipeLength;
        distanceY /= swipeLength;

        Vector3f rot = tmp1.set(0, 0, 1).cross(tmp2.set(-distanceX, distanceY, 0));
        sphereScene.getSphere().rotateAroundAxis(rot, swipeLength);
    }


    public void updateScene() {
        sphereScene.update();
    }


    public void getPrimitives(Matrix4f modelMatrix, List<Primitive> primitives) {
        sphereScene.getPrimitives(modelMatrix, primitives);
    }
}
