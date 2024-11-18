package com.parcial.apparquip1.Screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.rounded.*
import androidx.compose.material.icons.sharp.*
import androidx.compose.material.icons.twotone.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavHostController
import com.parcial.apparquip1.Datos.entidades.Cliente
import com.parcial.apparquip1.Presentacion.PCliente
import com.parcial.apparquip1.common.IAFitness
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.solid.*
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PCliente(navController: NavHostController, screenWidth: Dp, screenHeight: Dp) {
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val pCliente = remember { PCliente(context) }
    var isCreate by remember { mutableStateOf(true) }
    val focusManager = LocalFocusManager.current

    // Estados básicos
    var id by remember { mutableStateOf(pCliente.id) }
    var nombre by remember { mutableStateOf(pCliente.nombre) }
    var telefono by remember { mutableStateOf(pCliente.telefono) }
    var email by remember { mutableStateOf(pCliente.email) }
    var meta by remember { mutableStateOf(pCliente.meta) }
    var caracteristicas by remember { mutableStateOf(pCliente.caracteristicas) }

    // Estados físicos
    var altura by remember { mutableStateOf(pCliente.altura.toString()) }
    var peso by remember { mutableStateOf(pCliente.peso.toString()) }
    var imc by remember { mutableStateOf(pCliente.imc.toString()) }
    var edad by remember { mutableStateOf(pCliente.edad.toString()) }
    var genero by remember { mutableStateOf(pCliente.genero) }
    var ocupacion by remember { mutableStateOf(pCliente.ocupacion) }

    // Estados de entrenamiento
    var horarioPreferido by remember { mutableStateOf(pCliente.horarioPreferido) }
    var nivelActividad by remember { mutableStateOf(pCliente.nivelActividad) }
    var experienciaEjercicio by remember { mutableStateOf(pCliente.experienciaEjercicio) }
    var tipoObjetivo by remember { mutableStateOf(pCliente.tipoObjetivo) }
    var restriccionesSalud by remember { mutableStateOf(pCliente.restriccionesSalud) }
    var alergias by remember { mutableStateOf(pCliente.alergias) }
    var necesitaRutinaCasa by remember { mutableStateOf(pCliente.necesitaRutinaCasa) }


    // Estados UI
    var clientes: List<Cliente> by remember { mutableStateOf(pCliente.obtenerClientes()) }
    var showClientList by remember { mutableStateOf(false) }
    var showIADialog by remember { mutableStateOf(false) }

    // IA
    var extra by remember { mutableStateOf("") }
    var iaFitness by remember { mutableStateOf(IAFitness("", listOf(), listOf())) }


    var canUndo by remember { mutableStateOf(false) }
    var canRedo by remember { mutableStateOf(false) }

   fun actuliazarVarPCliente() {
    pCliente.id = id
    pCliente.nombre = nombre
    pCliente.telefono = telefono
    pCliente.email = email
    pCliente.meta = meta
    pCliente.caracteristicas = caracteristicas
    pCliente.altura = altura.toDoubleOrNull() ?: 0.0
    pCliente.peso = peso.toDoubleOrNull() ?: 0.0
    pCliente.imc = imc.toDoubleOrNull() ?: 0.0
    pCliente.edad = edad.toIntOrNull() ?: 0
    pCliente.genero = genero
    pCliente.ocupacion = ocupacion
    pCliente.horarioPreferido = horarioPreferido
    pCliente.nivelActividad = nivelActividad
    pCliente.experienciaEjercicio = experienciaEjercicio
    pCliente.tipoObjetivo = tipoObjetivo
    pCliente.restriccionesSalud = restriccionesSalud
    pCliente.alergias = alergias
    pCliente.necesitaRutinaCasa = necesitaRutinaCasa
    pCliente.extra = extra
    }

    fun actualizarVarForm(){
        id = pCliente.id
        nombre = pCliente.nombre
        telefono = pCliente.telefono
        email = pCliente.email
        meta = pCliente.meta
        caracteristicas = pCliente.caracteristicas
        altura = pCliente.altura.toString()
        peso = pCliente.peso.toString()
        imc = pCliente.imc.toString()
        edad = pCliente.edad.toString()
        genero = pCliente.genero
        ocupacion = pCliente.ocupacion
        horarioPreferido = pCliente.horarioPreferido
        nivelActividad = pCliente.nivelActividad
        experienciaEjercicio = pCliente.experienciaEjercicio
        tipoObjetivo = pCliente.tipoObjetivo
        restriccionesSalud = pCliente.restriccionesSalud
        alergias = pCliente.alergias
        necesitaRutinaCasa = pCliente.necesitaRutinaCasa
        extra = pCliente.extra
        if (extra.isNotEmpty()) {
            iaFitness = pCliente.convertObject(extra)
        }
    }

    fun limpiarVarForm(){
        id = 0
        nombre = ""
        telefono = ""
        email = ""
        genero = ""
        ocupacion = ""
        altura = ""
        peso = ""
        edad = ""
        restriccionesSalud = ""
        alergias = ""
        experienciaEjercicio = ""
        horarioPreferido = ""
        nivelActividad = ""
        tipoObjetivo = ""
        meta = ""
        caracteristicas = ""
        necesitaRutinaCasa = false
        extra = ""
        iaFitness = IAFitness("", listOf(), listOf())
    }

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Header Section
            item {
                HeaderSection(isCreate)
            }

            // Datos Básicos Section
            item {
                DatosBasicosSection(
                    nombre = nombre,
                    onNombreChange = {nombre = it},
                    telefono = telefono,
                    onTelefonoChange = { telefono = it },
                    email = email,
                    onEmailChange = { email = it },
                    genero = genero,
                    onGeneroChange = { genero = it },
                    ocupacion = ocupacion,
                    onOcupacionChange = { ocupacion = it }
                )
            }

            // Datos Físicos Section
            item {
                DatosFisicosSection(
                    altura = altura,
                    onAlturaChange = { altura = it },
                    peso = peso,
                    onPesoChange = { peso = it },
                    edad = edad,
                    onEdadChange = { edad = it },
                    restriccionesSalud = restriccionesSalud,
                    onRestriocionesSaludChange = { restriccionesSalud = it },
                    alergias = alergias,
                    onAlergiasChange = { alergias = it }
                )
            }

            // Experiencia y Preferencias Section
            item {
                ExperienciaPreferenciasSection(
                    experienciaEjercicio = experienciaEjercicio,
                    onExperienciaEjercicioChange = { experienciaEjercicio = it },
                    horarioPreferido = horarioPreferido,
                    onHorarioPreferidoChange = { horarioPreferido = it },
                    nivelActividad = nivelActividad,
                    onNivelActividadChange = { nivelActividad = it },
                    tipoObjetivo = tipoObjetivo,
                    onTipoObjetivoChange = { tipoObjetivo = it },
                    meta = meta,
                    onMetaChange = { meta = it },
                    caracteristicas = caracteristicas,
                    onCaracteristicasChange = { caracteristicas = it },
                    necesitaRutinaCasa = necesitaRutinaCasa,
                    onNecesitaRutinaCasaChange = { necesitaRutinaCasa = it }
                )
            }

            // Botones de Acción
            item {
                BotonesAccion(
                    isCreate = isCreate,
                    onGuardarClick = {
                        actuliazarVarPCliente()
                        if (isCreate) {
                            pCliente.validarCamposObligatorios(context);
                            pCliente.insertarCliente()
                        } else {
                            pCliente.actualizarCliente()
                        }
                        focusManager.clearFocus()
                        clientes = pCliente.obtenerClientes()
                        isCreate = true
                    },
                    onLimpiarClick = {
                        focusManager.clearFocus()
                        limpiarVarForm()
                    },
                    onCrearClick = {
                        isCreate = true
                        focusManager.clearFocus()
                        limpiarVarForm()
                        actuliazarVarPCliente()
                    },
                    onVolverAtrasClick = {
                        navController.popBackStack()
                    }
                )
            }

            // Botones Adicionales
            item {
                BotonesAdicionales(
                    onIAClick = { showIADialog = true },
                    onListaClick = { showClientList = true }
                )
            }

            item {
                BotonesMemento(
                    onRehacerClick = {
                        if (pCliente.rehacer()) {
                            actualizarVarForm()
                            // Actualizar estados de los botones
                            canUndo = pCliente.puedeDeshacer()
                            canRedo = pCliente.puedeRehacer()
                        }
                    },
                    onDeshacerClick = {
                        if (pCliente.deshacer()) {
                            actualizarVarForm()
                            // Actualizar estados de los botones
                            canUndo = pCliente.puedeDeshacer()
                            canRedo = pCliente.puedeRehacer()
                        }
                    },
                    canUndo = canUndo,
                    canRedo = canRedo
                )
            }
        }
    }

    // Diálogos
    if (showClientList) {
        ClienteListDialog(
            clientes = clientes,
            onDismiss = { showClientList = false },
            onEditClick = { cliente, index ->
                isCreate = false
                id = clientes[index].id
                nombre = clientes[index].nombre
                telefono = clientes[index].telefono
                email = clientes[index].email
                genero = clientes[index].genero
                ocupacion = clientes[index].ocupacion
                altura = clientes[index].altura.toString()
                peso = clientes[index].peso.toString()
                edad = clientes[index].edad.toString()
                restriccionesSalud = clientes[index].restriccionesSalud
                alergias = clientes[index].alergias
                experienciaEjercicio = clientes[index].experienciaEjercicio
                horarioPreferido = clientes[index].horarioPreferido
                nivelActividad = clientes[index].nivelActividad
                tipoObjetivo = clientes[index].tipoObjetivo
                meta = clientes[index].meta
                caracteristicas = clientes[index].caracteristicas
                necesitaRutinaCasa = clientes[index].necesitaRutinaCasa
                showClientList = false
                extra = clientes[index].extra
                if (extra.isNotEmpty()) {
                    iaFitness = pCliente.convertObject(extra)
                }
                actuliazarVarPCliente()
                pCliente.guardarEstado()
            },
            onDeleteClick = { cliente, index ->
                pCliente.id = clientes[index].id
                pCliente.eliminarCliente()
                clientes = pCliente.obtenerClientes()
            }
        )
    }

    if (showIADialog) {
        IADialog(
            iaFitness = iaFitness,
            onDismiss = { showIADialog = false },
            onConfirm = {
                // PROCESAR IA
                scope.launch {
                    // Generar nuevo plan de IA
                    extra = pCliente.AIFitness()
                    iaFitness = pCliente.convertObject(extra)

                    actuliazarVarPCliente()
                    pCliente.guardarEstado()
                    // Actualizar estados de los botones
                    canUndo = pCliente.puedeDeshacer()
                    canRedo = pCliente.puedeRehacer()
                }
            }
        )
    }
}

