package com.co.sunflowerslang.gamesapp.allgames.adapter

import com.bumptech.glide.RequestManager
import com.co.sunflowerslang.gamesapp.allgames.presenter.GamesListPresenter
import com.co.sunflowerslang.gamesapp.data.Game

interface IGamesAdapterBuilder {
    fun setLayoutType(layoutType: GamesAdapter.LayoutType): IGamesAdapterBuilder
    fun setPresenter(presenter: GamesListPresenter): IGamesAdapterBuilder
    fun setRequestManager(requestManager: RequestManager): IGamesAdapterBuilder
    fun setOnDataSetChanged(onDataSetChanged: (Int) -> Unit): IGamesAdapterBuilder
    fun setOnClickListener(onClickListener: ((Game) -> Unit)): IGamesAdapterBuilder
    fun create(): GamesAdapter
}