package com.skintone.me.ui.ui.story1

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.skintone.me.R
import com.skintone.me.databinding.FragmentDifferenceStoriesBinding

class DifferenceStories : Fragment() {

    private var _binding: FragmentDifferenceStoriesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentDifferenceStoriesBinding.inflate(inflater, container, false)
        val view= binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnDifferentStoriesNext.setOnClickListener {view ->
            view.findNavController().navigate(R.id.action_differenceStories_to_differenceStories2)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}