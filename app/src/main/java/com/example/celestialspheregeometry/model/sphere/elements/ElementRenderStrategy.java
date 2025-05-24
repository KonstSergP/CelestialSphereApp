package com.example.celestialspheregeometry.model.sphere.elements;


import com.example.celestialspheregeometry.model.utils.Primitive;
import org.joml.Matrix4f;
import java.util.List;


public interface ElementRenderStrategy {

    void getPrimitives(Matrix4f modelMatrix, List<Primitive> primitives);
}
