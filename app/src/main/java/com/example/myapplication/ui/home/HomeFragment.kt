package com.example.myapplication.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.myapplication.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View {

        binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.homeRecyclerView.apply {
            adapter = Adapter()
            layoutManager = GridLayoutManager(activity, 2)
        }

        return binding.root
    }
}