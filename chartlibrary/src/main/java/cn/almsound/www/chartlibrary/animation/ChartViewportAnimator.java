package cn.almsound.www.chartlibrary.animation;


import cn.almsound.www.chartlibrary.model.Viewport;

@SuppressWarnings("unused")
public interface ChartViewportAnimator {

    int FAST_ANIMATION_DURATION = 300;

    void startAnimation(Viewport startViewport, Viewport targetViewport);

    void startAnimation(Viewport startViewport, Viewport targetViewport, long duration);

    void cancelAnimation();

    boolean isAnimationStarted();

    void setChartAnimationListener(ChartAnimationListener animationListener);

}
