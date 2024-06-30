package model.loaders;

import java.io.File;

public class SamPreProcessorConfig extends PreProcessorConfig{

    public static final SamPreProcessorConfig fromFile(final File preprocessorFile) {
        return ConfigReader.readFromJson(preprocessorFile, SamPreProcessorConfig.class);
    }

}
