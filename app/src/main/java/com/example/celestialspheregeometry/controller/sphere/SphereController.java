package com.example.celestialspheregeometry.controller.sphere;

import android.content.Context;

import com.example.celestialspheregeometry.model.sphere.SphereScene;
import com.example.celestialspheregeometry.model.utils.math.MathUtils;
import com.example.celestialspheregeometry.model.utils.math.Vector;
import com.example.celestialspheregeometry.rendering.SphereGLRenderer;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class SphereController {

    public SphereScene sphereScene;
    public SphereGLRenderer sphereGLRenderer;


    public SphereController(Context context) {
        this.sphereGLRenderer = new SphereGLRenderer(context, this);
    }


    public void scaleSphere(float scale) {
        sphereScene.getSphere().scale(scale);
    }

    public void handleScroll(float distanceX, float distanceY) {
        float swipeLength = (float) Math.sqrt(distanceX * distanceX + distanceY * distanceY);
        distanceX /= swipeLength;
        distanceY /= swipeLength;

        Vector rot = MathUtils.cross(new Vector(0, 0, 1), new Vector(-distanceX, distanceY, 0));

        sphereScene.getSphere().rotateAroundAxis(rot, swipeLength);
    }
}
