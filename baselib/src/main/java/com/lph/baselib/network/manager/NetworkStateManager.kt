package me.hgj.jetpackmvvm.network.manager

import com.lph.baselib.data.livedata.UnPeekLiveData
import com.lph.baselib.network.manager.NetState

/**
 * 作者　: hegaojian
 * 时间　: 2020/5/2
 * 描述　: 网络变化管理者
 */
class NetworkStateManager private constructor() {

    val mNetworkStateCallback = UnPeekLiveData<NetState>()

    companion object {
        val instance: NetworkStateManager by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            NetworkStateManager.instance
        }
    }

}