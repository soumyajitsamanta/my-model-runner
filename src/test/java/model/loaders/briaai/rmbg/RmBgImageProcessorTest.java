package model.loaders.briaai.rmbg;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.IntStream;

import javax.imageio.ImageIO;

import org.junit.jupiter.api.Test;

import boofcv.io.image.ConvertBufferedImage;
import boofcv.struct.image.GrayF32;
import boofcv.struct.image.Planar;
import model.loaders.ImageSize;

public class RmBgImageProcessorTest {
    RmBgConfig config = RmBgConfig.loadFromFolder(
            new File("src/test/resources/briaai/RMBG/"));
    RmBgImageProcessor processor = new RmBgImageProcessor(config);

    @Test
    void testImageProcessorValid() throws IOException {
        System.err.println(config.getPreProcessorConfig().getSize());
        RmBgPreProcessorConfig preProcessorConfig = config.getPreProcessorConfig();
        preProcessorConfig.setDoPad(true);;
//        preProcessorConfig.setDoResize(false);
//        preProcessorConfig.setDoRescale(false);
//        preProcessorConfig.setDoNormalize(false);
        BufferedImage string = ImageIO.read(new File("src/test/resources/images/corgi.jpg"));
        List<Planar<GrayF32>> list = processor.preProcessImage(string,new ImageSize(1024,1024));
        assertNotNull(list);
        assertEquals(1, list.size());
        Planar<GrayF32> planar = list.get(0);
        BufferedImage convertTo_F32 = ConvertBufferedImage.convertTo_F32(planar, null, false);
        ImageIO.write(convertTo_F32, "jpg", new File("src/test/resources/images/corgi.out.jpg"));
        assertEquals(3, planar.getNumBands());
        assertEquals(1024, planar.getWidth());
        assertEquals(1024, planar.getHeight());
        IntStream.range(686, 694)
        .forEach(i->{
            assertEquals(0, planar.getBand(0).get(0, i));
            assertEquals(0, planar.getBand(1).get(0, i));
            assertEquals(0, planar.getBand(2).get(0, i));
        });
        {
            GrayF32 band = planar.getBand(0);
            double[] pythonOutput = { -0.1863, -0.1824, -0.1784, -0.1706,
                    -0.1667, -0.1588, -0.1510, -0.1431, -0.1392, -0.1353,
                    -0.1235, -0.1196, -0.1196, -0.1118, -0.1039, -0.1039,
                    -0.1039, -0.1039, -0.1039, -0.1078, -0.1118, -0.1196,
                    -0.1275, -0.1392, -0.1471, -0.1588, -0.1667, -0.1706,
                    -0.1784, -0.1824, -0.1745, -0.1745, -0.1784, -0.1784,
                    -0.1784, -0.1784, -0.1784, -0.1784, -0.1784, -0.1784,
                    -0.1784, -0.1745, -0.1745, -0.1706, -0.1706, -0.1706,
                    -0.1706, -0.1706, -0.1706, -0.1706, -0.1706, -0.1706,
                    -0.1745, -0.1667, -0.1588, -0.1588, -0.1588, -0.1627,
                    -0.1706, -0.1706, -0.1706, -0.1667, -0.1627, -0.1667,
                    -0.1706, -0.1706, -0.1667, -0.1627, -0.1627, -0.1627,
                    -0.1627, -0.1706, -0.1784, -0.1863, -0.1863, -0.1824,
                    -0.1784, -0.1745, -0.1824, -0.1902, -0.2020, -0.2098,
                    -0.2098, -0.2059, -0.2020, -0.2020, -0.1980, -0.1980,
                    -0.1980, -0.1980, -0.1980, -0.1980, -0.1941, -0.1863,
                    -0.1784, -0.1784, -0.1745, -0.1706, -0.1627, -0.1627 };
            IntStream.range(0, 100).forEach(i -> {
                float javaoutput = band.get(i, 0);
                assertEquals(pythonOutput[i], javaoutput, .008);
            });
        }
        {
            GrayF32 band = planar.getBand(1);
            double[] pythonOutput = { -0.2922, -0.2882, -0.2843, -0.2765, -0.2725,
                    -0.2647, -0.2569, -0.2490, -0.2451, -0.2412, -0.2294,
                    -0.2255, -0.2255, -0.2176, -0.2098, -0.2098, -0.2098,
                    -0.2098, -0.2098, -0.2137, -0.2176, -0.2255, -0.2333,
                    -0.2451, -0.2569, -0.2647, -0.2686, -0.2686, -0.2647,
                    -0.2647, -0.2765, -0.2765, -0.2765, -0.2765, -0.2765,
                    -0.2765, -0.2765, -0.2765, -0.2765, -0.2765, -0.2725,
                    -0.2686, -0.2686, -0.2686, -0.2725, -0.2765, -0.2765,
                    -0.2725, -0.2608, -0.2490, -0.2490, -0.2451, -0.2451,
                    -0.2529, -0.2569, -0.2569, -0.2529, -0.2490, -0.2569,
                    -0.2647, -0.2686, -0.2686, -0.2647, -0.2529, -0.2451,
                    -0.2451, -0.2451, -0.2451, -0.2451, -0.2412, -0.2412,
                    -0.2451, -0.2451, -0.2490, -0.2529, -0.2608, -0.2686,
                    -0.2725, -0.2765, -0.2765, -0.2765, -0.2804, -0.2804,
                    -0.2765, -0.2725, -0.2725, -0.2686, -0.2686, -0.2686,
                    -0.2686, -0.2686, -0.2765, -0.2804, -0.2804, -0.2804,
                    -0.2765, -0.2725, -0.2725, -0.2647, -0.2569 };
            IntStream.range(0, 100).forEach(i -> {
                float javaoutput = band.get(i, 0);
                assertEquals(pythonOutput[i], javaoutput, .008);
            });
        }
        {
            GrayF32 band = planar.getBand(2);
            double[] pythonOutput = { -0.3667, -0.3627, -0.3588, -0.3549,
                    -0.3549, -0.3471, -0.3392, -0.3314, -0.3275, -0.3235,
                    -0.3118, -0.3078, -0.3078, -0.3000, -0.2922, -0.2922,
                    -0.2922, -0.2922, -0.2922, -0.2961, -0.3000, -0.3078,
                    -0.3157, -0.3275, -0.3392, -0.3471, -0.3510, -0.3549,
                    -0.3549, -0.3588, -0.3588, -0.3627, -0.3627, -0.3627,
                    -0.3627, -0.3627, -0.3627, -0.3627, -0.3627, -0.3627,
                    -0.3627, -0.3627, -0.3588, -0.3549, -0.3588, -0.3588,
                    -0.3588, -0.3588, -0.3510, -0.3431, -0.3431, -0.3392,
                    -0.3392, -0.3431, -0.3431, -0.3431, -0.3353, -0.3235,
                    -0.3275, -0.3353, -0.3353, -0.3353, -0.3314, -0.3353,
                    -0.3392, -0.3392, -0.3353, -0.3314, -0.3392, -0.3471,
                    -0.3431, -0.3431, -0.3471, -0.3471, -0.3471, -0.3471,
                    -0.3431, -0.3431, -0.3392, -0.3431, -0.3549, -0.3588,
                    -0.3588, -0.3549, -0.3510, -0.3510, -0.3471, -0.3471,
                    -0.3471, -0.3471, -0.3471, -0.3510, -0.3510, -0.3510,
                    -0.3510, -0.3510, -0.3471, -0.3392, -0.3353, -0.3392 };
            IntStream.range(0, 100).forEach(i -> {
                float javaoutput = band.get(i, 0);
                assertEquals(pythonOutput[i], javaoutput, .008);
            });
        }
    }
}
