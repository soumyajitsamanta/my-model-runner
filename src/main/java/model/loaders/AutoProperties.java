package model.loaders;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AutoProperties {
    @JsonProperty("AutoConfig")
    private String autoConfig;
    @JsonProperty("AutoModelForImageSegmentation")
    private String autoModelForImageSegmentation;

    public AutoProperties() {
    }

    public AutoProperties(final String autoConfig,
            final String autoModelForImageSegmentation) {
        this.autoConfig = autoConfig;
        this.autoModelForImageSegmentation = autoModelForImageSegmentation;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if ((obj == null) || (getClass() != obj.getClass())) {
            return false;
        }
        final AutoProperties other = (AutoProperties) obj;
        return Objects.equals(autoConfig, other.autoConfig)
                && Objects.equals(autoModelForImageSegmentation,
                        other.autoModelForImageSegmentation);
    }

    public String getAutoConfig() {
        return autoConfig;
    }

    public String getAutoModelForImageSegmentation() {
        return autoModelForImageSegmentation;
    }

    @Override
    public int hashCode() {
        return Objects.hash(autoConfig, autoModelForImageSegmentation);
    }

    public void setAutoConfig(final String autoConfig) {
        this.autoConfig = autoConfig;
    }

    public void setAutoModelForImageSegmentation(
            final String autoModelForImageSegmentation) {
        this.autoModelForImageSegmentation = autoModelForImageSegmentation;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("AutoProperties [autoConfig=");
        builder.append(autoConfig);
        builder.append(", autoModelForImageSegmentation=");
        builder.append(autoModelForImageSegmentation);
        builder.append("]");
        return builder.toString();
    }
}
