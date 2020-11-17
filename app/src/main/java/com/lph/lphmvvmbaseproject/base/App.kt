package com.lph.lphmvvmbaseproject.base

import android.app.Application
import android.hardware.Camera
import com.kingja.loadsir.callback.SuccessCallback
import com.kingja.loadsir.core.LoadSir
import com.lph.lphmvvmbaseproject.widget.loadCallBack.EmptyCallback
import com.lph.lphmvvmbaseproject.widget.loadCallBack.LoadingCallback
import com.tencent.mmkv.MMKV

val appContext: Application by lazy { App.INSTANCE }
class App :Application(){

    companion object{
        lateinit var INSTANCE:Application
    }


    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
        MMKV.initialize(this.filesDir.absolutePath + "/mmkv")

        LoadSir.beginBuilder()
            .addCallback(LoadingCallback())//加载
            .addCallback(EmptyCallback())//空
            .setDefaultCallback(SuccessCallback::class.java)//设置默认加载状态页
            .commit()
    }
}