// components/HeaderSection.kt
@Composable
fun HeaderSection(isCreate: Boolean) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.7f)
        ),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = if (isCreate) "Nuevo Cliente" else "Actualizar Cliente",
                style = MaterialTheme.typography.headlineMedium.copy(
                    fontWeight = FontWeight.Bold
                )
            )
        }
    }
}

// components/DatosBasicosSection.kt
@Composable
fun DatosBasicosSection(
    nombre: String,
    onNombreChange: (String) -> Unit,
    telefono: String,
    onTelefonoChange: (String) -> Unit,
    email: String,
    onEmailChange: (String) -> Unit,
    genero: String,
    onGeneroChange: (String) -> Unit,
    ocupacion: String,
    onOcupacionChange: (String) -> Unit
) {
    SectionCard(
        title = "Datos Básicos",
        icon = FontAwesomeIcons.Solid.AddressCard
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            CustomOutlinedTextField(
                value = nombre,
                onValueChange = onNombreChange,
                label = "Nombre Completo",
                icon = FontAwesomeIcons.Solid.User
            )
            CustomOutlinedTextField(
                value = telefono,
                onValueChange = onTelefonoChange,
                label = "Teléfono",
                icon = FontAwesomeIcons.Solid.Phone,
                keyboardType = KeyboardType.Phone
            )
            CustomOutlinedTextField(
                value = email,
                onValueChange = onEmailChange,
                label = "Email",
                icon = FontAwesomeIcons.Solid.Envelope,
                keyboardType = KeyboardType.Email
            )
            CustomDropdownMenu(
                value = genero,
                onValueChange = onGeneroChange,
                label = "Género",
                options = listOf("Masculino", "Femenino", "Otro"),
                icon = FontAwesomeIcons.Solid.VenusMars
            )
            CustomOutlinedTextField(
                value = ocupacion,
                onValueChange = onOcupacionChange,
                label = "Ocupación",
                icon = FontAwesomeIcons.Solid.Briefcase
            )
        }
    }
}

