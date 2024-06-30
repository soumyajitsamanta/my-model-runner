package model.loaders.briaai.rmbg;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.util.List;

import org.junit.jupiter.api.Test;

public class RmBgConfigTest {
    @Test
    void testConfigLoadsFromJson() throws Exception {
        RmBgConfig config = RmBgConfig.loadFromFolder(
                new File("src/test/resources/briaai/RMBG/"));
        assertEquals(List.of("BriaRMBG"), config.getModel().getArchitectures());
    }
}
