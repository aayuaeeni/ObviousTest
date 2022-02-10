package com.raju.obvioustest.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.raju.obvioustest.databinding.FragmentDetailsBinding
import com.raju.obvioustest.model.NasaPictureModel
import com.raju.obvioustest.utils.AppConstants
import com.raju.obvioustest.utils.loadImageFromUrlWithoutPlaceholder

class DetailsFragment : Fragment() {
    lateinit var binding: FragmentDetailsBinding
    private var nasaPictureModel: NasaPictureModel? = null

    companion object {
        fun newInstance(nasaPictureModel: NasaPictureModel) = DetailsFragment()
            .apply {
                arguments = Bundle().apply {
                    putSerializable(AppConstants.NASA_PIC_DATA, nasaPictureModel)
                }
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        nasaPictureModel = arguments?.getSerializable(AppConstants.NASA_PIC_DATA) as NasaPictureModel?
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentDetailsBinding.inflate(layoutInflater)
        setViews(nasaPictureModel)
        return binding.root
    }

    private fun setViews(nasaPictureModel: NasaPictureModel?) {
        binding.ivImage.loadImageFromUrlWithoutPlaceholder(nasaPictureModel?.hdurl)
        binding.tvTitle.text = nasaPictureModel?.title?:""
        binding.tvDescription.text = nasaPictureModel?.explanation?:""

    }
}