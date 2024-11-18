package com.parcial.apparquip1.Screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.ThumbUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
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
import com.parcial.apparquip1.Datos.entidades.CategoriaEjer
import com.parcial.apparquip1.Datos.entidades.PlanEjercicio
import com.parcial.apparquip1.DialogoCustom
import com.parcial.apparquip1.Presentacion.PPlanEjercicio

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PPlanEjercicio(navController: NavHostController, screenWidth: Dp, screenHeight: Dp) {
    val context = LocalContext.current
    val pPlanEjercicio = PPlanEjercicio(context)
    var isCreate by remember { mutableStateOf(true) }
    val focusManager = LocalFocusManager.current
    var id: Int by remember { mutableStateOf(0) }
    var titulo by remember { mutableStateOf("") }
    var video by remember { mutableStateOf("") }
    var objetivo by remember { mutableStateOf("") }
    var motivo by remember { mutableStateOf("Buen Motivo") }
    var proceso by remember { mutableStateOf("") }
    var planesEjercicio: List<PlanEjercicio> by remember { mutableStateOf(pPlanEjercicio.obtenerPlanesEjercicio()) }

    var cantRelacion: Int by remember { mutableStateOf(0) }

    // DROPDOWN PERSONALIZADO
    var categoriasEjercicio: List<CategoriaEjer> by remember { mutableStateOf(pPlanEjercicio.obtenerCategoriasEjer()) }
    var isExpanded by remember { mutableStateOf(false) }
    var selectedCE by remember { mutableStateOf(if (categoriasEjercicio.isNotEmpty()) categoriasEjercicio[0].nombre else "No hay categorias añadidas") }
    val icon = if (isExpanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown
    var idCategoriaEjercicio: Int by remember {
        mutableStateOf(
            categoriasEjercicio.getOrNull(0)?.id ?: 0
        )
    }

    // DIALOGO DE ALERTA
    var dialogAbierto by rememberSaveable { mutableStateOf(false) }
    var messageText: String by remember { mutableStateOf("No hay categorias de ejercicio registradas, por favor añada") }
    DialogoCustom(messageText = messageText,
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
                        .weight(0.60f)
                        .fillMaxWidth()
                ) {
                    // FORMULARIO DE Planes de Ejercicio
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Text(
                            text = if (isCreate) "Crear plan ejercicio" else "Editar plan ejercicio",
                            style = MaterialTheme.typography.titleLarge,
                        )
                        TextField(modifier = Modifier.fillMaxWidth(),
                            value = titulo,
                            label = {
                                Text(
                                    text = "Titulo del plan",
                                    style = MaterialTheme.typography.titleSmall
                                )
                            },
                            textStyle = MaterialTheme.typography.titleMedium,
                            leadingIcon = {
                                Icon(Icons.Filled.Star, contentDescription = "Icono de persona")
                            },
                            singleLine = true,
                            onValueChange = { titulo = it },
                            shape = RoundedCornerShape(9.dp, 9.dp, 0.dp, 0.dp)
                        )
                        TextField(modifier = Modifier.fillMaxWidth(),
                            value = video,
                            label = {
                                Text(
                                    text = "Video informativo",
                                    style = MaterialTheme.typography.titleSmall
                                )
                            },
                            textStyle = MaterialTheme.typography.titleMedium,
                            leadingIcon = {
                                Icon(Icons.Filled.Share, contentDescription = "Icono de persona")
                            },
                            singleLine = true,
                            onValueChange = { video = it },
                            shape = RoundedCornerShape(9.dp, 9.dp, 0.dp, 0.dp)
                        )
                        TextField(modifier = Modifier.fillMaxWidth(),
                            value = objetivo,
                            label = {
                                Text(
                                    text = "Objetivo del plan",
                                    style = MaterialTheme.typography.titleSmall
                                )
                            },
                            textStyle = MaterialTheme.typography.titleMedium,
                            leadingIcon = {
                                Icon(Icons.Filled.Build, contentDescription = "Icono de persona")
                            },
                            singleLine = true,
                            onValueChange = { objetivo = it },
                            shape = RoundedCornerShape(9.dp, 9.dp, 0.dp, 0.dp)
                        )
                        TextField(modifier = Modifier.fillMaxWidth(),
                            value = proceso,
                            label = {
                                Text(
                                    text = "Proceso duracion y repeticiones",
                                    style = MaterialTheme.typography.titleSmall
                                )
                            },
                            textStyle = MaterialTheme.typography.titleMedium,
                            leadingIcon = {
                                Icon(Icons.Filled.Build, contentDescription = "Icono de persona")
                            },
                            singleLine = true,
                            onValueChange = { proceso = it },
                            shape = RoundedCornerShape(9.dp, 9.dp, 0.dp, 0.dp)
                        )
                        Column(
                            modifier = Modifier
                                .size(
                                    screenWidth, screenHeight * 0.07f
                                )
                                .fillMaxSize(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                        ) {
                            TextField(modifier = Modifier
                                .size(
                                    screenWidth, screenHeight * 0.07f
                                )
                                .fillMaxSize(),
                                value = selectedCE,
                                textStyle = MaterialTheme.typography.titleMedium,
                                readOnly = true,
                                onValueChange = { },
                                trailingIcon = {
                                    IconButton(onClick = { isExpanded = !isExpanded }) {
                                        Icon(icon, contentDescription = null)
                                    }
                                })
                            DropdownMenu(
                                expanded = isExpanded,
                                onDismissRequest = { isExpanded = false },
                                modifier = Modifier.heightIn(max = 200.dp)
                            ) {
                                categoriasEjercicio.forEach { categoria ->
                                    DropdownMenuItem(text = {
                                        Text(
                                            text = categoria.nombre,
                                            style = MaterialTheme.typography.titleMedium.copy(
                                                color = MaterialTheme.colorScheme.onSurface
                                            )
                                        )
                                    }, onClick = {
                                        selectedCE = categoria.nombre
                                        idCategoriaEjercicio = categoria.id
                                        isExpanded = false
                                    })
                                }
                            }
                        }
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ) {
                            Button(
                                onClick = {
                                    pPlanEjercicio.idCategoriaEjercicio = idCategoriaEjercicio
                                    pPlanEjercicio.id = id
                                    pPlanEjercicio.titulo = titulo
                                    pPlanEjercicio.motivo = motivo
                                    pPlanEjercicio.objetivo = objetivo
                                    pPlanEjercicio.video = video
                                    pPlanEjercicio.proceso = proceso
                                    if (categoriasEjercicio.isNotEmpty()) {
                                        if (isCreate) {
                                            pPlanEjercicio.insertarPlanEjercicio()
                                        } else {
                                            pPlanEjercicio.actualizarPlanEjercicio()
                                        }
                                        planesEjercicio = pPlanEjercicio.obtenerPlanesEjercicio()
                                    } else {
                                        messageText =
                                            "No hay categorias de ejercicio registradas, por favor añada."
                                        dialogAbierto = true
                                    }
                                    focusManager.clearFocus()
                                    id = 0
                                    titulo = ""
                                    video = ""
                                    objetivo = ""
                                    proceso = ""
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
                                    video = ""
                                    objetivo = ""
                                    proceso = ""
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
                                    video = ""
                                    objetivo = ""
                                    proceso = ""
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
                        .weight(0.30f)
                        .fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize(),

                        ) {
                        Text(
                            text = "Lista planes de ejercicio",
                            style = MaterialTheme.typography.titleLarge,
                        )
                        if (planesEjercicio.isEmpty()) {
                            Box(
                                modifier = Modifier.fillMaxSize(),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = "No hay planes registrados",
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis,
                                    style = MaterialTheme.typography.titleMedium
                                )
                            }
                        } else {
                            LazyColumn() {
                                items(planesEjercicio.size) { index ->
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
                                                    text = planesEjercicio[index].titulo,
                                                    overflow = TextOverflow.Ellipsis,
                                                    maxLines = 1,
                                                    style = MaterialTheme.typography.titleMedium
                                                )
                                                Text(
                                                    text = planesEjercicio[index].motivo,
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
                                                    id = planesEjercicio[index].id
                                                    titulo = planesEjercicio[index].titulo
                                                    video = planesEjercicio[index].video
                                                    objetivo = planesEjercicio[index].objetivo
                                                    proceso = planesEjercicio[index].proceso
                                                }) {
                                                    Icon(
                                                        Icons.Filled.Edit,
                                                        contentDescription = "Editar Cliente",
                                                        tint = MaterialTheme.colorScheme.secondary
                                                    ) // Change the color here
                                                }
                                                IconButton(onClick = {
                                                    id = planesEjercicio[index].id
                                                    cantRelacion =
                                                        pPlanEjercicio.getRelacionOfRutina(id).size
                                                    if (cantRelacion > 0) {
                                                        messageText =
                                                            "No se puede eliminar el plan de ejercicio, tiene $cantRelacion rutinas  relacionadas"
                                                        dialogAbierto = true
                                                    } else {
                                                        pPlanEjercicio.id = planesEjercicio[index].id
                                                            pPlanEjercicio.eliminarPlanEjercicio()
                                                        planesEjercicio =
                                                            pPlanEjercicio.obtenerPlanesEjercicio()
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





