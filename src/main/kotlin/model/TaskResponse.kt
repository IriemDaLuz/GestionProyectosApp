package model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TaskResponse(
    @SerialName("id") val id: Int,
    @SerialName("nombre") val name: String,
    @SerialName("descripcion") val description: String,
    @SerialName("estimacion") val estimation: Int,
    @SerialName("fecha_creacion") val createdAt: String,
    @SerialName("fecha_finalizacion") val endDate: String,
    @SerialName("programador") val programmerId: Int,
    @SerialName("proyecto") val projectId: Int
)