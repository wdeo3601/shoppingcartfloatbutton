package com.wdeo3601.shoppingcartfloatbutton

import android.app.Application

/**
 * Created by wendong on 2018/7/22 0022.
 * Email:       wdeo3601@163.com
 * Description:
 */
class APP:Application(){
    override fun onCreate() {
        super.onCreate()
        AppUtil.INSTANCE.init(this)
    }
}