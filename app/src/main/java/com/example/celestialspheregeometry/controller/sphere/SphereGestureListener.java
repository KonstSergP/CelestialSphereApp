package com.example.celestialspheregeometry.controller.sphere;

import android.view.GestureDetector;
import android.view.MotionEvent;


public class SphereGestureListener extends GestureDetector.SimpleOnGestureListener {

    private final SphereController sphereController;


    public SphereGestureListener(SphereController sphereController) {
        this.sphereController = sphereController;
    }


    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        sphereController.handleScroll(distanceX, distanceY);
        return true;
    }


    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        sphereController.handleTap(e.getX(), e.getY());
        return true;
    }
}
