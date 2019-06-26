package com.co.gamesapp.onboarding.fragment

interface OnBoardingActions {
    fun onStepDone(step: IStepFactory.StepType)
}