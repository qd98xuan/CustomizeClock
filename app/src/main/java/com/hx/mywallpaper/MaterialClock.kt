package com.hx.mywallpaper

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.os.Build
import android.util.AttributeSet
import android.view.View
import androidx.annotation.RequiresApi
import java.util.*

/**
* @author huangxuan
* @since 2022/10/11 14:10
* QQ: 1360643904
* 模拟时钟控件
*/
class MaterialClock : View {
    constructor(context: Context?) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        secondTimer
    }

    //秒针角度
    var secondPointDegree = 0f

    //分针角度
    var minutePointDegree = 0f

    //时针角度
    var hourPointDegree = 0f

    //秒针基数
    val bedSecond = 1000L

    //日历时钟
    var calendar = Calendar.getInstance()


    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val widthMode = MeasureSpec.getMode(widthMeasureSpec)
        val heightMode = MeasureSpec.getMode(heightMeasureSpec)
        val widthSize = MeasureSpec.getSize(widthMeasureSpec)
        val heightSize = MeasureSpec.getSize(heightMeasureSpec)
        var width = 0
        var height = 0
        if (widthMode == MeasureSpec.AT_MOST && heightMode == MeasureSpec.AT_MOST) {
            width = 100
            height = 100
        } else if (widthMode == MeasureSpec.AT_MOST && heightMode == MeasureSpec.EXACTLY) {
            width = widthSize
        } else if (widthMode == MeasureSpec.EXACTLY && heightMode == MeasureSpec.AT_MOST) {
            height = heightSize
        } else if (widthMode == MeasureSpec.EXACTLY && widthMode == MeasureSpec.EXACTLY) {
            width = widthSize
            height = heightSize
        } else {
            width = widthSize
            height = heightSize
        }
        setMeasuredDimension(width, height)
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)

    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        //字体大小
        val textSize = 50f
        //背景笔刷
        val paint = Paint().apply {
            setColor(Color.WHITE)
        }
        //长刻线笔刷
        val paintLine = Paint().apply {
            setColor(Color.BLACK)
            strokeWidth = 10f
            this.textSize = textSize
        }
        //短刻线笔刷
        val paintLightLine = Paint().apply {
            setColor(Color.GRAY)
            strokeWidth = 5f
            this.textSize = textSize
        }
        //秒针笔刷
        val paintSecond = Paint().apply {
            setColor(context.getColor(R.color.orange))
            strokeWidth = 3f
        }
        //分针笔刷
        val paintMinute = Paint().apply {
            setColor(Color.BLACK)
            strokeWidth = 16f
        }
        //时针笔刷
        val paintHour = Paint().apply {
            setColor(Color.BLACK)
            strokeWidth = 16f
        }
        canvas?.drawCircle(width.toFloat() / 2, height.toFloat() / 2, width.toFloat() / 2, paint)

        //绘制小刻度
        for (i in 1..60) {
            canvas?.rotate(6f, width.toFloat() / 2, width.toFloat() / 2)
            canvas?.drawLine(width.toFloat() / 2, 5f, width.toFloat() / 2, 20f, paintLightLine)
        }

        //绘制大刻度
        for (i in 1..12) {
            canvas?.rotate(30f, width.toFloat() / 2, height.toFloat() / 2)
            canvas?.drawLine(width.toFloat() / 2, 5f, width.toFloat() / 2, 40f, paintLine)
            canvas?.drawText(
                i.toString(), width.toFloat() / 2 - (if (i < 10) {
                    textSize / 2 - 5
                } else {
                    textSize / 2
                }), 60f + textSize / 2, paintLine
            )
            //绘制中心原点
            canvas?.drawCircle(width.toFloat() / 2, height.toFloat() / 2, 10f, paintLine)
        }

        val hour = calendar.get(Calendar.HOUR)
        val minute = calendar.get(Calendar.MINUTE)
        val second = calendar.get(Calendar.SECOND)

        //绘制时针
        hourPointDegree = hour * 30f + minute * 0.5f
        canvas?.rotate(hourPointDegree, width.toFloat() / 2, height.toFloat() / 2)
        canvas?.drawLine(
            width.toFloat() / 2,
            (width.toFloat() / 2) / 2.2f,
            width.toFloat() / 2,
            width.toFloat() / 2,
            paintHour
        )
        canvas?.rotate(360 - hourPointDegree, width.toFloat() / 2, height.toFloat() / 2)

        //绘制分针
        minutePointDegree = minute * 6f + second * 0.1f
        canvas?.rotate(minutePointDegree, width.toFloat() / 2, height.toFloat() / 2)
        canvas?.drawLine(
            width.toFloat() / 2,
            (width.toFloat() / 2) / 4,
            width.toFloat() / 2,
            width.toFloat() / 2,
            paintMinute
        )
        canvas?.rotate(360 - minutePointDegree, width.toFloat() / 2, height.toFloat() / 2)


        //绘制秒针
        secondPointDegree = second * 6f
        canvas?.rotate(secondPointDegree, width.toFloat() / 2, height.toFloat() / 2)
        canvas?.drawLine(
            width.toFloat() / 2,
            5f,
            width.toFloat() / 2,
            width.toFloat() / 2,
            paintSecond
        )
        canvas?.rotate(360 - secondPointDegree, width.toFloat() / 2, height.toFloat() / 2)

    }

    //秒针定时器
    val secondTimer = Timer().schedule(object : TimerTask() {
        override fun run() {
            calendar = Calendar.getInstance()
            invalidate()
        }
    }, 0, bedSecond)
}