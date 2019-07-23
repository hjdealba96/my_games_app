package com.co.sunflowerslang.gamesapp.onboarding.fragment

import androidx.fragment.app.Fragment

abstract class OnBoardingStepFragment : Fragment(), OnBoardingStep {

    companion object {
        const val STEP_TAG = "step"
    }

    private var onBoardingActions: OnBoardingActions? = null

    fun setOnBoardingActions(onBoardingActions: OnBoardingActions) {
        this.onBoardingActions = onBoardingActions
    }

    private fun getStep() = arguments?.getParcelable(STEP_TAG) as IStepFactory.StepType

    final override fun submit() {
        onBoardingActions?.onStepDone(getStep())
    }

}