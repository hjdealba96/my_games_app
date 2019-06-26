package com.co.gamesapp.gamesfilter.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.recyclerview.widget.RecyclerView
import com.co.gamesapp.R

class BrandsAdapter(
    private val brands: List<String>,
    private val clickListener: (Boolean, String) -> Unit
) : RecyclerView.Adapter<BrandsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_brand_check, parent, false) as CheckBox
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int = brands.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val brand = brands[position]
        holder.checkBrand.text = brand
        holder.checkBrand.setOnCheckedChangeListener { _, isChecked -> clickListener(isChecked, brand) }
    }

    class ViewHolder(val checkBrand: CheckBox) : RecyclerView.ViewHolder(checkBrand)
}