package model.loaders;

import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;

import com.twelvemonkeys.image.ResampleOp;

import boofcv.struct.image.GrayF32;
import boofcv.struct.image.Planar;


public class ImageTransforms {
    public static final BufferedImage resize(BufferedImage image,
            ImageSize toSize) {
        ResampleOp op = new ResampleOp(toSize.getWidth(), toSize.getHeight());
        BufferedImage destImage =
                op.createCompatibleDestImage(image, image.getColorModel());
        return op.filter(image, destImage);
    }

//    public static Planar<GrayF32> normalize(Planar<GrayF32> image, float[] mean, float[] std) {
//        int numChannels = image.getNumBands();
//        if(numChannels != mean.length) {
//            throw new RuntimeException(
//                    "The number of channels should match the number of mean values provided. "
//                            + "Channels:"
//                            + numChannels
//                            + ", no of means:"
//                            + mean.length);
//        }
//        if(numChannels != std.length) {
//            throw new RuntimeException(
//                    "The number of channels should match the number of standard deviation values provided. "
//                            + "Channels:"
//                            + numChannels
//                            + ", no of means:"
//                            + std.length);
//        }
//        Planar<GrayF32> createSameShape = image.createSameShape();
//        
//        for (int i = 0; i < numChannels; i++) {
//            final int index=i;
//            image.getBand(index).forEachPixel((x, y, value) -> {
//                createSameShape.getBand(index).set(x, y, (value - mean[index]) / std[index]);
//            });
//        }
//        return image;
//    }

    public static Planar<GrayF32> padImage(Planar<GrayF32> image, ImageSize padSize) {
        int outputHeight = padSize.height;
        int outputWidth = padSize.width;
        int inputHeight = image.getHeight();
        int inputWidth = image.getWidth();
        int padWidth = outputWidth-inputWidth;
        int padHeigh = outputHeight-inputHeight;
        if(padWidth<=0 && padHeigh<=0) {
            return image;
        }
        return pad(image, padSize, PadMode.CONSTANT, new float[] {0});
    }

    private static Planar<GrayF32> pad(Planar<GrayF32> image, ImageSize padSize,
            PadMode constant, float[] constantValue) {
        Planar<GrayF32> newImage = image.createNew(padSize.width, padSize.height);
        newImage.setTo(image);
        newImage.reshape(padSize.width, padSize.height);
        return newImage;
    }
}
