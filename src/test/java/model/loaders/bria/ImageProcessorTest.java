package model.loaders.bria;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.awt.image.BufferedImage;
import java.util.List;

import org.junit.jupiter.api.Test;

import boofcv.struct.image.GrayF32;
import boofcv.struct.image.Planar;
import model.loaders.ImageProcessor;

public class ImageProcessorTest {

    ImageProcessor op = new ImageProcessor() {
        @Override
        public List<float[][][]> preProcessImage(BufferedImage image) {
            return null;
        }

        @Override
        public List<BufferedImage> postProcessImage(float[][][][] output) {
            // TODO Auto-generated method stub
            return null;
        }
    };

    @Test
    void testBreakImageToTilesNull() throws Exception {
        BufferedImage[] tiles = op.breakImageIntoTiles(null, 0, 0);
        assertEquals(0, tiles.length);
    }

    @Test
    void testBreakImageToTilesSmallSize() throws Exception {
        BufferedImage[] tiles = op.breakImageIntoTiles(
                new BufferedImage(10, 10, BufferedImage.TYPE_INT_ARGB), 100,
                100);
        assertEquals(1, tiles.length);
    }

    @Test
    void testBreakImageToTilesBiggerSize() throws Exception {
        BufferedImage[] tiles1 = op.breakImageIntoTiles(
                new BufferedImage(120, 10, BufferedImage.TYPE_INT_ARGB), 100,
                100);
        assertEquals(2, tiles1.length);
        assertEqualsWidthHeight(100, 10, tiles1[0].getWidth(),
                tiles1[0].getHeight());
        assertEqualsWidthHeight(20, 10, tiles1[1].getWidth(),
                tiles1[1].getHeight());

        BufferedImage[] tiles2 = op.breakImageIntoTiles(
                new BufferedImage(120, 120, BufferedImage.TYPE_INT_ARGB), 100,
                100);
        assertEquals(4, tiles2.length);
        assertEqualsWidthHeight(100, 100, tiles2[0].getWidth(),
                tiles2[0].getHeight());
        assertEqualsWidthHeight(20, 100, tiles2[1].getWidth(),
                tiles2[1].getHeight());
        assertEqualsWidthHeight(100, 20, tiles2[2].getWidth(),
                tiles2[2].getHeight());
        assertEqualsWidthHeight(20, 20, tiles2[3].getWidth(),
                tiles2[3].getHeight());
    }

    private void assertEqualsWidthHeight(int expectedWidth, int expectedHeight,
            int actualWidth, int actualHeight) {
        assertEquals(expectedWidth, actualWidth);
        assertEquals(expectedHeight, actualHeight);
    }
}
