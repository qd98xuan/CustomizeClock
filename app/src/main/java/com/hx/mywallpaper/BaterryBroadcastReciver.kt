package com.hx.mywallpaper

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.util.Log
import android.widget.Toast

object RegisterReciver {
    var baterryCallBack: BaterryCallBack? = null
    fun register(context: Context, baterryCallBack: BaterryCallBack) {
        this.baterryCallBack = baterryCallBack
        var baterryBroadcastReciver = BaterryBroadcastReciver()
        var filter = IntentFilter()
        filter.addAction(Intent.ACTION_POWER_CONNECTED)
        filter.addAction(Intent.ACTION_POWER_DISCONNECTED)
        context.registerReceiver(baterryBroadcastReciver, filter)
    }

    class BaterryBroadcastReciver : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            val action = intent?.action
            when (action) {
                Intent.ACTION_POWER_CONNECTED -> {
                    Log.d("log-power->", "电源已链接")
                    baterryCallBack?.callback(BaterryStatus.CONNECT)
                }
                Intent.ACTION_POWER_DISCONNECTED -> {
                    Log.d("log-power->", "电源已断开")
                    baterryCallBack?.callback(BaterryStatus.DISCONNECT)
                }
            }
        }
    }
}

interface BaterryCallBack {
    fun callback(status: BaterryStatus)
}

enum class BaterryStatus {
    CONNECT, DISCONNECT
}