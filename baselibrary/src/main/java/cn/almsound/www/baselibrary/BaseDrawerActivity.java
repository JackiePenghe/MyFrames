package cn.almsound.www.baselibrary;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

/**
 * Created by alm on 17-6-6.
 * 带有侧边栏的Activity父类
 */

public abstract class BaseDrawerActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private DrawerLayout.DrawerListener drawerListener;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private NavigationView.OnNavigationItemSelectedListener onNavigationItemSelectedListener;
    private NavigationView navigationView;
    private TextView titleView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        drawerListener = new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                drawerSlide(drawerView, slideOffset);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                drawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                drawerClosed(drawerView);
            }

            @Override
            public void onDrawerStateChanged(int newState) {
                drawerStateChanged(newState);
            }
        };
        onNavigationItemSelectedListener = new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                return navigationItemSelected(item);
            }
        };
        doBeforeSetLayout();

        setContentView(R.layout.activity_base_drawer);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        titleView = (TextView) findViewById(R.id.toolbar_title);
        if (titleView != null) {
            titleView.setText(R.string.app_name);
        }
        setSupportActionBar(toolbar);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
            supportActionBar.setDisplayShowTitleEnabled(false);
        }
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        drawerLayout.addDrawerListener(drawerListener);
        actionBarDrawerToggle.syncState();
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(onNavigationItemSelectedListener);
        FrameLayout content = (FrameLayout) findViewById(R.id.content);
        View view = getLayoutInflater().inflate(setLayout(), null);
        content.addView(view);

        doBeforeInitOthers();
        initViews();
        initViewData();
        initOtherData();
        initEvents();
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        toolbar.setNavigationOnClickListener(null);
        toolbar.setNavigationIcon(null);
        toolbar = null;
        drawerLayout.removeAllViews();
        drawerLayout.removeDrawerListener(drawerListener);
        drawerLayout.removeDrawerListener(actionBarDrawerToggle);
        drawerLayout = null;
        drawerListener = null;
        actionBarDrawerToggle = null;
        navigationView.setNavigationItemSelectedListener(null);
        navigationView = null;
        onNavigationItemSelectedListener = null;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return createOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return optionsItemSelected(item);
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

    /**
     * DrawerLayout的滑动监听
     *
     * @param drawerView  View
     * @param slideOffset slideOffset
     */
    protected abstract void drawerSlide(View drawerView, float slideOffset);

    /**
     * DrawerLayout已经完全打开了
     *
     * @param drawerView View
     */
    protected abstract void drawerOpened(View drawerView);

    /**
     * DrawerLayout已经完全关闭了
     *
     * @param drawerView View
     */
    protected abstract void drawerClosed(View drawerView);

    /**
     * DrawerLayout的状态改变了
     *
     * @param newState 新的状态
     */
    protected abstract void drawerStateChanged(int newState);

    /**
     * 侧边栏的监听
     *
     * @param menuItem 侧边栏item
     * @return true表示处理了监听事件
     */
    protected abstract boolean navigationItemSelected(MenuItem menuItem);

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
    protected void hideTitle() {
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
     * 关闭侧边栏
     */
    protected void closeDrawer() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
    }

    /**
     * 设置标题栏的内容
     *
     * @param titleResId 标题栏的资源id
     */
    protected void setTitleText(@StringRes int titleResId) {
        titleView.setText(titleResId);
    }

    /**
     * 设置标题栏的内容
     *
     * @param titleText 标题栏内容
     */
    protected void setTitleText(String titleText) {
        titleView.setText(titleText);
    }
}
