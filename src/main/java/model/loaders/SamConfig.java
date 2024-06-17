package model.loaders;

import java.nio.file.Files;
import java.nio.file.Path;

public class SamConfig {
    private static final String PRE_PROCESSOR_CONFIG_FILENAME =
            "preprocessor_config.json";
    private static final String CONFIG_FILENAME = "config.json";

    private String modelFile;
    private SamPreProcessorConfig preProcessorConfig;
    private SamPostProcessorConfig postProcessorConfig;

    public SamConfig(String modelFile, SamPreProcessorConfig preProcessorConfig,
            SamPostProcessorConfig postProcessorConfig) {
        this.modelFile = modelFile;
        this.preProcessorConfig = preProcessorConfig;
        this.postProcessorConfig = postProcessorConfig;
    }

    /**
     * Convenience utility to create config from model file and the folder
     * containing config json files.
     * 
     * For all other scenarios create config manually.
     * 
     * @param modelFile    Path to model file.
     * @param configFolder Path to configuration folder.
     * @return Object of SamConfig.
     */
    public static SamConfig fromFiles(String modelFile, Path configFolder) {
        if (configFolder == null) {
            throw new RuntimeException("Configuration folder cannot be null.");
        }
        if (Files.notExists(configFolder)) {
            throw new RuntimeException("Configuration folder does not exist.");
        }
        Path preProcessorConfigPath =
                configFolder.resolve(PRE_PROCESSOR_CONFIG_FILENAME);
        return new SamConfig(modelFile,
                SamPreProcessorConfig.fromFile(preProcessorConfigPath.toFile()),
                null);
    }

    public String getModelFile() {
        return modelFile;
    }

    public SamPreProcessorConfig getPreProcessorConfig() {
        return preProcessorConfig;
    }

    public SamPostProcessorConfig getPostProcessorConfig() {
        return postProcessorConfig;
    }
}
