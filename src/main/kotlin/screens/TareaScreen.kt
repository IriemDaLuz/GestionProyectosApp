package screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.List
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator

class TareaScreen(private val tareaNombre: String, private val tareaDescripcion: String) : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.current
        var nombreTarea by remember { mutableStateOf(tareaNombre) }
        var descripcionTarea by remember { mutableStateOf(tareaDescripcion) }
        var programadorSeleccionado by remember { mutableStateOf("") }
        val listaProgramadores = listOf("Maria", "Juan", "Luis", "Ana", "Carlos")

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFF121212)),
            contentAlignment = Alignment.TopCenter
        ) {
            Column(
                modifier = Modifier
                    .padding(24.dp)
                    .fillMaxHeight(),
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "Modificacion de ",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                    Text(
                        text = nombreTarea,
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Cyan
                    )
                }
                    Text("Nombre de la tarea:", color = Color.White)
                BasicTextField(
                    value = nombreTarea,
                    onValueChange = { nombreTarea = it },
                    modifier = Modifier
                        .background(Color(0xFF2E2E2E), RoundedCornerShape(8.dp))
                        .fillMaxWidth()
                        .padding(16.dp),
                    textStyle = LocalTextStyle.current.copy(color = Color.White)
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text("Descripción de la tarea:", color = Color.White)
                BasicTextField(
                    value = descripcionTarea,
                    onValueChange = { descripcionTarea = it },
                    modifier = Modifier
                        .background(Color(0xFF2E2E2E), RoundedCornerShape(8.dp))
                        .fillMaxWidth()
                        .padding(16.dp)
                        .height(100.dp),
                    textStyle = LocalTextStyle.current.copy(color = Color.White)
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text("Asignar Programador:", color = Color.White)
                var expanded by remember { mutableStateOf(false) }
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .background(Color(0xFF2E2E2E), RoundedCornerShape(8.dp))
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { expanded = !expanded }
                            .padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = if (programadorSeleccionado.isEmpty()) "Seleccionar programador" else programadorSeleccionado,
                            color = Color.White,
                            modifier = Modifier.weight(1f)
                        )
                        Icon(
                            imageVector = Icons.Filled.List,
                            contentDescription = "Dropdown",
                            tint = Color.White
                        )
                    }

                    DropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false }
                    ) {
                        listaProgramadores.forEach { programador ->
                            DropdownMenuItem(onClick = {
                                programadorSeleccionado = programador
                                expanded = false
                            }) {
                                Text(text = programador, color = Color.Black)
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Button(
                        onClick = {},
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color.Cyan),
                        modifier = Modifier.padding(8.dp)
                    ) {
                        Icon(Icons.Filled.Favorite, contentDescription = "Guardar", tint = Color.Black)
                        Text(" Guardar", color = Color.Black)
                    }

                    IconButton(
                        onClick = { navigator?.pop() },
                        modifier = Modifier.padding(8.dp)
                    ) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Regresar", tint = Color.White)
                    }
                }
            }
        }
    }
}
