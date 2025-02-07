package network

import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import model.ListProjectResponse
import model.ProjectResponse
import network.NetworkUtils.httpClient

suspend fun getProjects(estado: String = "todos"): List<ProjectResponse> {
    val url = "http://127.0.0.1:5000/proyecto/proyectos?estado=$estado"
    return try {
        val response: HttpResponse = httpClient.get(url) {
            contentType(ContentType.Application.Json)
        }
        if (response.status == HttpStatusCode.OK) {
            response.body<ListProjectResponse>().projects
        } else {
            emptyList()
        }
    } catch (e: Exception) {
        println("Error al obtener proyectos: ${e.message}")
        emptyList()
    }
}
