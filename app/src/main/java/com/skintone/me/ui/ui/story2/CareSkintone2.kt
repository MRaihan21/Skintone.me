package com.skintone.me.ui.ui.story2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.skintone.me.R
import com.skintone.me.databinding.FragmentCareSkintone2Binding

class CareSkintone2 : Fragment() {

    private var _binding: FragmentCareSkintone2Binding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentCareSkintone2Binding.inflate(inflater, container, false)
        val view= binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnCareSkintoneNext2.setOnClickListener{
            view.findNavController().navigate(R.id.action_careSkintone2_to_careSkintone3)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}