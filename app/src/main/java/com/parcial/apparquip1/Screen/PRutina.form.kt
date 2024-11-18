package com.parcial.apparquip1.Screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.ThumbUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.Dp
import androidx.navigation.NavHostController
import com.parcial.apparquip1.Datos.entidades.Alimentacion
import com.parcial.apparquip1.Datos.entidades.PlanEjercicio
import androidx.compose.material3.TextField
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.text.style.TextOverflow
import com.parcial.apparquip1.Datos.entidades.Rutina
import com.parcial.apparquip1.DialogoCustom
import com.parcial.apparquip1.Presentacion.PRutina
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PRutina(navController: NavHostController, screenWidth: Dp, screenHeight: Dp) {
    val context = LocalContext.current
    val pRutina = PRutina(context)
    val focusManager = LocalFocusManager.current

    var isCreate by remember { mutableStateOf(true) }
    var id: Int by remember { mutableStateOf(0) }
    var titulo: String by remember { mutableStateOf("") }
    var idPlanAlimentacion by remember { mutableStateOf(0) }
    var rutinas: List<Rutina> by remember { mutableStateOf(pRutina.obtenerRutinas()) }

    var planesAlimentacion: List<Alimentacion> by remember { mutableStateOf(pRutina.getPlanesAlimentacion()) }

    var planesEjercicios: List<PlanEjercicio> by remember { mutableStateOf(pRutina.getPlanesEjercicios()) }

    var selectPlanAlimentacion: Int by remember { mutableStateOf(0) }
    var selectPlanesEjercicios: MutableList<PlanEjercicio> by remember {
        mutableStateOf(
            mutableListOf()
        )
    }

    // DIALOGO DE ALERTA
    var dialogAbierto by rememberSaveable { mutableStateOf(false) }
    var messageText: String by remember { mutableStateOf("") }
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
                    modifier = Modifier.size(
                        width = screenWidth, height = screenHeight * 0.7f
                    ),
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Text(
                            text = if (isCreate) "Crear una rutina" else "Editar una rutina",
                            style = MaterialTheme.typography.titleLarge,
                        )
                        TextField(
                            modifier = Modifier.fillMaxWidth(),
                            value = titulo,
                            label = {
                                Text(
                                    text = "Titulo de la rutina",
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
                        Column(
                            modifier = Modifier.size(
                                width = screenWidth, height = screenHeight * 0.25f
                            ),
                        ) {
                            Text(
                                text = "Lista planes de alimentacion",
                                style = MaterialTheme.typography.titleLarge,
                            )
                            if (planesAlimentacion.isEmpty()) {
                                Box(
                                    modifier = Modifier.size(
                                        width = screenWidth, height = screenHeight * 0.25f
                                    ), contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        text = "No hay planes alimentacion",
                                        maxLines = 1,
                                                overflow = TextOverflow.Ellipsis,
                                        style = MaterialTheme.typography.titleMedium
                                    )
                                }
                            } else {
                                Box(
                                    modifier = Modifier.size(
                                        width = screenWidth, height = screenHeight * 0.25f
                                    )
                                ) {
                                    LazyColumn {
                                        items(planesAlimentacion.size) { index ->
                                            Row(
                                                Modifier
                                                    .fillMaxWidth()
                                                    .clickable {
                                                        selectPlanAlimentacion =
                                                            planesAlimentacion[index].id
                                                    },
                                                verticalAlignment = Alignment.CenterVertically
                                            ) {
                                                RadioButton(selected = selectPlanAlimentacion == planesAlimentacion[index].id,
                                                    onClick = {
                                                        selectPlanAlimentacion =
                                                            planesAlimentacion[index].id
                                                    })
                                                Text(
                                                    text = planesAlimentacion[index].titulo,
                                                    style = MaterialTheme.typography.titleMedium,
                                                    maxLines = 1,
                                                    overflow = TextOverflow.Ellipsis,
                                                    modifier = Modifier.padding(
                                                        start =
                                                        16.dp
                                                    )
                                                )
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        Column(
                            modifier = Modifier.size(
                                width = screenWidth, height = screenHeight * 0.25f
                            ),
                        ) {
                            Text(
                                text = "Lista planes de ejercicios",
                                maxLines = 1,

                                style = MaterialTheme.typography.titleLarge,
                            )
                            if (planesEjercicios.isEmpty()) {
                                Box(
                                    modifier = Modifier.size(
                                        width = screenWidth, height = screenHeight * 0.25f
                                    ), contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        text = "No hay planes de ejercicios",
                                        maxLines = 1,
                                        overflow = TextOverflow.Ellipsis,
                                        style = MaterialTheme.typography.titleMedium
                                    )
                                }
                            } else {
                                Box(
                                    modifier = Modifier.size(
                                        width = screenWidth, height = screenHeight * 0.25f
                                    ),
                                ) {
                                    LazyColumn { // (3)
                                        items(planesEjercicios.size) { item ->
                                            SelectableRow(text = planesEjercicios[item].titulo,
                                                onCheckedChange = { isChecked ->
                                                    if (isChecked) { // (5)
                                                        selectPlanesEjercicios += planesEjercicios[item]
                                                    } else {
                                                        selectPlanesEjercicios -= planesEjercicios[item]
                                                    }
                                                })
                                        }
                                    }
                                }
                            }
                        }
                        Box(
                            modifier = Modifier.size(
                                width = screenWidth, height = screenHeight * 0.07f
                            ),
                        ) {
                            Row(
                                modifier = Modifier.size(
                                    width = screenWidth, height = screenHeight * 0.7f
                                ), horizontalArrangement = Arrangement.SpaceEvenly
                            ) {
                                Button(
                                    onClick = {
                                        if (planesAlimentacion.isEmpty() || planesEjercicios.isEmpty()) {
                                            messageText =
                                                "No hay planes de alimentacion o ejercicios"
                                            dialogAbierto = true
                                            return@Button
                                        }

                                        if (selectPlanAlimentacion == 0 || selectPlanesEjercicios.isEmpty()) {
                                            messageText =
                                                "Debe seleccionar un plan de alimentacion y ejercicios"
                                            dialogAbierto = true
                                            return@Button
                                        }
                                        pRutina.id = id
                                        pRutina.titulo = titulo
                                        pRutina.idPlanAlimentacion = selectPlanAlimentacion
                                        pRutina.idPlanesEjercicios = selectPlanesEjercicios
                                        pRutina.rutina = Rutina(
                                            id = id,
                                            titulo = titulo,
                                            idPlanAlimentacion = selectPlanAlimentacion
                                        )
                                        if (isCreate) {
                                            messageText = pRutina.insertarRutina()
                                        } else {
                                            messageText = pRutina.actualizarRutina()
                                        }

                                        focusManager.clearFocus()
                                        id = 0
                                        titulo = ""
                                        selectPlanAlimentacion = 0
                                        selectPlanesEjercicios = mutableListOf()
                                        rutinas = pRutina.obtenerRutinas()
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
                                        selectPlanAlimentacion = 0
                                        selectPlanesEjercicios = mutableListOf()
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
                                        selectPlanAlimentacion = 0
                                        selectPlanesEjercicios = mutableListOf()
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
                                    onClick = { navController.popBackStack() },
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
                }
                Box(
                    modifier = Modifier.size(
                        width = screenWidth, height = screenHeight * 0.3f
                    ),
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Text(
                            text = "Lista de rutinas",
                            style = MaterialTheme.typography.titleLarge,
                        )
                        if (pRutina.obtenerRutinas().isEmpty()) {
                            Box(
                                modifier = Modifier.size(
                                    width = screenWidth, height = screenHeight * 0.3f
                                ), contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = "No hay rutinas",
                                    maxLines = 1,

                                    style = MaterialTheme.typography.titleMedium
                                )
                            }
                        } else {
                            Box(
                                modifier = Modifier.size(
                                    width = screenWidth, height = screenHeight * 0.3f
                                ),
                            ) {
                                LazyColumn {
                                    items(rutinas.size) { index ->
                                        Row(
                                            modifier = Modifier.fillMaxWidth(),
                                            horizontalArrangement = Arrangement.SpaceBetween,
                                            verticalAlignment = Alignment.CenterVertically
                                        ) {
                                            Box(
                                                modifier = Modifier.size(
                                                    screenWidth * 0.75f, screenHeight * 0.07f
                                                ),
                                            ) {
                                                Text(
                                                    text = rutinas[index].titulo,
                                                    overflow = TextOverflow.Ellipsis,
                                                    maxLines = 1,
                                                    style = MaterialTheme.typography.titleMedium,
                                                    modifier = Modifier.align(Alignment.Center)
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
                                                    id = rutinas[index].id
                                                    titulo = rutinas[index].titulo
                                                    idPlanAlimentacion =
                                                        rutinas[index].idPlanAlimentacion
                                                    rutinas = pRutina.obtenerRutinas()
                                                }) {
                                                    Icon(
                                                        Icons.Filled.Edit,
                                                        contentDescription = "Editar Cliente",
                                                        tint = MaterialTheme.colorScheme.secondary
                                                    ) // Change the color here
                                                }
                                                IconButton(onClick = {
                                                    pRutina.id = rutinas[index].id
                                                    pRutina.eliminarRutina()
                                                    rutinas = pRutina.obtenerRutinas()
                                                }) {
                                                    Icon(
                                                        Icons.Filled.Clear,
                                                        contentDescription = "Eliminar Cliente",
                                                        tint = MaterialTheme.colorScheme.secondary
                                                    ) // Change the color here
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
    })
}

@Composable // (4)
private fun SelectableRow(
    text: String, onCheckedChange: (Boolean) -> Unit
) {
    var checked by remember { mutableStateOf(false) }

    ListItemRowWithControl(
        item = text, control = {
            Checkbox(checked = checked, onCheckedChange = { value ->
                checked = value
                onCheckedChange(value)
            })
        }, modifier = Modifier
    )
}

@Composable
fun ListItemRowWithControl(
    item: String, control: @Composable () -> Unit, modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(48.dp)
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Text(
            text = item,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.titleMedium, modifier = Modifier.align(
                Alignment.CenterStart
            )
        )

        Box(modifier = Modifier.align(Alignment.CenterEnd)) {
            control()
        }
    }
}