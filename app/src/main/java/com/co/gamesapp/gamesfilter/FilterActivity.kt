package com.co.gamesapp.gamesfilter

import android.content.Intent
import android.os.Bundle
import android.widget.CheckBox
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.co.gamesapp.R
import com.co.gamesapp.data.FilterParameters
import com.co.gamesapp.data.PricesRange
import com.co.gamesapp.extension.showBackArrowButton
import com.co.gamesapp.gamesfilter.adapter.BrandsAdapter
import com.co.gamesapp.utils.numberToCurrency
import kotlinx.android.synthetic.main.activity_filter.*
import java.util.*

class FilterActivity : AppCompatActivity(), FilterContract.View {

    companion object {
        const val FILTER_PARAMETERS_TAG = "filter-parameters"
    }

    private var presenter: FilterPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_filter)
        setSupportActionBar(toolbar)
        showBackArrowButton(R.color.colorPrimary)
        setSortOptionsListener()
        setRatingsListener()
        setApplyListener()
        loadPresenter()
    }

    private fun loadPresenter() {
        presenter = FilterPresenter()
        presenter?.setView(this)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun checkDefaultSortOption() {
        radio_date_added.isChecked = true
    }

    override fun showAllBrands(brands: LiveData<List<String>>) {
        brands.observe(this, Observer {
            val brandsAdapter = BrandsAdapter(it) { isChecked, brand ->
                run {
                    if (isChecked) {
                        presenter?.addBrandToFilter(brand)
                    } else {
                        presenter?.removeBrandFromFilter(brand)
                    }
                }
            }
            list_brands.apply {
                setHasFixedSize(true)
                adapter = brandsAdapter
                layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            }
        })
    }

    override fun showPricesRanges(ranges: LiveData<PricesRange>) {
        ranges.observe(this, Observer {
            configurePricesSelector(it.minAmount, it.maxAmount)
            seek_min_price.setOnRangeSeekbarChangeListener { minValue, maxValue ->
                run {
                    val minPrice = minValue.toFloat()
                    val maxPrice = maxValue.toFloat()
                    showPricesRange(minPrice, maxPrice)
                    presenter?.setMinPriceToFilter(minPrice)
                    presenter?.setMaxPriceToFilter(maxPrice)
                }
            }

        })
    }

    private fun configurePricesSelector(minAmount: Float, maxAmount: Float) {
        seek_min_price.setMinValue(minAmount)
        seek_min_price.setMinStartValue(minAmount)
        seek_min_price.setMaxValue(maxAmount)
        seek_min_price.setMaxStartValue(maxAmount)
    }

    private fun showPricesRange(minPrice: Float, maxPrice: Float) {
        text_min_price_indicator.text = numberToCurrency(Locale.US, minPrice)
        text_min_price_indicator_max.text = numberToCurrency(Locale.US, maxPrice)
    }

    private fun setSortOptionsListener() {
        group_sort_options.setOnCheckedChangeListener { _, checkedId ->
            onSortOptionClicked(checkedId)
        }
    }

    private fun setRatingsListener() {
        check_one_rating.setOnClickListener {
            onRatingSeleted(check_one_rating)
        }
        check_two_rating.setOnClickListener {
            onRatingSeleted(check_two_rating)
        }
        check_three_rating.setOnClickListener {
            onRatingSeleted(check_three_rating)
        }
        check_four_rating.setOnClickListener {
            onRatingSeleted(check_four_rating)
        }
        check_five_rating.setOnClickListener {
            onRatingSeleted(check_five_rating)
        }
    }

    private fun setApplyListener() {
        button_apply.setOnClickListener {
            presenter?.generateFilter()
        }
    }

    private fun onSortOptionClicked(buttonId: Int) {
        when (buttonId) {
            R.id.radio_downloads -> {
                if (radio_downloads.isChecked) presenter?.setSortOptionByDownloads()
            }
            R.id.radio_date_added -> {
                if (radio_date_added.isChecked) presenter?.setSortOptionByDateAdded()
            }
            R.id.radio_price -> {
                if (radio_price.isChecked) presenter?.setSortOptionByPrice()
            }
        }
    }

    private fun onRatingSeleted(check: CheckBox) {
        val rating = when (check.id) {
            R.id.check_five_rating -> rating_five.rating.toInt()
            R.id.check_four_rating -> rating_four.rating.toInt()
            R.id.check_three_rating -> rating_three.rating.toInt()
            R.id.check_two_rating -> rating_two.rating.toInt()
            R.id.check_one_rating -> rating_one.rating.toInt()
            else -> 0
        }
        if (check.isChecked) {
            presenter?.addRatingToFilter(rating)
        } else {
            presenter?.removeRatingFromFilter(rating)
        }
    }

    override fun showAllGamesFiltered(filterParameters: FilterParameters) {
        val intent = Intent()
        intent.putExtra(FILTER_PARAMETERS_TAG, filterParameters)
        setResult(RESULT_OK, intent)
        finish()
    }

}