// components/DatosFisicosSection.kt
@Composable
fun DatosFisicosSection(
    altura: String,
    onAlturaChange: (String) -> Unit,
    peso: String,
    onPesoChange: (String) -> Unit,
    edad: String,
    onEdadChange: (String) -> Unit,
    restriccionesSalud: String,
    onRestriocionesSaludChange: (String) -> Unit,
    alergias: String,
    onAlergiasChange: (String) -> Unit
) {
    SectionCard(
        title = "Datos Físicos",
        icon = FontAwesomeIcons.Solid.Weight
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                CustomOutlinedTextField(
                    value = altura,
                    onValueChange = onAlturaChange,
                    label = "Altura (m)",
                    icon = FontAwesomeIcons.Solid.RulerVertical,
                    modifier = Modifier.weight(1f),
                    keyboardType = KeyboardType.Decimal
                )
                CustomOutlinedTextField(
                    value = peso,
                    onValueChange = onPesoChange,
                    label = "Peso (kg)",
                    icon = FontAwesomeIcons.Solid.Weight,
                    modifier = Modifier.weight(1f),
                    keyboardType = KeyboardType.Decimal
                )
            }
            CustomOutlinedTextField(
                value = edad,
                onValueChange = onEdadChange,
                label = "Edad",
                icon = FontAwesomeIcons.Solid.Calendar,
                keyboardType = KeyboardType.Number
            )
            CustomOutlinedTextField(
                value = restriccionesSalud,
                onValueChange = onRestriocionesSaludChange,
                label = "Restricciones de Salud",
                icon = FontAwesomeIcons.Solid.FileAlt,
                maxLines = 3,
                singleLine = false
            )
            CustomOutlinedTextField(
                value = alergias,
                onValueChange = onAlergiasChange,
                label = "Alergias",
                icon = FontAwesomeIcons.Solid.ExclamationTriangle,
                maxLines = 2,
                singleLine = false
            )
        }
    }
}

