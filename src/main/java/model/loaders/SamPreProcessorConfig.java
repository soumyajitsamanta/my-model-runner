package model.loaders;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class SamPreProcessorConfig {

    private static final ObjectMapper JSON_MAPPER = new ObjectMapper();
    public static final SamPreProcessorConfig fromFile(final File preprocessorFile) {
        try {
            return JSON_MAPPER.readValue(preprocessorFile,
                    SamPreProcessorConfig.class);
        } catch (final StreamReadException e) {
            throw new RuntimeException("Reading config file content failed.",
                    e);
        } catch (final DatabindException e) {
            throw new RuntimeException("Creating config object failed.", e);
        } catch (final IOException e) {
            throw new RuntimeException("Opening, locating config file failed..",
                    e);
        }
    }
    public static ObjectMapper getJsonmapper() {
        return JSON_MAPPER;
    }

    /**
     *
     */
    @JsonProperty("do_convert_rgb")
    private boolean doConvertRgb = true;

    /**
     *
     */
    @JsonProperty("do_normalize")
    private boolean doNormalize = true;

    /**
     *
     */
    @JsonProperty("do_pad")
    private boolean doPad = true;
    /**
     *
     */
    @JsonProperty("do_rescale")
    private boolean doRescale = true;
    /**
     *
     */
    @JsonProperty("do_resize")
    private boolean doResize = true;
    /**
     *
     */
    @JsonProperty("image_mean")
    private float[] imageMean = { .485f, .456f, .406f };
    /**
     *
     */
    @JsonProperty("image_std")
    private float[] imageStd = { 0.229f, 0.224f, 0.225f };
    /**
     *
     */
    @JsonProperty("image_processor_type")
    private String imageProcessorType = "SamImageProcessor";
    /**
     *
     */
    @JsonProperty("processor_class")
    private String processorClass = "SamProcessor";
    /**
     *
     */
    @JsonProperty("pad_size")
    private ImageSize padSize = new ImageSize(1024, 1024);
    /**
     *
     */
    @JsonProperty("resample")
    private int resample = 2;
    /**
     *
     */
    @JsonProperty("rescale_factor")
    private float rescaleFactor = 0.00392156862745098f;
    /**
     *
     */
    @JsonProperty("size")
    private Map<String, Integer> size = Map.of("longest_edge", 1024);

    public SamPreProcessorConfig() {
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if ((obj == null) || (getClass() != obj.getClass())) {
            return false;
        }
        final SamPreProcessorConfig other = (SamPreProcessorConfig) obj;
        return doConvertRgb == other.doConvertRgb
                && doNormalize == other.doNormalize && doPad == other.doPad
                && doRescale == other.doRescale && doResize == other.doResize
                && Arrays.equals(imageMean, other.imageMean)
                && Objects.equals(imageProcessorType, other.imageProcessorType)
                && Arrays.equals(imageStd, other.imageStd)
                && Objects.equals(padSize, other.padSize)
                && Objects.equals(processorClass, other.processorClass)
                && resample == other.resample
                && Float.floatToIntBits(rescaleFactor) == Float
                        .floatToIntBits(other.rescaleFactor)
                && Objects.equals(size, other.size);
    }

    public float[] getImageMean() {
        return imageMean;
    }

    public String getImageProcessorType() {
        return imageProcessorType;
    }

    public float[] getImageStd() {
        return imageStd;
    }


    public String getProcessorClass() {
        return processorClass;
    }

    public Integer getResample() {
        return resample;
    }

    public float getRescaleFactor() {
        return rescaleFactor;
    }

    public Map<String, Integer> getSize() {
        return size;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Arrays.hashCode(imageMean);
        result = prime * result + Arrays.hashCode(imageStd);
        result = prime * result + Objects.hash(doConvertRgb, doNormalize, doPad,
                doRescale, doResize, imageProcessorType, padSize,
                processorClass, resample, rescaleFactor, size);
        return result;
    }

    public boolean isDoConvertRgb() {
        return doConvertRgb;
    }

    public boolean isDoNormalize() {
        return doNormalize;
    }
    public boolean isDoPad() {
        return doPad;
    }

    public boolean isDoRescale() {
        return doRescale;
    }

    public boolean isDoResize() {
        return doResize;
    }

    public void setDoConvertRgb(boolean doConvertRgb) {
        this.doConvertRgb = doConvertRgb;
    }

    public void setDoNormalize(boolean doNormalize) {
        this.doNormalize = doNormalize;
    }

    public void setDoPad(boolean doPad) {
        this.doPad = doPad;
    }

    public void setDoRescale(boolean doRescale) {
        this.doRescale = doRescale;
    }

    public void setDoResize(boolean doResize) {
        this.doResize = doResize;
    }

    public void setImageMean(float[] imageMean) {
        this.imageMean = imageMean;
    }

    public void setImageProcessorType(String imageProcessorType) {
        this.imageProcessorType = imageProcessorType;
    }

    public void setImageStd(float[] imageStd) {
        this.imageStd = imageStd;
    }


    public void setProcessorClass(String processorClass) {
        this.processorClass = processorClass;
    }

    public void setResample(int resample) {
        this.resample = resample;
    }

    public void setRescaleFactor(float rescaleFactor) {
        this.rescaleFactor = rescaleFactor;
    }

    public void setSize(Map<String, Integer> size) {
        this.size = size;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("SamPreProcessorConfig [doConvertRgb=");
        builder.append(doConvertRgb);
        builder.append(", doNormalize=");
        builder.append(doNormalize);
        builder.append(", doPad=");
        builder.append(doPad);
        builder.append(", doRescale=");
        builder.append(doRescale);
        builder.append(", doResize=");
        builder.append(doResize);
        builder.append(", imageMean=");
        builder.append(Arrays.toString(imageMean));
        builder.append(", imageStd=");
        builder.append(Arrays.toString(imageStd));
        builder.append(", imageProcessorType=");
        builder.append(imageProcessorType);
        builder.append(", processorClass=");
        builder.append(processorClass);
        builder.append(", padSize=");
        builder.append(padSize);
        builder.append(", resample=");
        builder.append(resample);
        builder.append(", rescaleFactor=");
        builder.append(rescaleFactor);
        builder.append(", size=");
        builder.append(size);
        builder.append("]");
        return builder.toString();
    }

}
