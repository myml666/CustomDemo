package com.itfitness.customdeom.widget

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import android.view.animation.AccelerateInterpolator
import android.view.animation.Animation
import android.view.animation.LinearInterpolator
import android.widget.FrameLayout
import kotlinx.android.synthetic.main.activity_loadingview.*
import kotlin.math.cos
import kotlin.math.sin

/**
 *
 * @ProjectName:    CustomDemo
 * @Package:        com.itfitness.customdeom.widget
 * @ClassName:      LoadingShapeView
 * @Description:     java类作用描述 加载的形状view
 * @Author:         作者名
 * @CreateDate:     2020/10/19 21:16
 * @UpdateUser:     更新者：itfitness
 * @UpdateDate:     2020/10/19 21:16
 * @UpdateRemark:   更新说明：
 * @Version:        1.0
 */
class LoadingShapeView : View {
    private var paint: Paint = Paint()
    private var pathTriangle = Path()//三角形路径
    private var pathCircle = Path()//圆形路径
    private var pathSquare = Path()//正方形路径
    private var shape:LoadingShape = LoadingShape.TRIANGLE//默认三角形
    constructor(context: Context) : this(context,null)
    constructor(context: Context, attribute: AttributeSet?) : this(context,attribute,0)
    constructor(context: Context, attribute: AttributeSet?, style: Int) : super(context,attribute,style){
        init()
    }
    private fun init() {
        paint.color = Color.RED
        paint.strokeWidth = 3f
        paint.style = Paint.Style.FILL
        startAnim()
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        initTrianglePath()
        initSquarePath()
        initCirclePath()
    }

    private fun startAnim() {
        val animator = ValueAnimator.ofInt(0, 10)
        animator.duration = 2000
        animator.repeatCount = Animation.INFINITE
        animator.interpolator = LinearInterpolator()//匀速
        animator.addUpdateListener {
            rotation = it.animatedFraction  * 360f
        }
        animator.start()
    }

    /**
     * 设置形状
     */
    fun setShape(loadingShape: LoadingShape){
        shape = loadingShape
        postInvalidate()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.translate((width/2).toFloat(), (height/2).toFloat())
        when(shape){
            LoadingShape.TRIANGLE->{
                //三角形
                paint.color = Color.RED
                canvas?.drawPath(pathTriangle,paint)
            }
            LoadingShape.CIRCLE->{
                //圆形
                paint.color = Color.GREEN
                canvas?.drawPath(pathCircle,paint)
            }
            LoadingShape.SQUARE->{
                //正方形
                paint.color = Color.BLUE
                canvas?.drawPath(pathSquare,paint)
            }
        }
    }

    /**
     * 画正方形
     */
    private fun initSquarePath() {
        pathSquare.reset()
        val left = -width / 3f
        val top = -width / 3f
        val right = width / 3f
        val bottom = width / 3f
        pathSquare.addRect(RectF(left,top,right,bottom),Path.Direction.CW)
    }

    /**
     * 画圆形
     */
    private fun initCirclePath() {
        pathCircle.reset()
        pathCircle.addCircle(0f,0f, (width/2).toFloat(),Path.Direction.CW)
    }

    /**
     * 画三角形
     */
    private fun initTrianglePath() {
        pathTriangle.reset()
        val radius = width / 2
        for(i in 0..2){
            val degree = 360.0 / 3 * i
            val x =
                (radius * cos(Math.toRadians(degree))).toFloat()
            val y =
                (radius * sin(Math.toRadians(degree))).toFloat()
            if(i == 0){
                pathTriangle.moveTo(x,y)
            }else{
                pathTriangle.lineTo(x,y)
            }
        }
        pathTriangle.close()
    }

    enum class LoadingShape{
        TRIANGLE,//三角形
        CIRCLE,//圆形
        SQUARE//正方形
    }
}