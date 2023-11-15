package com.orhanobut.dialog.utils

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Environment
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.Target
import com.ved.framework.utils.KLog
import java.io.File
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException

class FileUtils {
    private var currentFile: File? = null
    fun startDownload(context: Context, url: String?, callBack: ImageDownLoadCallBack) {
        val bitmap = arrayOf<Bitmap?>(null)
        try {
            Thread {
                try {
                    bitmap[0] = Glide.with(context)
                        .asBitmap()
                        .load(url)
                        .into(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
                        .get()
                } catch (e: Exception) {
                    e.printStackTrace()
                    KLog.i("lx_154", "文件下载失败 : " + e.message)
                }
                if (bitmap[0] != null) {
                    // 在这里执行图片保存方法
                    saveImageToGallery(context, bitmap[0])
                }
                if (bitmap[0] != null && currentFile?.exists() == true) {
                    callBack.onDownLoadSuccess(bitmap[0])
                } else {
                    callBack.onDownLoadFailed()
                }
            }.start()
        } catch (e: Exception) {
            e.printStackTrace()
            KLog.i("lx_154", "文件下载失败 : " + e.message)
        }
    }

    private fun saveImageToGallery(context: Context, bmp: Bitmap?) {
        // 首先保存图片
        val file =
            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).absoluteFile //注意小米手机必须这样获得public绝对路径
        var fileName = "syyk"
        val appDir = File(file, fileName)
        if (!appDir.exists()) {
            appDir.mkdirs()
        }
        fileName = System.currentTimeMillis().toString() + ".jpg"
        currentFile = File(appDir, fileName)
        var fos: FileOutputStream? = null
        try {
            fos = FileOutputStream(currentFile)
            bmp?.compress(Bitmap.CompressFormat.JPEG, 100, fos)
            fos.flush()
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        } finally {
            try {
                fos?.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }

        // 最后通知图库更新
        context.sendBroadcast(
            Intent(
                Intent.ACTION_MEDIA_SCANNER_SCAN_FILE,
                Uri.fromFile(currentFile?.path?.let { File(it) })
            )
        )
    }
}