package com.example.celestialspheregeometry.activities;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import com.example.celestialspheregeometry.controller.sphere.SphereController;
import com.example.celestialspheregeometry.model.sphere.SphereScene;
import com.example.celestialspheregeometry.rendering.SphereGLSurfaceView;


public class MainActivity extends AppCompatActivity {

    private SphereGLSurfaceView glSurfaceView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SphereController sphereController = new SphereController(this);
        glSurfaceView = new SphereGLSurfaceView(this, sphereController);

        setContentView(glSurfaceView);
    }

    @Override
    protected void onPause() {
        super.onPause();
        glSurfaceView.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        glSurfaceView.onResume();
    }
}
