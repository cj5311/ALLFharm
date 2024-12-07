package com.lcj.allpharm.ui.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.lcj.allpharm.R
import com.lcj.allpharm.databinding.FragmentFirstBinding
import com.lcj.allpharm.repository.Category

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class HomeFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<HomeViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getYakList(Category.Efficacy, "감기")
        viewModel.yakDataList.observe(viewLifecycleOwner) {
            Log.d("YagBang", "list $it")
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}