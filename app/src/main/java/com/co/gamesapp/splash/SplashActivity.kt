package com.co.gamesapp.splash

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.co.gamesapp.extension.startActivity
import com.co.gamesapp.SharedPreferencesManager
import com.co.gamesapp.allgames.AllGamesActivity
import com.co.gamesapp.onboarding.OnBoardingActivity

class SplashActivity : AppCompatActivity(), SplashContract.View {

    private var presenter: SplashPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = SplashPresenter(SharedPreferencesManager(this))
        presenter?.setView(this)
    }

    override fun showOnBoardingScreen() {
        startActivity(OnBoardingActivity::class.java)
        finish()
    }

    override fun showAllGamesScreen() {
        startActivity(AllGamesActivity::class.java)
        finish()
    }

}