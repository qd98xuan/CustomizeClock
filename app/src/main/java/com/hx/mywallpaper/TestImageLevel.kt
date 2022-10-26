package com.hx.mywallpaper

import android.media.Image
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class TestImageLevel:AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.activity_test_image_level)
        findViewById<ImageView>(R.id.img_baterry).setImageLevel(3)
    }
}