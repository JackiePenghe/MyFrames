package cn.almsound.www.baselibrary;

import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

/**
 * Created by alm on 17-6-6.
 * AppCompatActivity的父类
 */

public abstract class BaseAppcompatActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private View.OnClickListener mTitleBackButtonOnClickListener;
    private FrameLayout content;
    private TextView titleView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTitleBackButtonOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               titleBackClicked();
            }
        };

        doBeforeSetLayout();

        setContentView(R.layout.activity_base_appcompat);

        initThisView();
        initThisData();
        initThisEvents();

        doBeforeInitOthers();
        initViews();
        initViewData();
        initOtherData();
        initEvents();
        doAfterAll();
    }

    protected abstract void titleBackClicked();

    /**
     * Initialize the contents of the Activity's standard options menu.  You
     * should place your menu items in to <var>menu</var>.
     * <p>
     * <p>This is only called once, the first time the options menu is
     * displayed.  To update the menu every time it is displayed, see
     * {@link #onPrepareOptionsMenu}.
     * <p>
     * <p>The default implementation populates the menu with standard system
     * menu items.  These are placed in the {@link Menu#CATEGORY_SYSTEM} group so that
     * they will be correctly ordered with application-defined menu items.
     * Deriving classes should always call through to the base implementation.
     * <p>
     * <p>You can safely hold on to <var>menu</var> (and any items created
     * from it), making modifications to it as desired, until the next
     * time onCreateOptionsMenu() is called.
     * <p>
     * <p>When you add items to the menu, you can implement the Activity's
     * {@link #onOptionsItemSelected} method to handle them there.
     *
     * @param menu The options menu in which you place your items.
     * @return You must return true for the menu to be displayed;
     * if you return false it will not be shown.
     * @see #onPrepareOptionsMenu
     * @see #onOptionsItemSelected
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return createOptionsMenu(menu);
    }

    /**
     * This hook is called whenever an item in your options menu is selected.
     * The default implementation simply returns false to have the normal
     * processing happen (calling the item's Runnable or sending a message to
     * its Handler as appropriate).  You can use this method for any items
     * for which you would like to do processing without those other
     * facilities.
     * <p>
     * <p>Derived classes should call through to the base class for it to
     * perform the default menu handling.</p>
     *
     * @param item The menu item that was selected.
     * @return boolean Return false to allow normal menu processing to
     * proceed, true to consume it here.
     * @see #onCreateOptionsMenu
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return optionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        toolbar.setNavigationOnClickListener(null);
        toolbar = null;
        mTitleBackButtonOnClickListener = null;
        content.removeAllViews();
        content = null;
    }

    private void initThisView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        titleView = (TextView) findViewById(R.id.toolbar_title);
        content = (FrameLayout) findViewById(R.id.content);
    }

    private void initThisData() {
        toolbar.setNavigationIcon(R.drawable.back);
        titleView = (TextView) findViewById(R.id.toolbar_title);
        titleView.setText(R.string.app_name);
        setSupportActionBar(toolbar);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
            supportActionBar.setDisplayShowTitleEnabled(false);
        }
        View view = getLayoutInflater().inflate(setLayout(), null);
        content.addView(view);
    }

    private void initThisEvents() {
        toolbar.setNavigationOnClickListener(mTitleBackButtonOnClickListener);
    }

    /**
     * 设置title返回按钮的处理事件
     *
     * @param titleBackButtonOnClickListener 返回按钮的处理事件
     */
    protected void setTitleBackOnClickListener(View.OnClickListener titleBackButtonOnClickListener) {
        mTitleBackButtonOnClickListener = titleBackButtonOnClickListener;
    }

    /**
     * 隐藏标题栏的返回按钮
     */
    protected void hideTitleBackButton() {
        toolbar.setNavigationIcon(null);
    }

    /**
     * 显示标题栏的返回按钮
     */
    protected void showTitleBackButton() {
        toolbar.setNavigationIcon(R.drawable.back);
    }

    /**
     * 隐藏标题栏
     */
    protected void hideTitleBar() {
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.hide();
        }
    }

    /**
     * 显示标题栏
     */
    protected void showTitle() {
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.show();
        }
    }

    /**
     * 设置标题栏的内容
     *
     * @param titleRes 标题栏的资源id
     */
    protected void setTitleText(@StringRes int titleRes) {
        titleView.setText(titleRes);
    }

    /**
     * 设置标题栏的内容
     *
     * @param titleText 标题栏的文本
     */
    protected void setTitleText(String titleText) {
        titleView.setText(titleText);
    }

    /**
     * 设置标题栏左边的图片
     *
     * @param drawableId 标题栏左边的图片资源id
     */
    protected void setTitleBackIcon(@DrawableRes int drawableId) {
        toolbar.setNavigationIcon(drawableId);
    }

    /**
     * 在设置布局之前需要进行的操作
     */
    protected abstract void doBeforeSetLayout();

    /**
     * 设置布局
     *
     * @return 布局id
     */
    @LayoutRes
    protected abstract int setLayout();

    /**
     * 在设置布局之后，进行其他操作之前，所需要初始化的数据
     */
    protected abstract void doBeforeInitOthers();

    /**
     * 初始化布局控件
     */
    protected abstract void initViews();

    /**
     * 初始化控件数据
     */
    protected abstract void initViewData();

    /**
     * 初始化其他数据
     */
    protected abstract void initOtherData();

    /**
     * 初始化事件
     */
    protected abstract void initEvents();

    /**
     * 在最后进行的操作
     */
    protected abstract void doAfterAll();

    /**
     * 设置菜单
     *
     * @param menu 菜单
     * @return 只是重写 public boolean onCreateOptionsMenu(Menu menu)
     */
    protected abstract boolean createOptionsMenu(Menu menu);

    /**
     * 设置菜单监听
     *
     * @param item 菜单的item
     * @return true表示处理了监听事件
     */
    protected abstract boolean optionsItemSelected(MenuItem item);
}
