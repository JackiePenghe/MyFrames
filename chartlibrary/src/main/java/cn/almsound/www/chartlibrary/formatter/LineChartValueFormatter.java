package cn.almsound.www.chartlibrary.formatter;


import cn.almsound.www.chartlibrary.model.PointValue;

public interface LineChartValueFormatter {

     int formatChartValue(char[] formattedValue, PointValue value);
}
