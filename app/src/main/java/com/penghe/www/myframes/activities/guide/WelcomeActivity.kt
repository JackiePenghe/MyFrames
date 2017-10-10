package com.penghe.www.myframes.activities.guide

import android.app.Activity
import android.content.Intent
import android.os.Bundle

import com.penghe.www.myframes.activities.HomeActivity
import com.penghe.www.myframes.activities.MainActivity

import cn.almsound.www.baselibrary.BaseWelcomeActivity

class WelcomeActivity : BaseWelcomeActivity() {
    override fun doAfterAnimation() {
        //        Intent intent = new Intent(WelcomeActivity.this, ChartActivity.class);
        val intent = Intent(this@WelcomeActivity, HomeActivity::class.java)

        startActivity(intent)
        onBackPressed()
    }
}
