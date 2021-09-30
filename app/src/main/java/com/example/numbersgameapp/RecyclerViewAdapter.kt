package com.example.numbersgameapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_row.view.*

class RecyclerViewAdapter(private val numbers:ArrayList<String>): RecyclerView.Adapter<RecyclerViewAdapter.ItemViewHolder>() {
    class ItemViewHolder(itemView :View):RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
       return ItemViewHolder(
           LayoutInflater.from(parent.context).inflate(
               R.layout.item_row,
               parent,
               false
           )
       )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val num = numbers[position]
        holder.itemView.apply {
            tvnumber.text=num
        }
    }

    override fun getItemCount()=numbers.size
}