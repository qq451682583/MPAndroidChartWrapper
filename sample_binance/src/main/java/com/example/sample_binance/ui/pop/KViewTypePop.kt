package com.example.sample_binance.ui.pop

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.sample_binance.R
import com.example.sample_binance.model.kview.BinKType
import com.example.sample_binance.repository.sp.BinSpHelper
import com.matt.libwrapper.ui.base.BasePopWindow
import com.matt.mpwrapper.ktx.getColor
import kotlinx.android.synthetic.main.bin_item_pop_kview_type.view.*
import kotlinx.android.synthetic.main.bin_pop_kview_type.view.*

/**
 *
 * Author : wgyscsf@163.com
 * Github : https://github.com/scsfwgy
 * Date   : 2020/7/19 11:13 AM
 * 描 述 ：
 *
 **/
class KViewTypePop(
    context: Context,
    var mCurrKType: BinKType,
    val kTypeListener: KTypeListener
) :
    BasePopWindow(context) {
    override fun onCreateContentView(): View {
        return createPopupById(R.layout.bin_pop_kview_type)
    }

    val mBaseQuickAdapter by lazy {
        object :
            BaseQuickAdapter<BinKType, BaseViewHolder>(R.layout.bin_item_pop_kview_type, null) {
            override fun convert(holder: BaseViewHolder, item: BinKType) {
                holder.itemView.run {
                    bipkt_tv_txt.text = item.label
                    if (item == mCurrKType) {
                        bipkt_tv_txt.setTextColor(getColor(R.color.bin_global_yellow))
                    } else {
                        bipkt_tv_txt.setTextColor(getColor(R.color.bin_txt_first))
                    }
                }
            }
        }
    }

    init {
        initAdapter()
        initListener()
        loadData()
    }

    private fun initListener() {
        mBaseQuickAdapter.setOnItemClickListener { _, _, position ->
            dismiss()
            val binKType = mBaseQuickAdapter.data[position]
            BinSpHelper.updateBinKType(binKType)
            kTypeListener.onClick(binKType)
            mCurrKType = binKType
            mBaseQuickAdapter.notifyDataSetChanged()
        }
    }

    private fun loadData() {
        val list = BinKType.binKTypeList().toMutableList()
        mBaseQuickAdapter.setNewInstance(list)
    }

    private fun initAdapter() {
        val quickAdapter = mBaseQuickAdapter
        contentView.run {
            bpkt_rv_recycle.layoutManager = GridLayoutManager(mContext, 5)
            bpkt_rv_recycle.adapter = quickAdapter
        }
    }

    interface KTypeListener {
        fun onClick(binKType: BinKType)
    }
}