package com.raju.obvioustest.ui.details

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.raju.obvioustest.model.NasaPictureModel

class DetailsPagerAdapter(activity: AppCompatActivity, val dataList: MutableList<NasaPictureModel> = mutableListOf()) : FragmentStateAdapter(activity) {
    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun createFragment(position: Int): Fragment {
        return DetailsFragment.newInstance(dataList[position])
    }
}