package com.parcial.apparquip1.Screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.navigation.NavHostController
import com.parcial.apparquip1.Datos.entidades.Cliente
import com.parcial.apparquip1.Datos.entidades.Rutina
import com.parcial.apparquip1.DialogoCustom
import com.parcial.apparquip1.R
import com.parcial.apparquip1.Presentacion.PInformeEntrenamiento
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PInformeEntrenamiento(navController: NavHostController, screenWidth: Dp, screenHeight: Dp) {
    val context = LocalContext.current
    val pInformeEntrenamiento = PInformeEntrenamiento(context);
    val focusManager = LocalFocusManager.current
    var pdfPath by remember { mutableStateOf<String?>(null) }

    var rutinas: List<Rutina> by remember { mutableStateOf(pInformeEntrenamiento.obtenerRutinas()) }
    var clientes: List<Cliente> by remember { mutableStateOf(pInformeEntrenamiento.obtenerClientes()) }


    var defaultRutina = Rutina(id = 0, idPlanAlimentacion = 0, titulo = "")
    var defaultCliente = Cliente(
        id = 0,
        nombre = "",
        telefono = "",
        email = "",
        meta = "",
        caracteristicas = "",
        altura = 0.0,
        peso = 0.0,
        imc = 0.0,
        edad = 0,
        genero = "",
        ocupacion = "",
        horarioPreferido = "",
        nivelActividad = "",
        experienciaEjercicio = "",
        tipoObjetivo = "",
        restriccionesSalud = "",
        alergias = "",
        necesitaRutinaCasa = false,
        extra = ""
    )


    var selectRutina: Rutina by remember { mutableStateOf(rutinas.firstOrNull() ?: defaultRutina) }
    var selectCliente: Cliente by remember {
        mutableStateOf(
            clientes.firstOrNull() ?: defaultCliente
        )
    }

    // DIALOGO DE ALERTA
    var dialogAbierto by rememberSaveable { mutableStateOf(false) }
    var messageText: String by remember { mutableStateOf("") }
    DialogoCustom(messageText = messageText,
        show = dialogAbierto,
        onDismissRequest = { dialogAbierto = false })

    Scaffold(
        content = { paddingValues ->
            Box(
                modifier = Modifier
                    .size(screenWidth, screenHeight)
                    .fillMaxSize()
                    .clickable(indication = null,
                        interactionSource = remember { MutableInteractionSource() },
                        onClick = {
                            focusManager.clearFocus()
                        })
                    .padding(paddingValues)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(
                            horizontal = screenHeight * 0.01f, vertical = screenHeight * 0.01f
                        ),
                ) {
                    Box(
                        modifier = Modifier.size(
                            width = screenWidth, height = screenHeight * 0.8f
                        ),
                    ) {
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.SpaceEvenly
                        ) {
                            Text(
                                text = "Genera una plan rutina",
                                style = MaterialTheme.typography.titleLarge,
                            )
                            Column(
                                modifier = Modifier.size(
                                    width = screenWidth, height = screenHeight * 0.35f
                                ),
                            ) {
                                Text(
                                    text = "Lista clientes registrados",
                                    style = MaterialTheme.typography.titleLarge,
                                )
                                if (clientes.isEmpty()) {
                                    Box(
                                        modifier = Modifier.size(
                                            width = screenWidth, height = screenHeight * 0.35f
                                        ), contentAlignment = Alignment.Center
                                    ) {
                                        Text(
                                            text = "No hay clientes registrados",
                                            style = MaterialTheme.typography.titleMedium
                                        )
                                    }
                                } else {
                                    Box(
                                        modifier = Modifier.size(
                                            width = screenWidth, height = screenHeight * 0.35f
                                        )
                                    ) {
                                        LazyColumn {
                                            items(clientes.size) { index ->
                                                Row(
                                                    Modifier
                                                        .fillMaxWidth()
                                                        .clickable {
                                                            selectCliente =
                                                                clientes[index]
                                                        },
                                                    verticalAlignment = Alignment.CenterVertically
                                                ) {
                                                    RadioButton(selected = selectCliente == clientes[index],
                                                        onClick = {
                                                            selectCliente =
                                                                clientes[index]
                                                        })
                                                    Text(
                                                        text = clientes[index].nombre,
                                                        style = MaterialTheme.typography.titleMedium,
                                                        maxLines = 1,
                                                        overflow = TextOverflow.Ellipsis
                                                    )
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                            Column(
                                modifier = Modifier.size(
                                    width = screenWidth, height = screenHeight * 0.35f
                                ),
                            ) {
                                Text(
                                    text = "Lista rutinas registradas",
                                    style = MaterialTheme.typography.titleLarge,
                                )
                                if (rutinas.isEmpty()) {
                                    Box(
                                        modifier = Modifier.size(
                                            width = screenWidth, height = screenHeight * 0.35f
                                        ), contentAlignment = Alignment.Center
                                    ) {
                                        Text(
                                            text = "No hay rutinas registradas",
                                            style = MaterialTheme.typography.titleMedium
                                        )
                                    }
                                } else {
                                    Box(
                                        modifier = Modifier.size(
                                            width = screenWidth, height = screenHeight * 0.35f
                                        )
                                    ) {
                                        LazyColumn {
                                            items(rutinas.size) { index ->
                                                Row(
                                                    Modifier
                                                        .fillMaxWidth()
                                                        .clickable {
                                                            selectRutina =
                                                                rutinas[index]
                                                        },
                                                    verticalAlignment = Alignment.CenterVertically
                                                ) {
                                                    RadioButton(selected = selectRutina == rutinas[index],
                                                        onClick = {
                                                            selectRutina =
                                                                rutinas[index]
                                                        })
                                                    Text(

                                                        text = rutinas[index].titulo,
                                                        style = MaterialTheme.typography.titleMedium,
                                                        maxLines = 1,
                                                        overflow = TextOverflow.Ellipsis
                                                    )
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    Box(
                        modifier = Modifier.size(
                            width = screenWidth, height = screenHeight * 0.2f
                        ),
                        contentAlignment = Alignment.Center
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.iconpdfview),
                                contentDescription = "Descripción de la imagen 1",
                                modifier = Modifier
                                    .size(
                                        width = screenWidth * 0.15f, height = screenHeight * 0.15f
                                    )
                                    .clickable {
                                        if (rutinas.isEmpty() || clientes.isEmpty()) {
                                            messageText = "No hay rutinas o clientes registrados"
                                            dialogAbierto = true
                                            return@clickable
                                        }

                                        if (selectCliente.id == 0 || selectRutina.id == 0) {
                                            messageText = "Seleccione un cliente y una rutina"
                                            dialogAbierto = true
                                            return@clickable
                                        }

                                        if (pdfPath==null || pdfPath!!.isEmpty()) {
                                            pInformeEntrenamiento.idRutina = selectRutina.id
                                            pInformeEntrenamiento.alimentacion = pInformeEntrenamiento.getPlanAlimentacionPorRutina()
                                            pInformeEntrenamiento.ejercicios = pInformeEntrenamiento.getPlanesEjerciciosPorRutina()
                                            pInformeEntrenamiento.numeroWhatsapp = selectCliente.telefono
                                            pInformeEntrenamiento.path = pInformeEntrenamiento.generarPdf(
                                                context,
                                            )
                                        }
                                        pInformeEntrenamiento.abrirPdf(context)
                                    }
                            )
                            Image(
                                painter = painterResource(id = R.drawable.iconwhatapp),
                                contentDescription = "Descripción de la imagen 2",
                                modifier = Modifier
                                    .size(
                                        width = screenWidth * 0.15f, height = screenHeight * 0.15f
                                    )
                                    .clickable {
                                        if (pdfPath==null) {
                                            pInformeEntrenamiento.idRutina = selectRutina.id
                                            pInformeEntrenamiento.alimentacion = pInformeEntrenamiento.getPlanAlimentacionPorRutina()
                                            pInformeEntrenamiento.ejercicios = pInformeEntrenamiento.getPlanesEjerciciosPorRutina()
                                            pInformeEntrenamiento.numeroWhatsapp = selectCliente.telefono
                                            pInformeEntrenamiento.path = pInformeEntrenamiento.generarPdf(
                                                context,
                                            )
                                        }
                                        pInformeEntrenamiento.compartirPdfWhatsapp(context)
                                    }
                            )
                            Image(
                                painter = painterResource(id = R.drawable.iconvolveratras),
                                contentDescription = "Descripción de la imagen 2",
                                modifier = Modifier
                                    .size(
                                        width = screenWidth * 0.15f, height = screenHeight * 0.15f
                                    )
                                    .clickable {
                                        navController.popBackStack()
                                    }
                            )
                        }
                    }
                }
            }
        }
    )
}








