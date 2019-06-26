package com.co.gamesapp.allgames.adapter

import com.bumptech.glide.RequestManager
import com.co.gamesapp.allgames.presenter.GamesListPresenter
import com.co.gamesapp.data.Game

class GamesAdapterBuilder : IGamesAdapterBuilder {

    private lateinit var layoutType: GamesAdapter.LayoutType
    private lateinit var presenter: GamesListPresenter
    private lateinit var requestManager: RequestManager
    private var onDataSetChanged: ((Int) -> Unit)? = null
    private var onClickListener: ((Game) -> Unit)? = null

    override fun setLayoutType(layoutType: GamesAdapter.LayoutType): IGamesAdapterBuilder {
        this.layoutType = layoutType
        return this
    }

    override fun setPresenter(presenter: GamesListPresenter): IGamesAdapterBuilder {
        this.presenter = presenter
        return this
    }

    override fun setRequestManager(requestManager: RequestManager): IGamesAdapterBuilder {
        this.requestManager = requestManager
        return this
    }

    override fun setOnDataSetChanged(onDataSetChanged: (Int) -> Unit): IGamesAdapterBuilder {
        this.onDataSetChanged = onDataSetChanged
        return this
    }

    override fun setOnClickListener(onClickListener: (Game) -> Unit): IGamesAdapterBuilder {
        this.onClickListener = onClickListener
        return this
    }

    override fun create(): GamesAdapter =
        GamesAdapter(layoutType, presenter, requestManager, onDataSetChanged, onClickListener)

}