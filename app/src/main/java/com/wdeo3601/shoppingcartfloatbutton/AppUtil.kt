package com.wdeo3601.shoppingcartfloatbutton

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.AssetManager
import android.content.res.Resources
import android.os.Handler
import android.os.Looper
import android.util.TypedValue
import java.text.DecimalFormat


/**
 * Created by wendong on 2017/10/24 0024.
 * Email:       wdeo3601@163.com
 * Description:全局上下文 资源文件夹 主线程 handler （需要在 application 初始化）
 */
class AppUtil {
    private lateinit var mContext: Context
    private lateinit var mUiThread: Thread

    private val sHandler = Handler(Looper.getMainLooper())

    companion object {
        @SuppressLint("StaticFieldLeak")
        val INSTANCE = AppUtil()
    }

    fun init(context: Context) {
        this.mContext = context
        this.mUiThread = Thread.currentThread()
    }

    fun getAppContext(): Context {
        return mContext
    }

    fun getAssets(): AssetManager {
        return mContext.assets
    }

    fun getResource(): Resources {
        return mContext.getResources()
    }

    fun getColor(resId: Int): Int {
        return getResource().getColor(resId)
    }

    fun getString(resId: Int): String {
        return getResource().getString(resId)
    }

    fun isUIThread(): Boolean {
        return Thread.currentThread() === mUiThread
    }

    fun runOnUI(r: Runnable) {
        sHandler.post(r)
    }

    fun runOnUIDelayed(r: Runnable, delayMills: Long) {
        sHandler.postDelayed(r, delayMills)
    }

    fun removeRunnable(r: Runnable?) {
        if (r == null) {
            sHandler.removeCallbacksAndMessages(null)
        } else {
            sHandler.removeCallbacks(r)
        }
    }

    //region start-----------------------------------------
//    fun runInMainThread(runnable: Runnable) {
//        // 在主线程运行
//        if (isRunInMainThread()) {
//            runnable.run()
//        } else {
//            post(runnable)
//        }
//    }

//    /**
//     * 获取主线程id
//     */
//    private fun getMainThreadId(): Long {
//        return App.mMainThreadId!!
//    }

//    /**
//     * 在主线程执行runnable
//     */
//    fun post(runnable: Runnable): Boolean {
//        return getHandler().post(runnable)
//    }
//
//    /**
//     * 获取主线程的handler
//     */
//    private fun getHandler(): Handler {
//        return App.mMainThreadHandler!!
//    }

//    // 判断当前的线程是不是在主线程
//    private fun isRunInMainThread(): Boolean {
//        return android.os.Process.myTid().toLong() == getMainThreadId()
//    }
    //endregion-----------------------------------------------------
    //region 尺寸单位转换
    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    fun dp2px(dpValue: Float): Int {
        val scale = getResource().displayMetrics.density
        return (dpValue * scale + 0.5f).toInt()
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    fun px2pt(pxValue: Float): Int {
        val scale = getResource().displayMetrics.density
        return (pxValue / scale + 0.5f).toInt()
    }

    /**
     * 根据手机的分辨率从 spValue(像素) 的单位 转成为 px
     */
    fun sp2px(spValue: Float): Int {
        val scale = getResource().displayMetrics.scaledDensity
        return (spValue / scale + 0.5f).toInt()
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 sp
     */
    fun px2sp(pxValue: Float): Int {
        val scale = getResource().displayMetrics.scaledDensity
        return (pxValue / scale + 0.5f).toInt()
    }

    /**
     * 转换pt为px
     *
     * @param context
     * @param value   需要转换的pt值，若context.resources.displayMetrics经过resetDensity()的修改则得到修正的相对长度，否则得到原生的磅
     * @return
     */
    fun pt2px(context: Context, value: Float): Int {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PT, value, context.resources.displayMetrics).toInt()
    }
    //endregion

    /**
     * 数字转换，把超过10000的数字转换成汉字万
     */
    fun formatNumString(num: String?): String {
        var resultNum = num ?: "0"
        val intPrice = resultNum.toFloat()
        if (intPrice >= 10000) {
            val decimalFormat = DecimalFormat("#.0")
            resultNum = "${decimalFormat.format(((intPrice / 10000f) * 10).toInt() / 10f)}万"
        }
        return resultNum
    }
}