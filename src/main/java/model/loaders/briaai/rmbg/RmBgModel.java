package model.loaders.briaai.rmbg;

import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Map;

import ai.onnxruntime.OnnxTensor;
import ai.onnxruntime.OrtEnvironment;
import ai.onnxruntime.OrtException;
import ai.onnxruntime.OrtSession;
import ai.onnxruntime.OrtSession.Result;

public class RmBgModel {
    final RmBgConfig config;
    final RmBgImageProcessor imageProcessor;
    final OrtEnvironment env;

    public RmBgModel(RmBgConfig config, RmBgImageProcessor imageProcessor) {
        this.config = config;
        this.imageProcessor = imageProcessor;
        this.env = OrtEnvironment.getEnvironment();
    }

    public List<BufferedImage> generateMask(BufferedImage image) {
        try {
            OrtSession session = env.createSession(config.getModelPath());
            List<float[][][]> floats = imageProcessor.preProcessImage(image);
            float[][][][] input= new float[floats.size()][][][];
            for(int i=0;i<floats.size();i++) {
                input[i]=floats.get(i);
            }
            OnnxTensor t = OnnxTensor.createTensor(env, input);
            Result run = session.run(Map.of("input", t));
            try (run) {
                return run.get("output")
                        .map(output -> {
                            try {
                                return imageProcessor.postProcessImage(
                                        (float[][][][]) output.getValue());
                            } catch (OrtException e) {
                                e.printStackTrace();
                            }
                            return null;
                        })
                        .orElse(List.of());
            }
        } catch (OrtException e) {
            e.printStackTrace();
        }
        
        return List.of();
    }

}
