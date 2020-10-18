package com.itfitness.customdeom.activitys

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.animation.*
import com.itfitness.customdeom.R
import kotlinx.android.synthetic.main.activity_scananim.*

/**
 *
 * @ProjectName:    CustomDemo
 * @Package:        com.itfitness.customdeom.activitys
 * @ClassName:      ScanAnimActivity
 * @Description:     java类作用描述
 * @Author:         作者名
 * @CreateDate:     2020/10/14 20:49
 * @UpdateUser:     更新者：itfitness
 * @UpdateDate:     2020/10/14 20:49
 * @UpdateRemark:   更新说明：
 * @Version:        1.0
 */
class ScanAnimActivity:AppCompatActivity(){
    private val animArray = ArrayList<AnimationSet>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scananim)
        initAnim()
        img_smile.setOnClickListener {
            startAnim()
        }
    }

    private fun startAnim() {
        img_scan_one.startAnimation(animArray[0])
        img_scan_two.startAnimation(animArray[1])
        img_scan_three.startAnimation(animArray[2])
    }

    private fun initAnim() {
        for(i in 0..2){
            val offsetTime = i * 500L
            val animSet = AnimationSet(false)
            val scalAnim = ScaleAnimation(1f,3f,1f,3f,Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f)
            val alphaAnim = AlphaAnimation(1f,0f)
            scalAnim.repeatCount = Animation.INFINITE
            alphaAnim.repeatCount = Animation.INFINITE
            animSet.addAnimation(scalAnim)
            animSet.addAnimation(alphaAnim)
            animSet.interpolator = LinearInterpolator()
            animSet.duration = 3000
            animSet.startOffset = offsetTime
            animArray.add(animSet)
        }
    }
}