// components/ExperienciaPreferenciasSection.kt
@Composable
fun ExperienciaPreferenciasSection(
    experienciaEjercicio: String,
    onExperienciaEjercicioChange: (String) -> Unit,
    horarioPreferido: String,
    onHorarioPreferidoChange: (String) -> Unit,
    nivelActividad: String,
    onNivelActividadChange: (String) -> Unit,
    tipoObjetivo: String,
    onTipoObjetivoChange: (String) -> Unit,
    meta: String,
    onMetaChange: (String) -> Unit,
    caracteristicas: String,
    onCaracteristicasChange: (String) -> Unit,
    necesitaRutinaCasa: Boolean,
    onNecesitaRutinaCasaChange: (Boolean) -> Unit
) {
    SectionCard(
        title = "Experiencia y Preferencias",
        icon = FontAwesomeIcons.Solid.Dumbbell
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            CustomDropdownMenu(
                value = experienciaEjercicio,
                onValueChange = onExperienciaEjercicioChange,
                label = "Nivel de Experiencia",
                options = listOf("Principiante", "Intermedio", "Avanzado"),
                icon = FontAwesomeIcons.Solid.ChartLine
            )
            CustomDropdownMenu(
                value = meta,
                onValueChange = onMetaChange,
                label = "Meta Principal",
                options = listOf(
                    "Pérdida de peso",
                    "Ganancia muscular",
                    "Tonificación",
                    "Mejora de resistencia",
                    "Rehabilitación",
                    "Mantenimiento",
                    "Preparación deportiva"
                ),
                icon = FontAwesomeIcons.Solid.Crosshairs
            )
            CustomDropdownMenu(
                value = caracteristicas,
                onValueChange = onCaracteristicasChange,
                label = "Caract. Entrenamiento",
                options = listOf(
                    "Alto rendimiento",
                    "Rehabilitación",
                    "Principiante",
                    "Deportista amateur",
                    "Competidor",
                    "Fitness recreativo",
                    "Salud y bienestar"
                ),
                icon = FontAwesomeIcons.Solid.ListUl
            )

            CustomDropdownMenu(
                value = horarioPreferido,
                onValueChange = onHorarioPreferidoChange,
                label = "Horario Preferido",
                options = listOf("Mañana", "Tarde", "Noche"),
                icon = FontAwesomeIcons.Solid.Clock
            )
            CustomDropdownMenu(
                value = nivelActividad,
                onValueChange = onNivelActividadChange,
                label = "Nivel de Actividad",
                options = listOf("Sedentario", "Moderado", "Activo", "Muy activo"),
                icon = FontAwesomeIcons.Solid.PersonBooth
            )
            CustomDropdownMenu(
                value = tipoObjetivo,
                onValueChange = onTipoObjetivoChange,
                label = "Tipo de Objetivo",
                options = listOf(
                    "Culturismo",
                    "Danza",
                    "Influencer fitness",
                    "Modelo fitness",
                    "Deportista amateur",
                    "Bienestar general",
                    "Competidor"
                ),
                icon = FontAwesomeIcons.Solid.Flag
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Checkbox(
                    checked = necesitaRutinaCasa,
                    onCheckedChange = onNecesitaRutinaCasaChange
                )
                Text("Necesita rutina en casa")
            }
        }
    }
}

