package com.ilv.yuka.ui.details

import android.content.res.ColorStateList
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.fragment.app.Fragment
import com.ilv.yuka.R
import com.ilv.yuka.databinding.DetailsTabNutritionBinding
import com.ilv.yuka.databinding.DetailsTabNutritionItemBinding
import com.ilv.yuka.ui.NutritionQuantityType

class ProductDetailsNutritionFragment : Fragment() {

    lateinit var binding: DetailsTabNutritionBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DetailsTabNutritionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        editNutritionContentItem(
            view = binding.detailsTabNutritionFat,
            quantity = "0,8g",
            label = R.string.tab_nutrition_item_fat,
            quantityType = NutritionQuantityType.LOW
        )

        editNutritionContentItem(
            view = binding.detailsTabNutritionFattyAcid,
            quantity = "0,1g",
            label = R.string.tab_nutrition_item_fatty_acid,
            quantityType = NutritionQuantityType.HIGH
        )

        editNutritionContentItem(
            view = binding.detailsTabNutritionSugar,
            quantity = "5,2g",
            label = R.string.tab_nutrition_item_quantity_sugar,
            quantityType = NutritionQuantityType.MODERATE
        )

        editNutritionContentItem(
            view = binding.detailsTabNutritionSalt,
            quantity = "0,75g",
            label = R.string.tab_nutrition_item_quantity_salt,
            quantityType = NutritionQuantityType.HIGH
        )
    }


    private fun editNutritionContentItem(
        view: DetailsTabNutritionItemBinding,
        quantity: String,
        @StringRes label: Int,
        quantityType: NutritionQuantityType
    ) {
        with(view) {
            // Le texte
            nutritionItemLabel.text = getString(
                R.string.tab_nutrition_item,
                quantity,
                getString(label),
                getString(quantityType.label)
            )

            // Le rond
            DrawableCompat.setTintList(
                nutritionItemLevel.background,
                ColorStateList.valueOf(
                    ContextCompat.getColor(requireActivity(), quantityType.color)
                )
            )
        }
    }

}