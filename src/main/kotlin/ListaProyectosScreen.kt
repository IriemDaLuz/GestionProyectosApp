import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator

class ListaProyectosScreen : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.current
        var filtro by remember { mutableStateOf("Todos") }
        val proyectos = listOf(
            Triple("Proyecto Alpha", "Propietario", "Activo"),
            Triple("Proyecto Beta", "Desconocido", "Activo"),
            Triple("Proyecto Gamma", "Desconocido", "Activo"),
            Triple("Proyecto Zeta", "Desconocido", "Finalizado: 12/01/2024"),
            Triple("Proyecto Delta", "Propietario", "Finalizado: 08/12/2023"),
            Triple("Proyecto Epsilon", "Desconocido", "Finalizado: 05/10/2023")
        )

        val proyectosActivos = proyectos.filter { it.third == "Activo" }

        val proyectosFiltrados = proyectosActivos.filter { proyecto ->
            filtro == "Todos" || (filtro == "Propietario" && proyecto.second.contains("Propietario"))
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFF121212)),
            contentAlignment = Alignment.TopCenter
        ) {
            Column(
                modifier = Modifier.padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Lista de Proyectos",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(10.dp))
                Row {
                    Button(
                        onClick = { filtro = "Todos" },
                        colors = ButtonDefaults.buttonColors(backgroundColor = if (filtro == "Todos") Color.Cyan else Color.Gray)
                    ) {
                        Text("Todos", color = Color.Black, fontWeight = FontWeight.Bold)
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                    Button(
                        onClick = { filtro = "Propietario" },
                        colors = ButtonDefaults.buttonColors(backgroundColor = if (filtro == "Propietario") Color.Cyan else Color.Gray)
                    ) {
                        Text("Propietario", color = Color.Black, fontWeight = FontWeight.Bold)
                    }
                }
                Spacer(modifier = Modifier.height(20.dp))
                LazyColumn(modifier = Modifier.fillMaxHeight()) {
                    items(proyectosFiltrados) { proyecto ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp)
                                .clickable { navigator?.push(ProyectoScreen(proyecto.first,proyecto.second)) },
                            backgroundColor = Color(0xFF1E1E1E),
                            elevation = 4.dp,
                            shape = RoundedCornerShape(8.dp)
                        ) {
                            Row{
                                Column(modifier = Modifier.padding(16.dp)) {
                                    Text(
                                        text = proyecto.first,
                                        color = Color.White,
                                        fontSize = 16.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                    Text(
                                        text = proyecto.second,
                                        color = Color.Gray,
                                        fontSize = 14.sp
                                    )
                                    Text(
                                        text = proyecto.third,
                                        color = Color.Green,
                                        fontSize = 14.sp,
                                        fontWeight = FontWeight.Medium
                                    )
                                }
                            }
                        Row(modifier = Modifier.padding(start = 580.dp, top = 40.dp)) {
                                Button(
                                    onClick = {
                                        navigator?.push(ProyectoScreen(proyecto.first,proyecto.second))
                                    },
                                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.Cyan),
                                ) {
                                    Text("Más detalles", color = Color.Black, fontWeight = FontWeight.Bold)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
