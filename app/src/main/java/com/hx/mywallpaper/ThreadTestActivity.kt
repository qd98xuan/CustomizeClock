package com.hx.mywallpaper

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.loader.content.AsyncTaskLoader
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit

class ThreadTestActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_thread_test)
        val thread = Thread(Runnable {

        })
        thread.start()

    }
}