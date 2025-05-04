package com.example.celestialspheregeometry.controller.sphere;

import android.content.Context;

import com.example.celestialspheregeometry.model.sphere.SphereScene;
import com.example.celestialspheregeometry.rendering.SphereGLRenderer;

import org.joml.Vector3f;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class SphereController {

    public SphereScene sphereScene;


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

        Vector3f rot = new Vector3f(0, 0, 1).cross(new Vector3f(-distanceX, distanceY, 0));

        sphereScene.getSphere().rotateAroundAxis(rot, swipeLength);
    }
}
