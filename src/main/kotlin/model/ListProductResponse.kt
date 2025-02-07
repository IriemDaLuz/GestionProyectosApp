package model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ListProductResponse(
    @SerialName("productos") val products: List<ProductResponse>,
    @SerialName("total") val total: Int
)