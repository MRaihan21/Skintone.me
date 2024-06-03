package com.skintone.me.ui.ui.story3

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.skintone.me.R
import com.skintone.me.databinding.FragmentDifferenceStories2Binding
import com.skintone.me.databinding.FragmentKnowSkintoneBinding

class KnowSkintone : Fragment() {

    private var _binding: FragmentKnowSkintoneBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentKnowSkintoneBinding.inflate(inflater, container, false)
        val view= binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnKnowSkintoneNext.setOnClickListener {view ->
            view.findNavController().navigate(R.id.action_knowSkintone_to_knowSkintone2)
        }

    }


}