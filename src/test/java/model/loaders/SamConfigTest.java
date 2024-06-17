package model.loaders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.nio.file.Path;

import org.junit.jupiter.api.Test;

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
            SamConfig.fromFiles(null, null);
        });
    }

    @Test
    void testConvenienceCreationNonexitentPath() throws Exception {
        assertThrows(RuntimeException.class, () -> {
            SamConfig.fromFiles(null, Path.of(""));
        });
    }

    @Test
    void testConvenienceSuccessCreation() throws Exception {
        Path samConfigFolder = Path.of("src/test/resources/sam-vit-base");
        SamConfig config = SamConfig.fromFiles(null, samConfigFolder);
        SamPreProcessorConfig preProcessorConfig =
                config.getPreProcessorConfig();
        assertEquals("SamImageProcessor", preProcessorConfig.getImageProcessorType());
        ImagePreProcessor imagePreProcessor = config.getImagePreProcessor();
        assertEquals(SamImagePreProcessor.class.getName(), imagePreProcessor.getClass().getName());
    }
}
