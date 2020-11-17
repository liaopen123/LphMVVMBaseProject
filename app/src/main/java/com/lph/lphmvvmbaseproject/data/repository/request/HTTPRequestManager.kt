package com.lph.lphmvvmbaseproject.data.repository.request

import com.lph.baselib.network.bean.ProjectResponse
import com.lph.lphmvvmbaseproject.data.model.bean.HomeArticleBean
import com.lph.lphmvvmbaseproject.data.model.bean.HomeBanner
import com.lph.lphmvvmbaseproject.data.model.bean.HomeData
import com.lph.lphmvvmbaseproject.utils.network.mApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext


val HttpRequestCoroutine:HTTPRequestManager by lazy (mode = LazyThreadSafetyMode.SYNCHRONIZED){
    HTTPRequestManager()
}

class HTTPRequestManager {

    suspend fun getBanner(): ProjectResponse<ArrayList<HomeBanner>> {
            return mApiService.getBanner()
    }

    suspend fun getHomeArticle(pageNo:Int):ProjectResponse<HomeArticleBean>{
            return mApiService.getHomeArticle(pageNo)
    }




    suspend fun getHomeData():HomeData{
        val homedata  = withContext(Dispatchers.IO){

            val banner = async { getBanner() }
            val homeArticle = async { getHomeArticle(0) }

          HomeData( banner.await(),homeArticle.await())

        }
        return homedata
    }


}