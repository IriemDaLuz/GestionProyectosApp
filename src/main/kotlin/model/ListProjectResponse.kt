package model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ListProjectResponse(
    @SerialName("proyectos") val projects: List<ProjectResponse>,
    @SerialName("total") val total: Int
)
