package com.hx.mywallpaper

import android.animation.ValueAnimator
import android.graphics.*
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.LinearLayout

class SinWaveActivity : AppCompatActivity() {
    var moveDis = 0f
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sin_wave)
        val bitmap = Bitmap.createBitmap(500, 500, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        val animValue = ValueAnimator.ofFloat(moveDis,300f)
        animValue.setDuration(5000)
        animValue.addUpdateListener {
            moveDis = it.getAnimatedValue() as Float
            val path = Path()
            path.moveTo(0f + moveDis, 200f)
            path.quadTo(100f + moveDis, 0f, 200f + moveDis, 200f)
            val paint = Paint().apply {
                color = Color.BLUE
                style = Paint.Style.STROKE
            }
            path.quadTo(300f + moveDis, 400f, 400f + moveDis, 200f)
            canvas.drawPath(path, paint)
            findViewById<ImageView>(R.id.img_cavas).setImageBitmap(bitmap)
            Log.d("flag",it.getAnimatedValue().toString())

        }
        animValue.start()
    }
}