package com.ilv.yuka.ui

import android.os.Bundle
import androidx.annotation.ColorRes
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.add
import androidx.fragment.app.commit
import com.ilv.yuka.R
import com.ilv.yuka.databinding.ActivityMainBinding
import com.ilv.yuka.ui.details.ProductDetailsFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

    }

}

enum class NutritionQuantityType(@StringRes val label: Int, @ColorRes val color: Int) {
    LOW(
        label = R.string.tab_nutrition_item_quantity_low,
        color = R.color.nutrient_level_low
    ),
    MODERATE(
        label = R.string.tab_nutrition_item_quantity_moderate,
        color = R.color.nutrient_level_moderate
    ),
    HIGH(
        label = R.string.tab_nutrition_item_quantity_high,
        color = R.color.nutrient_level_high
    )
}