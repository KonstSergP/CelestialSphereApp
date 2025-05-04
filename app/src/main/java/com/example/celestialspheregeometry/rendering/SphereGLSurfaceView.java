package com.example.celestialspheregeometry.rendering;


import android.content.Context;
import android.opengl.GLSurfaceView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;

import com.example.celestialspheregeometry.controller.sphere.SphereController;
import com.example.celestialspheregeometry.controller.sphere.SphereGestureListener;
import com.example.celestialspheregeometry.controller.sphere.SphereScaleGestureListener;
import com.example.celestialspheregeometry.model.sphere.SphereScene;


public class SphereGLSurfaceView extends GLSurfaceView {

    public final SphereController sphereController;
    public final GestureDetector gestureDetector;
    public final ScaleGestureDetector scaleGestureDetector;

    public final SphereGLRenderer sphereGLRenderer;


    public SphereGLSurfaceView(Context context, SphereScene sphereScene, SphereController sphereController){
        super(context);

        setEGLContextClientVersion(2);

        this.sphereController = sphereController;
        sphereGLRenderer = new SphereGLRenderer(context, sphereScene);
        gestureDetector = new GestureDetector(context, new SphereGestureListener(sphereController));
        scaleGestureDetector = new ScaleGestureDetector(context, new SphereScaleGestureListener(sphereController));

        setRenderer(sphereGLRenderer);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean retVal = scaleGestureDetector.onTouchEvent(event);
        retVal = gestureDetector.onTouchEvent(event) || retVal;
        return retVal || super.onTouchEvent(event);
    }
}
