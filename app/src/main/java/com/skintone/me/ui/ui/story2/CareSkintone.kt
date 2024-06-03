package com.skintone.me.ui.ui.story2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.skintone.me.R
import com.skintone.me.databinding.FragmentCareSkintoneBinding

class CareSkintone : Fragment() {

    private var _binding: FragmentCareSkintoneBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCareSkintoneBinding.inflate(inflater, container, false)
        val view= binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnCareSkintoneNext.setOnClickListener { view ->
            view.findNavController().navigate(R.id.action_careSkintone_to_careSkintone2)
        }

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}