// components/Buttons.kt
@Composable
fun BotonesAccion(
    isCreate: Boolean,
    onGuardarClick: () -> Unit,
    onCrearClick: () -> Unit,
    onLimpiarClick: () -> Unit,
    onVolverAtrasClick: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Button(
            modifier = Modifier.weight(1f),
            onClick = onGuardarClick,
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.secondary
            )
        ) {
            Icon(
                imageVector = FontAwesomeIcons.Solid.Save,
                contentDescription = null,
                modifier = Modifier.size(20.dp)
            )
        }

        Button(
            modifier = Modifier.weight(1f),
            onClick = onCrearClick,
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.secondary
            )
        ) {
            Icon(
                imageVector = FontAwesomeIcons.Solid.Plus,
                contentDescription = null,
                modifier = Modifier.size(20.dp)
            )
        }

        Button(
            modifier = Modifier.weight(1f),
            onClick = onLimpiarClick,
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.secondary
            )
        ) {
            Icon(
                imageVector = FontAwesomeIcons.Solid.Eraser,
                contentDescription = null,
                modifier = Modifier.size(20.dp)
            )
        }

        Button(
            modifier = Modifier.weight(1f),
            onClick = onVolverAtrasClick,
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.secondary
            )
        ) {
            Icon(
                imageVector = FontAwesomeIcons.Solid.ArrowLeft,
                contentDescription = null,
                modifier = Modifier.size(20.dp)
            )
        }
    }
}

@Composable
fun BotonesMemento(
    onDeshacerClick: () -> Unit,
    onRehacerClick: () -> Unit,
    canUndo: Boolean,
    canRedo: Boolean
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Button(
            modifier = Modifier.weight(1f),
            onClick = onDeshacerClick,
            enabled = canUndo,  // Deshabilitar si no hay estados para deshacer
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.tertiary,
                // Color cuando está deshabilitado
                disabledContainerColor = MaterialTheme.colorScheme.tertiary.copy(alpha = 0.5f)
            )
        ) {
            Icon(
                imageVector = FontAwesomeIcons.Solid.Undo,
                contentDescription = null,
                modifier = Modifier.size(24.dp),
                // Color del icono cuando está deshabilitado
                tint = if (canUndo)
                    MaterialTheme.colorScheme.onTertiary
                else
                    MaterialTheme.colorScheme.onTertiary.copy(alpha = 0.5f)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                "Deshacer",
                // Color del texto cuando está deshabilitado
                color = if (canUndo)
                    MaterialTheme.colorScheme.onTertiary
                else
                    MaterialTheme.colorScheme.onTertiary.copy(alpha = 0.5f)
            )
        }

        Button(
            modifier = Modifier.weight(1f),
            onClick = onRehacerClick,
            enabled = canRedo,  // Deshabilitar si no hay estados para rehacer
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.tertiary,
                disabledContainerColor = MaterialTheme.colorScheme.tertiary.copy(alpha = 0.5f)
            )
        ) {
            Icon(
                imageVector = FontAwesomeIcons.Solid.Redo,
                contentDescription = null,
                modifier = Modifier.size(24.dp),
                tint = if (canRedo)
                    MaterialTheme.colorScheme.onTertiary
                else
                    MaterialTheme.colorScheme.onTertiary.copy(alpha = 0.5f)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                "Rehacer",
                color = if (canRedo)
                    MaterialTheme.colorScheme.onTertiary
                else
                    MaterialTheme.colorScheme.onTertiary.copy(alpha = 0.5f)
            )
        }
    }
    // Feedback visual opcional cuando los botones están deshabilitados
    if (!canUndo && !canRedo) {
        Text(
            text = "No hay cambios para deshacer o rehacer",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 4.dp),
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun BotonesAdicionales(
    onIAClick: () -> Unit,
    onListaClick: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Button(
            modifier = Modifier.weight(1f),
            onClick = onIAClick,
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.tertiary
            )
        ) {
            Icon(
                imageVector = FontAwesomeIcons.Solid.Robot,
                contentDescription = null,
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text("IA")
        }

        Button(
            modifier = Modifier.weight(1f),
            onClick = onListaClick,
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.tertiary
            )
        ) {
            Icon(
                imageVector = FontAwesomeIcons.Solid.List,
                contentDescription = null,
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text("Lista")
        }
    }
}


