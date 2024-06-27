package model.loaders;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.stream.Stream;

import javax.imageio.ImageIO;

public class SamImageProcessorVarbatim extends ImageProcessor {
    public static final String PROCESSOR_TYPE = "SamImageProcessorVerbatim";

    private final SamConfig config;

    public SamImageProcessorVarbatim(SamConfig config) {
        this.config = config;
    }

    @Override
    public void preProcessImage(String filename) {
        SamPreProcessorConfig preProcessorConfig = config.getPreProcessorConfig();
        Stream.of(readImage(filename))
        .map(image -> {
            if (!preProcessorConfig.isDoResize()) {
                return image;
            }
            return resize(image, preProcessorConfig.getSize().get("longest_edge"));
        })
        .map(image->toImageArray(image))
//        .map(image -> {
//            if(!preProcessorConfig.isDoRescale()) {
//                return image;
//            }
//            return rescale(image, preProcessorConfig.getRescaleFactor());
//        })
        ;
    }

    private Object toImageArray(BufferedImage image) {
        image.getData();
        return null;
    }

    private ImageSize _get_preprocess_shape(ImageSize size, int longestEdge) {
        return _get_preprocess_shape(size.height, size.width, longestEdge);
    }

    protected ImageSize _get_preprocess_shape(int height, int width,
            int longestEdge) {
        float scale = longestEdge / Math.max(height, width);
        float newh = height * scale, neww = width * scale;
        return new ImageSize((int) Math.floor(newh + 0.5f),
                (int) Math.floor(neww + 0.5f));
    }

    public BufferedImage resize(BufferedImage image, Integer longestEdge) {
        ImageSize shape = _get_preprocess_shape(ImageUtils.getImageSize(image),
                longestEdge);
        return ImageTransforms.resize(image, shape);
    }
    

}
