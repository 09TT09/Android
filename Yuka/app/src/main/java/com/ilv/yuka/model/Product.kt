package com.ilv.yuka.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Product(
    val name: String,
    val brand: String,
    val barcode: String,
    val nutriscore: String,
    val thumbnail: String?,
    val quantity: String,
    val countries: List<String>,
    val ingredients: List<String>,
    val allergens: List<String>,
    val additives: List<String>,
    val nutritionFacts: NFs
) : Parcelable

@Parcelize
data class NFI (
    val unit: String
) : Parcelable

@Parcelize
data class NFs (
    val salt: NFI
) : Parcelable
/*
fun generateRandomProduct() = Product(
    name = "Petits pois et carottes",
    brand = "Cassegrain",
    barcode = "3083680085304",
    quantity = "400 g (280 g net égoutté)",
    thumbnail = "https://static.openfoodfacts.org/images/products/308/368/008/5304/front_fr.7.full.jpg",
    nutriscore = "B",
    countries = listOfNotNull("France", "Japon", "Suisse"),
    ingredients = listOfNotNull(
        "Petits pois 66%",
        "eau",
        "garniture 2,8% (salade, oignon grelot)",
        "sucre",
        "sel",
        "arôme naturel"
    ),
    allergens = emptyList(),
    additives = emptyList(),
    nutritionFacts = NFs(
        salt = NFI(
            unit = "g"
        )
    )
)*/