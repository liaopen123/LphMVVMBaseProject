package com.lph.lphmvvmbaseproject.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lph.baselib.viewmodel.BaseViewModel
import com.lph.lphmvvmbaseproject.data.model.bean.HomeBanner
import com.lph.lphmvvmbaseproject.data.model.bean.HomeData
import com.lph.lphmvvmbaseproject.data.repository.request.HttpRequestCoroutine
import com.lph.lphmvvmbaseproject.utils.network.mApiService
import kotlinx.coroutines.launch

class HomeDataViewModel :BaseViewModel(){

    var pageNo=0
    // 创建一个LiveData实例保存String类型的数据
    val bannerList: MutableLiveData<ArrayList<HomeBanner>> by lazy {
        MutableLiveData<ArrayList<HomeBanner>>()
    }
    // 创建一个LiveData实例保存String类型的数据
    val homeData: MutableLiveData<HomeData> by lazy {
        MutableLiveData<HomeData>()
    }

    fun requestBannerData(){
        viewModelScope.launch {
            val banner = mApiService.getBanner()
            bannerList.value = banner.data

        }

    }

    fun requestArticleData(){
        viewModelScope.launch {
            val homeArticle = HttpRequestCoroutine.getHomeArticle(pageNo++)
            val homeData1 = homeData.value
            homeData1?.homeArticle?.data?.datas?.addAll(homeArticle.data.datas)
         homeData1?.let {
             homeData.value = homeData1
         }
        }
    }

    fun requestHomeData(){
        viewModelScope.launch {
            val homeData1 = HttpRequestCoroutine.getHomeData()
            homeData.value  = homeData1
        }
    }



}