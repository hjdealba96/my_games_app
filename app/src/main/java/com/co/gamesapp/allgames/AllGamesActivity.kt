package com.co.gamesapp.allgames

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.co.gamesapp.R
import com.co.gamesapp.allgames.adapter.GamesAdapter
import com.co.gamesapp.allgames.adapter.GamesAdapterBuilder
import com.co.gamesapp.allgames.adapter.BrandsAdapterBuilder
import com.co.gamesapp.allgames.presenter.AllGamesPresenter
import com.co.gamesapp.allgames.presenter.GamesListPresenter
import com.co.gamesapp.data.Game
import com.co.gamesapp.extension.unsuscribeObserver
import kotlinx.android.synthetic.main.activity_all_games.*

class AllGamesActivity : AppCompatActivity(), AllGamesContract.View {

    companion object {
        private const val FILTER_REQUEST_CODE = 1
    }

    private var presenter: AllGamesPresenter? = null
    private var newGamesListPresenter: GamesListPresenter? = null
    private var popularGamesListPresenter: GamesListPresenter? = null
    private var allGamesListPresenter: GamesListPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_games)
        setSupportActionBar(toolbar)
        swipe_refresh.setOnRefreshListener {
            presenter?.init()
        }
        presenter = AllGamesPresenter()
        presenter?.setView(this)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_all_games, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.filter -> {
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            FILTER_REQUEST_CODE -> {

            }
        }
    }

    private fun hideSwipeRefreshing() {
        swipe_refresh?.isRefreshing = false
    }

    override fun showAllBrands(brands: LiveData<List<String>>) {
        unsuscribeObserver(brands)
        brands.observe(this, Observer {
            hideSwipeRefreshing()

            val brandsAdapter = BrandsAdapterBuilder()
                .setBrands(it)
                .setOnFilterOptionClicked { universe ->
                    newGamesListPresenter?.filterByBrand(universe)
                    popularGamesListPresenter?.filterByBrand(universe)
                    allGamesListPresenter?.filterByBrand(universe)
                }
                .setOnClearFilterOptionClicked {
                    newGamesListPresenter?.clearFilter()
                    popularGamesListPresenter?.clearFilter()
                    allGamesListPresenter?.clearFilter()
                }
                .create()

            list_universes.apply {
                setHasFixedSize(true)
                adapter = brandsAdapter
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            }
        })
    }

    override fun showPopularGames(games: LiveData<List<Game>>) {
        unsuscribeObserver(games)
        games.observe(this, Observer {
            hideSwipeRefreshing()
            text_popular.text = getString(R.string.text_popular, it.size)
            popularGamesListPresenter = GamesListPresenter(it)
            val popularGamesAdapter = createGamesAdapter(
                GamesAdapter.LayoutType.POPULAR,
                popularGamesListPresenter!!
            ) { count ->
                text_all.text = getString(R.string.text_all, count)
            }
            list_popular_games.apply {
                setHasFixedSize(true)
                adapter = popularGamesAdapter
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            }
        })
    }


    override fun showNewGames(games: LiveData<List<Game>>) {
        unsuscribeObserver(games)
        games.observe(this, Observer {
            hideSwipeRefreshing()
            text_new.text = getString(R.string.text_new, it.size)
            newGamesListPresenter = GamesListPresenter(it)
            val newGamesAdapter = createGamesAdapter(
                GamesAdapter.LayoutType.POPULAR,
                popularGamesListPresenter!!
            ) { count ->
                text_new.text = getString(R.string.text_new, count)
            }
            list_new_games.apply {
                setHasFixedSize(true)
                adapter = newGamesAdapter
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            }

        })
    }

    override fun showAllGames(games: LiveData<List<Game>>) {
        unsuscribeObserver(games)
        games.observe(this, Observer {
            hideSwipeRefreshing()
            text_all.text = getString(R.string.text_all, it.size)
            allGamesListPresenter = GamesListPresenter(it)
            val allGamesAdapter = createGamesAdapter(
                GamesAdapter.LayoutType.POPULAR,
                popularGamesListPresenter!!
            ) { count ->
                text_all.text = getString(R.string.text_all, count)
            }
            list_all_games.apply {
                setHasFixedSize(true)
                adapter = allGamesAdapter
                layoutManager = GridLayoutManager(context, 2)
            }
        })
    }

    fun createGamesAdapter(
        type: GamesAdapter.LayoutType, presenter: GamesListPresenter, onDataSetChanged: (Int) -> Unit
    ) = GamesAdapterBuilder()
        .setLayoutType(type)
        .setPresenter(presenter)
        .setRequestManager(Glide.with(this))
        .setOnDataSetChanged(onDataSetChanged).create()

    override fun showFilteredGames(games: LiveData<List<Game>>) {
    }

}