package com.penghe.www.myframes.others.leson_3

import android.view.Menu
import android.view.MenuItem
import cn.almsound.www.baselibrary.BaseAppcompatActivity
import com.penghe.www.circleprogressbarlibrary.CircleProgressBar

import com.penghe.www.myframes.R

class CircleProgressBarActivity : BaseAppcompatActivity() {

    private var circleProgressBar: CircleProgressBar? = null

    override fun titleBackClicked() {
        onBackPressed()
    }

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
        return R.layout.activity_circle_progress_bar
    }

    /**
     * 在设置布局之后，进行其他操作之前，所需要初始化的数据
     */
    override fun doBeforeInitOthers() {
        setTitleText(R.string.circle_progress_bar)
    }

    /**
     * 初始化布局控件
     */
    override fun initViews() {
        circleProgressBar = findViewById(R.id.circle_progress_bar) as CircleProgressBar
    }

    private var progress:Double? = 0.0

    private val delay: Long = 10

    private var refresh: Boolean? = true

    /**
     * 初始化控件数据
     */
    override fun initViewData() {

        val thread = Thread{
            ->

            Thread.sleep(2000)
            while (refresh!!){
                circleProgressBar!!.setProgress(progress!!)
                Thread.sleep(delay)
                progress= progress!! + 0.2
                if (progress!! > circleProgressBar!!.maxProgress + 1){
                    refresh = false
                }
            }
        }

        thread.start()
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
     * 在最后进行的操作
     */
    override fun doAfterAll() {
    }

    /**
     * 设置菜单

     * @param menu 菜单
     * *
     * @return 只是重写 public boolean onCreateOptionsMenu(Menu menu)
     */
    override fun createOptionsMenu(menu: Menu?): Boolean {
        return false
    }

    /**
     * 设置菜单监听

     * @param item 菜单的item
     * *
     * @return true表示处理了监听事件
     */
    override fun optionsItemSelected(item: MenuItem?): Boolean {
        return false
    }

    override fun onBackPressed() {
        super.onBackPressed()
        if(refresh!!){
            refresh = false
        }
    }
}
