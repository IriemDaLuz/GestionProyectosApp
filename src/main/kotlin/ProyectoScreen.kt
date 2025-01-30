import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.TopCenter
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator

class ProyectoScreen(private val nombreProyecto: String, private val owner: String) : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.current

        var showDialog by remember { mutableStateOf(false) }
        var selectedTask by remember { mutableStateOf("") }
        val programadores = listOf("Juan", "Ana", "Luis", "María", "Carlos")
        val selectedProgramadores = remember { mutableStateMapOf<String, Boolean>() }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFF121212))
                .padding(top = 20.dp),
            contentAlignment = TopCenter
        ) {
            Column(modifier = Modifier.padding(20.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "Detalles de ",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                    Text(
                        text = nombreProyecto,
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Cyan
                    )
                }
                Text(
                    text = owner,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    fontStyle = FontStyle.Italic,
                    color = Color.Gray
                )

                val tareas = listOf(
                    Triple("Tarea 1", "Descripción 1", "Urgente"),
                    Triple("Tarea 2", "Descripción 2", "Urgente"),
                    Triple("Tarea 3", "Descripción 3", "Urgente"),
                    Triple("Tarea 4", "Descripción 4", "Urgente"),
                )

                Spacer(modifier = Modifier.height(20.dp))
                LazyColumn(modifier = Modifier.fillMaxHeight()) {
                    items(tareas) { tarea ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp)
                                .clickable { navigator?.push(TareaScreen(tarea.first, tarea.second)) },
                            backgroundColor = Color(0xFF1E1E1E),
                            elevation = 4.dp,
                            shape = RoundedCornerShape(8.dp)
                        ) {
                            Column(modifier = Modifier.padding(16.dp)) {

                                Row {
                                    Column{
                                        Text(
                                        text = tarea.first,
                                        color = Color.White,
                                        fontSize = 16.sp,
                                        fontWeight = FontWeight.Bold
                                        )
                                        Text(
                                            text = tarea.second,
                                            color = Color.Gray,
                                            fontSize = 14.sp
                                        )
                                        Text(
                                            text = tarea.third,
                                            color = Color.Gray,
                                            fontSize = 14.sp,
                                            fontWeight = FontWeight.Medium
                                        )

                                    }
                                    Column(modifier = Modifier.padding(start = 400.dp)) {
                                        Text(
                                            text = "Programadores Asignados:",
                                            color = Color.White,
                                            fontSize = 14.sp
                                        )
                                        Text(
                                            text = "Maria, Juan, Luis",
                                            color = Color.Gray,
                                            fontSize = 14.sp
                                        )
                                        Button(
                                                onClick = {
                                                    selectedTask = tarea.first
                                                },
                                        colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
                                        modifier = Modifier.padding(top = 8.dp)
                                        ) {
                                        Text(
                                            "Modificar programadores",
                                            color = Color.Black,
                                            fontWeight = FontWeight.Bold,
                                            fontSize = 10.sp
                                        )
                                    }
                                    }
                                }

                            }
                        }
                    }
                }
            }
        }
    }
}
