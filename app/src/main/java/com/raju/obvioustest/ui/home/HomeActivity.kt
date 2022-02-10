package com.raju.obvioustest.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.raju.obvioustest.R
import com.raju.obvioustest.databinding.ActivityHomeBinding
import com.raju.obvioustest.model.NasaPictureModel
import com.raju.obvioustest.ui.base.BaseActivity
import com.raju.obvioustest.ui.details.DetailsActivity
import com.raju.obvioustest.utils.AppConstants
import com.raju.obvioustest.utils.AppConstants.SPAN_COUNT
import com.raju.obvioustest.utils.RecycleViewItemDecorator
import com.raju.obvioustest.utils.ScreenUtils
import java.io.Serializable

class HomeActivity : BaseActivity(), HomeListAdapter.HomeListAdapterListener,
    SwipeRefreshLayout.OnRefreshListener {
    private lateinit var binding: ActivityHomeBinding
    private var homeListAdapter: HomeListAdapter? = null
    private var nasaPictureList: MutableList<NasaPictureModel> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()
        setViews()
    }

    private fun setViews() {
        nasaPictureList = loadNasaPictureData()
        if (homeListAdapter == null) {
            homeListAdapter =
                HomeListAdapter(nasaPictureList, this)
            homeListAdapter?.pageState = AppConstants.FINISHED
            binding.rvImages.adapter = homeListAdapter
        } else {
            homeListAdapter?.pictureList = nasaPictureList
            homeListAdapter?.pageState = AppConstants.FINISHED
            homeListAdapter?.notifyDataSetChanged()
        }
        binding.srLayout.isRefreshing = false
        binding.loaderView.rlProgressContainer.visibility = View.GONE
    }

    private fun initViews() {
        binding.loaderView.rlProgressContainer.visibility = View.VISIBLE

        binding.rlTopView.ivBack.visibility = View.GONE
        binding.rlTopView.tvTitle.text = resources.getString(R.string.txx_nasa_picture)

        binding.srLayout.setOnRefreshListener(this)
        binding.srLayout.setColorSchemeResources(R.color.purple_200)

        binding.rvImages.setHasFixedSize(true)
        binding.rvImages.layoutManager = GridLayoutManager(this, SPAN_COUNT)
        binding.rvImages.addItemDecoration(
            RecycleViewItemDecorator(
                ScreenUtils.convertDpToPx(8f),
                RecycleViewItemDecorator.GRID
            )
        )

    }

    private fun loadNasaPictureData(): MutableList<NasaPictureModel> {
        val mockData = resources.openRawResource(R.raw.nasa_picture_data)
        val dataString = mockData.bufferedReader().readText()

        val gson = Gson()
        val dataType = object : TypeToken<ArrayList<NasaPictureModel>>() {}.type
        return gson.fromJson<ArrayList<NasaPictureModel>>(dataString, dataType)
    }

    override fun onItemClicked(nasaPictureModel: NasaPictureModel, position: Int) {
        val intent = Intent(this, DetailsActivity::class.java)
        intent.putExtra(AppConstants.NASA_PIC_DATA, nasaPictureModel)
        intent.putExtra(AppConstants.NASA_PIC_POSITION, position)
        intent.putExtra(AppConstants.NASA_PIC_LIST, nasaPictureList as Serializable )
        startActivity(intent)
    }

    override fun onRefresh() {
        binding.srLayout.isRefreshing = true
        resetData()
        setViews()
    }

    private fun resetData() {
        nasaPictureList = mutableListOf()
        nasaPictureList.clear()
        homeListAdapter = null
    }
}