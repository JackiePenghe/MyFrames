package cn.almsound.www.chartlibrary.formatter;


import cn.almsound.www.chartlibrary.model.SubcolumnValue;

public interface ColumnChartValueFormatter {

     int formatChartValue(char[] formattedValue, SubcolumnValue value);

}
