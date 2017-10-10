package cn.almsound.www.chartlibrary.formatter;


import cn.almsound.www.chartlibrary.model.BubbleValue;

public interface BubbleChartValueFormatter {

     int formatChartValue(char[] formattedValue, BubbleValue value);
}
