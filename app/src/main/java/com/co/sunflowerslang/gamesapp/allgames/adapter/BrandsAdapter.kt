package com.co.sunflowerslang.gamesapp.allgames.adapter

import android.content.Context
import android.graphics.PorterDuff
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.co.sunflowerslang.gamesapp.R
import kotlinx.android.synthetic.main.item_brand_button.view.*

class BrandsAdapter(
    private val brands: List<String>,
    private val onFilterOptionClicked: (String) -> Unit,
    private val onClearFilterOptionClicked: () -> Unit
) : RecyclerView.Adapter<BrandsAdapter.ViewHolder>() {

    private val options = brands.toMutableList()
    private var lastCheckedButton: Button? = null

    init {
        options.add(0, "")
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_brand_button, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int = brands.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val game = options[position]
        if (position == 0) {
            holder.buttonUniverse.text = holder.itemView.context.getString(R.string.text_all_option)
        } else {
            holder.buttonUniverse.text = game
        }
        holder.bind(game, onFilterOptionClicked, onClearFilterOptionClicked, {
            if (lastCheckedButton != null) uncheckButton(lastCheckedButton!!, holder.itemView.context)
            checkButton(it, it.context)
            lastCheckedButton = it
        })

    }

    private fun checkButton(button: Button, context: Context) {
        applyColorFilter(android.R.color.white, R.color.colorPrimary, button, context)
    }

    private fun uncheckButton(button: Button, context: Context) {
        applyColorFilter(R.color.colorPrimary, android.R.color.white, button, context)
    }

    private fun applyColorFilter(textColor: Int, backgroundColor: Int, button: Button, context: Context) {
        button.setTextColor(context.getColor(textColor))
        button.background.setColorFilter(context.getColor(backgroundColor), PorterDuff.Mode.SRC)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val buttonUniverse: Button = itemView.button_brand
        fun bind(
            universe: String,
            onFilterOptionClicked: (String) -> Unit,
            onClearFilterOptionClicked: () -> Unit,
            onButtonClicked: (Button) -> Unit
        ) {
            buttonUniverse.setOnClickListener {
                onButtonClicked(buttonUniverse)
                if (adapterPosition == 0) {
                    onClearFilterOptionClicked()
                } else {
                    onFilterOptionClicked(universe)
                }
            }
        }

    }
}