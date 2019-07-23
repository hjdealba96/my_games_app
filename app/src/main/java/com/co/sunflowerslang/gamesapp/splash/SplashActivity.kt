package com.co.sunflowerslang.gamesapp.splash

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.co.sunflowerslang.gamesapp.extension.startActivity
import com.co.sunflowerslang.gamesapp.SharedPreferencesManager
import com.co.sunflowerslang.gamesapp.allgames.AllGamesActivity
import com.co.sunflowerslang.gamesapp.onboarding.OnBoardingActivity

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