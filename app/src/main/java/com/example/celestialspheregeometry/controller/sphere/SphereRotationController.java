package com.example.celestialspheregeometry.controller.sphere;

import android.widget.CheckBox;
import android.widget.SeekBar;

import com.example.celestialspheregeometry.model.sphere.SphereScene;

import org.joml.Vector3f;

public class SphereRotationController {

    private SphereScene sphereScene;

    private final Vector3f scrollTmp1 = new Vector3f(), scrollTmp2 = new Vector3f();

    public static final int MIN_SPEED_PROGRESS = 0;
    public static final int MAX_SPEED_PROGRESS = 100;
    public static final float MIN_SPEED_FACTOR = 0.0f;
    public static final float MAX_SPEED_FACTOR = 2.0f;


    public SphereRotationController(SphereScene sphereScene) {
        this.sphereScene = sphereScene;
    }


    public void initRotateCheckBox(CheckBox rotateCheckBox)
    {
        rotateCheckBox.setChecked(isRotationEnabled());
        rotateCheckBox.setOnCheckedChangeListener((buttonView, isChecked) -> setRotationEnabled(isChecked));
    }


    public void initRotationSeekBar(SeekBar speedSeekBar)
    {
        speedSeekBar.setMax(MAX_SPEED_PROGRESS - MIN_SPEED_PROGRESS);
        speedSeekBar.setProgress(getRotationSpeedProgress() - MIN_SPEED_PROGRESS);
        speedSeekBar.setOnSeekBarChangeListener(new SphereRotationSpeedSeekBar(this));
    }


    public void handleScroll(float distanceX, float distanceY) {
        float swipeLength = (float) Math.sqrt(distanceX * distanceX + distanceY * distanceY);
        distanceX /= swipeLength;
        distanceY /= swipeLength;

        Vector3f rot = scrollTmp1.set(0, 0, 1).cross(scrollTmp2.set(-distanceX, distanceY, 0));
        sphereScene.getSphere().rotateAroundAxis(rot, swipeLength);
    }


    public void setRotationEnabled(boolean enabled) {
        sphereScene.setRotationEnabled(enabled);
    }


    public boolean isRotationEnabled() {
        return sphereScene.isRotationEnabled();
    }


    public void setRotationSpeedProgress(int progress) {
        float normalizedProgress = (float) (progress - MIN_SPEED_PROGRESS) / (MAX_SPEED_PROGRESS - MIN_SPEED_PROGRESS);
        float speedFactor = MIN_SPEED_FACTOR + normalizedProgress * (MAX_SPEED_FACTOR - MIN_SPEED_FACTOR);
        sphereScene.setRotationSpeedFactor(speedFactor);
    }


    public int getRotationSpeedProgress() {
        float currentFactor = sphereScene.getRotationSpeedFactor();
        float normalizedProgress = (currentFactor - MIN_SPEED_FACTOR) / (MAX_SPEED_FACTOR - MIN_SPEED_FACTOR);
        return Math.round(MIN_SPEED_PROGRESS + normalizedProgress * (MAX_SPEED_PROGRESS - MIN_SPEED_PROGRESS));
    }
}
