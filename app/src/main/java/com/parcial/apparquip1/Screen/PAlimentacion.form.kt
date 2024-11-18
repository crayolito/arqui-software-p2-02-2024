package com.parcial.apparquip1.Screen


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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.ThumbUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.parcial.apparquip1.Datos.entidades.Alimentacion
import com.parcial.apparquip1.DialogoCustom
import com.parcial.apparquip1.Presentacion.PAlimentacion

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PAlimentacion(navController: NavHostController, screenWidth: Dp, screenHeight: Dp) {
    val context = LocalContext.current
    val pAlimentacion = PAlimentacion(context)
    var isCreate by remember { mutableStateOf(true) }
    val focusManager = LocalFocusManager.current
    var id: Int by remember { mutableStateOf(0) }
    var titulo: String by remember { mutableStateOf("") }
    var descripcion: String by remember { mutableStateOf("\n") }
    var ali_no_procesados: String by remember { mutableStateOf("\n") }
    var ali_procesados by remember { mutableStateOf("\n") }
    var planesAlimentacion: List<Alimentacion> by remember { mutableStateOf(pAlimentacion.obtenerAlimentos()) }

    var cantRelacion: Int by remember { mutableStateOf(0) }

    // DIALOGO DE ALERTA
    var dialogAbierto by rememberSaveable { mutableStateOf(false) }
    DialogoCustom(messageText = "No se puede eliminar el plan de alimentacion, tiene $cantRelacion rutinas asociadas",
        show = dialogAbierto,
        onDismissRequest = { dialogAbierto = false })

    Scaffold(content = { paddingValues ->
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
                    modifier = Modifier
                        .weight(0.6f)
                        .fillMaxWidth()
                ) {
                    // FORMULARIO DE PLAN ALIMENTACION
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Text(
                            text = if (isCreate) "Crear Plan Alimentacion" else "Editar Plan Alimentacion",
                            style = MaterialTheme.typography.titleLarge,
                        )
                        TextField(
                            modifier = Modifier.fillMaxWidth(),
                            value = titulo,
                            label = {
                                Text(
                                    text = "Titulo del plan ALimen.",
                                    style = MaterialTheme.typography.titleSmall
                                )
                            },
                            textStyle = MaterialTheme.typography.titleMedium,
                            leadingIcon = {
                                Icon(Icons.Filled.DateRange, contentDescription = "Icono de titulo")
                            },
                            singleLine = true,
                            onValueChange = { titulo = it },
                            shape = RoundedCornerShape(9.dp, 9.dp, 0.dp, 0.dp)
                        )
                        TextField(
                            modifier = Modifier.fillMaxWidth(),
                            value = descripcion,
                            label = {
                                Text(
                                    text = "Motivo de la dieta",
                                    style = MaterialTheme.typography.titleSmall
                                )
                            },
                            textStyle = MaterialTheme.typography.titleMedium,
                            leadingIcon = {
                                Icon(
                                    Icons.Filled.Notifications,
                                    contentDescription = "Icono de motivo"
                                )
                            },
                            maxLines = 2,
                            onValueChange = { descripcion = it },
                            shape = RoundedCornerShape(9.dp, 9.dp, 0.dp, 0.dp)
                        )
                        TextField(
                            modifier = Modifier.fillMaxWidth(),
                            value = ali_no_procesados,
                            label = {
                                Text(
                                    text = "Alimentos no procesados",
                                    style = MaterialTheme.typography.titleSmall
                                )
                            },
                            textStyle = MaterialTheme.typography.titleMedium,
                            leadingIcon = {
                                Icon(
                                    Icons.Filled.Menu,
                                    contentDescription = "Icono de ali_no_procesados"
                                )
                            },
                            maxLines = 2,
                            onValueChange = { ali_no_procesados = it },
                            shape = RoundedCornerShape(9.dp, 9.dp, 0.dp, 0.dp)
                        )
                        TextField(
                            modifier = Modifier.fillMaxWidth(),
                            value = ali_procesados,
                            label = {
                                Text(
                                    text = "Alimentos procesados",
                                    style = MaterialTheme.typography.titleSmall
                                )
                            },
                            textStyle = MaterialTheme.typography.titleMedium,
                            leadingIcon = {
                                Icon(
                                    Icons.Filled.Menu,
                                    contentDescription = "Icono de ali_procesados"
                                )
                            },
                            maxLines = 2,
                            onValueChange = { ali_procesados = it },
                            shape = RoundedCornerShape(9.dp, 9.dp, 0.dp, 0.dp)
                        )
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ) {

                            Button(
                                onClick = {
                                    if (isCreate) {
                                        pAlimentacion.titulo = titulo
                                        pAlimentacion.descripcion = descripcion
                                        pAlimentacion.noprocesado = descripcion
                                        pAlimentacion.procesado= ali_procesados
                                        pAlimentacion.insertarAlimento()
                                    } else {
                                        pAlimentacion.id = id
                                        pAlimentacion.titulo = titulo
                                        pAlimentacion.descripcion = descripcion
                                        pAlimentacion.noprocesado = ali_no_procesados
                                        pAlimentacion.procesado = ali_procesados
                                        pAlimentacion.actualizarAlimento()
                                    }
                                    planesAlimentacion = pAlimentacion.obtenerAlimentos()
                                    focusManager.clearFocus()
                                    id = 0
                                    titulo = ""
                                    descripcion = "\n"
                                    ali_no_procesados = "\n"
                                    ali_procesados = "\n"
                                },
                                shape = RoundedCornerShape(5.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = MaterialTheme.colorScheme.primary,
                                    contentColor = MaterialTheme.colorScheme.primary,
                                )
                            ) {
                                Icon(
                                    Icons.Outlined.ThumbUp,
                                    contentDescription = "Salvar Datos",
                                    tint = MaterialTheme.colorScheme.secondary
                                )
                            }
                            Button(
                                onClick = {
                                    focusManager.clearFocus()
                                    isCreate = true
                                    titulo = ""
                                    descripcion = "\n"
                                    ali_no_procesados = "\n"
                                    ali_procesados = "\n"
                                },
                                shape = RoundedCornerShape(5.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = MaterialTheme.colorScheme.primary,
                                    contentColor = MaterialTheme.colorScheme.primary,
                                )
                            ) {
                                Icon(
                                    Icons.Filled.Add,
                                    contentDescription = "Cambiar a modalidad crear",
                                    tint = MaterialTheme.colorScheme.secondary
                                )
                            }
                            Button(
                                onClick = {
                                    focusManager.clearFocus()
                                    id = 0
                                    titulo = ""
                                    descripcion = "\n"
                                    ali_no_procesados = "\n"
                                    ali_procesados = "\n"
                                },
                                shape = RoundedCornerShape(5.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = MaterialTheme.colorScheme.primary,
                                    contentColor = MaterialTheme.colorScheme.primary,
                                )
                            ) {
                                Icon(
                                    Icons.Outlined.Delete,
                                    contentDescription = "Salvar Datos",
                                    tint = MaterialTheme.colorScheme.secondary
                                )
                            }
                            Button(
                                onClick = {
                                    navController.popBackStack()
                                },
                                shape = RoundedCornerShape(5.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = MaterialTheme.colorScheme.primary,
                                    contentColor = MaterialTheme.colorScheme.primary,
                                )
                            ) {
                                Icon(
                                    Icons.Outlined.ArrowBack,
                                    contentDescription = "Volver Atras",
                                    tint = MaterialTheme.colorScheme.secondary
                                )
                            }

                        }
                    }
                }
                Box(
                    modifier = Modifier
                        .weight(0.4f)
                        .fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize(),

                        ) {
                        Text(
                            text = "Lista planes de alimentacion",
                            style = MaterialTheme.typography.titleLarge,
                        )
                        if (planesAlimentacion.isEmpty()) {
                            Box(
                                modifier = Modifier.fillMaxSize(),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = "No hay planes de alimentacion",
                                    style = MaterialTheme.typography.titleMedium
                                )
                            }
                        } else {
                            LazyColumn() {
                                items(planesAlimentacion.size) { index ->
                                    Column(
                                        modifier = Modifier.padding(bottom = screenHeight * 0.01f)
                                    ) {
                                        Row(
                                            modifier = Modifier.fillMaxWidth(),
                                            horizontalArrangement = Arrangement.SpaceBetween,
                                            verticalAlignment = Alignment.CenterVertically
                                        ) {
                                            Column(
                                                modifier = Modifier.size(
                                                    screenWidth * 0.75f, screenHeight * 0.07f
                                                ), verticalArrangement = Arrangement.Center
                                            ) {
                                                Text(
                                                    text = planesAlimentacion[index].titulo,
                                                    overflow = TextOverflow.Ellipsis,
                                                    maxLines = 1,
                                                    style = MaterialTheme.typography.titleMedium
                                                )
                                                Text(
                                                    text = planesAlimentacion[index].descripcion,
                                                    overflow = TextOverflow.Ellipsis,
                                                    maxLines = 1,
                                                    style = MaterialTheme.typography.titleMedium
                                                )
                                            }
                                            Row(
                                                modifier = Modifier.size(
                                                    screenWidth * 0.2f, screenHeight * 0.07f
                                                ),
                                                horizontalArrangement = Arrangement.Center,
                                                verticalAlignment = Alignment.CenterVertically
                                            ) {
                                                IconButton(onClick = {
                                                    isCreate = false
                                                    id = planesAlimentacion[index].id
                                                    titulo = planesAlimentacion[index].titulo
                                                    descripcion =
                                                        planesAlimentacion[index].descripcion
                                                    ali_no_procesados =
                                                        planesAlimentacion[index].noprocesado
                                                    ali_procesados =
                                                        planesAlimentacion[index].procesado
                                                }) {
                                                    Icon(
                                                        Icons.Filled.Edit,
                                                        contentDescription = "Editar Cliente",
                                                        tint = MaterialTheme.colorScheme.secondary
                                                    ) // Change the color here
                                                }
                                                IconButton(onClick = {
                                                    pAlimentacion.id = planesAlimentacion[index].id
                                                    cantRelacion =
                                                        pAlimentacion.getRelacionOfRutina().size
                                                    if (cantRelacion > 0) {
                                                        dialogAbierto = true
                                                    } else {
                                                        pAlimentacion.eliminarAlimento()
                                                        planesAlimentacion =
                                                            pAlimentacion.obtenerAlimentos()
                                                    }
                                                }) {
                                                    Icon(
                                                        Icons.Filled.Clear,
                                                        contentDescription = "Eliminar Cliente",
                                                        tint = MaterialTheme.colorScheme.secondary
                                                    ) // Change the color here
                                                }
                                            }

                                        }
                                        Divider(
                                            color = MaterialTheme.colorScheme.secondary.copy(alpha = 0.4f),
                                            thickness = 1.5.dp
                                        )
                                    }

                                }
                            }
                        }

                    }
                }
            }
        }
    })
}