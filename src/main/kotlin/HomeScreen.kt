import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator

class HomeScreen : Screen {
    @Composable
    override fun Content() {
        var username by remember { mutableStateOf("Fabio") }
        val navigator = LocalNavigator.current
        var filtro by remember { mutableStateOf("Activo") }


        val proyectos = listOf(
            Triple("Proyecto Alpha", "Propietario", "Activo"),
            Triple("Proyecto Beta", "@JavierMontilla", "Activo"),
            Triple("Proyecto Gamma", "@JavierMontilla", "Activo"),
            Triple("Proyecto Zeta", "@LuisSantos", "Finalizado"),
            Triple("Proyecto Delta", "Propietario", "Finalizado"),
            Triple("Proyecto Epsilon", "@JavierMontilla", "Finalizado")
        )

        val proyectosActivos = proyectos.filter { it.third == "Activo" }

        val proyectosFiltrados = proyectosActivos.filter { proyecto ->
            filtro == "Historial" && proyecto.third.contains("Finalizado")|| (filtro == "Activo" && proyecto.third.contains("Activo"))
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFF121212)),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier.padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row {
                    Text(
                        text = "Hola, ",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                    Text(
                        text = username,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }
                Text(
                    text = "Gestor",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    fontStyle = FontStyle.Italic,
                    color = Color.Gray
                )
                Spacer(modifier = Modifier.height(10.dp))
                Row {
                    Button(
                        onClick = { filtro = "Activo" },
                        colors = ButtonDefaults.buttonColors(backgroundColor = if (filtro == "Activo") Color.Cyan else Color.Gray)
                    ) {
                        Text("Activo", color = Color.Black, fontWeight = FontWeight.Bold)
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                    Button(
                        onClick = { filtro = "Historial" },
                        colors = ButtonDefaults.buttonColors(backgroundColor = if (filtro == "Historial") Color.Cyan else Color.Gray)
                    ) {
                        Text("Historial", color = Color.Black, fontWeight = FontWeight.Bold)
                    }
                }
                Spacer(modifier = Modifier.height(20.dp))
                LazyColumn(
                    modifier = Modifier.height(300.dp)
                ) {
                    items(proyectosFiltrados) { proyecto ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp),
                            backgroundColor = Color(0xFF1E1E1E),
                            elevation = 4.dp,
                            shape = RoundedCornerShape(8.dp)
                        ) {
                            Column(modifier = Modifier.padding(16.dp)) {
                                Row {
                                    Text(
                                        text = proyecto.first,
                                        color = Color.White,
                                        fontSize = 16.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                    Text(
                                        text = proyecto.third,
                                        color = Color.Gray,
                                        fontSize = 12.sp,
                                        fontWeight = FontWeight.Medium,
                                        modifier = Modifier.padding(start = 400.dp)
                                    )
                                }

                                Text(
                                    text = proyecto.second,
                                    color = Color.Gray,
                                    fontSize = 14.sp
                                )
                            }
                        }
                    }
                }
                Row{
                    Button(
                        onClick = {
                            navigator?.push(ListaProyectosScreen())
                        },
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
                        modifier = Modifier.padding(25.dp)
                    ) {
                        Text("Ver Todos", color = Color.Black, fontWeight = FontWeight.Bold)
                    }
                    Button(
                        onClick = {
                            navigator?.pop()
                        },
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color.Cyan),
                        modifier = Modifier.padding(25.dp)
                    ) {
                        Text("Desconectar", color = Color.Black, fontWeight = FontWeight.Bold)
                    }
                }

            }

        }

    }
}
