package com.example.myapplication.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.myapplication.databinding.FragmentHomeBinding

class HomeFragment : Fragment(), IClickListener {

    private lateinit var binding: FragmentHomeBinding
    private val rvAdapter by lazy { Adapter(this) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View {

        binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.homeRecyclerView.apply {
            adapter = rvAdapter
            layoutManager = GridLayoutManager(activity, 2)
        }

        return binding.root
    }

    override fun onItemClicked(id: Int) {
        Toast.makeText(activity, "TODO", Toast.LENGTH_LONG).show()
    }

    override fun onItemSelected(position: Int) {
        TODO("Not yet implemented")
    }
}