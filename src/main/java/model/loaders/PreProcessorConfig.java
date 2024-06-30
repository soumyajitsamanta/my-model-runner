package model.loaders;

import java.util.Arrays;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PreProcessorConfig {
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
    private PreProcessorSize size = new PreProcessorSize();

    public PreProcessorConfig() {
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

    public ImageSize getPadSize() {
        return padSize;
    }

    public String getProcessorClass() {
        return processorClass;
    }

    public int getResample() {
        return resample;
    }

    public float getRescaleFactor() {
        return rescaleFactor;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("PreProcessorConfig [doConvertRgb=");
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

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        PreProcessorConfig other = (PreProcessorConfig) obj;
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

    public void setDoConvertRgb(final boolean doConvertRgb) {
        this.doConvertRgb = doConvertRgb;
    }

    public void setDoNormalize(final boolean doNormalize) {
        this.doNormalize = doNormalize;
    }

    public void setDoPad(final boolean doPad) {
        this.doPad = doPad;
    }

    public void setDoRescale(final boolean doRescale) {
        this.doRescale = doRescale;
    }

    public void setDoResize(final boolean doResize) {
        this.doResize = doResize;
    }

    public void setImageMean(final float[] imageMean) {
        this.imageMean = imageMean;
    }

    public void setImageProcessorType(final String imageProcessorType) {
        this.imageProcessorType = imageProcessorType;
    }

    public void setImageStd(final float[] imageStd) {
        this.imageStd = imageStd;
    }

    public void setPadSize(final ImageSize padSize) {
        this.padSize = padSize;
    }

    public void setProcessorClass(final String processorClass) {
        this.processorClass = processorClass;
    }

    public void setResample(final int resample) {
        this.resample = resample;
    }

    public void setRescaleFactor(final float rescaleFactor) {
        this.rescaleFactor = rescaleFactor;
    }

    public PreProcessorSize getSize() {
        return size;
    }

    public void setSize(PreProcessorSize size) {
        this.size = size;
    }

    public void valiadte() {
        if(size.getLongestEdge()==null && size.getHeight() == null && size.getWidth() == null) {
            throw new RuntimeException("Size has no value fields and all fieldds are null.");
        }
    }
}
