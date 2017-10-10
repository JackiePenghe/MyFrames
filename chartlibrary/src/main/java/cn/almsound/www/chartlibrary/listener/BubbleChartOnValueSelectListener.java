package cn.almsound.www.chartlibrary.listener;


import cn.almsound.www.chartlibrary.model.BubbleValue;

public interface BubbleChartOnValueSelectListener extends OnValueDeselectListener {

     void onValueSelected(int bubbleIndex, BubbleValue value);

}
