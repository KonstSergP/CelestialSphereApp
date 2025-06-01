package com.example.celestialspheregeometry.controller;


import android.widget.CheckBox;
import android.widget.SeekBar;

import com.example.celestialspheregeometry.model.sphere.SphereScene;
import com.example.celestialspheregeometry.rendering.SphereGLRenderer;

import org.joml.Vector3f;


public class SphereRotationController {

    private SphereGLRenderer sphereRenderer;
    private SphereScene sphereScene;

    private final Vector3f scrollTmp1 = new Vector3f(), scrollTmp2 = new Vector3f();

    public static final int MIN_SPEED_PROGRESS = 0;
    public static final int MAX_SPEED_PROGRESS = 100;
    public static final float MIN_SPEED_FACTOR = 0.0f;
    public static final float MAX_SPEED_FACTOR = 2.0f;


    public SphereRotationController(SphereScene sphereScene) {
        this.sphereScene = sphereScene;
    }


    public void setRenderer(SphereGLRenderer sphereGLRenderer) {
        this.sphereRenderer = sphereGLRenderer;
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
        distanceX /= swipeLength; distanceX = -distanceX;
        distanceY /= swipeLength;

        var camera = sphereRenderer.getCamera();

        var p0 = camera.getViewCenter();
        var n = new Vector3f(p0).sub(camera.getEye());
        var p1 = new Vector3f(p0).add(camera.getUp());
        float D = -(n.x*p0.x + n.y*p0.y + n.z*p0.z);
        float t = -(n.x*p1.x + n.y*p1.y + n.z*p1.z + D) / n.lengthSquared();
        var upVector = p1.add(n.mul(t)).sub(p0).normalize();

        var reversedSightVec = scrollTmp1.set(camera.getEye()).sub(camera.getViewCenter());
        var rightVector = scrollTmp2.set(upVector).cross(reversedSightVec).normalize();

        var shift = rightVector.mul(distanceX).add(upVector.mul(distanceY));

        Vector3f rot = reversedSightVec.cross(shift).normalize();

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
