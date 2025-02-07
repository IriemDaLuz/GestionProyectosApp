package model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProgrammerResponse(
    @SerialName("id") val id: Int,
    @SerialName("nombre") val name: String,
    @SerialName("email") val email: String
)
