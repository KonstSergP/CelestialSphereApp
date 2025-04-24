package com.example.celestialspheregeometry.rendering;


import android.content.Context;
import android.opengl.GLSurfaceView;


public class SphereGLSurfaceView extends GLSurfaceView {

    public SphereGLSurfaceView(Context context){
        super(context);

        setEGLContextClientVersion(2);

        setRenderer(new SphereGLRenderer(context));
    }
}
