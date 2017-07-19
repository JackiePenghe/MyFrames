package com.penghe.www.myframes.others.leson_3

import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.Toast
import cn.almsound.www.baselibrary.BaseAppcompatActivity
import com.penghe.www.myframes.R
import com.penghe.www.waterfalllayoutlibrary.WaterfallLayout
import kotlinx.android.synthetic.main.activity_water_fall_layout.*
import java.util.*


class WaterFallLayoutActivity : BaseAppcompatActivity() {

    companion object {
        private const val IMG_COUNT = 5
    }

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
        return R.layout.activity_water_fall_layout
    }

    /**
     * 在设置布局之后，进行其他操作之前，所需要初始化的数据
     */
    override fun doBeforeInitOthers() {
        setTitleText(R.string.water_fall_layout)
    }

    /**
     * 初始化布局控件
     */
    override fun initViews() {
       /* val waterfallLayout = findViewById(R.id.waterfallLayout) as WaterfallLayout
        findViewById(R.id.add_btn).setOnClickListener { addView(waterfallLayout) }*/

        add_btn.setOnClickListener { addView(waterfallLayout as WaterfallLayout) }

    }

   private fun addView(waterfallLayout: WaterfallLayout) {
        val random = Random()
        val num = Math.abs(random.nextInt())
        val layoutParams = WaterfallLayout.WaterfallLayoutParams(
                WaterfallLayout.WaterfallLayoutParams.MATCH_PARENT,
                WaterfallLayout.WaterfallLayoutParams.MATCH_PARENT)
        /*WaterfallLayout.LayoutParams layoutParams = new WaterfallLayout.LayoutParams(
                100,
                100);*/
        val imageView = ImageView(this)
        if (num % IMG_COUNT == 0) {
            imageView.setImageResource(R.drawable.pic_1)
        } else if (num % IMG_COUNT == 1) {
            imageView.setImageResource(R.drawable.pic_2)
        } else if (num % IMG_COUNT == 2) {
            imageView.setImageResource(R.drawable.pic_3)
        } else if (num % IMG_COUNT == 3) {
            imageView.setImageResource(R.drawable.pic_4)
        } else if (num % IMG_COUNT == 4) {
            imageView.setImageResource(R.drawable.pic_5)
        }
        imageView.scaleType = ImageView.ScaleType.CENTER_CROP

        waterfallLayout.addView(imageView, layoutParams)

        waterfallLayout.setOnItemClickListener {
            view, index ->
            Toast.makeText(this@WaterFallLayoutActivity, "item=" + index, Toast.LENGTH_SHORT).show()
        }
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
