package com.example.ui.screens

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.ChatBubbleOutline
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.HourglassBottom
import androidx.compose.material.icons.filled.Sync
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.ui.components.CoralOrbLoader
import com.example.ui.components.StudioTopBar
import com.example.ui.theme.KimeraBadgePink
import com.example.ui.theme.KimeraCharcoal
import com.example.ui.theme.KimeraCoral
import com.example.ui.theme.KimeraDeepCoral
import com.example.ui.theme.KimeraIvory
import com.example.ui.theme.KimeraSuccessGreen
import com.example.ui.theme.KimeraWarmCream
import com.example.ui.theme.KimeraWarmGrey
import com.example.ui.theme.KimeraWarmSand
import com.example.ui.theme.KimeraWhite
import com.example.viewmodel.ConsultationViewModel

@Composable
fun GeneratingPreviewScreen(
    viewModel: ConsultationViewModel,
    modifier: Modifier = Modifier
) {
    val timer by viewModel.generationTimer.collectAsState()
    val clientName by viewModel.clientName.collectAsState()
    val scrollState = rememberScrollState()

    val infiniteTransition = rememberInfiniteTransition(label = "spin")
    val spinnerAngle by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(1200, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "rotation"
    )

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(KimeraWarmCream)
    ) {
        StudioTopBar(
            onBackClick = { viewModel.cancelGeneration() },
            onDashboardClick = { viewModel.showDashboard.value = true }
        )

        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(scrollState)
                .padding(horizontal = 20.dp, vertical = 6.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Target Chip
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(9999.dp))
                    .background(KimeraIvory)
                    .border(1.dp, KimeraWarmSand, RoundedCornerShape(9999.dp))
                    .padding(horizontal = 12.dp, vertical = 4.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(6.dp)
                            .clip(CircleShape)
                            .background(KimeraDeepCoral)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "TARGET: < 2 MIN",
                        style = MaterialTheme.typography.labelSmall,
                        fontWeight = FontWeight.Bold,
                        color = KimeraDeepCoral,
                        letterSpacing = 0.5.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            // Animated Coral Gradient-Blur Orb
            CoralOrbLoader(
                elapsedSeconds = timer,
                targetSeconds = 105,
                modifier = Modifier.testTag("coral_orb_loader")
            )

            Spacer(modifier = Modifier.height(14.dp))

            Text(
                text = "Synthesizing Your Adapted Style",
                style = MaterialTheme.typography.titleLarge,
                color = KimeraCharcoal,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "Mapping reference texture to ${clientName.split(" ").firstOrNull() ?: "client"}'s facial geometry and hair density...",
                style = MaterialTheme.typography.bodyMedium,
                color = KimeraWarmGrey,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Client Base & Reference Mini Cards
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Card(
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(14.dp),
                    colors = CardDefaults.cardColors(containerColor = KimeraIvory)
                ) {
                    Row(
                        modifier = Modifier.padding(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.img_client_chair),
                            contentDescription = null,
                            modifier = Modifier
                                .size(36.dp)
                                .clip(RoundedCornerShape(8.dp)),
                            contentScale = ContentScale.Crop
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Column {
                            Text(
                                text = "CLIENT BASE",
                                fontSize = 9.sp,
                                fontWeight = FontWeight.Bold,
                                color = KimeraWarmGrey
                            )
                            Text(
                                text = clientName,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold,
                                color = KimeraCharcoal
                            )
                        }
                    }
                }

                Card(
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(14.dp),
                    colors = CardDefaults.cardColors(containerColor = KimeraIvory)
                ) {
                    Row(
                        modifier = Modifier.padding(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.img_style_ref),
                            contentDescription = null,
                            modifier = Modifier
                                .size(36.dp)
                                .clip(RoundedCornerShape(8.dp)),
                            contentScale = ContentScale.Crop
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Column {
                            Text(
                                text = "REFERENCE",
                                fontSize = 9.sp,
                                fontWeight = FontWeight.Bold,
                                color = KimeraWarmGrey
                            )
                            Text(
                                text = "Textured Crop...",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold,
                                color = KimeraCharcoal
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            // Synthesis Milestones Card
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(18.dp),
                colors = CardDefaults.cardColors(containerColor = KimeraIvory)
            ) {
                Column(modifier = Modifier.padding(14.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "SYNTHESIS MILESTONES",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = KimeraCharcoal,
                            letterSpacing = 0.5.sp
                        )
                        Text(
                            text = "Step 3 of 4",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = KimeraDeepCoral
                        )
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    // Milestone 1
                    MilestoneRow(
                        isDone = true,
                        isActive = false,
                        title = "Facial landmark & hairline detection",
                        timing = "0.12s"
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    // Milestone 2
                    MilestoneRow(
                        isDone = true,
                        isActive = false,
                        title = "Analyzing reference texture & taper...",
                        timing = "0.24s"
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    // Milestone 3 (Active)
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(10.dp))
                            .background(KimeraBadgePink.copy(alpha = 0.6f))
                            .padding(8.dp),
                        verticalAlignment = Alignment.Top
                    ) {
                        Icon(
                            imageVector = Icons.Default.Sync,
                            contentDescription = null,
                            tint = KimeraDeepCoral,
                            modifier = Modifier
                                .size(16.dp)
                                .rotate(spinnerAngle)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = "Generating 4 tailored preview variations...",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold,
                                color = KimeraDeepCoral
                            )
                            Text(
                                text = "Synthesizing volume distributions & parietal ridge",
                                fontSize = 10.sp,
                                color = KimeraCharcoal
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    // Milestone 4 (Pending)
                    MilestoneRow(
                        isDone = false,
                        isActive = false,
                        title = "Chairside consultation lighting calibration",
                        timing = ""
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Chairside Tip Card
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(14.dp),
                colors = CardDefaults.cardColors(containerColor = KimeraWhite)
            ) {
                Row(
                    modifier = Modifier.padding(12.dp),
                    verticalAlignment = Alignment.Top
                ) {
                    Box(
                        modifier = Modifier
                            .size(24.dp)
                            .clip(CircleShape)
                            .background(KimeraBadgePink),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.ChatBubbleOutline,
                            contentDescription = null,
                            tint = KimeraDeepCoral,
                            modifier = Modifier.size(13.dp)
                        )
                    }

                    Spacer(modifier = Modifier.width(10.dp))

                    Column {
                        Text(
                            text = "CHAIRSIDE TIP",
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold,
                            color = KimeraDeepCoral
                        )
                        Text(
                            text = "While generating: Discuss current scalp routine and styling wax preference with ${clientName.split(" ").firstOrNull() ?: "client"}.",
                            fontSize = 11.sp,
                            color = KimeraCharcoal,
                            lineHeight = 15.sp
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            // Incoming Variations row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "INCOMING VARIATIONS",
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold,
                    color = KimeraWarmGrey
                )
                Text(
                    text = "Buffering high-res...",
                    fontSize = 10.sp,
                    color = KimeraDeepCoral
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                repeat(4) { idx ->
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .height(68.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .background(KimeraIvory)
                            .border(1.dp, KimeraWarmSand, RoundedCornerShape(10.dp)),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.img_style_var1),
                            contentDescription = null,
                            modifier = Modifier
                                .fillMaxSize()
                                .blur(8.dp),
                            contentScale = ContentScale.Crop
                        )
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(Color.White.copy(alpha = 0.45f))
                        )
                        CircularProgressIndicator(
                            modifier = Modifier.size(16.dp),
                            color = KimeraCoral,
                            strokeWidth = 2.dp
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(18.dp))

            // View Results Now Button (Immediate completion)
            Button(
                onClick = { viewModel.completeGeneration() },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
                    .testTag("view_results_button"),
                colors = ButtonDefaults.buttonColors(
                    containerColor = KimeraCoral,
                    contentColor = KimeraWhite
                ),
                shape = RoundedCornerShape(9999.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.AutoAwesome,
                        contentDescription = null,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "View 4 Synthesized Variations",
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Cancel Button
            OutlinedButton(
                onClick = { viewModel.cancelGeneration() },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(44.dp)
                    .testTag("cancel_generation_button"),
                shape = RoundedCornerShape(9999.dp),
                colors = ButtonDefaults.outlinedButtonColors(
                    contentColor = KimeraWarmGrey
                ),
                border = androidx.compose.foundation.BorderStroke(1.dp, KimeraWarmSand)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = null,
                        modifier = Modifier.size(14.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(text = "Cancel Style Generation", fontSize = 13.sp)
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Kimera Neural Engine v2.4 • Low Heat Mode Active",
                fontSize = 10.sp,
                color = KimeraWarmGrey
            )

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
private fun MilestoneRow(
    isDone: Boolean,
    isActive: Boolean,
    title: String,
    timing: String
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            if (isDone) {
                Icon(
                    imageVector = Icons.Default.CheckCircle,
                    contentDescription = null,
                    tint = KimeraSuccessGreen,
                    modifier = Modifier.size(14.dp)
                )
            } else {
                Box(
                    modifier = Modifier
                        .size(14.dp)
                        .clip(CircleShape)
                        .border(1.dp, KimeraWarmGrey, CircleShape)
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = title,
                fontSize = 11.sp,
                color = if (isDone) KimeraCharcoal else KimeraWarmGrey,
                fontWeight = if (isDone) FontWeight.Medium else FontWeight.Normal
            )
        }

        if (timing.isNotBlank()) {
            Text(
                text = timing,
                fontSize = 11.sp,
                color = KimeraWarmGrey
            )
        }
    }
}
