package com.example.celestialspheregeometry.rendering;


import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;

import com.example.celestialspheregeometry.controller.SphereController;
import com.example.celestialspheregeometry.controller.SphereGestureListener;
import com.example.celestialspheregeometry.controller.SphereScaleGestureListener;


public class SphereGLSurfaceView extends GLSurfaceView {

    private SphereController sphereController;
    private GestureDetector gestureDetector;
    private ScaleGestureDetector scaleGestureDetector;

    private SphereGLRenderer sphereGLRenderer;


    public SphereGLSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);

        setEGLContextClientVersion(2);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean retVal = scaleGestureDetector.onTouchEvent(event);
        retVal = gestureDetector.onTouchEvent(event) || retVal;
        return retVal || super.onTouchEvent(event);
    }


    public void initController(SphereController controller) {
        this.sphereController = controller;
        commonInit(getContext());
    }


    private void commonInit(Context context) {
        sphereGLRenderer = new SphereGLRenderer(context, sphereController);
        sphereController.setRenderer(sphereGLRenderer);

        gestureDetector = new GestureDetector(context, new SphereGestureListener(sphereController));
        scaleGestureDetector = new ScaleGestureDetector(context, new SphereScaleGestureListener(sphereController));

        setRenderer(sphereGLRenderer);
    }
}
