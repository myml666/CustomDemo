package com.itfitness.customdeom.widget

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import kotlin.math.tan

/**
 *
 * @ProjectName:    CustomDemo
 * @Package:        com.itfitness.customdeom.widget
 * @ClassName:      TrianglePathView
 * @Description:     java类作用描述
 * @Author:         作者名
 * @CreateDate:     2020/10/9 22:07
 * @UpdateUser:     更新者：itfitness
 * @UpdateDate:     2020/10/9 22:07
 * @UpdateRemark:   更新说明：
 * @Version:        1.0
 */
class TrianglePathView:View {
    private var paint:Paint = Paint()
    private var path:Path = Path()
    private var num = 0
    private var colors:ArrayList<Int>? = null //颜色
    constructor(context: Context) : this(context,null)
    constructor(context: Context, attribute: AttributeSet?) : this(context,attribute,0)
    constructor(context: Context,attribute: AttributeSet?,style: Int) : super(context,attribute,style){
        init()
    }

    private fun init() {
        paint.color = Color.RED
        paint.strokeWidth = 3f
        paint.style = Paint.Style.STROKE
        initColors()
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        val triangleEdge = w / 4 * 3 //三角形边长

        val firstPointX = -triangleEdge/2
        val firstPointY = tan(Math.PI/6) * triangleEdge/2

        val secondPointX = triangleEdge/2
        val secondPointY = tan(Math.PI/6) * triangleEdge/2

        val thirdPointX = 0
        val thirdPointY = -2 * tan(Math.PI/6) * triangleEdge/2

        path.moveTo(firstPointX.toFloat(), firstPointY.toFloat())
        path.lineTo(secondPointX.toFloat(), secondPointY.toFloat())
        path.lineTo(thirdPointX.toFloat(), thirdPointY.toFloat())
        path.close()

        startAnim()
    }

    private fun startAnim() {
        val anim = ValueAnimator.ofInt(0, 99)
        anim.duration = 10000
        anim.addUpdateListener {
            num = it.animatedValue as Int
            postInvalidate()
        }
        anim.addListener(object : AnimatorListenerAdapter(){
            override fun onAnimationEnd(animation: Animator?) {
                super.onAnimationEnd(animation)
                startRotateAnim()
            }
        })
        anim.start()
    }
    private fun startRotateAnim(){
        val anim = ValueAnimator.ofFloat(0f, 3600f)
        anim.duration = 20000
        anim.addUpdateListener {
            val angle = it.animatedValue as Float
            rotation = angle
        }
        anim.start()
    }
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.translate((width/2).toFloat(), (height/2).toFloat())
        for (i in 0..num){
            val angle = i * 3.6f//每次旋转的角度
            paint.color = colors!![i]
            canvas?.rotate(angle)
            canvas?.drawPath(path,paint)
        }
    }

    private fun initColors(){
        colors = ArrayList()
        for(i in 0..99){
            val r = (0..255).random()
            val g = (0..255).random()
            val b = (0..255).random()
            val rgb = Color.rgb(r, g, b)
            colors!!.add(rgb)
        }
    }
}