package model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class ListTaskResponse(
    @SerialName("tareas") val tasks: List<TaskResponse>,
    @SerialName("total") val total: Int
)

