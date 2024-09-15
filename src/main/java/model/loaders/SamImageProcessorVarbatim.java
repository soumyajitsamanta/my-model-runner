package model.loaders;

import java.awt.image.BufferedImage;
import java.util.List;
import java.util.stream.Stream;

import boofcv.struct.image.GrayF32;
import boofcv.struct.image.Planar;

public class SamImageProcessorVarbatim extends ImageProcessor {
    public static final String PROCESSOR_TYPE = "SamImageProcessor";

    private final SamConfig config;

    public SamImageProcessorVarbatim(final SamConfig config) {
        this.config = config;
    }

    @Override
    public List<float[][][]> preProcessImage(final BufferedImage originalImage) {
        final SamPreProcessorConfig preProcessorConfig = config.getPreProcessorConfig();
//        return Stream.<BufferedImage>of(originalImage)
//        .map(image -> {
//            if (preProcessorConfig.isDoResize()) {
//                BufferedImage image2 = resizeIfSmall(image,
//                        preProcessorConfig.getSize().getLongestEdge());
//                return image2;
//            }
//            return image;
//        })
//        .flatMap(image -> batchImage(image, preProcessorConfig.getSize().getLongestEdge()))
//        .map(image -> toBoofImage(image))
//        .map(image -> {
//            if (preProcessorConfig.isDoRescale()) {
//            final Planar<GrayF32> newImage = image.createSameShape();
//            final float rescaleFactor = preProcessorConfig.getRescaleFactor();
//            final float[] mean = preProcessorConfig.getImageMean();
//            final float[] std = preProcessorConfig.getImageStd();
//            final int numBands = image.getNumBands();
//            for (int i = 0; i < numBands; i++) {
//                final int index = i;
//                image.getBand(index).forEachPixel((x, y, value) -> {
//                    final float rescaled = value * rescaleFactor;
//                    final float normalized =
//                            (rescaled - mean[index]) / std[index];
//                    System.err.println(List.of(x, y, value, rescaled,
//                            normalized, mean[index], std[index]));
//                    newImage.getBand(index).set(x, y, normalized);
//                });
//            }
//            return newImage;
//            }
//            return image;
//        })
//        .map(image -> {
//            if (preProcessorConfig.isDoPad()) {
//                return ImageTransforms.padImage(image,
//                        preProcessorConfig.getPadSize());
//            }
//
//            return image;
//        })
//        .toList();
        return null;
    }

    @Override
    public List<BufferedImage> postProcessImage(float[][][][] output) {
        // TODO Auto-generated method stub
        return null;
    }

    

    

}
