package com.co.gamesapp.onboarding

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.co.gamesapp.R
import com.co.gamesapp.SharedPreferencesManager
import com.co.gamesapp.allgames.AllGamesActivity
import com.co.gamesapp.extension.replaceFragment
import com.co.gamesapp.extension.replaceFragmentBackStack
import com.co.gamesapp.extension.startActivity
import com.co.gamesapp.onboarding.fragment.StepFragment

class OnBoardingActivity : AppCompatActivity(), OnBoardingContract.View, StepFragment.IOnBoardingActions {

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
            is StepFragment -> fragment.setOnBoardingActions(this)
        }
    }

    override fun showFirstScreen() {
        replaceFragment(R.id.fragment_container, StepFragment.newInstance(StepFragment.Step.FIRST_STEP))
    }

    override fun onStepDone(step: StepFragment.Step) {
        when (step) {
            StepFragment.Step.FIRST_STEP -> {
                replaceFragmentBackStack(
                    R.id.fragment_container,
                    StepFragment.newInstance(StepFragment.Step.SECOND_STEP)
                )
            }
            StepFragment.Step.SECOND_STEP -> {
                replaceFragmentBackStack(
                    R.id.fragment_container,
                    StepFragment.newInstance(StepFragment.Step.THIRD_STEP)
                )
            }
            StepFragment.Step.THIRD_STEP -> {
                presenter?.setStarted()
            }
        }
    }

    override fun showAllGames() {
        startActivity(AllGamesActivity::class.java)
        finish()
    }

}