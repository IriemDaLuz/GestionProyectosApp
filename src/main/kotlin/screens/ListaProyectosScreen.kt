package screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
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
            Triple("Proyecto Beta", "@JavierMontilla", "Activo"),
            Triple("Proyecto Gamma", "@SantaMariaDR", "Activo"),
            Triple("Proyecto Zeta", "@LuisSantos", "Finalizado"),
            Triple("Proyecto Delta", "Propietario", "Finalizado"),
            Triple("Proyecto Epsilon", "@JavierMontilla", "Finalizado")
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
                Spacer(modifier = Modifier.height(20.dp))
                Row {
                    Text("Search ", color = Color.White, modifier = Modifier.padding(top = 5.dp))
                    BasicTextField(
                        value ="" ,
                        onValueChange = { },
                        modifier = Modifier
                            .background(Color(0xFF2E2E2E), RoundedCornerShape(8.dp))
                            .padding(5.dp),
                        textStyle = LocalTextStyle.current.copy(color = Color.White)
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    IconButton(onClick = { filtro = "Todos" }) {
                        Icon(
                            imageVector = Icons.Filled.List,
                            contentDescription = "Todos",
                            tint = if (filtro == "Todos") Color.Cyan else Color.Gray
                        )
                    }
                    IconButton(onClick = { filtro = "Propietario" }) {
                        Icon(
                            imageVector = Icons.Filled.Person,
                            contentDescription = "Propietario",
                            tint = if (filtro == "Propietario") Color.Cyan else Color.Gray
                        )
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
                        Row(horizontalArrangement = Arrangement.End, verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(15.dp)) {
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
                    item{
                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                            IconButton(
                                onClick = { navigator?.pop() },
                                modifier = Modifier.padding(16.dp),
                            ) {
                                Icon(
                                    Icons.Filled.ArrowBack,
                                    contentDescription = "Vuelve a casa vuelve",
                                    tint = Color.White
                                )
                            }
                        }
                    }
                }

            }
        }
    }
}
