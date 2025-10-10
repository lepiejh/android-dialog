package com.orhanobut.dialog.base

import android.app.Dialog
import android.content.Context
import android.util.DisplayMetrics
import android.view.Gravity
import android.view.WindowManager

class BaseCenterDialog(context: Context, theme: Int) : Dialog(context, theme){

    init {
        window?.let { win ->
            val lp = win.attributes
            val displayMetrics = DisplayMetrics()
            win.windowManager.defaultDisplay.getMetrics(displayMetrics)

            // 改进的尺寸计算
            val screenWidth = displayMetrics.widthPixels
            val screenHeight = displayMetrics.heightPixels

            // 横屏适配：使用较小的维度作为基准
            val baseDimension = minOf(screenWidth, screenHeight)
            lp.width = (baseDimension * 0.8).toInt()
            lp.height = WindowManager.LayoutParams.WRAP_CONTENT

            // 添加重力设置，确保居中显示
            lp.gravity = Gravity.CENTER

            win.attributes = lp
        }
        setCanceledOnTouchOutside(false)
    }
}