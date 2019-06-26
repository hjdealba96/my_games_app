package com.co.gamesapp.onboarding.fragment

object StepFragmentFactory : IStepFactory {
    override fun getStep(stepType: IStepFactory.StepType): OnBoardingStepFragment {
        return when (stepType) {
            IStepFactory.StepType.FIRST -> {
                FirstFragment.newInstance(stepType)
            }
            IStepFactory.StepType.SECOND -> {
                SecondStepFragment.newInstance(stepType)
            }
            IStepFactory.StepType.THIRD -> {
                ThirdStepFragment.newInstance(stepType)
            }
        }
    }
}