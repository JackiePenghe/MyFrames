package com.penghe.www.myframes.activities

import android.content.Intent
import android.view.Menu
import android.view.MenuItem
import android.view.View
import cn.almsound.www.baselibrary.BaseDrawerActivity
import com.penghe.www.myframes.R
import com.penghe.www.myframes.others.GoogleMapActivity
import com.penghe.www.myframes.others.Leson_2.FlowLayoutActivity
import com.penghe.www.myframes.others.leson_3.CircleProgressBarActivity
import com.penghe.www.myframes.others.leson_3.WaterFallLayoutActivity

class HomeActivity : BaseDrawerActivity() {

    private var menuItemId: Int = 0

    /**
     * 在设置布局之前需要进行的操作
     */
    override fun doBeforeSetLayout() {

    }

    /**
     * 设置布局

     * @return 布局id
     */
    override fun setLayout(): Int {
        return R.layout.activity_home
    }

    /**
     * 在设置布局之后，进行其他操作之前，所需要初始化的数据
     */
    override fun doBeforeInitOthers() {
    }

    /**
     * 初始化布局控件
     */
    override fun initViews() {
    }

    /**
     * 初始化控件数据
     */
    override fun initViewData() {
    }

    /**
     * 初始化其他数据
     */
    override fun initOtherData() {
    }

    /**
     * 初始化事件
     */
    override fun initEvents() {
    }

    /**
     * 设置菜单

     * @param menu 菜单
     * *
     * @return 只是重写 public boolean onCreateOptionsMenu(Menu menu)
     */
    override fun createOptionsMenu(menu: Menu): Boolean {
        return false
    }

    /**
     * 设置菜单监听

     * @param item 菜单的item
     * *
     * @return true表示处理了监听事件
     */
    override fun optionsItemSelected(item: MenuItem): Boolean {
        return false
    }

    /**
     * DrawerLayout的滑动监听

     * @param drawerView  View
     * *
     * @param slideOffset slideOffset
     */
    override fun drawerSlide(drawerView: View?, slideOffset: Float) {
    }

    /**
     * DrawerLayout已经完全打开了

     * @param drawerView View
     */
    override fun drawerOpened(drawerView: View) {
    }

    /**
     * DrawerLayout已经完全关闭了

     * @param drawerView View
     */
    override fun drawerClosed(drawerView: View) {
        val intent: Intent?
        when (menuItemId) {
            R.id.flow_layout -> {
                intent = Intent(this@HomeActivity, FlowLayoutActivity::class.java)
            }
            R.id.water_fall_layout ->{
                intent = Intent(this@HomeActivity, WaterFallLayoutActivity::class.java)
            }
            R.id.sidebar ->{
                //TODO 未完成
                intent = null
            }
            R.id.circle_progress_bar ->{
                intent = Intent(this@HomeActivity, CircleProgressBarActivity::class.java)
            }

            //其他
            R.id.google_map ->{
                intent = Intent(this@HomeActivity,GoogleMapActivity::class.java)
            }
            else ->{
                intent = null
            }
        }
        if (intent != null){
            startActivity(intent)
            menuItemId = 0
        }
    }

    /**
     * DrawerLayout的状态改变了

     * @param newState 新的状态
     */
    override fun drawerStateChanged(newState: Int) {
    }

    /**
     * 侧边栏的监听

     * @param menuItem 侧边栏item
     * *
     * @return true表示处理了监听事件
     */
    override fun navigationItemSelected(menuItem: MenuItem): Boolean {
        menuItemId = menuItem.itemId
        closeDrawer()
        return true
    }
}
