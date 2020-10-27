package com.itfitness.customdeom.activitys

import android.animation.ValueAnimator
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import com.itfitness.customdeom.R
import kotlinx.android.synthetic.main.activity_animmenu.*
import java.lang.Math.cos
import java.lang.Math.sin
import java.util.ArrayList

/**
 *
 * @ProjectName:    CustomDemo
 * @Package:        com.itfitness.customdeom.activitys
 * @ClassName:      AnimMenuActivity
 * @Description:     java类作用描述 动画菜单
 * @Author:         作者名
 * @CreateDate:     2020/10/27 19:36
 * @UpdateUser:     更新者：itfitness
 * @UpdateDate:     2020/10/27 19:36
 * @UpdateRemark:   更新说明：
 * @Version:        1.0
 */
class AnimMenuActivity : AppCompatActivity(){
    private var isOpen = false //菜单是否打开
    private var menuArray = ArrayList<View>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animmenu)
        img_00.setOnClickListener {
            opreaMenu()
        }
        menuArray.add(img_01)
        menuArray.add(img_02)
        menuArray.add(img_03)
        menuArray.add(img_04)
        menuArray.add(img_05)
        menuArray.add(img_06)
        for ((index,view) in menuArray.withIndex()){
            view.setOnClickListener {
                Toast.makeText(this@AnimMenuActivity,"我是$index",Toast.LENGTH_SHORT).show()
            }
        }
    }

    /**
     * 操作菜单
     */
    private fun opreaMenu() {
        val openAnimator:ValueAnimator = if(isOpen){
            isOpen = false
            ValueAnimator.ofFloat(200f, 0f)
        }else{
            isOpen = true
            ValueAnimator.ofFloat(0f, 200f)
        }
        openAnimator.duration = 600
        openAnimator.addUpdateListener {
            val animVal = it.animatedValue as Float
            for ((index,view) in menuArray.withIndex()){
                //位移
                val degree = 360.0 / menuArray.size * index
                //计算位置
                val translateX = animVal * cos(Math.toRadians(degree))
                val translateY = animVal * sin(Math.toRadians(degree))
                view.translationX = translateX.toFloat()
                view.translationY = translateY.toFloat()

                //旋转
                view.rotation = 360f * it.animatedFraction

                //缩放
                if(animVal > 0){
                    view.scaleX = animVal / 200f
                    view.scaleY = animVal / 200f
                }

                //透明
                if(animVal > 0){
                    view.alpha = animVal / 200f
                }
            }
        }
        openAnimator.start()
    }
}