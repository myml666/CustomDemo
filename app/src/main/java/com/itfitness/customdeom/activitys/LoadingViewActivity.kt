package com.itfitness.customdeom.activitys

import android.animation.*
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v7.app.AppCompatActivity
import android.view.animation.*
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.RelativeLayout
import com.itfitness.customdeom.R
import com.itfitness.customdeom.widget.LoadingShapeView
import kotlinx.android.synthetic.main.activity_changecolor.*
import kotlinx.android.synthetic.main.activity_loadingview.*

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
class LoadingViewActivity : AppCompatActivity(){
    private var repeatCount = 1
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loadingview)
        startAnim()
    }

    private fun startAnim() {
        val animator = ValueAnimator.ofInt(0, 180, 0)
        animator.duration = 1000
        animator.repeatCount = Animation.INFINITE
        animator.interpolator = LinearInterpolator()//先慢后快
        animator.addUpdateListener {
            val v = it.animatedValue as Int
            val lp = lsv_loading.layoutParams as RelativeLayout.LayoutParams
            lp.bottomMargin = v
            lsv_loading.layoutParams = lp

            val shadowScale = (180f - v.toFloat())/180f
            v_shadow.scaleX = shadowScale
        }
        animator.addListener(object : AnimatorListenerAdapter(){
            override fun onAnimationRepeat(animation: Animator?) {
                when(repeatCount % 3){
                    0->lsv_loading.setShape(LoadingShapeView.LoadingShape.TRIANGLE)
                    1->lsv_loading.setShape(LoadingShapeView.LoadingShape.SQUARE)
                    2->lsv_loading.setShape(LoadingShapeView.LoadingShape.CIRCLE)
                }
                repeatCount ++
            }
        })
        animator.start()
    }
}