package com.penghe.www.circleprogressbarlibrary;


import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;

import java.util.Locale;

/**
 * Created by pengh on 2017/7/15.
 */

public class CircleProgressBar extends View {

    private static final String TAG = "CircleProgressBar";
    //这个圆形进度条的最小宽度
    private double minWidth;
    //这个圆形进度条的最小高度
    private double minHeight;
    //进度条进度
    private double progress;
    //画笔
    private Paint paint;
    //最大进度
    private double maxProgress;
    //圆环的底色
    private int roundColor;
    //进度条的颜色
    private int progressColor;
    //在进度条中间显示的进度文字的颜色
    private int textColor;
    //在进度条中间显示的文字的大小
    private float textSize;
    //进度条的宽度（进度的粗细）
    private float roundWidth;
    //是否显示进度百分比文字
    private boolean showText;
    private RectF rectF;

    public CircleProgressBar(Context context) {
        this(context, null);
    }

    public CircleProgressBar(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircleProgressBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initial();
        getAttributeSet(attrs);
    }

    private void getAttributeSet(AttributeSet attrs) {
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.circle_progress_bar);
        maxProgress = typedArray.getFloat(R.styleable.circle_progress_bar_max_progress, 100);
        roundColor = typedArray.getColor(R.styleable.circle_progress_bar_round_color, Color.RED);
        progressColor = typedArray.getColor(R.styleable.circle_progress_bar_progress_color, Color.BLUE);
        textColor = typedArray.getColor(R.styleable.circle_progress_bar_text_color, Color.GREEN);
        textSize = typedArray.getDimension(R.styleable.circle_progress_bar_text_size, 55);
        roundWidth = typedArray.getDimension(R.styleable.circle_progress_bar_round_width, 10);
        showText = typedArray.getBoolean(R.styleable.circle_progress_bar_show_text, true);
        typedArray.recycle();
    }

    private void initial() {
        WindowManager windowManager = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        Point point = new Point();
        Display defaultDisplay = windowManager.getDefaultDisplay();
        defaultDisplay.getSize(point);
        minWidth = point.x * 1.0 / 3;
        minHeight = point.y * 1.0 / 3;
        paint = new Paint();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        switch (widthMode) {
            case MeasureSpec.AT_MOST:
                widthSize = (int) minWidth;
                break;
            case MeasureSpec.EXACTLY:
                break;
            case MeasureSpec.UNSPECIFIED:
                break;
        }

        switch (heightMode) {
            case MeasureSpec.AT_MOST:
                heightSize = (int) minHeight;
                break;
            case MeasureSpec.EXACTLY:
                break;
            case MeasureSpec.UNSPECIFIED:
                break;
        }

        if (widthSize < heightSize) {
            heightSize = widthSize;
        } else {
            widthSize = heightSize;
        }

        setMeasuredDimension(widthSize, heightSize);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int center = getWidth() / 2;

        //画背景圆环
        float radius = center - roundWidth / 2;
        paint.setColor(roundColor);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(roundWidth);
        paint.setAntiAlias(true);
        canvas.drawCircle(center, center, radius, paint);

        //画进度百分比
        if (showText) {
            paint.setColor(textColor);
            paint.setStrokeWidth(0);
            paint.setTextSize(textSize);
            paint.setTypeface(Typeface.DEFAULT_BOLD);
            double percent = progress * 1.0 / maxProgress * 100;
            String percenterString = String.format(Locale.getDefault(), "%.2f", percent) + "%";
            Paint.FontMetrics fontMetrics = paint.getFontMetrics();
            canvas.drawText(percenterString, getWidth() / 2 - paint.measureText(percenterString) / 2, getWidth() / 2 + (fontMetrics.bottom - fontMetrics.top) / 2 - fontMetrics.bottom, paint);
        }

        //画圆弧（进度）
        if (rectF == null) {
            rectF = new RectF(center - radius, center - radius, center + radius, center + radius);
        }
        paint.setColor(progressColor);
        paint.setStrokeWidth(roundWidth);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeCap(Paint.Cap.ROUND);
        canvas.drawArc(rectF, -90, (float) (360f * progress / maxProgress), false, paint);
    }

    public void setProgress(double progress) {
        if (progress < 0) {
            throw new IllegalArgumentException("progress must be a positive number,but a wrong number" + progress + "we got!");
        } else if (progress > maxProgress) {
            this.progress = maxProgress;
        } else if (progress <= maxProgress) {
            this.progress = progress;
        }
        postInvalidate();
    }

    public double getMaxProgress() {
        return maxProgress;
    }
}
