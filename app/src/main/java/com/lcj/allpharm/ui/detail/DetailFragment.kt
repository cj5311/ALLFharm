package com.lcj.allpharm.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayoutMediator
import com.lcj.allpharm.data.DetailAdapter
import com.lcj.allpharm.data.YakData
import com.lcj.allpharm.databinding.FragmentDetailBinding
import com.lcj.allpharm.R

class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            val detailList = arguments?.getParcelable<YakData>("yak_data")?.let {
                Glide.with(this@DetailFragment).load(it.imageUrl)
                    .placeholder(R.drawable.ic_open_yak).into(binding.yakIv)
                yakNameTv.text = it.productName
                yakCode.text = it.productCode
                yakVendor.text = it.companyName
                listOf(
                    it.efficacy ?: "",
                    it.interactions ?: "",
                    it.precautions ?: "",
                    it.storageMethod ?: ""
                )
            } ?: emptyList()
            viewPager.adapter = DetailAdapter(detailList)
            backButton.setOnClickListener {
                parentFragmentManager.popBackStack()
            }
            TabLayoutMediator(tabLayout, viewPager) { tab, position ->
                tab.text = when (position) {
                    0 -> "효능"
                    1 -> "상호작용"
                    2 -> "주의사항"
                    3 -> "보관법"
                    else -> null
                }
            }.attach()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}