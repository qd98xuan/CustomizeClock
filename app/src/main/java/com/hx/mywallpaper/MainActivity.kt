package com.hx.mywallpaper

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    var llBody: LinearLayout? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        llBody = findViewById(R.id.ll_base)
        findViewById<Button>(R.id.bt_generate).setOnClickListener {
            llBody = findViewById(R.id.ll_base)
            val sNumber = findViewById<EditText>(R.id.et_num).text.toString()
            if (sNumber.isNotEmpty()) {
                val iNum = sNumber.toInt()
                for (i in 0..iNum) {
                    val itemView = LayoutInflater.from(this).inflate(R.layout.view_item, null)
                    val llBody = itemView.findViewById<LinearLayout>(R.id.ll_body)
                    if (i == iNum){
                        llBody.setBackgroundResource(R.mipmap.message)
                    }
                    this.llBody?.addView(itemView)
                    this.llBody = llBody
                }
            } else {
                Toast.makeText(this, "请输入数字", Toast.LENGTH_SHORT).show()
            }
        }
    }
}