package model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProductResponse(
    @SerialName("id") val id: Int,
    @SerialName("nombre") val name: String,
    @SerialName("descripcion") val description: String,
    @SerialName("fecha_creacion") val createdAt: String,
    @SerialName("fecha_inicio") val startDate: String,
    @SerialName("fecha_finalizacion") val endDate: String,
    @SerialName("cliente") val clientId: Int
)