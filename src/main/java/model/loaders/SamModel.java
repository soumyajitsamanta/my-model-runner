package model.loaders;

import ai.onnxruntime.OrtEnvironment;
import ai.onnxruntime.OrtException;
import ai.onnxruntime.OrtSession;

public class SamModel {
    public static final String MODEL_NAME = "ai.facebook.sam-vit-base";

    private final SamConfig config;

    private SamModel(SamConfig config) {
        this.config = config;
    }

    private OrtSession createSession() {
        OrtEnvironment env = OrtEnvironment.getEnvironment();
        try {
            env.setTelemetry(false);
        } catch (OrtException e) {
            e.printStackTrace();
            System.err.println("Could not turn off telemetry. Continuing...");
        }
        try {
            return env.createSession(config.getModelFile());
        } catch (OrtException e) {
            throw new RuntimeException("Could not create session.", e);
        }
    }
    
    private void executeModel() {
    }
}
