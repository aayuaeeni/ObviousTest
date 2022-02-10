package com.raju.obvioustest.ui.details

import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.raju.obvioustest.databinding.ActivityDetailsBinding
import com.raju.obvioustest.model.NasaPictureModel
import com.raju.obvioustest.ui.base.BaseActivity
import com.raju.obvioustest.utils.AppConstants

class DetailsActivity : BaseActivity() {
    private lateinit var binding: ActivityDetailsBinding
    private var nasaPictureList: MutableList<NasaPictureModel> = mutableListOf()
    private var nasaPictureModel:NasaPictureModel?=null
    private var position:Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        hideSystemUI()
        initViews()
        setViews()
    }

    private fun setViews() {
        val detailsPagerAdapter = DetailsPagerAdapter(this, nasaPictureList)
        binding.vpImage.adapter = detailsPagerAdapter


        binding.vpImage.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
            }
        })
    }

    private fun initViews() {
        binding.ivBack.setOnClickListener {
            onBackPressed()
        }

        nasaPictureModel = intent.getSerializableExtra(AppConstants.NASA_PIC_DATA) as NasaPictureModel?
        nasaPictureList = intent.getSerializableExtra(AppConstants.NASA_PIC_LIST) as MutableList<NasaPictureModel>
        position = intent.getIntExtra(AppConstants.NASA_PIC_POSITION,0)

//        val position = nasaPictureList.indexOf(nasaPictureModel)
        for (sample in nasaPictureList)
        {
            if (nasaPictureList.indexOf(sample) == position)
            {
                nasaPictureList.removeAt(position)
                nasaPictureList.add(0,nasaPictureModel!!)
                break
            }
        }
    }
}