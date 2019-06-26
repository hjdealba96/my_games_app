package com.co.gamesapp.onboarding.fragment

import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.co.gamesapp.R
import kotlinx.android.parcel.Parcelize
import kotlinx.android.synthetic.main.fragment_onboarding_first_step.*

class StepFragment : Fragment() {

    companion object {
        private const val STEP_TAG = "step"

        fun newInstance(step: Step): StepFragment {
            val arguments = Bundle()
            arguments.putParcelable(STEP_TAG, step)
            val fragment = StepFragment()
            fragment.arguments = arguments
            return fragment
        }

    }

    @Parcelize
    enum class Step(val value: Int) : Parcelable {
        FIRST_STEP(R.layout.fragment_onboarding_first_step),
        SECOND_STEP(R.layout.fragment_onboarding_second_step),
        THIRD_STEP(R.layout.fragment_onboarding_third_step)
    }

    interface IOnBoardingActions {
        fun onStepDone(step: Step)
    }

    private var onBoardingActions: IOnBoardingActions? = null

    private fun getStep() = arguments?.getParcelable(STEP_TAG) as Step

    fun setOnBoardingActions(onBoardingActions: IOnBoardingActions) {
        this.onBoardingActions = onBoardingActions
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
        inflater.inflate(getStep().value, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        button_submit.setOnClickListener {
            onBoardingActions?.onStepDone(getStep())
        }
    }

}