import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.List
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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

        val proyectosFiltrados = proyectos.filter { proyecto ->
            (filtro == "Activo" && proyecto.third == "Activo") ||
                    (filtro == "Historial" && proyecto.third == "Finalizado")
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFF121212))
                .fillMaxHeight(),
            contentAlignment = Alignment.TopStart
        ) {
            Column(
                modifier = Modifier.padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(50.dp)
                            .clip(CircleShape)
                            .background(Color.Gray)
                    )

                    Spacer(modifier = Modifier.width(10.dp))
                    Column(modifier = Modifier.weight(1f)) {
                        Text(text = username, fontSize = 16.sp, color = Color.White)
                        Text(
                            text = "Gestor",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            fontStyle = FontStyle.Italic,
                            color = Color.Gray
                        )
                    }
                    Row {
                        IconButton(onClick = { filtro = "Activo" }) {
                            Icon(
                                imageVector = Icons.Filled.List,
                                contentDescription = "Activo",
                                tint = if (filtro == "Activo") Color.Cyan else Color.Gray
                            )
                        }
                        IconButton(onClick = { filtro = "Historial" }) {
                            Icon(
                                imageVector = Icons.Filled.CheckCircle,
                                contentDescription = "Historial",
                                tint = if (filtro == "Historial") Color.Cyan else Color.Gray
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))
                LazyColumn(
                    modifier = Modifier.height(300.dp).weight(1f)
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
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    Text(
                                        text = proyecto.first,
                                        color = Color.White,
                                        fontSize = 16.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                    Spacer(modifier = Modifier.weight(1f))
                                    Text(
                                        text = proyecto.third,
                                        color = if (proyecto.third == "Activo") Color.Green else Color.Red,
                                        fontSize = 12.sp,
                                        fontWeight = FontWeight.Medium
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
                Row {
                    Button(
                        onClick = { navigator?.push(ListaProyectosScreen()) },
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color.Cyan),
                        modifier = Modifier.padding(vertical = 16.dp)
                    ) {
                        Text("Explorar Proyectos", color = Color.Black, fontWeight = FontWeight.Bold)
                    }

                    Spacer(modifier = Modifier.weight(1f))
                    IconButton(
                        onClick = { navigator?.pop() },
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Icon(Icons.Filled.ExitToApp, contentDescription = "Logout", tint = Color.White)
                    }
                }
            }
        }
    }
}