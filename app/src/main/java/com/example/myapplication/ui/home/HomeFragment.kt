package com.example.myapplication.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.transition.Visibility
import com.example.myapplication.App
import com.example.myapplication.databinding.FragmentHomeBinding
import com.example.myapplication.di.ViewModelFactory
import javax.inject.Inject

class HomeFragment : Fragment(), IClickListener {

    private lateinit var binding: FragmentHomeBinding
    private val rvAdapter by lazy { Adapter(this) }

    @Inject
    lateinit var vmFactory: ViewModelFactory<HomeViewModel>
    private val viewModel by lazy {
        ViewModelProvider(requireActivity(), vmFactory)[HomeViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        (requireActivity().application as App).appComponent.inject(this)

        binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.homeRecyclerView.apply {
            adapter = rvAdapter
            layoutManager = GridLayoutManager(activity, 3)
        }
        registerViewStateObserver()

        return binding.root
    }

    private fun registerViewStateObserver() {
        viewModel.viewState.observe(viewLifecycleOwner) {
            when (it) {
                is DataResult.Loading -> {
                    loadingStateHandler()
                }

                is DataResult.Error -> {
                    errorStateHandler(it)
                }

                is DataResult.Success -> {
                    rvAdapter.submitList(it.data)
                }
            }
        }
    }

    private fun loadingStateHandler() {
        binding.HomeFragmentLoader.visibility = View.VISIBLE
        binding.homeRecyclerView.visibility = View.INVISIBLE
    }

    private fun errorStateHandler(err: DataResult) {
        Toast.makeText(
            activity,
            "Error happen: ${(err as DataResult.Error).errMsg}",
            Toast.LENGTH_LONG
        ).show()
    }

    override fun onItemClicked(id: Int) {
        Toast.makeText(activity, "TODO", Toast.LENGTH_LONG).show()
    }

    override fun onItemSelected(position: Int) {
        TODO("Not yet implemented")
    }
}