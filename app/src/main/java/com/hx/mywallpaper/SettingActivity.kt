package com.hx.mywallpaper

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class SettingActivity : AppCompatActivity() {
    var btnName = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)
        btnName = intent.getStringExtra("btnName")?:"默认名称"
        findViewById<Button>(R.id.btn_settings).setText(btnName)
        findViewById<Button>(R.id.btn_settings).setOnClickListener {
            startActivity(Intent(this,WallpaperActivity::class.java))
        }
    }
}