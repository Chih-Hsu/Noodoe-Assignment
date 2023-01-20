package com.chihwhsu.noodoeassigment.parkinglot

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.chihwhsu.noodoeassigment.databinding.FragmentParkingLotsBinding
import com.chihwhsu.noodoeassigment.network.ParkingLotApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ParkingLotsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentParkingLotsBinding.inflate(inflater,container,false)





        return binding.root
    }
}