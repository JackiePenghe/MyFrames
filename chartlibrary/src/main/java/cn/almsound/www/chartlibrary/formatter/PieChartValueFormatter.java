package cn.almsound.www.chartlibrary.formatter;


import cn.almsound.www.chartlibrary.model.SliceValue;

public interface PieChartValueFormatter {

     int formatChartValue(char[] formattedValue, SliceValue value);
}
