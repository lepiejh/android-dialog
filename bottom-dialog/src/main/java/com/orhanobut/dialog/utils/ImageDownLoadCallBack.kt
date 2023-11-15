package com.orhanobut.dialog.utils

import android.graphics.Bitmap

interface ImageDownLoadCallBack {
    fun onDownLoadSuccess(bitmap: Bitmap?)
    fun onDownLoadFailed()
}