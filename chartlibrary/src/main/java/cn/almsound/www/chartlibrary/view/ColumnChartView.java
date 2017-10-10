package cn.almsound.www.chartlibrary.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;

import cn.almsound.www.chartlibrary.BuildConfig;
import cn.almsound.www.chartlibrary.listener.ColumnChartOnValueSelectListener;
import cn.almsound.www.chartlibrary.listener.DummyColumnChartOnValueSelectListener;
import cn.almsound.www.chartlibrary.model.ColumnChartData;
import cn.almsound.www.chartlibrary.model.SelectedValue;
import cn.almsound.www.chartlibrary.model.SubcolumnValue;
import cn.almsound.www.chartlibrary.provider.ColumnChartDataProvider;
import cn.almsound.www.chartlibrary.renderer.ColumnChartRenderer;


/**
 * ColumnChart/BarChart, supports subcolumns, stacked collumns and negative values.
 *
 * @author Leszek Wach
 */
public class ColumnChartView extends AbstractChartView implements ColumnChartDataProvider {
    private static final String TAG = "ColumnChartView";
    private ColumnChartData data;
    private ColumnChartOnValueSelectListener onValueTouchListener = new DummyColumnChartOnValueSelectListener();

    public ColumnChartView(Context context) {
        this(context, null, 0);
    }

    public ColumnChartView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ColumnChartView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setChartRenderer(new ColumnChartRenderer(context, this, this));
        setColumnChartData(ColumnChartData.generateDummyData());
    }

    @Override
    public ColumnChartData getColumnChartData() {
        return data;
    }

    @Override
    public void setColumnChartData(ColumnChartData data) {
        if (BuildConfig.DEBUG) {
            Log.d(TAG, "Setting data for ColumnChartView");
        }

        if (null == data) {
            this.data = ColumnChartData.generateDummyData();
        } else {
            this.data = data;
        }

        super.onChartDataChange();

    }

    @Override
    public ColumnChartData getChartData() {
        return data;
    }

    @Override
    public void callTouchListener() {
        SelectedValue selectedValue = chartRenderer.getSelectedValue();

        if (selectedValue.isSet()) {
            SubcolumnValue value = data.getColumns().get(selectedValue.getFirstIndex()).getValues()
                    .get(selectedValue.getSecondIndex());
            onValueTouchListener.onValueSelected(selectedValue.getFirstIndex(), selectedValue.getSecondIndex(), value);
        } else {
            onValueTouchListener.onValueDeselected();
        }
    }

    public ColumnChartOnValueSelectListener getOnValueTouchListener() {
        return onValueTouchListener;
    }

    public void setOnValueTouchListener(ColumnChartOnValueSelectListener touchListener) {
        if (null != touchListener) {
            this.onValueTouchListener = touchListener;
        }
    }
}
