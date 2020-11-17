package com.lph.lphmvvmbaseproject

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.SystemClock
import androidx.appcompat.app.AppCompatActivity
import com.kingja.loadsir.core.LoadService
import com.kingja.loadsir.core.LoadSir
import com.lph.lphmvvmbaseproject.widget.loadCallBack.EmptyCallback
import com.lph.lphmvvmbaseproject.widget.loadCallBack.LoadingCallback

class TestActivity : AppCompatActivity() {
    lateinit   var loadService: LoadService<Any>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)
        // Your can change the callback on sub thread directly.
        // Your can change the callback on sub thread directly.
        loadService = LoadSir.getDefault().register(this) {
            // Your can change the status out of Main thread.
            Thread {
                loadService.showCallback(LoadingCallback::class.java)
                //do retry logic...
                SystemClock.sleep(500)
                //callback
                loadService.showSuccess()
            }.start()
        }.setCallBack(EmptyCallback::class.java) { context, view ->
        }

        Handler(Looper.getMainLooper()).postDelayed({ loadService.showCallback(EmptyCallback::class.java) }, 2000)
    }
}