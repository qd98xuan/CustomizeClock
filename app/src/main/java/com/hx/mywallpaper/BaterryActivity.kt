package com.hx.mywallpaper

import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

class BaterryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_baterry)
        RegisterReciver.register(this, object : BaterryCallBack {
            override fun callback(status: BaterryStatus) {
                Toast.makeText(this@BaterryActivity, status.name, Toast.LENGTH_SHORT).show()
            }
        })
    }
}