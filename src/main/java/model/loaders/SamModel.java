package model.loaders;

import ai.onnxruntime.OrtEnvironment;
import ai.onnxruntime.OrtException;
import ai.onnxruntime.OrtSession;

public class SamModel {
    public static final String MODEL_NAME = "ai.facebook.sam-vit-base";

    private final SamConfig config;
    private ImageProcessor imageProcessor;
    private OrtEnvironment env;
//    private OrtSession session;

    public SamModel(SamConfig config) {
        this.config = config;
        this.env = createEnvironment();
    }

    public OrtEnvironment createEnvironment() {
        OrtEnvironment env = OrtEnvironment.getEnvironment();
        try {
            env.setTelemetry(false);
        } catch (OrtException e) {
            e.printStackTrace();
            System.err.println("Could not turn off telemetry. Continuing...");
        }
        return env;
    }
    
    public OrtSession createSession(OrtEnvironment env) {
        try {
            return env.createSession(config.getModelFile());
        } catch (OrtException e) {
            throw new RuntimeException("Could not create session.", e);
        }
    }

    public ImageProcessor selectImageProcessorFromConfig(SamConfig config) {
        switch (config.getPreProcessorConfig().getImageProcessorType()) {
        case SamImageProcessor.PROCESSOR_TYPE:
            return new SamImageProcessor(config);
        default:
            throw new RuntimeException(
                    "Unknown image pre processor provided which cannot be instantiated from config.");
        }
    }

    /**
     * @return the imageProcessor
     */
    public ImageProcessor getImageProcessor() {
        return imageProcessor;
    }

    /**
     * @param imageProcessor the imageProcessor to set
     */
    public void setImageProcessor(ImageProcessor imageProcessor) {
        this.imageProcessor = imageProcessor;
    }
}
