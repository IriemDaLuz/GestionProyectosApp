package model
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ListProgrammerResponse(
    @SerialName("programadores") val programmers: List<ProgrammerResponse>,
    @SerialName("total") val total: Int
)