@Composable
fun ClienteListDialog(
    clientes: List<Cliente>,
    onDismiss: () -> Unit,
    onEditClick: (Cliente, Int) -> Unit,
    onDeleteClick: (Cliente, Int) -> Unit
) {
    AlertDialog(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(max = 600.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(MaterialTheme.colorScheme.surface),
        onDismissRequest = onDismiss,
        properties = DialogProperties(
            usePlatformDefaultWidth = false,
            dismissOnClickOutside = true
        ),
        title = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp, bottom = 8.dp),  // Solo padding vertical
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Icon(
                    imageVector = FontAwesomeIcons.Solid.Users,
                    contentDescription = null,
                    modifier = Modifier.size(24.dp),
                    tint = MaterialTheme.colorScheme.primary
                )
                Text(
                    "Lista de Clientes",
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.Bold
                    )
                )
            }
        },
        text = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(400.dp)
                    .padding(0.dp)  // Quitamos padding
            ) {
                if (clientes.isEmpty()) {
                    EmptyClientesList()
                } else {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(4.dp)  // Reducido el espaciado
                    ) {
                        itemsIndexed(clientes) { index, cliente ->
                            ClienteListItem(
                                cliente = cliente,
                                onEditClick = { onEditClick(cliente, index) },
                                onDeleteClick = { onDeleteClick(cliente, index) }
                            )
                        }
                    }
                }
            }
        },
        confirmButton = {

        }
    )
}

@Composable
private fun EmptyClientesList() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Icon(
                imageVector = FontAwesomeIcons.Solid.UserSlash,
                contentDescription = null,
                modifier = Modifier.size(48.dp),
                tint = MaterialTheme.colorScheme.primary.copy(alpha = 0.6f)
            )
            Text(
                "No hay clientes registrados",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
            )
        }
    }
}


@Composable
fun ClienteListItem(
    cliente: Cliente,
    onEditClick: () -> Unit,
    onDeleteClick: () -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth(),
        color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.7f)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp, horizontal = 4.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Avatar y detalles del cliente
            Row(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Avatar
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .background(
                            MaterialTheme.colorScheme.primary.copy(alpha = 0.1f),
                            CircleShape
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = FontAwesomeIcons.Solid.UserCircle,
                        contentDescription = null,
                        modifier = Modifier.size(24.dp),
                        tint = MaterialTheme.colorScheme.primary
                    )
                }

                // Información del cliente
                Column(
                    modifier = Modifier
                        .padding(start = 8.dp)
                        .weight(1f)
                ) {
                    Text(
                        text = cliente.nombre,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.SemiBold,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = FontAwesomeIcons.Solid.Phone,
                            contentDescription = null,
                            modifier = Modifier.size(12.dp),
                            tint = MaterialTheme.colorScheme.primary
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = cliente.telefono,
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
                        )
                    }
                }
            }

            // Botones
            // Botones
            Row(
                modifier = Modifier.padding(
                    start = 16.dp,
                    end = 8.dp
                ), // Aumentamos el padding del inicio para separar del contenido
                horizontalArrangement = Arrangement.spacedBy(16.dp)  // Aumentamos el espacio entre botones de 8.dp a 16.dp
            ) {
                IconButton(
                    onClick = onEditClick,
                    modifier = Modifier
                        .size(36.dp)
                        .background(
                            MaterialTheme.colorScheme.primary.copy(alpha = 0.1f),
                            CircleShape
                        )
                ) {
                    Icon(
                        imageVector = FontAwesomeIcons.Solid.Edit,
                        contentDescription = "Editar",
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(16.dp)
                    )
                }

                IconButton(
                    onClick = onDeleteClick,
                    modifier = Modifier
                        .size(36.dp)
                        .background(
                            MaterialTheme.colorScheme.error.copy(alpha = 0.1f),
                            CircleShape
                        )
                ) {
                    Icon(
                        imageVector = FontAwesomeIcons.Solid.TrashAlt,
                        contentDescription = "Eliminar",
                        tint = MaterialTheme.colorScheme.error,
                        modifier = Modifier.size(16.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun IADialog(
    iaFitness: IAFitness,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        containerColor = MaterialTheme.colorScheme.surface,
        properties = DialogProperties(
            dismissOnBackPress = true,
            dismissOnClickOutside = true,
            usePlatformDefaultWidth = false
        ),
        modifier = Modifier
            .fillMaxWidth(0.92f)
            .heightIn(max = 580.dp) // Altura máxima fija
            .clip(RoundedCornerShape(28.dp)),
        title = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Icono circular con fondo
                Box(
                    modifier = Modifier
                        .size(64.dp)
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.1f)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = FontAwesomeIcons.Solid.Robot,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(32.dp)
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                // Título
                Text(
                    text = "Entrenador Virtual",
                    style = MaterialTheme.typography.headlineSmall.copy(
                        fontWeight = FontWeight.Bold
                    ),
                    color = MaterialTheme.colorScheme.onSurface
                )

                Spacer(modifier = Modifier.height(4.dp))

                // Subtítulo
                Text(
                    text = "Tu compañero fitness personalizado",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Spacer(modifier = Modifier.height(12.dp))
                Divider(color = MaterialTheme.colorScheme.outlineVariant)
            }
        },
        text = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(max = 320.dp) // Altura máxima para el contenido scrolleable
            ) {
                if (iaFitness.descripcion.isEmpty()) {
                    EmptyStateContent()
                } else {
                    FilledStateContent(iaFitness)
                }
            }
        },
        confirmButton = {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterHorizontally),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Botón principal solo con icono
                Button(
                    onClick = {
                        onConfirm()
                        /*onDismiss()*/
                    },
                    modifier = Modifier
                        .size(56.dp),
                    shape = CircleShape,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary
                    ),
                    elevation = ButtonDefaults.buttonElevation(
                        defaultElevation = 4.dp,
                        pressedElevation = 0.dp,
                        hoveredElevation = 6.dp
                    ),
                    contentPadding = PaddingValues(0.dp)
                ) {
                    Icon(
                        imageVector = FontAwesomeIcons.Solid.Magic,
                        contentDescription = null,
                        modifier = Modifier.size(18.dp),
                        tint = MaterialTheme.colorScheme.onPrimary
                    )
                }

                // Botón secundario solo con icono
                OutlinedButton(
                    onClick = onDismiss,
                    modifier = Modifier
                        .size(56.dp),
                    shape = CircleShape,
                    border = BorderStroke(
                        width = 1.dp,
                        color = MaterialTheme.colorScheme.outline.copy(alpha = 0.3f)
                    ),
                    colors = ButtonDefaults.outlinedButtonColors(
                        containerColor = MaterialTheme.colorScheme.background
                    ),
                    contentPadding = PaddingValues(0.dp)
                ) {
                    Icon(
                        imageVector = FontAwesomeIcons.Solid.Clock,
                        contentDescription = "Más tarde",
                        modifier = Modifier.size(24.dp),
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
            }
        }
    )
}

