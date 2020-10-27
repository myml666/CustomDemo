package com.itfitness.customdeom

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.itfitness.customdeom.utils.JumpUtil
import kotlinx.android.synthetic.main.activity_main.*
import java.util.ArrayList

class MainActivity : AppCompatActivity() {
    private var mDemoAdapter: BaseQuickAdapter<String, BaseViewHolder>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rv_demos.layoutManager = LinearLayoutManager(this)
        initAdapter()
    }

    private fun initAdapter() {
        val demos = ArrayList<String>()
        demos.add("三角路径")
        demos.add("扫描动画")
        demos.add("蛛网控件")
        demos.add("改变颜色")
        demos.add("加载动画")
        demos.add("菜单动画")
        if (mDemoAdapter == null) {
            mDemoAdapter = object : BaseQuickAdapter<String, BaseViewHolder>(R.layout.item_demo, demos) {
                override fun convert(helper: BaseViewHolder, item: String) {
                    helper.setText(R.id.item_demo_tv, item)
                }
            }
            mDemoAdapter?.onItemClickListener = BaseQuickAdapter.OnItemClickListener { adapter, view, position ->
                when (position) {
                    0 ->
                        //商品详情图片列表
                        JumpUtil.gotoTrianglePath(this@MainActivity)
                    1 ->
                        //扫描动画
                        JumpUtil.gotoScanAnim(this@MainActivity)
                    2 ->
                        //蛛网控件
                        JumpUtil.cobWeb(this@MainActivity)
                    3 ->
                        //改变颜色
                        JumpUtil.changeColor(this@MainActivity)
                    4 ->
                        //加载动画
                        JumpUtil.loadingView(this@MainActivity)
                    5 ->
                        //菜单动画
                        JumpUtil.animMenu(this@MainActivity)
                }
            }
            rv_demos.adapter = mDemoAdapter
        } else {
            mDemoAdapter?.setNewData(demos)
            mDemoAdapter?.notifyDataSetChanged()
        }
    }
}
