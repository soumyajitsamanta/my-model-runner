package model.loaders;

import static org.junit.jupiter.api.Assertions.*;

import java.nio.file.Path;

import org.junit.jupiter.api.Test;

import model.loaders.SamConfig;
import model.loaders.SamPreProcessorConfig;

public class SamConfigTest {
    @Test
    void testNullCreation() throws Exception {
        SamConfig config = new SamConfig(null, null, null);
        assertNotNull(config);

        assertNull(config.getModelFile());
        assertNull(config.getPreProcessorConfig());
        assertNull(config.getPostProcessorConfig());
    }

    @Test
    void testNullConvenienceCreation() throws Exception {
        assertThrows(RuntimeException.class, () -> {
            SamConfig config = SamConfig.fromFiles(null, null);
        });
    }

    @Test
    void testConvenienceCreationNonexitentPath() throws Exception {
        assertThrows(RuntimeException.class, () -> {
            SamConfig config = SamConfig.fromFiles(null, Path.of(""));
        });
//        assertNotNull(config);
//
//        assertNull(config.getModelFile());
//        assertNull(config.getPreProcessorConfig());
//        assertNull(config.getPostProcessorConfig());
    }
    
    @Test
    void testConvenienceSuccessCreation() throws Exception {
        SamConfig config = SamConfig.fromFiles(null, Path.of("/home/serverpc/Project/Checkout/sam-vit-base"));
        SamPreProcessorConfig preProcessorConfig = config.getPreProcessorConfig();
        assertEquals("", preProcessorConfig.getImageProcessorType());
    }
}
