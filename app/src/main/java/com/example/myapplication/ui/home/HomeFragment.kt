package com.example.myapplication.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.myapplication.App
import com.example.myapplication.databinding.FragmentHomeBinding
import com.example.myapplication.di.ViewModelFactory
import com.example.myapplication.ui.common.Adapter
import com.example.myapplication.ui.common.IClickListener
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
            layoutManager = GridLayoutManager(activity, viewModel.columnsCountState.value ?: 2)
        }
        registerViewStateObserver()

        return binding.root
    }

    private fun registerViewStateObserver() {
        viewModel.viewState.observe(viewLifecycleOwner) {
            when (it) {
                is ViewState.Loading -> {
                    loadingStateHandler()
                }

                is ViewState.Error -> {
                    errorStateHandler(it)
                }

                is ViewState.Success -> {
                    successStateHandler(it)
                }
            }
        }
    }

    private fun successStateHandler(it: ViewState) {
        binding.homeRecyclerView.visibility = View.VISIBLE
        binding.HomeFragmentLoader.visibility = View.INVISIBLE
        rvAdapter.submitList((it as ViewState.Success).data)
    }

    private fun loadingStateHandler() {
        binding.HomeFragmentLoader.visibility = View.VISIBLE
        binding.homeRecyclerView.visibility = View.INVISIBLE
    }

    private fun errorStateHandler(err: ViewState) {
        Toast.makeText(
            activity,
            "Error happen: ${(err as ViewState.Error).errMsg}",
            Toast.LENGTH_LONG
        ).show()
    }

    override fun onItemClicked(id: Int) {
        Toast.makeText(activity, "TODO", Toast.LENGTH_LONG).show()
    }

    override fun onItemSelected(position: Int) {
        // position in data set
        val actualPosition = position - 1
        viewModel.markAsFavorite(actualPosition)
        rvAdapter.notifyItemChanged(actualPosition)
    }
}