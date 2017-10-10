package cn.almsound.www.chartlibrary.listener;


import cn.almsound.www.chartlibrary.model.PointValue;
import cn.almsound.www.chartlibrary.model.SubcolumnValue;

public interface ComboLineColumnChartOnValueSelectListener extends OnValueDeselectListener {

     void onColumnValueSelected(int columnIndex, int subcolumnIndex, SubcolumnValue value);

     void onPointValueSelected(int lineIndex, int pointIndex, PointValue value);

}
