package com.example.ui.components

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.KimeraCharcoal
import com.example.ui.theme.KimeraCoral
import com.example.ui.theme.KimeraDeepCoral
import com.example.ui.theme.KimeraSoftPeach
import com.example.ui.theme.KimeraWarmGrey

@Composable
fun CoralOrbLoader(
    elapsedSeconds: Int,
    targetSeconds: Int = 105,
    modifier: Modifier = Modifier
) {
    val infiniteTransition = rememberInfiniteTransition(label = "orb_pulse")

    val pulseScale by infiniteTransition.animateFloat(
        initialValue = 0.88f,
        targetValue = 1.08f,
        animationSpec = infiniteRepeatable(
            animation = tween(2200, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "scale"
    )

    val glowAlpha by infiniteTransition.animateFloat(
        initialValue = 0.35f,
        targetValue = 0.75f,
        animationSpec = infiniteRepeatable(
            animation = tween(1800, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "alpha"
    )

    val rotation by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(8000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "rotation"
    )

    val progress = (elapsedSeconds.toFloat() / targetSeconds.toFloat()).coerceIn(0.05f, 0.95f)

    Box(
        modifier = modifier.size(240.dp),
        contentAlignment = Alignment.Center
    ) {
        Canvas(modifier = Modifier.size(230.dp)) {
            val center = Offset(size.width / 2, size.height / 2)
            val baseRadius = size.minDimension / 2 - 20.dp.toPx()

            // Outer blurred/soft radial glow
            drawCircle(
                brush = Brush.radialGradient(
                    colors = listOf(
                        KimeraSoftPeach.copy(alpha = glowAlpha),
                        KimeraCoral.copy(alpha = glowAlpha * 0.4f),
                        Color.Transparent
                    ),
                    center = center,
                    radius = baseRadius * 1.35f * pulseScale
                ),
                center = center,
                radius = baseRadius * 1.35f * pulseScale
            )

            // Inner soft gradient orb
            drawCircle(
                brush = Brush.radialGradient(
                    colors = listOf(
                        Color.White.copy(alpha = 0.9f),
                        KimeraSoftPeach.copy(alpha = 0.7f),
                        KimeraCoral.copy(alpha = 0.25f)
                    ),
                    center = center,
                    radius = baseRadius * 0.95f
                ),
                center = center,
                radius = baseRadius * 0.95f
            )

            // Circular track ring
            drawCircle(
                color = Color.White.copy(alpha = 0.8f),
                radius = baseRadius,
                center = center,
                style = Stroke(width = 10.dp.toPx(), cap = StrokeCap.Round)
            )

            // Active progress ring arc
            drawArc(
                brush = Brush.sweepGradient(
                    colors = listOf(
                        KimeraCoral,
                        KimeraDeepCoral,
                        KimeraCoral
                    )
                ),
                startAngle = -90f + (rotation * 0.1f),
                sweepAngle = 360f * progress,
                useCenter = false,
                topLeft = Offset(center.x - baseRadius, center.y - baseRadius),
                size = androidx.compose.ui.geometry.Size(baseRadius * 2, baseRadius * 2),
                style = Stroke(width = 8.dp.toPx(), cap = StrokeCap.Round)
            )
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = Icons.Default.AutoAwesome,
                contentDescription = null,
                tint = KimeraDeepCoral,
                modifier = Modifier.size(28.dp)
            )

            Spacer(modifier = Modifier.height(4.dp))

            val mins = elapsedSeconds / 60
            val secs = elapsedSeconds % 60
            val formattedTime = String.format("%d:%02d", mins, secs)

            Text(
                text = formattedTime,
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.SansSerif,
                color = KimeraCharcoal
            )

            val targetMins = targetSeconds / 60
            val targetSecs = targetSeconds % 60
            val formattedTarget = String.format("%d:%02d", targetMins, targetSecs)

            Text(
                text = "/ $formattedTarget elapsed",
                fontSize = 12.sp,
                color = KimeraWarmGrey,
                fontWeight = FontWeight.Medium
            )
        }
    }
}
