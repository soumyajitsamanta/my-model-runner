package model.loaders.briaai.rmbg;

public class RmBgModel {
    RmBgConfig config;
    RmBgImageProcessor imageProcessor;

    public RmBgModel(RmBgConfig config, RmBgImageProcessor imageProcessor) {
        super();
        this.config = config;
        this.imageProcessor = imageProcessor;
    }

}
