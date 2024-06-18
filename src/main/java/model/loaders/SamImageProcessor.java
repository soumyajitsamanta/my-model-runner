package model.loaders;

import java.io.File;
import java.io.IOException;
import java.util.stream.Stream;

import javax.imageio.ImageIO;

public class SamImageProcessor implements ImageProcessor {
    public static final String PROCESSOR_TYPE = "SamImageProcessor";
    private final SamConfig config;
    
    public SamImageProcessor(SamConfig config) {
        this.config = config;
    }

    @Override
    public void preProcessImage(String filename) {
        SamPreProcessorConfig preProcessorConfig = config.getPreProcessorConfig();
        try {
            Stream.ofNullable(ImageIO.read(new File(filename)))
            .map(image->{
                int height = image.getHeight();
                int width = image.getWidth();
                Integer configLongestEdge = config.getPreProcessorConfig().getSize().get("longest_edge");
                if(configLongestEdge == null) {
                    throw new RuntimeException("Image Pre config > Size > LongestEdge is not defined.");
                }
                int imageLongestEdge = Math.max(height, width);
                if(imageLongestEdge>configLongestEdge) {
                    final int numOfTiles = imageLongestEdge/configLongestEdge;
                    for(int i=0;i<numOfTiles;i++) {
                        image.getRGB(0, 0, 0, width, null, imageLongestEdge, i);
                    }
                }
                return null;
            })
//            .map(image -> image.getRGB(0, 0, image.getWidth(),
//                    image.getHeight(), null, 0, 0))
//            .map(pixels -> {
//                if(preProcessorConfig.isDoConvertRgb()) {
//                    
//                }
//            })
                    ;
        } catch (IOException e) {
            throw new RuntimeException(
                    "Could not open image for pre processing.", e);
        }
    }
}
