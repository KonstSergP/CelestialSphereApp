package com.example.celestialspheregeometry.model.sphere.elements;


import android.util.Pair;

import com.example.celestialspheregeometry.model.utils.Primitive;
import org.joml.Matrix4f;
import java.util.List;
import java.util.Map;


public interface ElementRenderStrategy {

    void getPrimitives(Matrix4f modelMatrix, List<Primitive> primitives);
}
