package com.orhanobut.dialog.base

import android.view.View
import android.util.SparseArray
import androidx.annotation.IdRes
import android.widget.TextView

class DialogViewHolder private constructor(private val convertView: View) {
    private val views: SparseArray<View?> = SparseArray()

    /**
     * 获取View
     *
     * @param viewId
     * @param <T>
     * @return
    </T> */
    fun <T : View?> getView(@IdRes viewId: Int): T? {
        var view = views[viewId]
        if (view == null) {
            view = convertView.findViewById(viewId)
            views.put(viewId, view)
        }
        return view as T?
    }

    /**
     * 设置文本
     *
     * @param viewId
     * @param text
     */
    fun setText(viewId: Int, text: String?) {
        val textView = getView<TextView>(viewId)!!
        textView.text = text
    }

    /**
     * 设置字体颜色
     *
     * @param viewId
     * @param colorId
     */
    fun setTextColor(viewId: Int, colorId: Int) {
        val textView = getView<TextView>(viewId)!!
        textView.setTextColor(colorId)
    }

    /**
     * 设置背景图片
     *
     * @param viewId
     * @param resId
     */
    fun setBackgroundResource(viewId: Int, resId: Int) {
        val view = getView<View>(viewId)!!
        view.setBackgroundResource(resId)
    }

    /**
     * 设置背景颜色
     *
     * @param viewId
     * @param colorId
     */
    fun setBackgroundColor(viewId: Int, colorId: Int) {
        val view = getView<View>(viewId)!!
        view.setBackgroundColor(colorId)
    }

    /**
     * 设置点击事件
     *
     * @param viewId
     * @param listener
     */
    fun setOnClickListener(viewId: Int, listener: View.OnClickListener?) {
        val view = getView<View>(viewId)!!
        view.setOnClickListener(listener)
    }

    companion object {
        fun create(view: View): DialogViewHolder {
            return DialogViewHolder(view)
        }
    }
}