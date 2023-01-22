package com.chihwhsu.noodoeassigment.parkinglot

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.chihwhsu.noodoeassigment.databinding.FragmentParkingLotsBinding
import com.chihwhsu.noodoeassigment.ext.getViewModelFactory

class ParkingLotsFragment : Fragment() {

    private lateinit var binding: FragmentParkingLotsBinding
    private val viewModel by viewModels<ParkingLotsViewModel> { getViewModelFactory() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentParkingLotsBinding.inflate(inflater, container, false)

        val adapter = ParkingLotAdapter()
        binding.recyclerviewParkingLots.adapter = adapter

        viewModel.parkingLots.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }

        viewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            showProgressBar(isLoading)
        }

        viewModel.error.observe(viewLifecycleOwner) {
            showErrorMessage(it)
        }

        return binding.root
    }

    private fun showErrorMessage(error: String?) {
        Toast.makeText(requireContext(), error, Toast.LENGTH_SHORT).show()
    }

    private fun showProgressBar(
        isLoading: Boolean
    ) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }
}