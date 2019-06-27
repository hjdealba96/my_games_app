package com.co.gamesapp.allgames

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.co.gamesapp.extension.startActivityForResult
import com.co.gamesapp.R
import com.co.gamesapp.allgames.adapter.GamesAdapter
import com.co.gamesapp.allgames.adapter.GamesAdapterBuilder
import com.co.gamesapp.allgames.adapter.BrandsAdapterBuilder
import com.co.gamesapp.allgames.presenter.AllGamesPresenter
import com.co.gamesapp.allgames.presenter.GamesListPresenter
import com.co.gamesapp.data.FilterParameters
import com.co.gamesapp.data.Game
import com.co.gamesapp.extension.unsuscribeObserver
import com.co.gamesapp.gamesfilter.FilterActivity
import kotlinx.android.synthetic.main.activity_all_games.*
import kotlinx.android.synthetic.main.activity_all_games.toolbar

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
                startActivityForResult(FILTER_REQUEST_CODE, FilterActivity::class.java)
            }
            R.id.clear_filter -> {
                text_all.text = null
                toolbar.menu.findItem(R.id.filter).isVisible = true
                item.isVisible = false
                setScrollPositionToStart()
                setSwipeRefreshEnabled(true)
                showSections()
                presenter?.loadGamesList()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            FILTER_REQUEST_CODE -> {
                val filterParameters: FilterParameters? =
                    data?.extras?.getParcelable(FilterActivity.FILTER_PARAMETERS_TAG)
                if (filterParameters != null) {
                    presenter?.filterGames(filterParameters)
                }
            }
        }
    }

    private fun setScrollPositionToStart() {
        scroll_content.smoothScrollTo(0, 0)
    }

    private fun hideSwipeRefreshing() {
        swipe_refresh?.isRefreshing = false
    }

    private fun setSwipeRefreshEnabled(state: Boolean) {
        swipe_refresh.isEnabled = state
    }

    private fun changeBrandsSectionVisibility(visibility: Int) {
        list_universes.visibility = visibility
    }

    private fun changeNewGamesSectionVisibilty(visibility: Int) {
        text_new.visibility = visibility
        list_new_games.visibility = visibility
        line_text_new.visibility = visibility
    }

    private fun changePopularGamesSectionVisibilty(visibility: Int) {
        text_popular.visibility = visibility
        list_popular_games.visibility = visibility
        line_text_popular.visibility = visibility
    }

    private fun showSections() {
        changeBrandsSectionVisibility(View.VISIBLE)
        changePopularGamesSectionVisibilty(View.VISIBLE)
        changeNewGamesSectionVisibilty(View.VISIBLE)
    }

    private fun hiddenSections() {
        changeBrandsSectionVisibility(View.GONE)
        changeNewGamesSectionVisibilty(View.GONE)
        changePopularGamesSectionVisibilty(View.GONE)
    }

    private fun createGamesAdapter(
        type: GamesAdapter.LayoutType, presenter: GamesListPresenter, onDataSetChanged: (Int) -> Unit
    ) = GamesAdapterBuilder()
        .setLayoutType(type)
        .setPresenter(presenter)
        .setRequestManager(Glide.with(this))
        .setOnDataSetChanged(onDataSetChanged).create()

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

    override fun showNewGames(games: LiveData<List<Game>>) {
        unsuscribeObserver(games)
        games.observe(this, Observer {
            hideSwipeRefreshing()
            text_new.text = getString(R.string.text_new, it.size)
            newGamesListPresenter = GamesListPresenter(it)
            val newGamesAdapter = createGamesAdapter(
                GamesAdapter.LayoutType.NEWS,
                newGamesListPresenter!!
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

    fun showGamesList(games: LiveData<List<Game>>) {
        games.observe(this, Observer {

        })
    }

    override fun showPopularGames(games: LiveData<List<Game>>) {
        unsuscribeObserver(games)
        games.observe(this, Observer {
            hideSwipeRefreshing()
            text_popular.text = getString(R.string.text_popular, it.size)
            popularGamesListPresenter = GamesListPresenter(it)
            val popularGamesAdapter = createGamesAdapter(
                GamesAdapter.LayoutType.POPULAR, popularGamesListPresenter!!
            ) { count ->
                text_popular.text = getString(R.string.text_popular, count)
            }
            list_popular_games.apply {
                setHasFixedSize(true)
                adapter = popularGamesAdapter
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
                GamesAdapter.LayoutType.ALL,
                allGamesListPresenter!!
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

    override fun showFilteredGames(games: LiveData<List<Game>>) {
        setScrollPositionToStart()
        toolbar.menu.findItem(R.id.filter).isVisible = false
        toolbar.menu.findItem(R.id.clear_filter).isVisible = true
        setSwipeRefreshEnabled(false)
        hiddenSections()
        unsuscribeObserver(games)
        games.observe(this, Observer {
            hideSwipeRefreshing()
            text_all.text = getString(R.string.text_filtered, it.size)
            allGamesListPresenter = GamesListPresenter(it)
            val allGamesAdapter = createGamesAdapter(
                GamesAdapter.LayoutType.ALL,
                allGamesListPresenter!!
            ) { count ->
                text_all.text = getString(R.string.text_filtered, count)
            }
            list_all_games.apply {
                setHasFixedSize(true)
                adapter = allGamesAdapter
                layoutManager = GridLayoutManager(context, 2)
            }
        })
    }

}