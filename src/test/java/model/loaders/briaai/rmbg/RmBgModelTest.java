package model.loaders.briaai.rmbg;

import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.FloatBuffer;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import org.junit.jupiter.api.Test;

import ai.onnxruntime.NodeInfo;
import ai.onnxruntime.OnnxTensor;
import ai.onnxruntime.OnnxValue;
import ai.onnxruntime.OrtEnvironment;
import ai.onnxruntime.OrtException;
import ai.onnxruntime.OrtSession;
import ai.onnxruntime.OrtSession.Result;
import boofcv.io.image.ConvertBufferedImage;
import boofcv.struct.image.GrayF32;
import boofcv.struct.image.Planar;

public class RmBgModelTest {
    RmBgConfig config = RmBgConfig
            .loadFromFolder(new File("src/test/resources/briaai/RMBG/"));
    RmBgImageProcessor processor = new RmBgImageProcessor(config);
    RmBgModel model = new RmBgModel(config, processor);

    @Test
    void testProcessImage() throws OrtException {
        config.getModel().setModelPath(
                "/home/serverpc/Project/Checkout/RMBG-1.4/onnx/model_fp16.onnx");
        OrtEnvironment environment = OrtEnvironment.getEnvironment();
        OrtSession createSession = environment.createSession(
                "/home/serverpc/Project/Checkout/RMBG-1.4/onnx/model_fp16.onnx");
        List<float[][][]> inputImage = model.imageProcessor.preProcessImage("src/test/resources/images/corgi.jpg");
        
        System.err.println(createSession.getInputInfo());
        System.err.println(createSession.getOutputInfo());
        long[] shape = {1,3,1024,1024};
        float[][][][] input= new float[inputImage.size()][][][];
        for(int i=0;i<inputImage.size();i++) {
            input[i]=inputImage.get(i);
        }
        OnnxTensor t = OnnxTensor.createTensor(environment, input);
        System.err.println(t.getInfo());
        Result run = createSession.run(Map.of("input", t));
        run.forEach(entry->{
            try {
                System.err.println(entry.getKey());
                OnnxValue value = entry.getValue();
                Object value2 = value.getValue();
                System.err.println(value2.getClass());
                float[][][][] floats = (float[][][][]) value2;
                System.err.println(floats[0][0].length);
                float[][] resultMas = floats[0][0];
                float min = Float.POSITIVE_INFINITY;
                float max = Float.NEGATIVE_INFINITY;
                GrayF32 maybeMask = new GrayF32(resultMas.length, resultMas.length);
                for(int i=0;i<resultMas.length;i++) {
                    for(int j=0;j<resultMas[i].length;j++) {
                        float v = resultMas[j][i];
                        if(min>v) {
                            min=v;
                        }
                        if(max<v) {
                            max=v;
                        }
                        maybeMask.set(i, j, v);
                    }
                }
                float[] data = maybeMask.getData();
                for(int i=0;i<data.length;i++) {
                    data[i] = (data[i]-min)/(max-min)*255;
                }
                BufferedImage convertTo = ConvertBufferedImage.convertTo(maybeMask, null);
                ImageIO.write(convertTo, "jpg", new File("src/test/resources/images/corgi-maskedrmbg.jpg"));
            } catch (Exception e) {
                e.printStackTrace();
            }
            });
    }
}
