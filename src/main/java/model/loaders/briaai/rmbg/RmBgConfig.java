package model.loaders.briaai.rmbg;

import java.io.File;
import java.nio.file.Path;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import model.loaders.ConfigReader;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RmBgConfig {
    public static RmBgConfig loadFromFolder(final File folder) {
        return loadFromFolder(folder.toPath());
    }

    public static RmBgConfig loadFromFolder(final Path path) {
        RmBgConfig rmBgConfig = new RmBgConfig();
        rmBgConfig.model = ConfigReader.readFromJson(
                path.resolve("config.json"), RmBgModelConfig.class);
        rmBgConfig.preProcessorConfig = ConfigReader.readFromJson(
                path.resolve("preprocessor_config.json"),
                RmBgPreProcessorConfig.class);
        return rmBgConfig;
    }

    private RmBgModelConfig model;

    private RmBgPreProcessorConfig preProcessorConfig;

    public RmBgPreProcessorConfig getPreProcessorConfig() {
        return preProcessorConfig;
    }

    public void setPreProcessorConfig(
            final RmBgPreProcessorConfig preProcessorConfig) {
        this.preProcessorConfig = preProcessorConfig;
    }

    /**
     * @return the model
     */
    public RmBgModelConfig getModel() {
        return model;
    }

    /**
     * @param model the model to set
     */
    public void setModel(RmBgModelConfig model) {
        this.model = model;
    }
}
