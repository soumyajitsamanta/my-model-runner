package model.loaders.bria;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.nio.file.Path;

import org.junit.jupiter.api.Test;

import model.loaders.ImageProcessor;
import model.loaders.SamConfig;
import model.loaders.SamModel;

public class SamModelTest {
    @Test
    void testNullCreaaation() throws Exception {
        SamConfig config = SamConfig.fromFiles("",
                Path.of("src/test/resources/sam-vit-base"));
        SamModel m = new SamModel(config);
        assertNotNull(m);
        assertNull(m.getImageProcessor());
        ImageProcessor imageProcessor =
                m.selectImageProcessorFromConfig(config);
        assertNotNull(imageProcessor);
    }
}
