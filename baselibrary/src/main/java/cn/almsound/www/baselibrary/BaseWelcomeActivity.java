package cn.almsound.www.baselibrary;

import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

/**
 * Created by alm on 17-6-6.
 * 欢迎页
 */

public abstract class BaseWelcomeActivity extends BaseAppcompatActivity {

    private ImageView imageView;
    private Animation animation;
    private Animation.AnimationListener animationListener;

    public void titleBackClicked() {
        onBackPressed();
    }

    @Override
    protected void doBeforeSetLayout() {
        animationListener = new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                doAfterAnimation();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        };
    }

    @Override
    protected int setLayout() {
        return R.layout.activity_welcome;
    }

    @Override
    protected void doBeforeInitOthers() {
        hideTitleBar();
    }

    @Override
    protected void initViews() {
        imageView = (ImageView) findViewById(R.id.welcome_activity_image_view);
    }

    @Override
    protected void initViewData() {
        animation = AnimationUtils.loadAnimation(BaseWelcomeActivity.this, R.anim.anim_welcome);
    }

    @Override
    protected void initOtherData() {
    }

    @Override
    protected void initEvents() {
        animation.setAnimationListener(animationListener);
        imageView.startAnimation(animation);
    }

    /**
     * 在最后进行的操作
     */
    @Override
    protected void doAfterAll() {

    }

    @Override
    protected boolean createOptionsMenu(Menu menu) {
        return false;
    }

    @Override
    protected boolean optionsItemSelected(MenuItem item) {
        return false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        imageView = null;
        animation = null;
        animationListener = null;
    }

    protected abstract void doAfterAnimation();
}
