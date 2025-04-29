package com.example.celestialspheregeometry.controller.sphere;

import android.view.GestureDetector;
import android.view.MotionEvent;


public class SphereGestureListener extends GestureDetector.SimpleOnGestureListener {
    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        System.out.println(distanceX + " " + distanceY);
        return true;
    }
}
