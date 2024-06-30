package model.loaders;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import javax.imageio.ImageIO;

import boofcv.io.image.ConvertBufferedImage;
import boofcv.struct.image.GrayF32;
import boofcv.struct.image.ImageDataType;
import boofcv.struct.image.ImageType;
import boofcv.struct.image.ImageType.Family;
import boofcv.struct.image.Planar;

public abstract class ImageProcessor {
    public static final String PROCESSOR_TYPE = "UnconfiguredImageProcessor";

    public List<Planar<GrayF32>> preProcessImage(String filename){
        return preProcessImage(readImage(filename));
    }
    public abstract List<Planar<GrayF32>> preProcessImage(BufferedImage image);

    public BufferedImage[] breakImageIntoTiles(BufferedImage image,
            int maxWidthSize, int maxHeightSize) {
        if (image == null) {
            return new BufferedImage[0];
        }
        if(image.getWidth() < maxWidthSize && image.getHeight() < maxHeightSize) {
            return new BufferedImage[] {image};
        }
        int width = image.getWidth();
        int height = image.getHeight();

        final List<BufferedImage> images = new ArrayList<>(
                (1 + (width / maxWidthSize)) * (1 + (height / maxHeightSize)));
        for (int h = 0; h * maxHeightSize < height; h++) {
            for (int w = 0; w * maxWidthSize < width; w++) {
                int startWidth = w * maxWidthSize;
                int startHeight = h * maxHeightSize;
                int endWidth = Math.min((w + 1) * maxWidthSize, width);
                int endHeight = Math.min((h + 1) * maxHeightSize, height);
                images.add(new BufferedImage(endWidth - startWidth,
                        endHeight - startHeight, BufferedImage.TYPE_INT_ARGB));
            }
        }
        return images.toArray(new BufferedImage[images.size()]);
    }

    public final BufferedImage readImage(String filename) {
        try {
            return ImageIO.read(new File(filename));
        } catch (IOException e) {
            throw new RuntimeException("Iamge reading failed.", e);
        }
    }
    protected Planar<GrayF32> toBoofImage(final BufferedImage image) {
        final ImageType<Planar<GrayF32>> imageType = new ImageType<>(Family.PLANAR, ImageDataType.F32, 3);
        return ConvertBufferedImage.convertFrom(image, true, imageType);
    }
    public BufferedImage resizeIfSmall(final BufferedImage image, final Integer longestEdge) {
        if(Math.max(image.getWidth(), image.getHeight()) >= longestEdge) {
            return image;
        }
        final ImageSize shape = _get_preprocess_shape(ImageUtils.getImageSize(image),
                longestEdge);
        System.err.println("Resizing from {" + image.getWidth() +":"+image.getHeight()
        + "} to {"
        +shape.getWidth()+":"+shape.getHeight()
        + "}");
        return ImageTransforms.resize(image, shape);
    }
    
    private ImageSize _get_preprocess_shape(final ImageSize size, final int longestEdge) {
        final int height = size.height;
        final int width = size.width;
        System.err.println(List.of(width, height, longestEdge));
        final float scale = (float)longestEdge / Math.max(height, width);
        
        System.err.println("Scaling faactor:"+scale);
        final float newh = height * scale, neww = width * scale;
        return new ImageSize((int) Math.floor(newh + 0.5f),
                (int) Math.floor(neww + 0.5f));
    }
    
    protected Stream<BufferedImage> batchImage(final BufferedImage resized, final Integer longestEdge) {
        if(Math.max(resized.getWidth(), resized.getHeight()) > longestEdge) {
            return Stream.of(breakImageIntoTiles(resized, longestEdge, longestEdge));
        }
        return Stream.of(resized);
    }

}