package cn.almsound.www.chartlibrary.model;

import java.util.Arrays;

import cn.almsound.www.chartlibrary.util.ChartUtils;
import cn.almsound.www.chartlibrary.view.Chart;


/**
 * Model representing single slice on PieChart.
 */
@SuppressWarnings({"unused", "deprecation"})
public class SliceValue {
    private static final int DEFAULT_SLICE_SPACING_DP = 2;
    /**
     * Spacing between this slice and its neighbors.
     */
    @Deprecated
    private int sliceSpacing = DEFAULT_SLICE_SPACING_DP;
    /**
     * Current value of this slice.
     */
    private float value;
    /**
     * Origin value of this slice, used during value animation.
     */
    private float originValue;
    /**
     * Difference between originValue and targetValue.
     */
    private float diff;
    /**
     * Color of this slice.
     */
    private int color = ChartUtils.DEFAULT_COLOR;
    /**
     * Darken color used to draw label background and give touch feedback.
     */
    private int darkenColor = ChartUtils.DEFAULT_DARKEN_COLOR;
    /**
     * Custom label for this slice, if not set number formatting will be used.
     */
    private char[] label;

    public SliceValue() {
        setValue(0);
    }

    public SliceValue(float value) {
        setValue(value);
    }

    public SliceValue(float value, int color) {
        setValue(value);
        setColor(color);
    }

    public SliceValue(float value, int color, int sliceSpacing) {
        setValue(value);
        setColor(color);
        this.sliceSpacing = sliceSpacing;
    }

    public SliceValue(SliceValue sliceValue) {
        setValue(sliceValue.value);
        setColor(sliceValue.color);
        this.sliceSpacing = sliceValue.sliceSpacing;
        this.label = sliceValue.label;
    }

    public void update(float scale) {
        value = originValue + diff * scale;
    }

    public void finish() {
        setValue(originValue + diff);
    }

    public float getValue() {
        return value;
    }

    public SliceValue setValue(float value) {
        this.value = value;
        this.originValue = value;
        this.diff = 0;
        return this;
    }

    /**
     * Set target value that should be reached when data animation finish then call {@link Chart#startDataAnimation()}
     *
     * @param target target
     * @return SliceValue
     */
    public SliceValue setTarget(float target) {
        setValue(value);
        this.diff = target - originValue;
        return this;
    }

    public int getColor() {
        return color;
    }

    public SliceValue setColor(int color) {
        this.color = color;
        this.darkenColor = ChartUtils.darkenColor(color);
        return this;
    }

    public int getDarkenColor() {
        return darkenColor;
    }

    @Deprecated
    public int getSliceSpacing() {
        return sliceSpacing;
    }

    @Deprecated
    public SliceValue setSliceSpacing(int sliceSpacing) {
        this.sliceSpacing = sliceSpacing;
        return this;
    }

    @Deprecated
    public char[] getLabel() {
        return label;
    }

    @Deprecated
    public SliceValue setLabel(char[] label) {
        this.label = label;
        return this;
    }

    public SliceValue setLabel(String label) {
        this.label = label.toCharArray();
        return this;
    }

    public char[] getLabelAsChars() {
        return label;
    }

    @Override
    public String toString() {
        return "SliceValue [value=" + value + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SliceValue that = (SliceValue) o;

        return color == that.color && darkenColor == that.darkenColor && Float.compare(that.diff, diff) == 0 && Float.compare(that.originValue, originValue) == 0 && sliceSpacing == that.sliceSpacing && Float.compare(that.value, value) == 0 && Arrays.equals(label, that.label);

    }

    @Override
    public int hashCode() {
        int result = (value != +0.0f ? Float.floatToIntBits(value) : 0);
        result = 31 * result + (originValue != +0.0f ? Float.floatToIntBits(originValue) : 0);
        result = 31 * result + (diff != +0.0f ? Float.floatToIntBits(diff) : 0);
        result = 31 * result + color;
        result = 31 * result + darkenColor;
        result = 31 * result + sliceSpacing;
        result = 31 * result + (label != null ? Arrays.hashCode(label) : 0);
        return result;
    }
}
