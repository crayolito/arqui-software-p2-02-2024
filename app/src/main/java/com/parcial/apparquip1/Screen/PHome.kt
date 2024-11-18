package com.parcial.apparquip1.Screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.parcial.apparquip1.R
import com.parcial.apparquip1.common.OptionPS
import com.parcial.apparquip1.getScreenSize

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PHome(navController: NavHostController, screenWidth: Dp, screenHeight: Dp) {

    val (screenWidth, screenHeight) = getScreenSize()
    val optionsHomeScreen = listOf(
        OptionPS(R.drawable.cliente, "Clientes", "cliente"),
        OptionPS(R.drawable.plan_rutina2, "Ejercicios", "plan_ejercicio"),
        OptionPS(R.drawable.categoria_ejercicio, "Categorias Ejercicios", "categoria_ejercicio"),
        OptionPS(R.drawable.plan_alimentacion, "Alimentacion", "plan_alimentacion"),
        OptionPS(R.drawable.plan_rutina, "Rutina Personalizada", "plan_rutina"),
        OptionPS(R.drawable.plan_ejercicio, "Envio Planificacion", "envio_rutina"),
    )

    Scaffold(
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .size(screenWidth, screenHeight)
                .padding(innerPadding)
                .background(MaterialTheme.colorScheme.primary), contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    modifier = Modifier.padding(bottom = screenHeight * 0.02f),
                    text = "NextLevelFit",
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontSize = (screenWidth.value * 0.1f).sp
                    ),
                )
                Box(
                    modifier = Modifier.size(screenWidth, screenHeight * 0.6f)
                ) {
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2),
                        contentPadding = PaddingValues(5.dp)
                    ) {
                        items(optionsHomeScreen.size) { index ->
                            Column(
                                modifier = Modifier
                                    .size(screenWidth * 0.2f, screenHeight * 0.2f)
                                    .padding(5.dp)
                                    .clip(RoundedCornerShape(10.dp))
                                    .background(MaterialTheme.colorScheme.tertiary)
                                    .clickable { navController.navigate(optionsHomeScreen[index].ruta) },
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center
                            ) {
                                Image(
                                    modifier = Modifier
                                        .size(
                                            screenWidth * 0.18f, screenHeight * 0.1f
                                        )
                                        .fillMaxSize(),
                                    painter = painterResource(
                                        id = optionsHomeScreen[index].imagen
                                    ),
                                    contentDescription = optionsHomeScreen[index].titulo,
                                    contentScale = ContentScale.FillBounds
                                )
                                Spacer(modifier = Modifier.size(screenHeight * 0.01f))
                                Text(
                                    text = optionsHomeScreen[index].titulo,
                                    style = MaterialTheme.typography.bodyLarge,
                                    textAlign = TextAlign.Center
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}