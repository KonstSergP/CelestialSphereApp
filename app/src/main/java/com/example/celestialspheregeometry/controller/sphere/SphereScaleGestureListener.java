package com.example.celestialspheregeometry.controller.sphere;

import android.view.ScaleGestureDetector;


public class SphereScaleGestureListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {

    public SphereController sphereController;


    public SphereScaleGestureListener(SphereController sphereController) {
        this.sphereController = sphereController;
    }


    @Override
    public boolean onScale(ScaleGestureDetector detector) {
        sphereController.scaleSphere(detector.getScaleFactor());
        return true;
    }
}
