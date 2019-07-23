package com.co.sunflowerslang.gamesapp.gamedetails

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.co.sunflowerslang.gamesapp.R
import com.co.sunflowerslang.gamesapp.data.Game
import com.co.sunflowerslang.gamesapp.extension.showBackArrowButton
import kotlinx.android.synthetic.main.activity_game_details.*
import java.text.NumberFormat

class GameDetailsActivity : AppCompatActivity(), GameDetailsContract.View {

    companion object {
        const val GAME_ID_TAG = "game-id"
    }

    private var presenter: GameDetailsPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_details)
        setSupportActionBar(toolbar)
        supportActionBar?.title = null
        showBackArrowButton(android.R.color.white)
        startPresenter()
    }

    private fun startPresenter() {
        val gameId = intent?.extras?.getString(GAME_ID_TAG)
        if (!gameId.isNullOrEmpty()) {
            presenter = GameDetailsPresenter(gameId)
            presenter?.setView(this)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun showGame(game: LiveData<Game>) {
        game.observe(this, Observer {
            text_sku.text = getString(R.string.text_sku, it.sku)
            text_name.text = it.name
            text_brand.text = it.universe
            text_kind.text = it.kind
            if (!it.downloads.isNullOrEmpty()) {
                text_download.text = getString(R.string.text_downloads_number, NumberFormat.getInstance().format(it.downloads!!.toLong()))
            }
            text_description.text = it.description
            rating_game.rating = it.rating?.toFloat() ?: 0F
            button_price.text = getString(R.string.text_price_quantity, it.price)
            Glide.with(this).load(it.imageUrl).into(image_character)
        })
    }

}