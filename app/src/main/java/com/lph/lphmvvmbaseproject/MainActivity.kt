package com.lph.lphmvvmbaseproject

import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.kingja.loadsir.core.LoadSir
import com.lph.baselib.base.BaseDBVMActivity
import com.lph.lphmvvmbaseproject.adapter.ArticleAdapter
import com.lph.lphmvvmbaseproject.data.model.bean.HomeBanner
import com.lph.lphmvvmbaseproject.data.model.bean.HomeData
import com.lph.lphmvvmbaseproject.databinding.ActivityMainBinding
import com.lph.lphmvvmbaseproject.ext.hideLoadingExt
import com.lph.lphmvvmbaseproject.ext.init
import com.lph.lphmvvmbaseproject.ext.initFooter
import com.lph.lphmvvmbaseproject.ext.showLoadingExt
import com.lph.lphmvvmbaseproject.viewmodel.HomeDataViewModel
import com.lph.lphmvvmbaseproject.widget.DefineLoadMoreView
import com.lph.lphmvvmbaseproject.widget.SpaceItemDecoration
import com.lph.lphmvvmbaseproject.widget.banner.HomeBannerAdapter
import com.lph.lphmvvmbaseproject.widget.loadCallBack.LoadingCallback
import com.yanzhenjie.recyclerview.SwipeRecyclerView
import com.zhpan.bannerview.BannerViewPager
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : BaseDBVMActivity<HomeDataViewModel, ActivityMainBinding>() {

    //适配器
    private val articleAdapter: ArticleAdapter by lazy { ArticleAdapter(arrayListOf()) }
    override fun layoutId() = R.layout.activity_main

    //recyclerview的底部加载view 因为在首页要动态改变他的颜色，所以加了他这个字段
    private lateinit var footView: DefineLoadMoreView
    override fun showLoading(message: String) {
        showLoadingExt("qaq")
    }

    override fun hideLoading() {
        hideLoadingExt()
    }

    override fun init() {
        mDataBinding.viewmodel = mViewModel

       loadSir.showCallback(LoadingCallback::class.java)
        //初始化recyclerView
        recyclerView.init(LinearLayoutManager(this), articleAdapter).let {
            //因为首页要添加轮播图，所以我设置了firstNeedTop字段为false,即第一条数据不需要设置间距
            it.addItemDecoration(SpaceItemDecoration(0, 8, false))
            footView = it.initFooter(SwipeRecyclerView.LoadMoreListener {
                Toast.makeText(this, "loadmore", Toast.LENGTH_SHORT).show()
                mViewModel.requestArticleData()
            })

        }
// 加载更多的监听。
        recyclerView.loadMoreFinish(false, true)
    }

    override fun initObserver() {
        val bannerOberver = Observer<ArrayList<HomeBanner>> { t -> addHeaderView(t) }
        mViewModel.bannerList.observe(this, bannerOberver)
                val homeDataObserver = Observer<HomeData> { t ->
                    //更新数据了
                    Toast.makeText(this, "加载更多", Toast.LENGTH_SHORT).show()
                    articleAdapter.setList(t?.homeArticle?.data?.datas)
                    recyclerView.loadMoreFinish(false, true)
//                    loadSir.showSuccess()
                }
        mViewModel.homeData.observe(this, homeDataObserver)

//        mViewModel.requestBannerData()
//
//
//        mViewModel.requestHomeData()
    }


    private fun addHeaderView(data: ArrayList<HomeBanner>) {
        val headview = LayoutInflater.from(this).inflate(R.layout.include_banner, null).apply {
            findViewById<BannerViewPager<HomeBanner, HomeBannerAdapter.HomeBannerViewHolder>>(R.id.banner_view).apply {
                adapter = HomeBannerAdapter()
                setLifecycleRegistry(lifecycle)
                setOnPageClickListener {
//                    nav().navigateAction(R.id.action_to_webFragment, Bundle().apply {putParcelable("bannerdata", data[it])})
                }
                create(data)
            }
        }
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.addHeaderView(headview)
    }

    override fun getLoadingWrapper(): View =swipeRefresh
}




//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
////        setContentView(R.layout.activity_main)
//        val dataBinding =
//            DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
//        val model = ViewModelProvider(this).get(HomeDataViewModel::class.java)
//        dataBinding.viewmodel = model
//        dataBinding.lifecycleOwner = this   //这个代码要写 不然数据改变   UI不会自动更新
//        val bannerObserver = Observer<ArrayList<HomeBanner>> { t -> addHeaderView(t) }
//        val homeDataObserver = Observer<HomeData> {
//                t -> }
//        model.bannerList.observe(this,bannerObserver)
//        model.homeData.observe(this,homeDataObserver)
//        model.requestBannerData()
//        model.requestHomeData()
//
//    }
//
//    private fun addHeaderView( data:ArrayList<HomeBanner>) {
//        val headview = LayoutInflater.from(this).inflate(R.layout.include_banner, null).apply {
//            findViewById<BannerViewPager<HomeBanner, HomeBannerAdapter.HomeBannerViewHolder>>(R.id.banner_view).apply {
//                adapter = HomeBannerAdapter()
//                setLifecycleRegistry(lifecycle)
//                setOnPageClickListener {
////                    nav().navigateAction(R.id.action_to_webFragment, Bundle().apply {putParcelable("bannerdata", data[it])})
//                }
//                create(data)
//            }
//        }
//        recyclerView.layoutManager =LinearLayoutManager(this)
//        recyclerView.addHeaderView(headview)
//    }