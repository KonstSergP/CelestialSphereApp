package com.example.celestialspheregeometry.controller.sphere;

import android.view.ScaleGestureDetector;


public class SphereScaleGestureListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {
    @Override
    public boolean onScale(ScaleGestureDetector detector) {
        System.out.println(detector.getScaleFactor());
        return true;
    }
}
