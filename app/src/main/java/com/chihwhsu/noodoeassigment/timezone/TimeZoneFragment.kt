package com.chihwhsu.noodoeassigment.timezone

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.chihwhsu.noodoeassigment.R
import com.chihwhsu.noodoeassigment.databinding.FragmentTimezoneBinding
import com.chihwhsu.noodoeassigment.ext.getViewModelFactory
import com.chihwhsu.noodoeassigment.parkinglot.ParkingLotsViewModel
import java.util.*

class TimeZoneFragment : Fragment() {

    private lateinit var binding: FragmentTimezoneBinding
    private val viewModel by viewModels<TimeZoneViewModel> { getViewModelFactory() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTimezoneBinding.inflate(inflater, container, false)

        setSpinner()

        viewModel.user.observe(viewLifecycleOwner){
            binding.textUserEmail.text = it?.name
        }



        return binding.root
    }

    private fun setSpinner() {

        viewModel.timeZoneList.observe(viewLifecycleOwner) {

            val displayList = mutableListOf<String>()
            for (timeZone in it) {
                displayList.add(timeZone.displayName)
            }

            binding.spinnerTimeZone.adapter = ArrayAdapter(
                requireContext(),
                androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, displayList
            )

            binding.spinnerTimeZone.onItemSelectedListener =
                object : AdapterView.OnItemSelectedListener {
                    override fun onNothingSelected(p0: AdapterView<*>?) {
                    }

                    override fun onItemSelected(
                        Adapter: AdapterView<*>?,
                        view: View?,
                        position: Int,
                        id: Long
                    ) {
                        TimeZone.setDefault(TimeZone.getTimeZone(it[position].id))
                        Toast.makeText(
                            requireContext(),
                            "TimeZone is ${TimeZone.getDefault().displayName}",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
        }
    }
}