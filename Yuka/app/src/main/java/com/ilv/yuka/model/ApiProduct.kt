package com.ilv.yuka.model
import com.google.gson.annotations.SerializedName

data class ServerResponse(
    @SerializedName("error")
    val error: String?,
    @SerializedName("response")
    val response: Response?
) {
    fun toProduct(): Product {
        return response?.run {
            Product(
                name = name ?: "",
                brand = brands?.joinToString(", ") ?: "",
                barcode = barcode,
                nutriscore = nutriScore ?: "",
                thumbnail = picture ?: "",
                quantity = quantity ?: "",
                countries = manufacturingCountries as List<String>? ?: listOfNotNull(),
                ingredients = ingredients as List<String>? ?: listOfNotNull(),
                allergens = allergens as List<String>? ?: listOfNotNull(),
                additives = additives?.map {
                    "${it.key} : ${it.value}"
                } ?: listOfNotNull(),
                nutritionFacts = nutritionFacts?.run {
                    NFs(
                        salt = NFI(
                            unit = salt?.unit ?: ""
                        )
                    )
                } ?: NFs(salt = NFI(""))
            )
        } ?: generateRandomProduct()
    }

    data class Response(
        @SerializedName("additives")
        val additives: Map<String, String>?,
        @SerializedName("allergens")
        val allergens: List<String?>?,
        @SerializedName("altName")
        val altName: String?,
        @SerializedName("barcode")
        val barcode: String,
        @SerializedName("brands")
        val brands: List<String?>?,
        @SerializedName("containsPalmOil")
        val containsPalmOil: Boolean?,
        @SerializedName("ingredients")
        val ingredients: List<String?>?,
        @SerializedName("manufacturingCountries")
        val manufacturingCountries: List<String?>?,
        @SerializedName("name")
        val name: String?,
        @SerializedName("novaScore")
        val novaScore: String?,
        @SerializedName("nutriScore")
        val nutriScore: String?,
        @SerializedName("nutritionFacts")
        val nutritionFacts: NutritionFacts?,
        @SerializedName("picture")
        val picture: String?,
        @SerializedName("quantity")
        val quantity: String?,
        @SerializedName("traces")
        val traces: List<String?>?
    )

    data class NutritionFacts(
        @SerializedName("calories")
        val calories: NutritionFact?,
        @SerializedName("carbohydrate")
        val carbohydrate: NutritionFact?,
        @SerializedName("energy")
        val energy: NutritionFact?,
        @SerializedName("fat")
        val fat: NutritionFact?,
        @SerializedName("fiber")
        val fiber: NutritionFact?,
        @SerializedName("proteins")
        val proteins: NutritionFact?,
        @SerializedName("salt")
        val salt: NutritionFact?,
        @SerializedName("saturatedFat")
        val saturatedFat: NutritionFact?,
        @SerializedName("servingSize")
        val servingSize: String?,
        @SerializedName("sodium")
        val sodium: NutritionFact?,
        @SerializedName("sugar")
        val sugar: NutritionFact?
    ) {
        data class NutritionFact(
            @SerializedName("per100g")
            val per100g: String?,
            @SerializedName("perServing")
            val perServing: String?,
            @SerializedName("unit")
            val unit: String?
        )
    }
}

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
)