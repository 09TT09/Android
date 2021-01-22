package com.ilv.yuka.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.ilv.yuka.R
import com.ilv.yuka.databinding.DetailsBinding
import com.ilv.yuka.model.Product
import com.ilv.yuka.model.ServerResponse
import com.ilv.yuka.network.NetworkManager
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import okhttp3.Dispatcher
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

class ProductDetailsFragment : Fragment() {

    lateinit var binding: DetailsBinding
    private lateinit var product: Product

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DetailsBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 1 - Récup code barre
        val barcode = ProductDetailsFragmentArgs.fromBundle(requireArguments()).barcode

        // 2) Faire la requête
        GlobalScope.launch {
            val response : ServerResponse = NetworkManager.api.getProduct(barcode).await()
            product = response.toProduct()

            withContext(Dispatchers.Main) {
                // 3) Modifier l'interface utilisateur
                val navHost = childFragmentManager.findFragmentById(R.id.product_details_nav_host) as NavHostFragment
                NavigationUI.setupWithNavController(binding.productDetailsBottomNav, navHost.navController)
            }
        }
    }

    fun getProduct() : Product = product

}

/*val navHost = childFragmentManager.findFragmentById(R.id.product_details_nav_host) as NavHostFragment
NavigationUI.setupWithNavController(binding.productDetailsBottomNav, navHost.navController)
val product = ProductDetailsFragmentArgs.fromBundle(requireArguments()).product
product.toString()*/