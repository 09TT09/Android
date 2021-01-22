package com.ilv.yuka.ui.list

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ilv.yuka.R
import com.ilv.yuka.databinding.ListBinding
import com.ilv.yuka.model.Product
import com.ilv.yuka.model.generateRandomProduct

class ProductsListFragment : Fragment() {

    lateinit var binding: ListBinding

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = ListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = binding.productsList

        // Positionnement des cellules
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        val products = listOf(
                generateRandomProduct(),
                generateRandomProduct(),
                generateRandomProduct(),
                generateRandomProduct(),
                generateRandomProduct(),
                generateRandomProduct(),
                generateRandomProduct()
        )

        recyclerView.adapter = Adapter(products, object : OnProductCellListener {
            override fun onProductClicked(product: Product) {

                findNavController().navigate(
                        ProductsListFragmentDirections.actionProductsListFragmentToProductDetailsFragment(
                                product.barcode
                        )
                )

            }

        })

        binding.productsStartScan.setOnClickListener {
            startActivityForResult(Intent("com.google.zxing.client.android.SCAN")
                .putExtra("SCAN_FORMATS", "EAN_13"), 100)
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 100 && resultCode == Activity.RESULT_OK) {
            data?.getStringExtra("SCAN_RESULT")?.let { barcode ->
                findNavController().navigate(
                        ProductsListFragmentDirections.actionProductsListFragmentToProductDetailsFragment(
                                barcode
                        )
                )
            }
        }
    }

}

class Adapter(val products: List<Product>, val listener: OnProductCellListener) : RecyclerView.Adapter<ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
                LayoutInflater.from(parent.context)
                        .inflate(R.layout.item_cell_product, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = products[position]
        holder.bindProduct(product)

        holder.itemView.setOnClickListener {
            listener.onProductClicked(product)
        }
    }

    override fun getItemCount(): Int {
        return products.size
    }

}

class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {

    private val productNameTextView = v.findViewById<TextView>(R.id.cell_product_name)

    fun bindProduct(product: Product) {
        productNameTextView.text = product.name
    }

}

interface OnProductCellListener {
    fun onProductClicked(product: Product)
}

