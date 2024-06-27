package model.loaders;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.stream.Stream;

import javax.imageio.ImageIO;

public class SamImageProcessor extends ImageProcessor {
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
                Integer configLongestEdge = config.getPreProcessorConfig().getSize().get("longest_edge");
                if(configLongestEdge == null) {
                    throw new RuntimeException("Image Pre config > Size > LongestEdge is not defined.");
                }
                return breakImageIntoTiles(image, configLongestEdge, configLongestEdge);
            })
            .flatMap(Stream::of)
            
                    ;
        } catch (IOException e) {
            throw new RuntimeException(
                    "Could not open image for pre processing.", e);
        }
    }
}
