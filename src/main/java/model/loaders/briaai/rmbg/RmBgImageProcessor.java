package model.loaders.briaai.rmbg;

import java.awt.image.BufferedImage;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import boofcv.io.image.ConvertBufferedImage;
import boofcv.struct.image.GrayF32;
import boofcv.struct.image.Planar;
import model.loaders.ImageProcessor;
import model.loaders.ImageSize;

public class RmBgImageProcessor extends ImageProcessor {
    public static final String PROCESSOR_TYPE = "BRIA RmBg Image Processor";
    private final RmBgConfig config;

    public RmBgImageProcessor(RmBgConfig config) {
        this.config = config;
    }

    @Override
    public List<float[][][]> preProcessImage(BufferedImage image) {
        return preProcessImage(image, new ImageSize(config.getPreProcessorConfig().getSize().getWidth(), config.getPreProcessorConfig().getSize().getHeight()));
    }
    public List<float[][][]> preProcessImage(BufferedImage image, ImageSize size) {
        RmBgPreProcessorConfig preProcessorConfig = config.getPreProcessorConfig();
        preProcessorConfig.valiadte();
        return Stream.of(image)
        .map(im -> {
            if(preProcessorConfig.isDoResize()) {
                return resizeIfSmall(im,
                        preProcessorConfig.getSize().getWidth());
            }
            return im;
        })
        .flatMap(im -> batchImage(im, preProcessorConfig.getSize().getWidth()))
        .map(this::toBoofImage)
        .map(im->{
            Planar<GrayF32> resultIm = getResultingImageOfSize(size, im);
            int numBands = im.getNumBands();
            float rescaleFactor = preProcessorConfig.getRescaleFactor();
            boolean doRescale = preProcessorConfig.isDoRescale();
            boolean doNormalize = preProcessorConfig.isDoNormalize();
            float[] means = preProcessorConfig.getImageMean();
            float[] stds = preProcessorConfig.getImageStd();
            for(int i = 0;i<numBands;i++) {
                float mean = means[i];
                float std = stds[i];
                GrayF32 srcBand = im.getBand(i);
                GrayF32 resBand = resultIm.getBand(i);
                float[] values = srcBand.getData();
                float[] result = resBand.getData();
                int numValues = values.length;
                for(int j=0;j < numValues;j++) {
                    float resultTemp=values[j];
                    if (doRescale) {
                        resultTemp = resultTemp*rescaleFactor;
                    }
                    if(doNormalize) {
                        resultTemp=(resultTemp-mean)/std;
                    }
                    result[j]=resultTemp;
                }
            }
            return resultIm;
        })
        .map(im -> {
            int numBands = im.getNumBands();
            float[][][] chw=new float[numBands][][];
            for(int i=0;i<numBands;i++) {
                chw[i] = new float[1024][];
                for(int j=0;j<1024;j++) {
                    chw[i][j] = new float[1024];
                }
            }
            for(int i=0;i<numBands;i++) {
                int index = i;
                
                GrayF32 band = im.getBand(i);
                band.forEachPixel((x,y,v)->{
                    chw[index][y][x]=v;
                });
            }
            return chw;
        })
        .toList()
        ;
    }

    private Planar<GrayF32> getResultingImageOfSize(ImageSize size, Planar<GrayF32> im) {
        if(size==null) {
            return im.createSameShape();
        }
        return im.createNew(size.getWidth(), size.getHeight());
    }

    @Override
    public List<BufferedImage> postProcessImage(final float[][][][] output) {
        int numOfBatches = output.length;
        return IntStream.range(0, numOfBatches)
        .mapToObj(batch -> {
            float[][] resultMas = output[batch][0];
            float min = Float.POSITIVE_INFINITY;
            float max = Float.NEGATIVE_INFINITY;
            GrayF32 maybeMask = new GrayF32(resultMas.length, resultMas.length);
            for(int i=0;i<resultMas.length;i++) {
                for(int j=0;j<resultMas[i].length;j++) {
                    float v = resultMas[j][i];
                    if(min>v) {
                        min=v;
                    }
                    if(max<v) {
                        max=v;
                    }
                    maybeMask.set(i, j, v);
                }
            }
            float[] data = maybeMask.getData();
            for(int i=0;i<data.length;i++) {
                data[i] = (data[i]-min)/(max-min)*255;
            }
            return ConvertBufferedImage.convertTo(maybeMask, null);
        })
        .toList();
    }

}
