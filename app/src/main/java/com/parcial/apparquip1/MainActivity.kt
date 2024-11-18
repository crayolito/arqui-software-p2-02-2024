package com.parcial.apparquip1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.parcial.apparquip1.Datos.DConexion
import com.parcial.apparquip1.Screen.PAlimentacion
import com.parcial.apparquip1.Screen.PCategoriEjer
import com.parcial.apparquip1.Screen.PHome
import com.parcial.apparquip1.Screen.PPlanEjercicio
import com.parcial.apparquip1.ui.theme.Typography
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.ui.text.style.TextAlign
import com.parcial.apparquip1.Presentacion.PCliente
import com.parcial.apparquip1.Screen.MementoScreen
import com.parcial.apparquip1.Screen.PCliente
import com.parcial.apparquip1.Screen.PInformeEntrenamiento
import com.parcial.apparquip1.Screen.PRutina


class MainActivity : ComponentActivity() {
    private lateinit var db: DConexion
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        db = DConexion(this)


        setContent {
            val navController = rememberNavController()
            val (screenWidth, screenHeight) = getScreenSize()
            MaterialTheme(
                colorScheme = lightColorScheme(
                    primary = Color(0xFF222222),
                    secondary = Color(0xFFff6D1F),
                    tertiary = Color(0xFFF5E7C6),
                ), typography = Typography
            ) {
                NavHost(navController = navController, startDestination = "home") {
                    composable("home") {
                        PHome(navController, screenWidth, screenHeight)
                    }
                    composable("cliente") {
                        PCliente(navController,screenWidth, screenHeight)
                        /*MementoScreen()*/
                    }
                    composable("plan_alimentacion") {
                        PAlimentacion(navController, screenWidth, screenHeight)
                    }
                    composable("categoria_ejercicio") {
                        PCategoriEjer(navController, screenWidth, screenHeight)
                    }
                    composable("plan_ejercicio") {
                        PPlanEjercicio(navController, screenWidth, screenHeight)
                    }
                    composable("plan_rutina") {
                        PRutina(navController, screenWidth, screenHeight)
                    }
                    composable("envio_rutina") {
                        PInformeEntrenamiento(navController, screenWidth, screenHeight)
                    }
                }
            }
        }
    }
}

@Composable
fun getScreenSize(): Pair<Dp, Dp> {
    val configuration = LocalConfiguration.current
    return Pair(configuration.screenWidthDp.dp, configuration.screenHeightDp.dp)
}

@Composable
fun DialogoCustom(messageText: String, show: Boolean, onDismissRequest: () -> Unit) {
    if (show) {
        AlertDialog(onDismissRequest = onDismissRequest, title = {
            Text(
                "MENSAJE DE ALERTA",
                style = MaterialTheme.typography.titleLarge,
                textAlign = TextAlign.Center
            )
        }, text = {
            Text(
                messageText,
                //"No hay categorias de ejercicio registradas, por favor añada.",
                style = MaterialTheme.typography.titleMedium,
                textAlign = TextAlign.Center
            )
        }, confirmButton = {})
    }
}