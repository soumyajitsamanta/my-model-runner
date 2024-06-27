package model.loaders;

import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;

import com.twelvemonkeys.image.ResampleOp;

public class ImageTransforms {
    public static final BufferedImage resize(BufferedImage image,
            ImageSize toSize) {
        ResampleOp op = new ResampleOp(toSize.getWidth(), toSize.getHeight());
        BufferedImage destImage =
                op.createCompatibleDestImage(image, ColorModel.getRGBdefault());
        return op.filter(image, destImage);
    }
}
