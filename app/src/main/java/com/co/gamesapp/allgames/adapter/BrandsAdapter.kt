package com.co.gamesapp.allgames.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.co.gamesapp.R
import kotlinx.android.synthetic.main.item_brand_button.view.*

class BrandsAdapter(
    private val brands: List<String>,
    private val onFilterOptionClicked: (String) -> Unit,
    private val onClearFilterOptionClicked: () -> Unit
) : RecyclerView.Adapter<BrandsAdapter.ViewHolder>() {

    private val options = brands.toMutableList()

    init {
        options.add(0, "All")
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_brand_button, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int = brands.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val game = options[position]
        holder.buttonUniverse.text = game
        holder.bind(game, onFilterOptionClicked, onClearFilterOptionClicked)

    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val buttonUniverse: Button = itemView.button_brand
        fun bind(universe: String, onFilterOptionClicked: (String) -> Unit, onClearFilterOptionClicked: () -> Unit) {
            buttonUniverse.setOnClickListener {
                if (adapterPosition == 0) {
                    onClearFilterOptionClicked()
                } else {
                    onFilterOptionClicked(universe)
                }
            }
        }
    }
}