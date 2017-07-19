package cn.almsound.www.histogramviewlibrary;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by alm on 17-7-19.
 * 自定义柱状图
 */

public class HistogramView extends View {


    private Rect rect1;
    private Rect rect2;

    public HistogramView(Context context) {
        this(context, null);
    }

    public HistogramView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public HistogramView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    //  坐标轴 轴线 画笔：
    private Paint axisLinePaint;
    //  坐标轴水平内部 虚线画笔
    private Paint hLinePaint;
    //  绘制文本的画笔
    private Paint titlePaint;
    //  矩形画笔 柱状图的样式信息
    private Paint recPaint;

    private void init() {

        axisLinePaint = new Paint();
        hLinePaint = new Paint();
        titlePaint = new Paint();
        recPaint = new Paint();

        axisLinePaint.setColor(Color.DKGRAY);
        hLinePaint.setColor(Color.LTGRAY);
        titlePaint.setColor(Color.BLACK);

    }

    //7 条
    private int[] thisYear;

    //7 条
    private int[] lastYear;


    /**
     * 跟新自身的数据 需要View子类重绘。
     * <p>
     * 主线程 刷新控件的时候调用：
     * this.invalidate();  失效的意思。
     * this.postInvalidate();  可以子线程 更新视图的方法调用。
     */
    //updata this year data
    public void updateThisData(int[] thisData) {
        thisYear = thisData;
//      this.invalidate(); //失效的意思。  
        this.postInvalidate();  //可以子线程 更新视图的方法调用。
    }

    //updata last year data
    public void updateLastData(int[] lastData) {
        lastYear = lastData;
//      this.invalidate(); //失效的意思。  
        this.postInvalidate();  //可以子线程 更新视图的方法调用。
    }


    private String[] yTitlesStrings =
            new String[]{"80000", "60000", "40000", "20000", "0"};

    private String[] xTitles =
            new String[]{"1", "2", "3", "4", "5", "6", "7"};

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int width = getWidth();
//        int height = getHeight();

        // 1 绘制坐标线：
        canvas.drawLine(100, 10, 100, 320, axisLinePaint);

        canvas.drawLine(100, 320, width - 10, 320, axisLinePaint);

        // 2 绘制坐标内部的水平线

        int leftHeight = 300;// 左侧外周的 需要划分的高度：

        int hPerHeight = leftHeight / 4;

        hLinePaint.setTextAlign(Paint.Align.CENTER);
        for (int i = 0; i < 4; i++) {
            canvas.drawLine(100, 20 + i * hPerHeight, width - 10, 20 + i * hPerHeight, hLinePaint);
        }


        // 3 绘制 Y 周坐标

        Paint.FontMetrics metrics = titlePaint.getFontMetrics();
        int descent = (int) metrics.descent;
        titlePaint.setTextAlign(Paint.Align.RIGHT);
        for (int i = 0; i < yTitlesStrings.length; i++) {
            canvas.drawText(yTitlesStrings[i], 80, 20 + i * hPerHeight + descent, titlePaint);
        }

        // 4  绘制 X 周 做坐标

        int xAxisLength = width - 110;
        int columCount = xTitles.length + 1;
        int step = xAxisLength / columCount;

        for (int i = 0; i < columCount - 1; i++) {
            canvas.drawText(xTitles[i], 100 + step * (i + 1), 360, titlePaint);
        }

        // 5 绘制矩形

        if (thisYear != null && thisYear.length > 0) {
            int thisCount = thisYear.length;


            for (int i = 0; i < thisCount; i++) {
                int value = thisYear[i];

                int num = 8 - value / 10000;


                recPaint.setColor(0xFF1078CF);

                if (rect1 == null) {
                    rect1 = new Rect();
                }

                rect1.left = 100 + step * (i + 1) - 10;
                rect1.right = 100 + step * (i + 1) + 10;

//              当前的相对高度：  
                int rh = (leftHeight * num) / 8;

                rect1.top = rh + 20;
                rect1.bottom = 320;

                canvas.drawRect(rect1, recPaint);

            }
        }


        if (lastYear != null && lastYear.length > 0) {
            int thisCount = lastYear.length;


            for (int i = 0; i < thisCount; i++) {
                int value = lastYear[i];

                int num = 8 - value / 10000;


                recPaint.setColor(0xFFAA1122);

                if (rect2 == null) {
                    rect2 = new Rect();
                }

                rect2.left = 100 + step * (i + 1) - 10;
                rect2.right = 100 + step * (i + 1) + 10;

//              当前的相对高度：  
                int rh = (leftHeight * num) / 8;

                rect2.top = rh + 20;
                rect2.bottom = 320;

                canvas.drawRect(rect2, recPaint);

            }
        }


    }


}
