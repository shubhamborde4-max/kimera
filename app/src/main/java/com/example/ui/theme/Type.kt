package com.example.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

val KimeraSerifFamily = FontFamily.Serif
val KimeraSansFamily = FontFamily.SansSerif

val KimeraTypography = Typography(
    displayLarge = TextStyle(
        fontFamily = KimeraSerifFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 32.sp,
        lineHeight = 38.sp,
        letterSpacing = (-0.5).sp,
        color = KimeraCharcoal
    ),
    displayMedium = TextStyle(
        fontFamily = KimeraSerifFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 26.sp,
        lineHeight = 32.sp,
        color = KimeraCharcoal
    ),
    titleLarge = TextStyle(
        fontFamily = KimeraSerifFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        color = KimeraCharcoal
    ),
    titleMedium = TextStyle(
        fontFamily = KimeraSansFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 17.sp,
        lineHeight = 22.sp,
        letterSpacing = 0.sp,
        color = KimeraCharcoal
    ),
    titleSmall = TextStyle(
        fontFamily = KimeraSansFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        lineHeight = 18.sp,
        letterSpacing = 0.1.sp,
        color = KimeraCharcoal
    ),
    bodyLarge = TextStyle(
        fontFamily = KimeraSansFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 15.sp,
        lineHeight = 22.sp,
        letterSpacing = 0.2.sp,
        color = KimeraCharcoal
    ),
    bodyMedium = TextStyle(
        fontFamily = KimeraSansFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 13.sp,
        lineHeight = 18.sp,
        letterSpacing = 0.1.sp,
        color = KimeraWarmGrey
    ),
    labelLarge = TextStyle(
        fontFamily = KimeraSansFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 14.sp,
        lineHeight = 18.sp,
        letterSpacing = 0.3.sp
    ),
    labelMedium = TextStyle(
        fontFamily = KimeraSansFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.4.sp
    ),
    labelSmall = TextStyle(
        fontFamily = KimeraSansFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 10.sp,
        lineHeight = 14.sp,
        letterSpacing = 0.5.sp
    )
)
