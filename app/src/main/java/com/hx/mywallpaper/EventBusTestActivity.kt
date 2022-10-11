package com.hx.mywallpaper

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Message
import android.util.Log
import android.widget.TextView
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class EventBusTestActivity : AppCompatActivity() {
    var number = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event_bus_test)
        findViewById<TextView>(R.id.tv_text).setOnClickListener {
            val messageEvent = MessageEvent("第${number}次篡改页面信息")
            EventBus.getDefault().post(messageEvent)
            number++
        }
    }
}

class MessageEvent(message: String){
    var message  = message
}