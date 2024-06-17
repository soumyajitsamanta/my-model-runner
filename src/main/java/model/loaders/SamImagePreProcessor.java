package model.loaders;

public class SamImagePreProcessor implements ImagePreProcessor {
    private final SamPreProcessorConfig preProcessorConfig;

    public SamImagePreProcessor(SamPreProcessorConfig preProcessorConfig) {
        this.preProcessorConfig = preProcessorConfig;
    }

}
