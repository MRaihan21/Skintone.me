package com.skintone.me.ui.ui.story1

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.skintone.me.MainActivity
import com.skintone.me.R
import com.skintone.me.databinding.FragmentDifferenceStories3Binding


class DifferenceStories3 : Fragment() {

    private var _binding: FragmentDifferenceStories3Binding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDifferenceStories3Binding.inflate(inflater, container, false)
        val view= binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.ivClose.setOnClickListener {
            startActivity(Intent(requireContext(), MainActivity::class.java))
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}