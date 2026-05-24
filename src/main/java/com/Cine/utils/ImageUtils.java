package com.Cine.utils;

import javafx.scene.image.Image;
import java.io.ByteArrayInputStream;

public class ImageUtils {

    public static Image fromBytes(byte[] data) {
        if (data == null) return null;
        return new Image(new ByteArrayInputStream(data));
    }
}