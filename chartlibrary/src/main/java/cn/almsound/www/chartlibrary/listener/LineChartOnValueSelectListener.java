package cn.almsound.www.chartlibrary.listener;


import cn.almsound.www.chartlibrary.model.PointValue;

public interface LineChartOnValueSelectListener extends OnValueDeselectListener {

     void onValueSelected(int lineIndex, int pointIndex, PointValue value);

}
