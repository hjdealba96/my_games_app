package com.co.sunflowerslang.gamesapp.extension

import android.content.Intent
import android.graphics.PorterDuff
import android.os.Bundle
import androidx.annotation.ColorRes
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import com.co.sunflowerslang.gamesapp.R

fun <T : AppCompatActivity> AppCompatActivity.startActivity(activityClass: Class<T>) {
    val intent = Intent(this, activityClass)
    startActivity(intent)
}

fun <T : AppCompatActivity> AppCompatActivity.startActivity(activityClass: Class<T>, extras: Bundle) {
    val intent = Intent(this, activityClass)
    intent.putExtras(extras)
    startActivity(intent)
}

fun <T : AppCompatActivity> AppCompatActivity.startActivityForResult(requestCode: Int, activityClass: Class<T>) {
    val intent = Intent(this, activityClass)
    startActivityForResult(intent, requestCode)
}

fun AppCompatActivity.replaceFragment(@IdRes container: Int, fragment: Fragment) {
    supportFragmentManager
        .beginTransaction()
        .replace(container, fragment, fragment.javaClass.simpleName)
        .commit()
}

fun AppCompatActivity.replaceFragmentBackStack(@IdRes container: Int, fragment: Fragment) {
    supportFragmentManager
        .beginTransaction()
        .setCustomAnimations(
            android.R.anim.slide_in_left,
            android.R.anim.slide_out_right,
            android.R.anim.slide_in_left,
            android.R.anim.slide_out_right
        )
        .replace(container, fragment, fragment.javaClass.simpleName)
        .addToBackStack(null)
        .commit()
}

fun AppCompatActivity.showBackArrowButton(@ColorRes color: Int) {
    val upArrow = resources.getDrawable(R.drawable.abc_ic_ab_back_material, null)
    upArrow.setColorFilter(ResourcesCompat.getColor(resources, color, null), PorterDuff.Mode.SRC_ATOP)
    supportActionBar?.setDisplayShowHomeEnabled(true)
    supportActionBar?.setDisplayHomeAsUpEnabled(true)
    supportActionBar?.setHomeAsUpIndicator(upArrow)
}

fun <T> AppCompatActivity.unsuscribeObserver(liveData: LiveData<T>) {
    if (liveData.hasObservers()) liveData.removeObservers(this)
}