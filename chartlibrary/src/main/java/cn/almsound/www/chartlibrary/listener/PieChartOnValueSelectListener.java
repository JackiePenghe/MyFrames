package cn.almsound.www.chartlibrary.listener;


import cn.almsound.www.chartlibrary.model.SliceValue;

public interface PieChartOnValueSelectListener extends OnValueDeselectListener {

     void onValueSelected(int arcIndex, SliceValue value);

}
