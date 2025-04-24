package com.example.celestialspheregeometry.model.utils.math;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
public class Point
{
    float x;
    float y;
    float z;


    public Point(Point point) {
        x = point.getX(); y = point.getY(); z = point.getZ();
    }
}
