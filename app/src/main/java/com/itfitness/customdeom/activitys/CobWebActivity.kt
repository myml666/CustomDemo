package com.itfitness.customdeom.activitys

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.itfitness.customdeom.R
import com.itfitness.customdeom.bean.CobWebDataBean
import kotlinx.android.synthetic.main.activity_cobweb.*

/**
 *
 * @ProjectName:    CustomDemo
 * @Package:        com.itfitness.customdeom.activitys
 * @ClassName:      TrianglePathActivity
 * @Description:     java类作用描述 蛛网
 * @Author:         作者名
 * @CreateDate:     2020/10/9 22:01
 * @UpdateUser:     更新者：itfitness
 * @UpdateDate:     2020/10/9 22:01
 * @UpdateRemark:   更新说明：
 * @Version:        1.0
 */
class CobWebActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cobweb)
        cobweb_view.setDatas(getDatas())
    }

    private fun getDatas():ArrayList<CobWebDataBean>{
        val datas = ArrayList<CobWebDataBean>()
        for (i in 5..26 step 3){
            val data = CobWebDataBean("数据$i" , i.toFloat())
            datas.add(data)
        }
        return datas
    }
}