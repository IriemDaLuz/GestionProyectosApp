package model
import kotlinx.serialization.Serializable

@Serializable
data class LoginRequests(
    val user: String,
    val passwd: String
)