@Composable
private fun EmptyStateContent() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        Text(
            text = "¿Listo para comenzar tu viaje fitness? Te ayudaré a crear:",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        BenefitRow(
            icon = FontAwesomeIcons.Solid.Carrot,
            text = "Plan nutricional personalizado",
            subtext = "Adapto tu dieta a tus objetivos y preferencias"
        )

        BenefitRow(
            icon = FontAwesomeIcons.Solid.Dumbbell,
            text = "Rutinas de ejercicio adaptadas",
            subtext = "Ejercicios específicos para tu nivel y metas"
        )

        BenefitRow(
            icon = FontAwesomeIcons.Solid.ChartLine,
            text = "Seguimiento de progreso",
            subtext = "Monitoreo constante de tus avances"
        )
    }
}

@Composable
private fun BenefitRow(
    icon: ImageVector,
    text: String,
    subtext: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.05f))
            .padding(12.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.1f)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(20.dp)
            )
        }

        Column {
            Text(
                text = text,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface
            )
            Text(
                text = subtext,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun FilledStateContent(iaFitness: IAFitness) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        // Descripción principal
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.05f)
            ),
            shape = RoundedCornerShape(12.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Icon(
                        imageVector = FontAwesomeIcons.Solid.InfoCircle,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(24.dp)
                    )
                    Text(
                        text = "Resumen del Plan",
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.SemiBold
                        ),
                        color = MaterialTheme.colorScheme.primary
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = iaFitness.descripcion,
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
        }

        // Ventajas
        if (iaFitness.ventajas.isNotEmpty()) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.1f)
                ),
                shape = RoundedCornerShape(12.dp)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Icon(
                            imageVector = FontAwesomeIcons.Solid.CheckCircle,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(24.dp)
                        )
                        Text(
                            text = "Beneficios del Plan",
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontWeight = FontWeight.SemiBold
                            ),
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                    Spacer(modifier = Modifier.height(4.dp))
                    iaFitness.ventajas.forEach { ventaja ->
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(12.dp),
                            verticalAlignment = Alignment.Top,
                            modifier = Modifier.padding(vertical = 4.dp)
                        ) {
                            Icon(
                                imageVector = FontAwesomeIcons.Solid.Check,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.primary,
                                modifier = Modifier.size(20.dp)
                            )
                            Text(
                                text = ventaja,
                                style = MaterialTheme.typography.bodyLarge,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        }
                    }
                }
            }
        }

        // Consideraciones
        if (iaFitness.desventajas.isNotEmpty()) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.errorContainer.copy(alpha = 0.1f)
                ),
                shape = RoundedCornerShape(12.dp)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Icon(
                            imageVector = FontAwesomeIcons.Solid.ExclamationTriangle,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.error,
                            modifier = Modifier.size(24.dp)
                        )
                        Text(
                            text = "Consideraciones Importantes",
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontWeight = FontWeight.SemiBold
                            ),
                            color = MaterialTheme.colorScheme.error
                        )
                    }
                    Spacer(modifier = Modifier.height(4.dp))
                    iaFitness.desventajas.forEach { consideracion ->
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(12.dp),
                            verticalAlignment = Alignment.Top,
                            modifier = Modifier.padding(vertical = 4.dp)
                        ) {
                            Icon(
                                imageVector = FontAwesomeIcons.Solid.ExclamationCircle,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.error,
                                modifier = Modifier.size(20.dp)
                            )
                            Text(
                                text = consideracion,
                                style = MaterialTheme.typography.bodyLarge,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        }
                    }
                }
            }
        }

        // Footer con nota
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.3f)
            ),
            shape = RoundedCornerShape(12.dp)
        ) {
            Row(
                modifier = Modifier.padding(12.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = FontAwesomeIcons.Solid.InfoCircle,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(20.dp)
                )
                Text(
                    text = "Este plan se irá ajustando según tu progreso y feedback",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

@Composable
private fun BenefitRow(
    icon: ImageVector,
    text: String,
    iconTint: Color = MaterialTheme.colorScheme.primary
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = iconTint,
            modifier = Modifier.size(24.dp)
        )
        Text(
            text = text,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomOutlinedTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    icon: ImageVector,
    modifier: Modifier = Modifier,
    keyboardType: KeyboardType = KeyboardType.Text,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    isError: Boolean = false,
    maxLines: Int = 1,
    singleLine: Boolean = true
) {
    OutlinedTextField(
        modifier = modifier.fillMaxWidth(),
        value = value,
        onValueChange = onValueChange,
        label = {
            Text(
                text = label,
                style = MaterialTheme.typography.bodyMedium
            )
        },
        leadingIcon = {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(20.dp)
            )
        },
        shape = RoundedCornerShape(12.dp),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = MaterialTheme.colorScheme.primary,
            unfocusedBorderColor = MaterialTheme.colorScheme.outline.copy(alpha = 0.5f),
            focusedLabelColor = MaterialTheme.colorScheme.primary,
            unfocusedLabelColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
        ),
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType
        ),
        visualTransformation = visualTransformation,
        isError = isError,
        maxLines = maxLines,
        singleLine = singleLine,
        textStyle = MaterialTheme.typography.bodyLarge
    )
}

