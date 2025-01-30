import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusEvent
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

        val projects = listOf(
            Triple("Proyecto Alpha", "jksajsdajhsad", "Activo"),
            Triple("Proyecto Beta", "jksajsdajhsad", "Activo"),
            Triple("Proyecto Gamma", "jksajsdajhsad", "Activo"),
            Triple("Proyecto Zeta", "jksajsdajhsad", "Finalizado: 12/01/2024"),
            Triple("Proyecto Delta", "jksajsdajhsad", "Finalizado: 08/12/2023"),
            Triple("Proyecto Epsilon", "jksajsdajhsad", "Finalizado: 05/10/2023")
        )

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
                Spacer(modifier = Modifier.height(20.dp))

                LazyColumn(
                    modifier = Modifier.height(300.dp)
                ) {
                    items(projects) { project ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp),
                            backgroundColor = Color(0xFF1E1E1E),
                            elevation = 4.dp,
                            shape = RoundedCornerShape(8.dp)
                        ) {
                            Column(modifier = Modifier.padding(16.dp)) {
                                Text(
                                    text = project.first,
                                    color = Color.White,
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Bold
                                )
                                Text(
                                    text = project.second,
                                    color = Color.Gray,
                                    fontSize = 14.sp
                                )
                                Text(
                                    text = project.third,
                                    color = if (project.third == "Activo") Color.Green else Color.Red,
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Medium
                                )
                            }
                        }
                    }
                }


                Spacer(modifier = Modifier.height(40.dp))
                Button(
                    onClick = {
                        navigator?.pop()
                    },
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.Cyan),
                ) {
                    Text("Desconectar", color = Color.Black, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}