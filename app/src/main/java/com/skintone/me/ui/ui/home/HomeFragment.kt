package com.skintone.me.ui.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.skintone.me.R
import com.skintone.me.databinding.FragmentHomeBinding
import com.skintone.me.ui.ui.FavoriteActivity
import com.skintone.me.ui.ui.camera.CameraActivity

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.ivFavorite.setOnClickListener {
            startActivity(Intent(requireContext(), FavoriteActivity::class.java))
        }

        binding.analyze.setOnClickListener {
            startActivity(Intent(requireContext(), CameraActivity::class.java))
        }

        binding.btnDifferentStories.setOnClickListener { view ->
            view.findNavController().navigate(R.id.action_navigation_home_to_differenceStories)
        }
        binding.btnKnowSkintone.setOnClickListener { view ->
            view.findNavController().navigate(R.id.action_navigation_home_to_knowSkintone)
        }
        binding.btnCareSkintone.setOnClickListener { view ->
            view.findNavController().navigate(R.id.action_navigation_home_to_careSkintone)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}