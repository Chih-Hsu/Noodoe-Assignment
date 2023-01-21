package com.chihwhsu.noodoeassigment.parkinglot

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.chihwhsu.noodoeassigment.R
import com.chihwhsu.noodoeassigment.data.DisplayParkingLots
import com.chihwhsu.noodoeassigment.databinding.ItemParkingLotBinding

class ParkingLotAdapter : ListAdapter<DisplayParkingLots,ParkingLotAdapter.ParkingLotViewHolder>(ParkingLotDiffUtil()) {

    class ParkingLotDiffUtil : DiffUtil.ItemCallback<DisplayParkingLots>(){
        override fun areItemsTheSame(
            oldItem: DisplayParkingLots,
            newItem: DisplayParkingLots
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: DisplayParkingLots,
            newItem: DisplayParkingLots
        ): Boolean {
            return oldItem == newItem
        }
    }

    class ParkingLotViewHolder(private val binding: ItemParkingLotBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(item:DisplayParkingLots){
            binding.apply {
                textId.text = item.id
                textName.text = item.name
                textAddress.text = item.address
                textAvailableTotal.text = itemView.context.getString(R.string.available_total,item.availableCar,item.totalCar)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ParkingLotViewHolder {
        val view = ItemParkingLotBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ParkingLotViewHolder(view)
    }

    override fun onBindViewHolder(holder: ParkingLotViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.bind(currentItem)
    }
}