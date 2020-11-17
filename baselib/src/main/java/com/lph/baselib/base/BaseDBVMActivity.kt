package com.lph.baselib.base

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.lph.baselib.ext.getVmClazz
import com.lph.baselib.network.manager.NetState
import com.lph.baselib.viewmodel.BaseViewModel
import me.hgj.jetpackmvvm.network.manager.NetworkStateManager

abstract class BaseDBVMActivity<VM: BaseViewModel,DB:ViewDataBinding>:BaseVMActivity<VM>(){

    lateinit var mDataBinding: DB

    override fun onCreate(savedInstanceState: Bundle?) {
        userDataBinding(true)//使用databinding
        super.onCreate(savedInstanceState)




    }

    override fun initDataBind() {
        mDataBinding = DataBindingUtil.setContentView(this,layoutId())
        mDataBinding.lifecycleOwner = this
    }



}