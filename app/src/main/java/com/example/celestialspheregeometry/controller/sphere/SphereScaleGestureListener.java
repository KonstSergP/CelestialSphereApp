package com.example.celestialspheregeometry.controller.sphere;

import android.view.ScaleGestureDetector;


public class SphereScaleGestureListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {

    private float scaleFactor = 1.0f;


    @Override
    public boolean onScale(ScaleGestureDetector detector) {
        scaleFactor *= detector.getScaleFactor();
        // Ограничиваем масштаб
        scaleFactor = Math.max(0.1f, Math.min(scaleFactor, 5.0f));
        //sphere.setScale(scaleFactor);
        return true;
    }
}
