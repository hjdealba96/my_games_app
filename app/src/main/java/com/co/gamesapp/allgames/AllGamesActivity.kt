package com.co.gamesapp.allgames

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.co.gamesapp.extension.startActivity
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
import com.co.gamesapp.gamedetails.GameDetailsActivity
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
            presenter?.loadGamesList()
        }
        loadPresenter()
    }

    private fun loadPresenter() {
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
                item.isVisible = false
                showDefaultGamesSections()
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

    private fun showDefaultGamesSections() {
        text_all.text = null
        toolbar.menu.findItem(R.id.filter).isVisible = true
        setScrollPositionToStart()
        setSwipeRefreshEnabled(true)
        showListSections()
        presenter?.loadGamesList()
    }

    private fun showFilteredGamesSection() {
        toolbar.menu.findItem(R.id.filter).isVisible = false
        toolbar.menu.findItem(R.id.clear_filter).isVisible = true
        setScrollPositionToStart()
        setSwipeRefreshEnabled(false)
        hidenListSections()
    }

    private fun showSectionIndicators(textView: TextView, separator: View) {
        textView.visibility = View.VISIBLE
        separator.visibility = View.VISIBLE
    }

    private fun hiddeSectionIndicators(textView: TextView, separator: View) {
        textView.visibility = View.GONE
        separator.visibility = View.GONE
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
        separator_new_games.visibility = visibility
    }

    private fun changePopularGamesSectionVisibilty(visibility: Int) {
        text_popular.visibility = visibility
        list_popular_games.visibility = visibility
        separator_popular_games.visibility = visibility
    }

    private fun showListSections() {
        changeBrandsSectionVisibility(View.VISIBLE)
        changePopularGamesSectionVisibilty(View.VISIBLE)
        changeNewGamesSectionVisibilty(View.VISIBLE)
    }

    private fun hidenListSections() {
        changeBrandsSectionVisibility(View.GONE)
        changeNewGamesSectionVisibilty(View.GONE)
        changePopularGamesSectionVisibilty(View.GONE)
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

    private fun showGameDetailsScreen(gameId: String) {
        val extras = Bundle()
        extras.putString(GameDetailsActivity.GAME_ID_TAG, gameId)
        startActivity(GameDetailsActivity::class.java, extras)
    }

    private fun populateGameList(
        list: RecyclerView,
        listLayoutManager: RecyclerView.LayoutManager,
        type: GamesAdapter.LayoutType,
        presenter: GamesListPresenter,
        onDataSetChanged: (Int) -> Unit,
        onClickListener: (Game) -> Unit
    ) {
        val listAdapter = GamesAdapterBuilder()
            .setLayoutType(type)
            .setPresenter(presenter)
            .setRequestManager(Glide.with(this))
            .setOnClickListener(onClickListener)
            .setOnDataSetChanged(onDataSetChanged).create()
        list.apply {
            setHasFixedSize(true)
            adapter = listAdapter
            layoutManager = listLayoutManager
        }

    }

    override fun showNewGames(games: LiveData<List<Game>>) {
        unsuscribeObserver(games)
        games.observe(this, Observer {
            hideSwipeRefreshing()
            if (it.isNotEmpty()) {
                showSectionIndicators(text_new, separator_new_games)
                text_new.text = getString(R.string.text_new, it.size)
            } else {
                hiddeSectionIndicators(text_new, separator_new_games)
            }
            newGamesListPresenter = GamesListPresenter(it)
            populateGameList(
                list_new_games,
                LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false),
                GamesAdapter.LayoutType.NEWS,
                newGamesListPresenter!!,
                { count ->
                    if (count > 0) {
                        showSectionIndicators(text_new, separator_new_games)
                        text_new.text = getString(R.string.text_new, count)
                    } else {
                        hiddeSectionIndicators(text_new, separator_new_games)
                    }
                }, { game ->
                    showGameDetailsScreen(game.objectId)
                }
            )
        })
    }

    override fun showPopularGames(games: LiveData<List<Game>>) {
        unsuscribeObserver(games)
        games.observe(this, Observer {
            hideSwipeRefreshing()
            if (it.isNotEmpty()) {
                showSectionIndicators(text_popular, separator_popular_games)
                text_popular.text = getString(R.string.text_popular, it.size)
            } else {
                hiddeSectionIndicators(text_popular, separator_popular_games)
            }
            popularGamesListPresenter = GamesListPresenter(it)
            populateGameList(
                list_popular_games,
                LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false),
                GamesAdapter.LayoutType.POPULAR,
                popularGamesListPresenter!!,
                { count ->
                    if (count > 0) {
                        showSectionIndicators(text_popular, separator_popular_games)
                        text_all.text = getString(R.string.text_popular, count)
                    } else {
                        hiddeSectionIndicators(text_popular, separator_popular_games)
                    }
                }, { game ->
                    showGameDetailsScreen(game.objectId)
                }
            )
        })
    }

    private fun populateAllGamesList(games: LiveData<List<Game>>, textIndicatorId: Int) {
        unsuscribeObserver(games)
        games.observe(this, Observer {
            hideSwipeRefreshing()
            if (it.isNotEmpty()) {
                showSectionIndicators(text_all, separator_all_games)
                text_all.text = getString(textIndicatorId, it.size)
            } else {
                hiddeSectionIndicators(text_all, separator_all_games)
            }
            allGamesListPresenter = GamesListPresenter(it)
            populateGameList(
                list_all_games,
                GridLayoutManager(this, 2),
                GamesAdapter.LayoutType.ALL,
                allGamesListPresenter!!,
                { count ->
                    if (count > 0) {
                        showSectionIndicators(text_all, separator_all_games)
                        text_all.text = getString(textIndicatorId, count)
                    } else {
                        hiddeSectionIndicators(text_all, separator_all_games)
                    }
                }, { game ->
                    showGameDetailsScreen(game.objectId)
                }
            )

        })
    }

    override fun showAllGames(games: LiveData<List<Game>>) {
        populateAllGamesList(games, R.string.text_all)
    }

    override fun showFilteredGames(games: LiveData<List<Game>>) {
        showFilteredGamesSection()
        populateAllGamesList(games, R.string.text_filtered)
    }

}