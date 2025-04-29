package com.example.celestialspheregeometry.rendering;


import android.content.Context;
import android.opengl.GLSurfaceView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;

import com.example.celestialspheregeometry.controller.sphere.SphereGestureListener;
import com.example.celestialspheregeometry.controller.sphere.SphereScaleGestureListener;


public class SphereGLSurfaceView extends GLSurfaceView {

    public final GestureDetector gestureDetector;
    public final ScaleGestureDetector scaleGestureDetector;
    public final SphereGLRenderer sphereGLRenderer;


    public SphereGLSurfaceView(Context context){
        super(context);

        setEGLContextClientVersion(2);

        sphereGLRenderer = new SphereGLRenderer(context);
        gestureDetector = new GestureDetector(context, new SphereGestureListener());
        scaleGestureDetector = new ScaleGestureDetector(context, new SphereScaleGestureListener());

        setRenderer(sphereGLRenderer);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean retVal = scaleGestureDetector.onTouchEvent(event);
        retVal = gestureDetector.onTouchEvent(event) || retVal;
        return retVal || super.onTouchEvent(event);
    }
}
