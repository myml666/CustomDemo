package com.itfitness.customdeom.widget

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.itfitness.customdeom.bean.CobWebDataBean
import kotlin.math.cos
import kotlin.math.sin

/**
 *
 * @ProjectName:    CustomDemo
 * @Package:        com.itfitness.customdeom.widget
 * @ClassName:      TrianglePathView
 * @Description:     java类作用描述 蛛网控件
 * @Author:         作者名
 * @CreateDate:     2020/10/9 22:07
 * @UpdateUser:     更新者：itfitness
 * @UpdateDate:     2020/10/9 22:07
 * @UpdateRemark:   更新说明：
 * @Version:        1.0
 */
class CobwebView:View {
    private var paint:Paint = Paint()
    private var pointConPath = Path() //值点的连线路径
    private var linePaths:ArrayList<Path> = ArrayList()//正多边形的路径集合
    private var linePoints:ArrayList<PointF> = ArrayList()//顶点的集合
    private var valuePoints:ArrayList<PointF> = ArrayList()//数据点的集合
    private var valDatas = ArrayList<CobWebDataBean>()//点的数据
    constructor(context: Context) : this(context,null)
    constructor(context: Context, attribute: AttributeSet?) : this(context,attribute,0)
    constructor(context: Context,attribute: AttributeSet?,style: Int) : super(context,attribute,style){
        init()
    }

    private fun init() {
        setLayerType(LAYER_TYPE_SOFTWARE,null)
        paint.color = Color.RED
        paint.strokeWidth = 2f
        paint.style = Paint.Style.STROKE
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        calc()
    }
    /**
     * 设置数据
     */
    fun setDatas(datas:ArrayList<CobWebDataBean>){
        valDatas = datas
        calc()
        postInvalidate()
    }

    /**
     * 计算
     */
    private fun calc(){
        val maxRadius = width / 2
        var maxVal = 0f //最大值
        //计算出所有值中的最大点
        for(cobWebData in valDatas){
            if(maxVal < cobWebData.value){
                maxVal = cobWebData.value
            }
        }
        //有几个数据画几个正多边形
        for(i in 0..valDatas.size){
            val radius = maxRadius / valDatas.size * i //计算每个正多边形的顶点到中心点的距离
            val path = Path()
            for((index,cobWebData) in valDatas.withIndex()){
                val degree = 360.0 / valDatas.size * index //计算出当前旋转的角度
                val x =
                    (radius * cos(Math.toRadians(degree))).toFloat()//计算出当前正多边形当前顶点的X
                val y =
                    (radius * sin(Math.toRadians(degree))).toFloat()//计算出当前正多边形当前顶点的Y

                //当前正多边形的顶点连线路径
                if(index == 0){
                    path.moveTo(x,y)
                }else{
                    path.lineTo(x, y)
                }

                if(i == valDatas.size){
                    //记录最外层正多边形的顶点
                    linePoints.add(PointF(x,y))


                    //计算当前值与最大值的比率
                    val rate = cobWebData.value / maxVal
                    //根据比率计算出各个点所在的位置
                    val valX =
                        (radius * rate * cos(Math.toRadians(degree))).toFloat() //数据所在的X位置
                    val valY =
                        (radius * rate * sin(Math.toRadians(degree))).toFloat() //数据所在的Y位置
                    //将计算出来的值点保存下来
                    valuePoints.add(PointF(valX,valY))


                    //各个值点的连线路径
                    if(index == 0){
                        pointConPath.moveTo(valX,valY)
                    }else{
                        pointConPath.lineTo(valX,valY)
                    }
                }
            }
            pointConPath.close()
            path.close()
            linePaths.add(path)
        }
    }
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        //将画布的中心点移动至（0，0）点
        canvas?.translate((width/2).toFloat(), (height/2).toFloat())

        paint.style = Paint.Style.STROKE
        paint.color = Color.RED
        //画多边形
        for(path in linePaths){
            canvas?.drawPath(path,paint)
        }

        //画顶点到中心点的线
        for(point in linePoints){
            canvas?.drawLine(0f,0f,point.x,point.y,paint)
        }

        //画值所在的点
        paint.style = Paint.Style.FILL
        for(point in valuePoints){
            canvas?.drawCircle(point.x,point.y,6f,paint)
        }

        //画所有值点的连线图形
        paint.color = Color.parseColor("#30ff0000")
        canvas?.drawPath(pointConPath,paint)
    }
}