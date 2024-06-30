package model.loaders.briaai.rmbg;

import java.io.File;

import com.fasterxml.jackson.annotation.JsonProperty;

import model.loaders.ConfigReader;
import model.loaders.PreProcessorConfig;

public class RmBgPreProcessorConfig extends PreProcessorConfig {
    public static RmBgPreProcessorConfig loadFromFile(File config) {
        return ConfigReader.readFromJson(config, RmBgPreProcessorConfig.class);
    }

    @JsonProperty("feature_extractor_type")
    private String featureExtractorType;

    /**
     * @return the featureExtractorType
     */
    public String getFeatureExtractorType() {
        return featureExtractorType;
    }

    /**
     * @param featureExtractorType the featureExtractorType to set
     */
    public void setFeatureExtractorType(String featureExtractorType) {
        this.featureExtractorType = featureExtractorType;
    }
}
