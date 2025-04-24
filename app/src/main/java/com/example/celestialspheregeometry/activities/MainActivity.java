package com.example.celestialspheregeometry.activities;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.example.celestialspheregeometry.rendering.SphereGLSurfaceView;


public class MainActivity extends AppCompatActivity {

    private SphereGLSurfaceView glSurfaceView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        glSurfaceView = new SphereGLSurfaceView(this);
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
