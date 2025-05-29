package com.example.celestialspheregeometry.activities;

import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.SeekBar;

import androidx.appcompat.app.AppCompatActivity;

import com.example.celestialspheregeometry.R;
import com.example.celestialspheregeometry.controller.sphere.SphereController;
import com.example.celestialspheregeometry.model.sphere.SphereScene;
import com.example.celestialspheregeometry.rendering.SphereGLSurfaceView;


public class MainActivity extends AppCompatActivity {

    private SphereGLSurfaceView sphereGLSurfaceView;
    private SphereController sphereController;

    private CheckBox rotateCheckBox;
    private SeekBar speedSeekBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sphereGLSurfaceView = findViewById(R.id.glSurfaceView);
        rotateCheckBox = findViewById(R.id.checkbox_rotate);
        speedSeekBar = findViewById(R.id.seekbar_speed);

        SphereScene sphereScene = new SphereScene();
        sphereController = new SphereController(sphereScene);
        sphereGLSurfaceView.initController(sphereController);

        setupUIListeners();
    }


    private void setupUIListeners() {
        var rotationController = sphereController.getSphereRotationController();
        rotationController.initRotateCheckBox(rotateCheckBox);
        rotationController.initRotationSeekBar(speedSeekBar);
    }


    @Override
    protected void onPause() {
        super.onPause();
        sphereGLSurfaceView.onPause();
    }


    @Override
    protected void onResume() {
        super.onResume();
        sphereGLSurfaceView.onResume();
    }
}
