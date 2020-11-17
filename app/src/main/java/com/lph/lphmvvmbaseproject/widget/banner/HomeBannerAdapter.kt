package com.lph.lphmvvmbaseproject.widget.banner

/**
 * 作者　: hegaojian
 * 时间　: 2020/2/20
 * 描述　:
 */

import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.lph.lphmvvmbaseproject.R
import com.lph.lphmvvmbaseproject.base.appContext
import com.lph.lphmvvmbaseproject.data.model.bean.HomeBanner
import com.zhpan.bannerview.BaseBannerAdapter
import com.zhpan.bannerview.BaseViewHolder

class HomeBannerAdapter : BaseBannerAdapter<HomeBanner, HomeBannerAdapter.HomeBannerViewHolder>() {
    override fun getLayoutId(viewType: Int): Int {
        return R.layout.banner_itemhome
    }

    override fun createViewHolder(itemView: View, viewType: Int): HomeBannerViewHolder {
        return HomeBannerViewHolder(itemView);
    }

    override fun onBind(
        holder: HomeBannerViewHolder?,
        data: HomeBanner?,
        position: Int,
        pageSize: Int
    ) {
        holder?.bindData(data, position, pageSize);
    }


    inner class HomeBannerViewHolder(view: View) : BaseViewHolder<HomeBanner>(view) {
        override fun bindData(data: HomeBanner?, position: Int, pageSize: Int) {
            val img = itemView.findViewById<ImageView>(R.id.bannerhome_img)
            data?.let {
                Glide.with(appContext)
                    .load(it.imagePath)
                    .transition(DrawableTransitionOptions.withCrossFade(500))
                    .into(img)
            }
        }

    }


}
