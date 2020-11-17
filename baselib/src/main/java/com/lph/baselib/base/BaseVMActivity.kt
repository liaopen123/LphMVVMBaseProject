package com.lph.baselib.base

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.kingja.loadsir.core.LoadSir
import com.lph.baselib.ext.getVmClazz
import com.lph.baselib.network.manager.NetState
import com.lph.baselib.viewmodel.BaseViewModel
import me.hgj.jetpackmvvm.network.manager.NetworkStateManager

abstract class BaseVMActivity<VM: BaseViewModel>:AppCompatActivity(){
    /**
     * 是否需要使用DataBinding 供子类BaseVmDbActivity修改，用户请慎动
     */
    private var isUserDb = false
    lateinit var mViewModel: VM
    //loading 加载框架
     val loadSir by lazy{
        LoadSir.getDefault().register(getLoadingWrapper())

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (!isUserDb) {
            setContentView(layoutId())
        } else {
            initDataBind()
        }
        mViewModel = createViewModel()
        netStateObserve()
        init()
        initObserver()

    }

    abstract fun init()
    abstract fun initObserver()



    abstract fun layoutId(): Int
    /**
     * 注册网络状态的监听
     */
    private fun netStateObserve() {
//        NetworkStateManager.instance.mNetworkStateCallback.observe(this, Observer {
//            onNetworkStateChanged(it)
//        })
    }

    private fun createViewModel(): VM {
        return ViewModelProvider(this).get(getVmClazz(this))
    }


    abstract fun showLoading(message:String="loading...")
    abstract  fun hideLoading()
    /**
     * 网络变化监听 子类重写
     */
    open fun onNetworkStateChanged(netState: NetState) {
        when(netState.isSuccess) {
            true -> Toast.makeText(this, "网络正常", Toast.LENGTH_SHORT).show()
            false -> Toast.makeText(this, "网络异常", Toast.LENGTH_SHORT).show()
        }
    }


    fun userDataBinding(isUserDb: Boolean) {
        this.isUserDb = isUserDb
    }


    /**
     * 供子类BaseVmDbActivity 初始化Databinding操作
     */
    open fun initDataBind() {}

    /**
     * 返回loading框住的界面
     */
    abstract fun getLoadingWrapper(): View

}