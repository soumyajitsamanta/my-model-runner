package model.loaders;

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
        if(configFolder == null) {
            throw new RuntimeException("Configuration folder cannot be null.");
        }
        Path preProcessorConfigPath =
                configFolder.resolve(PRE_PROCESSOR_CONFIG_FILENAME);
        SamPreProcessorConfig preProcessorConfig =
                SamPreProcessorConfig.fromFile(preProcessorConfigPath.toFile());
        return new SamConfig(modelFile, preProcessorConfig, null);
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
