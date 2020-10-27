package com.itfitness.customdeom.activitys

import android.animation.Animator
import android.animation.ValueAnimator
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v7.app.AppCompatActivity
import android.view.animation.Animation
import com.itfitness.customdeom.R
import kotlinx.android.synthetic.main.activity_changecolor.*

/**
 *
 * @ProjectName:    CustomDemo
 * @Package:        com.itfitness.customdeom.activitys
 * @ClassName:      ChangeColorActivity
 * @Description:     java类作用描述 改变颜色动画
 * @Author:         作者名
 * @CreateDate:     2020/10/19 21:00
 * @UpdateUser:     更新者：itfitness
 * @UpdateDate:     2020/10/19 21:00
 * @UpdateRemark:   更新说明：
 * @Version:        1.0
 */
class ChangeColorActivity : AppCompatActivity(){
    private var changeColorAnim:ValueAnimator? = null
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_changecolor)
        v_changecolor.setOnClickListener {
            startAnim()
        }
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private fun startAnim() {
        changeColorAnim = ValueAnimator.ofArgb(Color.RED, Color.GREEN)
        changeColorAnim?.duration = 3000
        changeColorAnim?.repeatCount = Animation.INFINITE
        changeColorAnim?.repeatMode = Animation.REVERSE
        changeColorAnim?.addUpdateListener {
            val color:Int = it.animatedValue as Int
            v_changecolor.setBackgroundColor(color)
        }
        changeColorAnim?.start()
    }

    override fun onDestroy() {
        super.onDestroy()
        changeColorAnim?.cancel()
    }
}