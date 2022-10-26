package com.hx.mywallpaper

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.os.Vibrator
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View

/**
 * @author huangxuan
 * @since 2022/10/26 14:23
 * QQ: 1360643904
 * 自定义摇杆
 */
class RockerView : View {
    var boxWidth = 0
    var boxHeight = 0
    val defaultSize = 400
    var rockerX = 0f
    var rockerY = 0f

    var moveX = 0f
    var moveY = 0f
    lateinit var vibratorService:Vibrator

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    @SuppressLint("ClickableViewAccessibility")
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ){
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val widthSize = MeasureSpec.getSize(widthMeasureSpec)
        val heightSize = MeasureSpec.getSize(heightMeasureSpec)
        val widthMode = MeasureSpec.getMode(widthMeasureSpec)
        val heightMode = MeasureSpec.getMode(heightMeasureSpec)
        if (widthMode == MeasureSpec.EXACTLY && heightMode == MeasureSpec.EXACTLY) {
            boxWidth = widthSize
            boxHeight = heightSize
        } else if (widthMode == MeasureSpec.EXACTLY && heightMode == MeasureSpec.AT_MOST) {
            boxWidth = widthSize
            boxHeight = defaultSize
        } else if (widthMode == MeasureSpec.AT_MOST && heightMode == MeasureSpec.EXACTLY) {
            boxHeight = heightSize
            boxWidth = defaultSize
        } else if (widthMode == MeasureSpec.AT_MOST && heightMode == MeasureSpec.AT_MOST) {
            boxWidth = defaultSize
            boxHeight = defaultSize
        } else {
            boxWidth = defaultSize
            boxHeight = defaultSize
        }
        rockerY = widthSize.toFloat()/2
        rockerX = widthSize.toFloat()/2
        vibratorService =
            context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        super.onMeasure(
            MeasureSpec.makeMeasureSpec(widthSize, widthMode),
            MeasureSpec.makeMeasureSpec(heightSize, heightMode)
        )
    }

    @SuppressLint("ClickableViewAccessibility", "MissingPermission")
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        val boxRadius = boxWidth.toFloat() / 2
        val boxPaint = Paint().apply {
            color = Color.GRAY
            strokeWidth = 3f
            style = Paint.Style.STROKE
        }
        canvas?.drawCircle(boxRadius, boxRadius, boxRadius, boxPaint)
        val rockerRadius = boxWidth.toFloat()/8
        val radioPaint = Paint().apply {
            color = Color.GRAY
            style = Paint.Style.FILL
        }
        canvas?.drawCircle(rockerX,rockerY,rockerRadius,radioPaint)

        //设置监听
        setOnTouchListener { v, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN,MotionEvent.ACTION_MOVE -> {
                    val radius =Math.sqrt(((Math.abs(boxRadius-event.x)*Math.abs(boxRadius-event.x))+(Math.abs(boxRadius-event.y)*Math.abs(boxRadius-event.y))).toDouble())
                    if (radius<boxRadius-rockerRadius){
                        rockerX = event.x
                        rockerY = event.y
                        Log.d("move","moveX->"+rockerX)
                        Log.d("move","moveY->"+rockerY)
                        vibratorService.cancel()
                    }else{
                        vibratorService.vibrate(1000)
                    }
                    invalidate()
                }
                MotionEvent.ACTION_UP -> {
                    rockerX = boxRadius
                    rockerY = boxRadius
                    vibratorService.cancel()
                }
            }
            true
        }
    }

}