package com.orhanobut.dialog.adapter

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.orhanobut.dialog.R
import com.orhanobut.dialog.mode.BottomListMode
import com.ved.framework.bus.RxBus
import com.ved.framework.utils.Constant

class BottomListAdapter(private val mData: MutableList<BottomListMode?>?) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private lateinit var bottomDialogListener: (view: View?) -> Unit
    fun setBottomDialogListener(bottomDialogListener: (view: View?) -> Unit) {
        this.bottomDialogListener = bottomDialogListener
    }

    @SuppressLint("NotifyDataSetChanged")
    fun addAll(data: MutableList<BottomListMode?>?) {
        mData?.clear()
        data?.let{
            mData?.addAll(it)
        }
        notifyDataSetChanged()
    }

    fun getData() = mData

    private fun isFootView(position: Int): Boolean {
        return position >= (mData?.size ?: 0)
    }

    override fun getItemViewType(position: Int): Int {
        return if (isFootView(position)) {
            Constant.ITEM_FOOT
        } else {
            Constant.ITEM_LAYOUT
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            Constant.ITEM_FOOT ->
                FootViewHolder(
                    LayoutInflater.from(
                        parent.context
                    ).inflate(
                        R.layout.dialog_foot_layout, parent,
                        false
                    )
                )
            else ->
                BottomListViewHolder(
                    LayoutInflater.from(
                        parent.context
                    ).inflate(
                        R.layout.bottom_list_item_layout, parent,
                        false
                    )
                )
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is FootViewHolder) {
            holder.tvCancel?.setOnClickListener { view: View? ->
                bottomDialogListener.invoke(view)
            }
        } else if (holder is BottomListViewHolder) {
            mData?.getOrNull(position)?.let { bottomListMode ->
                holder.tvTag?.text = bottomListMode.tag
                if (bottomListMode.isSelect) {
                    holder.tvTag?.setTextColor(Color.parseColor("#2587FF"))
                } else {
                    holder.tvTag?.setTextColor(Color.parseColor("#45494D"))
                }
                holder.tvTag?.setOnClickListener {
                    RxBus.getDefault().postSticky(bottomListMode)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return if (mData == null) 1 else mData.size + 1
    }

    internal class BottomListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvTag: TextView? = null

        init {
            tvTag = itemView.findViewById(R.id.tv_tag)
        }
    }

    internal class FootViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvCancel: TextView? = null

        init {
            tvCancel = itemView.findViewById(R.id.tv_cancel)
        }
    }
}