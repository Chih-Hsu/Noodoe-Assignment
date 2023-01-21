package com.chihwhsu.noodoeassigment.parkinglot

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.chihwhsu.noodoeassigment.databinding.FragmentParkingLotsBinding
import com.chihwhsu.noodoeassigment.ext.getViewModelFactory

class ParkingLotsFragment : Fragment() {

    private val viewModel by viewModels<ParkingLotsViewModel> { getViewModelFactory() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentParkingLotsBinding.inflate(inflater, container, false)

        val adapter = ParkingLotAdapter()
        binding.recyclerviewParkingLots.adapter = adapter

        viewModel.parkingLots.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }

        viewModel.isLoading.observe(viewLifecycleOwner) {
            if (it == true) {
                binding.progressBar.visibility = View.VISIBLE
            } else {
                binding.progressBar.visibility = View.GONE
            }
        }



        return binding.root
    }
}