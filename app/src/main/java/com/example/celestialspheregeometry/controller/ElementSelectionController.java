package com.example.celestialspheregeometry.controller;

import com.example.celestialspheregeometry.model.sphere.SphereScene;
import com.example.celestialspheregeometry.model.sphere.elements.GeometricElement;
import com.example.celestialspheregeometry.rendering.SphereGLRenderer;

import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.joml.Vector4f;

import lombok.Getter;


@Getter
public class ElementSelectionController {

    private SphereGLRenderer sphereRenderer;
    private SphereScene sphereScene;

    int width, height;


    public ElementSelectionController(SphereScene sphereScene) {
        this.sphereScene = sphereScene;
    }


    public void setWidthHeight(int width, int height) {
        this.width = width; this.height = height;
    }


    public void setRenderer(SphereGLRenderer sphereGLRenderer) {
        this.sphereRenderer = sphereGLRenderer;
    }


    public void handleTap(float x, float y) {
        var el = getIntersectedElement(x, y);
        System.out.println(el);
    }


    public GeometricElement getIntersectedElement(float x, float y)
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

        return sphereScene.getIntersectedElement(near3, far3);
    }
}
