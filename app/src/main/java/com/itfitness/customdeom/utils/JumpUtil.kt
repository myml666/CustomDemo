package com.itfitness.customdeom.utils

import android.content.Context
import android.content.Intent
import com.itfitness.customdeom.MainActivity
import com.itfitness.customdeom.activitys.*

/**
 *
 * @ProjectName:    CustomDemo
 * @Package:        com.itfitness.customdeom.utils
 * @ClassName:      JumpUtil
 * @Description:     java类作用描述
 * @Author:         作者名
 * @CreateDate:     2020/10/9 22:00
 * @UpdateUser:     更新者：itfitness
 * @UpdateDate:     2020/10/9 22:00
 * @UpdateRemark:   更新说明：
 * @Version:        1.0
 */
object JumpUtil {
    fun gotoTrianglePath(context: Context) {
        context.startActivity(Intent(context,TrianglePathActivity::class.java))
    }

    fun gotoScanAnim(context: Context) {
        context.startActivity(Intent(context,ScanAnimActivity::class.java))
    }

    fun cobWeb(context: Context) {
        context.startActivity(Intent(context,CobWebActivity::class.java))
    }

    fun changeColor(context: Context) {
        context.startActivity(Intent(context,ChangeColorActivity::class.java))
    }

    fun loadingView(context: Context) {
        context.startActivity(Intent(context,LoadingViewActivity::class.java))
    }

    fun animMenu(context: Context) {
        context.startActivity(Intent(context,AnimMenuActivity::class.java))
    }

}