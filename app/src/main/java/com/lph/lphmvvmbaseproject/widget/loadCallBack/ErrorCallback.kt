package com.lph.lphmvvmbaseproject.widget.loadCallBack

import com.kingja.loadsir.callback.Callback
import com.lph.lphmvvmbaseproject.R


class ErrorCallback : Callback() {

    override fun onCreateView(): Int {
        return R.layout.layout_error
    }

}