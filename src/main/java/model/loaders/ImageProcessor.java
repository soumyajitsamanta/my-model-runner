package model.loaders;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.imageio.ImageIO;

public abstract class ImageProcessor {
    public static final String PROCESSOR_TYPE = "UnconfiguredImageProcessor";

    public abstract void preProcessImage(String filename);

    public BufferedImage[] breakImageIntoTiles(BufferedImage image,
            int maxWidthSize, int maxHeightSize) {
        if (image == null) {
            return new BufferedImage[0];
        }
        int width = image.getWidth();
        int height = image.getHeight();

        List<BufferedImage> images = new ArrayList<>(
                (1 + (width / maxWidthSize)) * (1 + (height / maxHeightSize)));
        for (int h = 0; h * maxHeightSize < height; h++) {
            for (int w = 0; w * maxWidthSize < width; w++) {
                int startWidth = w * maxWidthSize;
                int startHeight = h * maxHeightSize;
                int endWidth = Math.min((w + 1) * maxWidthSize, width);
                int endHeight = Math.min((h + 1) * maxHeightSize, height);
                BufferedImage e = new BufferedImage(endWidth - startWidth,
                        endHeight - startHeight, BufferedImage.TYPE_INT_ARGB);
                
                images.add(e);
                throw new RuntimeException("Incomplete method");
            }
        }
        return images.toArray(new BufferedImage[images.size()]);
    }

    public final BufferedImage readImage(String filename) {
        File f = new File(filename);
        try {
            BufferedImage image = ImageIO.read(f);
            return image;
        } catch (IOException e) {
            throw new RuntimeException("Iamge reading failed.", e);
        }
    }

}