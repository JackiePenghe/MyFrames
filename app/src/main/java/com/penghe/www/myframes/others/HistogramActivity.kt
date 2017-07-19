package com.penghe.www.myframes.others

import android.view.Menu
import android.view.MenuItem
import android.widget.SeekBar
import cn.almsound.www.baselibrary.BaseAppcompatActivity
import cn.almsound.www.histogramviewlibrary.HistogramView
import com.penghe.www.myframes.R


class HistogramActivity : BaseAppcompatActivity() {
    private var seekBar: SeekBar? = null

    private var histogramView: HistogramView? = null

    private val lastData0 = intArrayOf(70000, 10000, 20000, 40000, 50000, 80000, 40000)
    private val thisData0 = intArrayOf(40000, 10000, 10000, 20000, 30000, 50000, 30000)

    private val lastData1 = intArrayOf(70000, 60000, 60000, 40000, 50000, 80000, 80000)
    private val thisData1 = intArrayOf(40000, 30000, 30000, 20000, 30000, 50000, 30000)

    private val lastData2 = intArrayOf(70000, 50000, 70000, 80000, 80000, 80000, 70000)
    private val thisData2 = intArrayOf(40000, 10000, 40000, 40000, 30000, 40000, 10000)

    private val lastData3 = intArrayOf(70000, 80000, 70000, 40000, 50000, 80000, 40000)
    private val thisData3 = intArrayOf(10000, 10000, 10000, 20000, 30000, 10000, 30000)

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
        return R.layout.activity_histogram
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
        seekBar = findViewById(R.id.seekBar) as SeekBar
        histogramView = findViewById(R.id.histogram_view) as HistogramView
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

    private val onSeekBarChangeListener: SeekBar.OnSeekBarChangeListener = object : SeekBar.OnSeekBarChangeListener {
        override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {

            val cc = progress / 4

            when (cc) {
                0 -> {
                    histogramView!!.updateThisData(lastData0)
                    histogramView!!.updateLastData(thisData0)
                }
                1 -> {
                    histogramView!!.updateThisData(lastData1)
                    histogramView!!.updateLastData(thisData1)
                }
                2 -> {
                    histogramView!!.updateThisData(lastData2)
                    histogramView!!.updateLastData(thisData2)
                }
                3 -> {
                    histogramView!!.updateThisData(lastData3)
                    histogramView!!.updateLastData(thisData3)
                }

                else -> {
                }
            }


        }

        override fun onStartTrackingTouch(seekBar: SeekBar) {
        }

        override fun onStopTrackingTouch(seekBar: SeekBar) {
        }

    }

    /**
     * 初始化事件
     */
    override fun initEvents() {
        seekBar!!.setOnSeekBarChangeListener(onSeekBarChangeListener)
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
}
