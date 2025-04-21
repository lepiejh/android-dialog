package com.orhanobut.dialog.dialog

import android.content.Context
import android.os.Bundle
import android.view.Gravity
import android.view.WindowManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.orhanobut.dialog.R
import com.orhanobut.dialog.adapter.BottomListAdapter
import com.orhanobut.dialog.mode.BottomListMode
import com.ved.framework.utils.DpiUtils
import com.ved.framework.widget.TouchOutsideDialog

class BottomListDialog : TouchOutsideDialog {
    private val mContext: Context
    private var bottomListAdapter: BottomListAdapter? = null

    constructor(context: Context) : super(context) {
        mContext = context
    }

    constructor(context: Context, theme: Int) : super(context, theme) {
        mContext = context
        initDialog()
    }

    fun addAll(data: MutableList<BottomListMode?>) {
        bottomListAdapter?.addAll(data)
        if (data.size >= 7) {
            this.window?.setLayout(
                    WindowManager.LayoutParams.MATCH_PARENT,
                    DpiUtils.dip2px(mContext, 45 * 9)
                )
        } else {
            this.window?.setLayout(
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT
            )
        }
    }

    fun getData() = bottomListAdapter?.getData()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.setContentView(R.layout.outbound_call_assistant_dialog_layout)
        val rvOutboundCall: RecyclerView = findViewById(R.id.rv_outbound_call)
        rvOutboundCall.layoutManager = LinearLayoutManager(mContext, RecyclerView.VERTICAL, false)
        bottomListAdapter = BottomListAdapter(ArrayList())
        bottomListAdapter?.setBottomDialogListener {
            dismiss()
        }
        rvOutboundCall.adapter = bottomListAdapter
    }

    private fun initDialog() {
        window?.let { win ->
            win.decorView.setPadding(0, 0, 0, 0)
            win.setGravity(Gravity.BOTTOM)
            val lp = win.attributes
            lp.width = WindowManager.LayoutParams.MATCH_PARENT
            lp.height = WindowManager.LayoutParams.WRAP_CONTENT
            win.attributes = lp
        }
        setCanceledOnTouchOutside(false)
        show()
    }
}