package model.loaders.bria;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.awt.image.BufferedImage;
import java.util.List;

import org.junit.jupiter.api.Test;

import boofcv.struct.image.GrayF32;
import boofcv.struct.image.Planar;
import model.loaders.SamConfig;
import model.loaders.SamImageProcessorVarbatim;
import model.loaders.SamPreProcessorConfig;

public class SamImageProcessorVarbatimTest {
    SamImageProcessorVarbatim ip = new SamImageProcessorVarbatim(null);

    @Test
//    @Timeout(unit = TimeUnit.MILLISECONDS, value = 300)
    void testName() throws Exception {
        
        
        /*
         * Expected:
         * RawImage {
  data: Uint8ClampedArray(755220) [
     80, 53, 34,  83, 56, 37,  85, 58, 37,  88, 61, 40,
     92, 65, 44,  93, 66, 45,  97, 70, 49,  98, 71, 50,
    101, 74, 53, 101, 74, 53, 101, 74, 53, 101, 74, 53,
     99, 72, 51,  95, 68, 47,  91, 63, 42,  87, 60, 39,
     84, 59, 37,  81, 61, 37,  84, 57, 36,  82, 57, 35,
     82, 57, 35,  82, 57, 35,  82, 57, 35,  82, 57, 35,
     83, 59, 35,  84, 59, 37,  84, 59, 37,  85, 57, 36,
     84, 59, 37,  84, 64, 40,  85, 65, 41,  83, 65, 41,
     87, 62, 40,  88,
    ... 755120 more items
  ],
  width: 614,
  height: 410,
  channels: 3
}
{
  pixel_values: Tensor {
    dims: [ 1, 3, 1024, 1024 ],
    type: 'float32',
    data: Float32Array(3145728) [
       -0.7479235529899597,  -0.7136740684509277,  -0.6965492963790894,
       -0.6622998118400574,   -0.645175039768219,   -0.610925555229187,
        -0.576676070690155,  -0.5424265265464783,  -0.5253018140792847,
       -0.4910522699356079,  -0.4568027853965759, -0.43967801332473755,
      -0.42255327105522156, -0.40542852878570557,  -0.3883037567138672,
       -0.3883037567138672,  -0.3883037567138672,  -0.3883037567138672,
       -0.3883037567138672, -0.40542852878570557, -0.42255327105522156,
       -0.4568027853965759,  -0.5081770420074463,  -0.5424265265464783,
       -0.5938007831573486,  -0.6280503273010254,  -0.6622998118400574,
       -0.6965492963790894,  -0.7136740684509277,  -0.7136740684509277,
       -0.6794245839118958,  -0.6965492963790894,  -0.7136740684509277,
       -0.7136740684509277,  -0.7136740684509277,  -0.7136740684509277,
       -0.7136740684509277,  -0.7136740684509277,  -0.7136740684509277,
       -0.7136740684509277,  -0.6965492963790894,  -0.6794245839118958,
       -0.6794245839118958,  -0.6794245839118958,  -0.6794245839118958,
       -0.6622998118400574,  -0.6794245839118958,  -0.6794245839118958,
       -0.6794245839118958,  -0.6794245839118958,  -0.6622998118400574,
       -0.6794245839118958,  -0.6794245839118958,   -0.645175039768219,
       -0.6280503273010254,   -0.610925555229187,  -0.6280503273010254,
        -0.645175039768219,  -0.6794245839118958,  -0.6794245839118958,
       -0.6622998118400574,   -0.645175039768219,  -0.6280503273010254,
       -0.6622998118400574,  -0.6622998118400574,  -0.6794245839118958,
        -0.645175039768219,  -0.6280503273010254,   -0.645175039768219,
        -0.645175039768219,   -0.645175039768219,  -0.6794245839118958,
       -0.7136740684509277,  -0.7479235529899597,  -0.7307988405227661,
       -0.7136740684509277,  -0.6965492963790894,  -0.6965492963790894,
       -0.7307988405227661,  -0.7821730971336365,  -0.8335473537445068,
       -0.8506720662117004,  -0.8506720662117004,  -0.8164225816726685,
       -0.8164225816726685,  -0.7992978096008301,  -0.7992978096008301,
       -0.7992978096008301,  -0.7992978096008301,  -0.7992978096008301,
       -0.7992978096008301,  -0.7821730971336365,  -0.7650483250617981,
       -0.7307988405227661,  -0.7136740684509277,  -0.6965492963790894,
       -0.6794245839118958,  -0.6622998118400574,  -0.6280503273010254,
        -0.645175039768219,
      ... 3145628 more items
    ],
    size: 3145728
  },
  original_sizes: [ [ 410, 614 ] ],
  reshaped_input_sizes: [ [ 683, 1024 ] ],
  input_points: Tensor {
    dims: [ 1, 1, 1, 2 ],
    type: 'float32',
    data: Float32Array(2) [ 566.3902587890625, 416.9381103515625 ],
    size: 2
  }
}

         */
        SamPreProcessorConfig preProcessorConfig = new SamPreProcessorConfig();
        preProcessorConfig.setDoResize(false);
        preProcessorConfig.setDoRescale(true);
        preProcessorConfig.setDoPad(true);
        preProcessorConfig.setDoNormalize(true);
        SamConfig config = new SamConfig("", preProcessorConfig, null);
        ip = new SamImageProcessorVarbatim(config);
        BufferedImage image2 = ip.readImage("src/test/resources/images/corgi-nano.jpg").getSubimage(0, 0, 4, 4);

        List<float[][][]> image = ip.preProcessImage(image2);
        assertEquals(1, image.size());
//        Planar<GrayF32> actual = image.get(0);
//        assertNotNull(actual);
//        assertEquals(3, actual.getNumBands());
//        assertEquals(1024, actual.getWidth());
//        assertEquals(1024, actual.getHeight());
//        System.err.println("completed");
//        actual.getBand(0).forEachXY((x,y)->{
//            System.err.println(List.of(x,y,actual.getBand(0).get(x, y)));
//        });
    }
    
//    @Test
//    void testImageSize() throws Exception {
//        BufferedImage subimage = ip.readImage("src/test/resources/images/corgi.jpg").getSubimage(0, 0, 4, 4);
//        ImageIO.write(subimage, "jpg", new File("src/test/resources/images/corgi-nano.jpg"));
//    }
}
