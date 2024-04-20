package com.orhanobut.dialog.utils

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import com.hjq.permissions.OnPermissionCallback
import com.hjq.permissions.Permission
import com.hjq.permissions.XXPermissions
import com.luck.picture.lib.basic.PictureSelector
import com.luck.picture.lib.config.SelectMimeType
import com.luck.picture.lib.config.SelectModeConfig
import com.luck.picture.lib.entity.LocalMedia
import com.luck.picture.lib.interfaces.OnResultCallbackListener
import com.orhanobut.dialog.R
import com.orhanobut.dialog.dialog.ViewDialog
import com.ved.framework.utils.ToastUtils
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import top.zibin.luban.Luban
import top.zibin.luban.OnCompressListener
import java.io.File

class ImageUploadingUtils private constructor() {


    companion object {
        private var instance: ImageUploadingUtils? = null
            get() {
                if (field == null) {
                    field = ImageUploadingUtils()
                }
                return field
            }

        @Synchronized
        fun getInstances(): ImageUploadingUtils {
            return instance!!
        }
    }

    //限制图片最大传多少M
    var imageMaxSize = "3"
    fun isFileSize(photoPath: String): Boolean {
        var fileOrImageFilesSize =
            FileSizeUtil.getFileOrFilesSize(photoPath, FileSizeUtil.SIZETYPE_MB)
        val compare = BigDecimalUtils.getInstance()
            .compare(fileOrImageFilesSize.toString() + "", imageMaxSize)
        return if (compare == 1) {
            ToastUtils.showShort("图片文件最大不能超过" + imageMaxSize + "M")

            false
        } else true
    }

    fun showDialogPublishType(
        activity: Activity,
        fm: FragmentManager,
        s1: String = "拍照",
        s2: String = "从手机相册中选择",
        s3: String = "取消",
        onImagePath: (String) -> Unit
    ) {
        ViewDialog.newInstance().setLayoutId(R.layout.dialog_select_publish_dynamics_type)
            .setConvertListener { holder, dialog ->

                val tv1 = holder.getView<TextView>(R.id.tvImage)
                val tv2 = holder.getView<TextView>(R.id.tvVideo)
                val tv3 = holder.getView<TextView>(R.id.tvClose)
                tv1.text = s1
                tv2.text = s2
                tv3.text = s3
                tv1.setOnClickListener {
                    dialog.dismiss()
                    takePhoto(activity)
                    {
                        onImagePath(it)
                    }

                }
                tv2.setOnClickListener {
                    dialog.dismiss()
                    openImage(activity)
                }
                holder.getView<TextView>(R.id.tvClose).setOnClickListener {
                    dialog.dismiss()
                }

            }.setOutCancel(true).setShowBottom(true).show(fm)
    }

    fun uploadFile(activity: Activity, path: String, callBack: (MultipartBody.Part?) -> Unit) {
        Luban.with(activity)
            .load(ArrayList<String>().apply { add(path) })
            .ignoreBy(500)
            .setCompressListener(object : OnCompressListener {
                override fun onStart() {}
                override fun onSuccess(file: File) {
                    callBack.invoke(MultipartBody.Part.createFormData("file", file.name, RequestBody.create("application/otcet-stream".toMediaTypeOrNull(), file)))
                }

                override fun onError(e: Throwable) {

                }
            }).launch()
    }


    fun openSelectImage(
        activity: Activity,
        maxImageNum: Int,
        dataList: ArrayList<LocalMedia>?,
        onImagePath: (String, ArrayList<LocalMedia>) -> Unit
    ) {

        XXPermissions.with(activity)
            .permission(*Permission.Group.STORAGE)
            .request(object : OnPermissionCallback {
                override fun onGranted(permissions: List<String>, all: Boolean) {
                    if (all) {
                        //判断是单选还是多选
                        val type =
                            if (maxImageNum > 1) SelectModeConfig.MULTIPLE else SelectModeConfig.MULTIPLE
                        PictureSelector.create(activity)
                            .openGallery(SelectMimeType.ofImage())
                            .setImageEngine(GlideEngine.createGlideEngine())
                            .setSelectionMode(type)
                            .setMaxSelectNum(maxImageNum)
                            .setSelectedData(dataList)
                            .isDisplayCamera(false)
                            .forResult(object : OnResultCallbackListener<LocalMedia> {
                                override fun onResult(result: ArrayList<LocalMedia>?) {

                                    if (result != null && result.size > 0) {
                                        val path = result[0].path
                                        val imagePath = getPhotoFromPhotoAlbum.getRealPathFromUri(
                                            activity,
                                            Uri.parse(path)
                                        )
                                        onImagePath(imagePath, result)

                                    }
                                }

                                override fun onCancel() {
                                }
                            })
                    }
                }

                override fun onDenied(permissions: List<String>, never: Boolean) {
                    if (never) {
                        // 如果是被永久拒绝就跳转到应用权限系统设置页面
                        XXPermissions.startPermissionActivity(activity, permissions)
                    }
                }
            })
    }


    inline fun takePhoto(activity: Activity, crossinline onImagePath: (String) -> Unit) {

        XXPermissions.with(activity) // 申请单个权限
            // 申请多个权限
            .permission(*Permission.Group.STORAGE)
            //   .permission(*Permission.Group.STORAGE)
            .permission(Permission.CAMERA)
            .request(object : OnPermissionCallback {
                override fun onGranted(permissions: List<String>, all: Boolean) {
                    if (all) {
                        PictureSelector.create(activity)
                            .openCamera(SelectMimeType.ofImage())
                            .forResult(object : OnResultCallbackListener<LocalMedia> {
                                override fun onResult(result: ArrayList<LocalMedia>?) {

                                    if (result != null && result.size > 0) {
                                        var path = result[0].path

                                        var imagePath = getPhotoFromPhotoAlbum.getRealPathFromUri(
                                            activity,
                                            Uri.parse(path)
                                        )
                                        onImagePath(imagePath)
                                        /*   if (isFileSize(imagePath))
                                           {
                                               onImagePath(path)
                                           }*/
                                    }
                                }

                                override fun onCancel() {
                                }
                            })
                    }
                }

                override fun onDenied(permissions: List<String>, never: Boolean) {
                    if (never) {
                        // 如果是被永久拒绝就跳转到应用权限系统设置页面
                        XXPermissions.startPermissionActivity(activity, permissions)
                    }
                }
            })

    }

    private fun openImage(activity: Activity) {

        XXPermissions.with(activity)
            .permission(*Permission.Group.STORAGE)
            //     .permission(Permission.READ_MEDIA_IMAGES)
            .request(object : OnPermissionCallback {
                override fun onGranted(permissions: List<String>, all: Boolean) {
                    if (all) {
                        val intent = Intent()
                        intent.action = Intent.ACTION_PICK
                        intent.type = "image/*"
                        activity.startActivityForResult(intent, 1014)
                    }
                }

                override fun onDenied(permissions: List<String>, never: Boolean) {
                    if (never) {
                        // 如果是被永久拒绝就跳转到应用权限系统设置页面
                        XXPermissions.startPermissionActivity(activity, permissions)
                    }
                }
            })
    }
}