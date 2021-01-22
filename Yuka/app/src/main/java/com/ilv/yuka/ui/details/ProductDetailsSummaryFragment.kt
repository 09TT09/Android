package com.ilv.yuka.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.ilv.yuka.R
import com.ilv.yuka.databinding.DetailsTabSummaryBinding
import com.ilv.yuka.model.generateRandomProduct
import com.ilv.yuka.utils.setTextOrEmpty
import com.ilv.yuka.utils.titleValue

class ProductDetailsSummaryFragment : Fragment() {

    lateinit var binding: DetailsTabSummaryBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DetailsTabSummaryBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun getProduct() =
            (requireParentFragment().requireParentFragment() as ProductDetailsFragment).getProduct()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(generateRandomProduct()) {
            binding.productDetailsName.text = name

            binding.productDetailsBrand.text = brand

            binding.productDetailsBarcode.titleValue(
                title = getString(R.string.tab_details_barcode),
                value = barcode
            )

            binding.productDetailsQuantity.titleValue(
                title = getString(R.string.tab_details_quantity),
                value = quantity
            )

            binding.productDetailsCountries.setTextOrEmpty(
                prefix = "Vendu en",
                list = countries,
                emptyText = "Aucun"
            )

            binding.productDetailsIngredients.setTextOrEmpty(
                prefix = "Ingrédients",
                list = ingredients,
                emptyText = "Aucun"
            )

            binding.productDetailsAllergens.setTextOrEmpty(
                prefix = "Allergènes",
                list = allergens,
                emptyText = "Aucun"
            )

            binding.productDetailsAdditives.setTextOrEmpty(
                prefix = "Additifs",
                list = additives,
                emptyText = "Aucun"
            )

            binding.productDetailsNutriscore.setImageResource(
                resources.getIdentifier(
                    "ic_nutriscore_${nutriscore.toLowerCase()}",
                    "drawable",
                    requireActivity().packageName
                )
            )

            Glide.with(requireActivity())
                .load(thumbnail)
                .into(binding.productDetailsImage)

        }
    }

}