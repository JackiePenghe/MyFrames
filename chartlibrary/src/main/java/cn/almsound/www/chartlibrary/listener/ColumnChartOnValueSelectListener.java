package cn.almsound.www.chartlibrary.listener;


import cn.almsound.www.chartlibrary.model.SubcolumnValue;

public interface ColumnChartOnValueSelectListener extends OnValueDeselectListener {

     void onValueSelected(int columnIndex, int subcolumnIndex, SubcolumnValue value);

}
