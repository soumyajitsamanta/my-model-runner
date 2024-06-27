package model.loaders;

import java.awt.image.BufferedImage;

public class ImageUtils {
    public static final ImageSize getImageSize(BufferedImage image) {
        return new ImageSize(image.getHeight(), image.getWidth());
    }
}
