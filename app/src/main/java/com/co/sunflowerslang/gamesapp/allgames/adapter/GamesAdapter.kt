package com.co.sunflowerslang.gamesapp.allgames.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.co.sunflowerslang.gamesapp.R
import com.co.sunflowerslang.gamesapp.allgames.GamesListContract
import com.co.sunflowerslang.gamesapp.allgames.presenter.GamesListPresenter
import com.co.sunflowerslang.gamesapp.data.Game
import kotlinx.android.synthetic.main.item_game.view.*

class GamesAdapter(
    private val layoutType: LayoutType,
    private val presenter: GamesListPresenter,
    private val requestManager: RequestManager,
    private val onDataSetChanged: ((Int) -> Unit)? = null,
    private val onClickListener: ((Game) -> Unit)? = null
) : RecyclerView.Adapter<GamesAdapter.ViewHolder>(), GamesListContract.View {

    init {
        presenter.setView(this)
    }

    enum class LayoutType(val value: Int) {
        NEWS((R.layout.item_new_game)),
        POPULAR(R.layout.item_popular_game),
        ALL(R.layout.item_game)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(layoutType.value, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int = presenter.getGamesCount()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val game = presenter.getGameAtPosition(position)
        holder.textCharacterName.text = game.name
        holder.textUniverseName.text = game.universe
        holder.bind(game, onClickListener)
        requestManager.load(game.imageUrl).into(holder.imageCharacter)
    }

    override fun updateView() {
        notifyDataSetChanged()
        onDataSetChanged?.invoke(itemCount)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageCharacter: ImageView = itemView.image_character
        val textCharacterName: TextView = itemView.text_character_name
        val textUniverseName: TextView = itemView.text_universe_name

        fun bind(game: Game, onClickListener: ((Game) -> Unit)?) {
            if (onClickListener != null) itemView.setOnClickListener { onClickListener(game) }
        }
    }
}