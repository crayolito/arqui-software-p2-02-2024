package com.parcial.apparquip1.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        color = Color(0xFFff6D1F),
        fontFamily = FontFamily.Monospace,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    titleLarge = TextStyle(
        color = Color(0xFFff6D1F),
        fontFamily = FontFamily.Monospace,
        fontWeight = FontWeight.Bold,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp,
        shadow = Shadow(
            offset = Offset(5f, 5f),
            blurRadius = 5f,
            color= Color(0xFFF5E7C6).copy(alpha = 0.5f)
        )
    ),
    // LETRAS PARA LOS INPUTS
    titleMedium = TextStyle(
        color = Color(0xFF222222),
        fontFamily = FontFamily.Monospace,
        fontSize = 18.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.sp
    ),
    titleSmall = TextStyle(
        color = Color(0xFF222222),
        fontFamily = FontFamily.Monospace,
        fontSize = 15.sp,
    ),

    /* Other default text styles to override


    */
)