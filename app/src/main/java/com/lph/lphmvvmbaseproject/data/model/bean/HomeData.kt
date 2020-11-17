package com.lph.lphmvvmbaseproject.data.model.bean

import com.lph.baselib.network.bean.ProjectResponse

data class HomeData (var homeBanner:ProjectResponse<ArrayList<HomeBanner>>,var homeArticle:ProjectResponse<HomeArticleBean>){
}