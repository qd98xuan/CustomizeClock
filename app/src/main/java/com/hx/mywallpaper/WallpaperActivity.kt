package com.hx.mywallpaper

import android.app.WallpaperManager
import android.content.ComponentName
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

/**
* @author huangxuan
* @since 2022/9/6 09:30
* QQ: 1360643904
* 壁纸
*/
class WallpaperActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wallpaper)
        findViewById<Button>(R.id.btn_set_wallpaper).setOnClickListener {
            val intent = Intent()
            intent.action = WallpaperManager.ACTION_CHANGE_LIVE_WALLPAPER
            intent.putExtra(
                WallpaperManager.EXTRA_LIVE_WALLPAPER_COMPONENT,
                ComponentName(applicationContext.packageName,MyWallpaperService::class.java.name)
            )
            startActivity(intent)
        }
    }
}