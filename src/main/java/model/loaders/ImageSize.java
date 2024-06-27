package model.loaders;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ImageSize {
    public final int height;
    public final int width;

    @JsonCreator
    public ImageSize(@JsonProperty("height") int height,
            @JsonProperty("width") int width) {
        this.height = height;
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

}