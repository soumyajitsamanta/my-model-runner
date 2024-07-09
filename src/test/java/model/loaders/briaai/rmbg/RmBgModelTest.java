package model.loaders.briaai.rmbg;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import org.junit.jupiter.api.Test;

import ai.onnxruntime.OnnxTensor;
import ai.onnxruntime.OrtEnvironment;
import ai.onnxruntime.OrtException;
import ai.onnxruntime.OrtSession;
import ai.onnxruntime.OrtSession.Result;

public class RmBgModelTest {
    RmBgConfig config = RmBgConfig
            .loadFromFolder(new File("src/test/resources/briaai/RMBG/"));
    RmBgImageProcessor processor = new RmBgImageProcessor(config);
    RmBgModel model = new RmBgModel(config, processor);

    @Test
    void testProcessImageManually() throws OrtException {
        config.getModel().setModelPath(
                "/home/serverpc/Project/Checkout/RMBG-1.4/onnx/model_fp16.onnx");
        OrtEnvironment environment = OrtEnvironment.getEnvironment();
        OrtSession createSession = environment.createSession(
                "/home/serverpc/Project/Checkout/RMBG-1.4/onnx/model_fp16.onnx");
        List<float[][][]> inputImage = model.imageProcessor.preProcessImage("src/test/resources/images/corgi.jpg");
        float[][][][] input= new float[inputImage.size()][][][];
        for(int i=0;i<inputImage.size();i++) {
            input[i]=inputImage.get(i);
        }
        OnnxTensor t = OnnxTensor.createTensor(environment, input);
        Result run = createSession.run(Map.of("input", t));
        try(run){
            run.forEach(entry -> {
                try {
                    Object value2 = entry.getValue().getValue();
                    float[][][][] floats = (float[][][][]) value2;
                    List<BufferedImage> convertedImage =
                            model.imageProcessor.postProcessImage(floats);
                    assertEquals(1024, convertedImage.get(0).getWidth());
                    assertEquals(1024, convertedImage.get(0).getHeight());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
    }

    @Test
    void testGenerateMask() throws Exception {
        config.setModelPath("/home/serverpc/Project/Checkout/RMBG-1.4/onnx/model_fp16.onnx");
        BufferedImage image = ImageIO.read(new File("src/test/resources/images/corgi.jpg"));
        List<BufferedImage> generateMask = model.generateMask(image);
        BufferedImage output = generateMask.get(0);
        assertEquals(1024, output.getWidth());
        assertEquals(1024, output.getHeight());
    }
}
