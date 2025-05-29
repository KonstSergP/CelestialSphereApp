package com.example.celestialspheregeometry.controller.sphere;

import android.widget.SeekBar;

import lombok.AllArgsConstructor;


@AllArgsConstructor
public class SphereRotationSpeedSeekBar implements SeekBar.OnSeekBarChangeListener{

    SphereRotationController sphereRotationController;


    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        if (fromUser) {
            sphereRotationController.setRotationSpeedProgress(progress + SphereRotationController.MIN_SPEED_PROGRESS);
        }
    }


    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }


    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
