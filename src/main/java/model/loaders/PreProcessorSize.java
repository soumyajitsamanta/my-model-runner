package model.loaders;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PreProcessorSize {
    private Integer width;
    private Integer height;
    @JsonProperty("longest_edge")
    private Integer longestEdge;

    public PreProcessorSize() {
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if ((obj == null) || (getClass() != obj.getClass())) {
            return false;
        }
        final PreProcessorSize other = (PreProcessorSize) obj;
        return Objects.equals(height, other.height)
                && Objects.equals(longestEdge, other.longestEdge)
                && Objects.equals(width, other.width);
    }

    public Integer getHeight() {
        return height;
    }

    public Integer getLongestEdge() {
        return longestEdge;
    }

    public Integer getWidth() {
        return width;
    }

    @Override
    public int hashCode() {
        return Objects.hash(height, longestEdge, width);
    }

    public void setHeight(final Integer height) {
        this.height = height;
    }

    public void setLongestEdge(final Integer longestEdge) {
        this.longestEdge = longestEdge;
    }

    public void setWidth(final Integer width) {
        this.width = width;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("PreProcessorSize [width=");
        builder.append(width);
        builder.append(", height=");
        builder.append(height);
        builder.append(", longestEdge=");
        builder.append(longestEdge);
        builder.append("]");
        return builder.toString();
    }

}
