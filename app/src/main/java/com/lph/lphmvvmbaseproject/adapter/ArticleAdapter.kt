package com.lph.lphmvvmbaseproject.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.lph.lphmvvmbaseproject.R
import com.lph.lphmvvmbaseproject.data.model.bean.HomeArticleBean
import com.lph.lphmvvmbaseproject.data.model.bean.HomeData

class ArticleAdapter:BaseQuickAdapter<HomeArticleBean.Data,BaseViewHolder>{

    constructor(data: ArrayList<HomeArticleBean.Data>) : super(
        R.layout.item_ariticle, data
    ) {
    }

    override fun convert(helper: BaseViewHolder, item: HomeArticleBean.Data) {
        item?.run {
            helper.setText(
                R.id.item_home_author,
                if (author.isNotEmpty()) author else shareUser
            )
            helper.setText(R.id.item_home_content, title)
            helper.setText(R.id.item_home_type2, "$superChapterName·$chapterName")
            helper.setText(R.id.item_home_date, niceDate)
        }
    }
}