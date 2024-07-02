package model.loaders;

import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ModelConfig {
    @JsonProperty("_name_or_path")
    String nameOrPath;

    @JsonProperty("architectures")
    private List<String> architectures;

    @JsonProperty("auto_map")
    AutoProperties autoMap;
    @JsonProperty("in_ch")
    int inChannels;
    @JsonProperty("model_type")
    String modelType;
    @JsonProperty("out_ch")
    int outChannels;
    @JsonProperty("torch_dtype")
    String torchDtype;
    @JsonProperty("transformers_version")
    String transformersVersion;

    String modelPath;

    public ModelConfig() {
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ModelConfig other = (ModelConfig) obj;
        return Objects.equals(architectures, other.architectures)
                && Objects.equals(autoMap, other.autoMap)
                && inChannels == other.inChannels
                && Objects.equals(modelPath, other.modelPath)
                && Objects.equals(modelType, other.modelType)
                && Objects.equals(nameOrPath, other.nameOrPath)
                && outChannels == other.outChannels
                && Objects.equals(torchDtype, other.torchDtype) && Objects
                        .equals(transformersVersion, other.transformersVersion);
    }
    public List<String> getArchitectures() {
        return architectures;
    }
    public AutoProperties getAutoMap() {
        return autoMap;
    }
    public int getInChannels() {
        return inChannels;
    }
    public String getModelPath() {
        return modelPath;
    }

    public String getModelType() {
        return modelType;
    }
    public String getNameOrPath() {
        return nameOrPath;
    }
    public int getOutChannels() {
        return outChannels;
    }
    public String getTorchDtype() {
        return torchDtype;
    }
    public String getTransformersVersion() {
        return transformersVersion;
    }
    @Override
    public int hashCode() {
        return Objects.hash(architectures, autoMap, inChannels, modelPath,
                modelType, nameOrPath, outChannels, torchDtype,
                transformersVersion);
    }
    public void setArchitectures(final List<String> architectures) {
        this.architectures = architectures;
    }
    public void setAutoMap(final AutoProperties autoMap) {
        this.autoMap = autoMap;
    }
    public void setInChannels(final int inChannels) {
        this.inChannels = inChannels;
    }
    public void setModelPath(String modelPath) {
        this.modelPath = modelPath;
    }
    public void setModelType(final String modelType) {
        this.modelType = modelType;
    }
    public void setNameOrPath(final String nameOrPath) {
        this.nameOrPath = nameOrPath;
    }

    public void setOutChannels(final int outChannels) {
        this.outChannels = outChannels;
    }
    public void setTorchDtype(final String torchDtype) {
        this.torchDtype = torchDtype;
    }
    public void setTransformersVersion(final String transformersVersion) {
        this.transformersVersion = transformersVersion;
    }
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("ModelConfig [nameOrPath=");
        builder.append(nameOrPath);
        builder.append(", architectures=");
        builder.append(architectures);
        builder.append(", autoMap=");
        builder.append(autoMap);
        builder.append(", inChannels=");
        builder.append(inChannels);
        builder.append(", modelType=");
        builder.append(modelType);
        builder.append(", outChannels=");
        builder.append(outChannels);
        builder.append(", torchDtype=");
        builder.append(torchDtype);
        builder.append(", transformersVersion=");
        builder.append(transformersVersion);
        builder.append(", modelPath=");
        builder.append(modelPath);
        builder.append("]");
        return builder.toString();
    }

}
