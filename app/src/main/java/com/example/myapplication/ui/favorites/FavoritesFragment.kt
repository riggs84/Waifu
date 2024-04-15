package com.example.myapplication.ui.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.App
import com.example.myapplication.databinding.FragmentFavoritesBinding
import com.example.myapplication.di.ViewModelFactory
import com.example.myapplication.ui.common.Adapter
import com.example.myapplication.ui.common.IClickListener
import javax.inject.Inject

class FavoritesFragment : Fragment(), IClickListener {

    private lateinit var binding: FragmentFavoritesBinding
    private val rvAdapter by lazy { Adapter(this) }

    @Inject
    lateinit var vmFactory: ViewModelFactory<FavoritesViewModel>
    private val viewModel by lazy {
        ViewModelProvider(requireActivity(), vmFactory)[FavoritesViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        (requireActivity().application as App).appComponent.inject(this)

        binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        binding.homeRecyclerView.apply {
            adapter = rvAdapter
            layoutManager = LinearLayoutManager(activity)
        }

        registerObserver()

        return binding.root
    }

    override fun onItemClicked(id: Int) {
        Toast.makeText(activity, "TODO", Toast.LENGTH_LONG).show()
    }

    override fun onItemSelected(position: Int) {
        val dataPosition = position - 1

        viewModel.markAsFavorite(dataPosition)
        rvAdapter.notifyItemChanged(dataPosition)
    }

    private fun registerObserver() {
        viewModel.viewState.observe(viewLifecycleOwner) {
            rvAdapter.submitList(it)
        }
    }
}