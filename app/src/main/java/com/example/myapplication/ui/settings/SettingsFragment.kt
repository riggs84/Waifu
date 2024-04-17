package com.example.myapplication.ui.settings

import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.App
import com.example.myapplication.databinding.FragmentSettingsBinding
import com.example.myapplication.di.ViewModelFactory
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import javax.inject.Inject

class SettingsFragment : Fragment() {

    private lateinit var binding: FragmentSettingsBinding

    @Inject
    lateinit var vmFactory: ViewModelFactory<SettingsViewModel>
    private val viewModel by lazy {
        ViewModelProvider(requireActivity(), vmFactory)[SettingsViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        (requireActivity().application as App).appComponent.inject(this)

        binding = FragmentSettingsBinding.inflate(inflater, container, false)
        binding.columnsValue.setOnClickListener { showDialog() }

        registerObserver()
        return binding.root
    }

    private fun showDialog() {
        val columns = arrayOf("2 columns", "3 columns", "4 columns")
        var checkedItem = when(viewModel.viewState.value) {
            2 -> 0
            3 -> 1
            4 -> 2
            else -> 2
        }
        MaterialAlertDialogBuilder(requireContext())
            .setTitle("Choose columns for home screen:")
            .setSingleChoiceItems(columns, checkedItem) { _, which ->
                checkedItem = when (which) {
                    0 -> 2
                    1 -> 3
                    2 -> 4
                    else -> 2
                }
            }
            .setPositiveButton("OK") { _, _ -> viewModel.setColumns(checkedItem) }
            .setNegativeButton("Cancel") { _, _ -> null }
            .show()
    }

    private fun registerObserver() {
        viewModel.viewState.observe(viewLifecycleOwner) {
            binding.columnsValue.text = Editable.Factory().newEditable(it.toString())
        }
    }

}