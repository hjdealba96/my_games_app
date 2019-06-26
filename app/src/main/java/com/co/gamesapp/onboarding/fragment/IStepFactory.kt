package com.co.gamesapp.onboarding.fragment

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

interface IStepFactory {
    @Parcelize
    enum class StepType : Parcelable { FIRST, SECOND, THIRD }

    fun getStep(stepType: StepType): OnBoardingStep
}