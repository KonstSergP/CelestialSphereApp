package com.example.celestialspheregeometry.model.utils;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;


/**
 * Утилитарный класс для работы с буферами OpenGL
 */
public class BufferUtils {

    private static final int BYTES_PER_FLOAT = 4;

    /**
     * Создаёт FloatBuffer из массива float
     * @param array массив данных
     * @return FloatBuffer с данными из массива
     */
    public static FloatBuffer convertToFloatBuffer(float[] array) {
        ByteBuffer bb = ByteBuffer.allocateDirect(array.length * BYTES_PER_FLOAT);
        bb.order(java.nio.ByteOrder.nativeOrder());
        FloatBuffer fb = bb.asFloatBuffer();
        fb.put(array).position(0);
        return fb;
    }
}
