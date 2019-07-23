package com.co.sunflowerslang.gamesapp.onboarding

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.co.sunflowerslang.gamesapp.R
import com.co.sunflowerslang.gamesapp.SharedPreferencesManager
import com.co.sunflowerslang.gamesapp.allgames.AllGamesActivity
import com.co.sunflowerslang.gamesapp.extension.replaceFragment
import com.co.sunflowerslang.gamesapp.extension.replaceFragmentBackStack
import com.co.sunflowerslang.gamesapp.extension.startActivity
import com.co.sunflowerslang.gamesapp.onboarding.fragment.*

class OnBoardingActivity : AppCompatActivity(), OnBoardingContract.View, OnBoardingActions {

    private var presenter: OnBoardingPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding)
        presenter = OnBoardingPresenter(SharedPreferencesManager(this))
        presenter?.setView(this)
    }

    override fun onAttachFragment(fragment: Fragment?) {
        super.onAttachFragment(fragment)
        when (fragment) {
            is OnBoardingStepFragment -> fragment.setOnBoardingActions(this)
        }
    }

    override fun showFirstScreen() {
        replaceFragment(R.id.fragment_container, StepFragmentFactory.getStep(IStepFactory.StepType.FIRST))
    }

    override fun onStepDone(step: IStepFactory.StepType) {
        when (step) {
            IStepFactory.StepType.FIRST -> {
                replaceFragmentBackStack(
                    R.id.fragment_container,
                    StepFragmentFactory.getStep(IStepFactory.StepType.SECOND)
                )
            }
            IStepFactory.StepType.SECOND -> {
                replaceFragmentBackStack(
                    R.id.fragment_container,
                    StepFragmentFactory.getStep(IStepFactory.StepType.THIRD)
                )
            }
            IStepFactory.StepType.THIRD -> {
                presenter?.setStarted()
            }
        }
    }

    override fun showAllGames() {
        startActivity(AllGamesActivity::class.java)
        finish()
    }

}