@Composable
fun SectionCard(
    title: String,
    icon: ImageVector,
    content: @Composable () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(24.dp)
                )
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Bold
                    ),
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
            Divider(
                color = MaterialTheme.colorScheme.primary.copy(alpha = 0.1f),
                thickness = 1.dp
            )
            content()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomDropdownMenu(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    options: List<String>,
    icon: ImageVector,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = it },
        modifier = modifier
    ) {
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .menuAnchor(),
            value = value,
            onValueChange = {},
            readOnly = true,
            label = {
                Text(
                    text = label,
                    style = MaterialTheme.typography.bodyMedium
                )
            },
            leadingIcon = {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(20.dp)
                )
            },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
            },
            shape = RoundedCornerShape(12.dp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = MaterialTheme.colorScheme.primary,
                unfocusedBorderColor = MaterialTheme.colorScheme.outline.copy(alpha = 0.5f),
                focusedLabelColor = MaterialTheme.colorScheme.primary,
                unfocusedLabelColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
            ),
            textStyle = MaterialTheme.typography.bodyLarge
        )

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .background(MaterialTheme.colorScheme.surface)
                .exposedDropdownSize()
        ) {
            options.forEach { option ->
                DropdownMenuItem(
                    text = {
                        Text(
                            text = option,
                            style = MaterialTheme.typography.bodyLarge
                        )
                    },
                    onClick = {
                        onValueChange(option)
                        expanded = false
                    },
                    contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
                )
            }
        }
    }
}


