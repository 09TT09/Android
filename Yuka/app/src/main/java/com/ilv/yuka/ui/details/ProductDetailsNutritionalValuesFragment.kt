package com.ilv.yuka.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ilv.yuka.databinding.DetailsTabNutritionalFactsBinding

class ProductDetailsNutritionalValuesFragment : Fragment() {

    lateinit var binding: DetailsTabNutritionalFactsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DetailsTabNutritionalFactsBinding.inflate(inflater, container, false)
        return binding.root
    }

}