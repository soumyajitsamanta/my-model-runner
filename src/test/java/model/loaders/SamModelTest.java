package model.loaders;

import static org.junit.jupiter.api.Assertions.*;

import java.nio.file.Path;

import org.junit.jupiter.api.Test;

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
