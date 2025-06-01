package com.example.celestialspheregeometry.controller;

import com.example.celestialspheregeometry.model.sphere.SphereScene;
import com.example.celestialspheregeometry.model.utils.Primitive;
import com.example.celestialspheregeometry.rendering.SphereGLRenderer;

import org.joml.Matrix4f;

import java.util.List;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class SphereController {

    private SphereScene sphereScene;
    private SphereRotationController sphereRotationController;
    private ElementSelectionController elementSelectionController;


    public SphereController(SphereScene sphereScene) {
        this.sphereScene = sphereScene;
        sphereRotationController = new SphereRotationController(sphereScene);
        elementSelectionController = new ElementSelectionController(sphereScene);
    }


    public void setRenderer(SphereGLRenderer sphereGLRenderer) {
        sphereRotationController.setRenderer(sphereGLRenderer);
        elementSelectionController.setRenderer(sphereGLRenderer);
    }


    public void setWidthHeight(int width, int height) {
        elementSelectionController.setWidthHeight(width, height);
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


    public void handleTap(float x, float y) {
        elementSelectionController.handleTap(x, y);
    }


    public void handleScroll(float distanceX, float distanceY) {
        sphereRotationController.handleScroll(distanceX, distanceY);
    }
}
