package com.penghe.www.myframes.activities.guide

import android.app.Activity
import android.content.Intent
import android.os.Bundle

import cn.almsound.www.baselibrary.BaseSplashActivity

class SplashActivity : BaseSplashActivity() {
    override fun onCreate() {
        val intent = Intent(this@SplashActivity, WelcomeActivity::class.java)
        startActivity(intent)
        onBackPressed()
    }
}
