package com.example.ui.screens

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
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.StarBorder
import androidx.compose.material.icons.filled.Tune
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.model.MatchOutcome
import com.example.ui.components.StudioTopBar
import com.example.ui.theme.KimeraBadgePink
import com.example.ui.theme.KimeraCharcoal
import com.example.ui.theme.KimeraCoral
import com.example.ui.theme.KimeraDeepCoral
import com.example.ui.theme.KimeraIvory
import com.example.ui.theme.KimeraSoftPeach
import com.example.ui.theme.KimeraWarmCream
import com.example.ui.theme.KimeraWarmGrey
import com.example.ui.theme.KimeraWarmSand
import com.example.ui.theme.KimeraWhite
import com.example.viewmodel.ConsultationViewModel

@Composable
fun OutcomeLogScreen(
    viewModel: ConsultationViewModel,
    modifier: Modifier = Modifier
) {
    val selectedIndex by viewModel.selectedVariationIndex.collectAsState()
    val hasCapturedFinal by viewModel.hasCapturedFinalCut.collectAsState()
    val selectedOutcome by viewModel.outcomeMatch.collectAsState()
    val realismRating by viewModel.realismRating.collectAsState()
    val feedback by viewModel.outcomeFeedback.collectAsState()

    val currentVariation = viewModel.variations[selectedIndex]
    val scrollState = rememberScrollState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(KimeraWarmCream)
    ) {
        StudioTopBar(
            onBackClick = { viewModel.navigateBack() },
            onDashboardClick = { viewModel.showDashboard.value = true }
        )

        // Post-Cut Evaluation Banner
        Box(
            modifier = Modifier
                .padding(horizontal = 20.dp, vertical = 4.dp)
                .clip(RoundedCornerShape(9999.dp))
                .background(KimeraBadgePink)
                .padding(horizontal = 12.dp, vertical = 5.dp)
        ) {
            Text(
                text = "✓ POST-CUT EVALUATION • PILOT METRIC LOGGING",
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold,
                color = KimeraDeepCoral,
                letterSpacing = 0.5.sp
            )
        }

        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(scrollState)
                .padding(horizontal = 20.dp, vertical = 8.dp)
        ) {
            Text(
                text = "Outcome & Accuracy Log",
                style = MaterialTheme.typography.displayMedium,
                color = KimeraCharcoal
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "Compare the finished haircut against the pre-cut AI preview.",
                style = MaterialTheme.typography.bodyMedium,
                color = KimeraWarmGrey
            )

            Spacer(modifier = Modifier.height(14.dp))

            // Consultation Fidelity Check Card
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = KimeraIvory),
                elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
            ) {
                Column(modifier = Modifier.padding(14.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Consultation Fidelity Check",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            color = KimeraCharcoal
                        )
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(9999.dp))
                                .background(KimeraWhite)
                                .border(1.dp, KimeraWarmSand, RoundedCornerShape(9999.dp))
                                .padding(horizontal = 8.dp, vertical = 2.dp)
                        ) {
                            Text(
                                text = "Chairside #4",
                                fontSize = 10.sp,
                                color = KimeraWarmGrey,
                                fontWeight = FontWeight.Medium
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        // AI Preview Card
                        Card(
                            modifier = Modifier.weight(1f),
                            shape = RoundedCornerShape(14.dp),
                            colors = CardDefaults.cardColors(containerColor = KimeraWhite)
                        ) {
                            Column(modifier = Modifier.padding(8.dp)) {
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(130.dp)
                                        .clip(RoundedCornerShape(10.dp))
                                ) {
                                    Image(
                                        painter = painterResource(id = currentVariation.imageRes),
                                        contentDescription = "AI Preview",
                                        modifier = Modifier.fillMaxSize(),
                                        contentScale = ContentScale.Crop
                                    )
                                    Box(
                                        modifier = Modifier
                                            .align(Alignment.TopStart)
                                            .padding(6.dp)
                                            .clip(RoundedCornerShape(9999.dp))
                                            .background(Color.Black.copy(alpha = 0.65f))
                                            .padding(horizontal = 6.dp, vertical = 2.dp)
                                    ) {
                                        Text(
                                            text = "✨ AI Preview",
                                            fontSize = 9.sp,
                                            color = KimeraWhite,
                                            fontWeight = FontWeight.Bold
                                        )
                                    }
                                }
                                Spacer(modifier = Modifier.height(6.dp))
                                Text(
                                    text = "Modern Taper + Fade",
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = KimeraCharcoal
                                )
                                Text(
                                    text = "Approved 24m ago",
                                    fontSize = 9.sp,
                                    color = KimeraWarmGrey
                                )
                            }
                        }

                        // Live Mirror Final Cut Card
                        Card(
                            modifier = Modifier.weight(1f),
                            shape = RoundedCornerShape(14.dp),
                            colors = CardDefaults.cardColors(containerColor = KimeraWhite)
                        ) {
                            Column(
                                modifier = Modifier
                                    .padding(8.dp)
                                    .fillMaxWidth(),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                if (hasCapturedFinal) {
                                    Box(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .height(130.dp)
                                            .clip(RoundedCornerShape(10.dp))
                                    ) {
                                        Image(
                                            painter = painterResource(id = R.drawable.img_final_cut),
                                            contentDescription = "Live Final Cut",
                                            modifier = Modifier.fillMaxSize(),
                                            contentScale = ContentScale.Crop
                                        )
                                    }
                                } else {
                                    Box(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .height(130.dp)
                                            .clip(RoundedCornerShape(10.dp))
                                            .background(KimeraIvory)
                                            .clickable { viewModel.captureFinalPhoto() },
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                            Box(
                                                modifier = Modifier
                                                    .size(36.dp)
                                                    .clip(CircleShape)
                                                    .background(KimeraBadgePink),
                                                contentAlignment = Alignment.Center
                                            ) {
                                                Icon(
                                                    imageVector = Icons.Default.CameraAlt,
                                                    contentDescription = null,
                                                    tint = KimeraDeepCoral,
                                                    modifier = Modifier.size(18.dp)
                                                )
                                            }
                                            Spacer(modifier = Modifier.height(6.dp))
                                            Text(
                                                text = "Live Mirror Final Cut",
                                                fontSize = 10.sp,
                                                fontWeight = FontWeight.Bold,
                                                color = KimeraCharcoal
                                            )
                                            Text(
                                                text = "Tap to capture result",
                                                fontSize = 9.sp,
                                                color = KimeraWarmGrey
                                            )
                                        }
                                    }
                                }

                                Spacer(modifier = Modifier.height(6.dp))

                                Box(
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(6.dp))
                                        .background(Color(0xFF272321))
                                        .clickable { viewModel.captureFinalPhoto() }
                                        .padding(horizontal = 8.dp, vertical = 3.dp)
                                        .testTag("capture_final_cut_button")
                                ) {
                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        Icon(
                                            imageVector = Icons.Default.CameraAlt,
                                            contentDescription = null,
                                            tint = KimeraWhite,
                                            modifier = Modifier.size(10.dp)
                                        )
                                        Spacer(modifier = Modifier.width(4.dp))
                                        Text(
                                            text = if (hasCapturedFinal) "Retake Photo" else "Capture Final Cut",
                                            fontSize = 9.sp,
                                            fontWeight = FontWeight.Bold,
                                            color = KimeraWhite
                                        )
                                    }
                                }

                                Text(
                                    text = if (hasCapturedFinal) "Verified in Mirror" else "Pending Photo\nChairside mirror view",
                                    fontSize = 9.sp,
                                    color = KimeraWarmGrey,
                                    textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                                    modifier = Modifier.padding(top = 2.dp)
                                )
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Match Question
            Text(
                text = "Did the final cut match the preview?",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = KimeraCharcoal
            )
            Text(
                text = "Pilot target: ≥66% direct preview accuracy rate.",
                fontSize = 11.sp,
                color = KimeraWarmGrey
            )

            Spacer(modifier = Modifier.height(10.dp))

            // 3 Match Options
            MatchOutcomeCard(
                outcome = MatchOutcome.EXACT_MATCH,
                isSelected = selectedOutcome == MatchOutcome.EXACT_MATCH,
                icon = {
                    Icon(
                        imageVector = Icons.Default.CheckCircle,
                        contentDescription = null,
                        tint = if (selectedOutcome == MatchOutcome.EXACT_MATCH) KimeraDeepCoral else KimeraWarmGrey,
                        modifier = Modifier.size(18.dp)
                    )
                },
                onClick = { viewModel.outcomeMatch.value = MatchOutcome.EXACT_MATCH }
            )

            Spacer(modifier = Modifier.height(8.dp))

            MatchOutcomeCard(
                outcome = MatchOutcome.MINOR_DIFFERENCES,
                isSelected = selectedOutcome == MatchOutcome.MINOR_DIFFERENCES,
                icon = {
                    Icon(
                        imageVector = Icons.Default.Tune,
                        contentDescription = null,
                        tint = if (selectedOutcome == MatchOutcome.MINOR_DIFFERENCES) KimeraDeepCoral else KimeraWarmGrey,
                        modifier = Modifier.size(18.dp)
                    )
                },
                onClick = { viewModel.outcomeMatch.value = MatchOutcome.MINOR_DIFFERENCES }
            )

            Spacer(modifier = Modifier.height(8.dp))

            MatchOutcomeCard(
                outcome = MatchOutcome.DID_NOT_MATCH,
                isSelected = selectedOutcome == MatchOutcome.DID_NOT_MATCH,
                icon = {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = null,
                        tint = if (selectedOutcome == MatchOutcome.DID_NOT_MATCH) KimeraDeepCoral else KimeraWarmGrey,
                        modifier = Modifier.size(18.dp)
                    )
                },
                onClick = { viewModel.outcomeMatch.value = MatchOutcome.DID_NOT_MATCH }
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Realism & Trustworthiness Card
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(18.dp),
                colors = CardDefaults.cardColors(containerColor = KimeraIvory),
                elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
            ) {
                Column(modifier = Modifier.padding(14.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Realism & Trustworthiness",
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold,
                            color = KimeraCharcoal
                        )
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(9999.dp))
                                .background(KimeraBadgePink)
                                .padding(horizontal = 8.dp, vertical = 2.dp)
                        ) {
                            Text(
                                text = "Target >90%",
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold,
                                color = KimeraDeepCoral
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = "Did the AI render provide achievable physical hair expectations?",
                        fontSize = 11.sp,
                        color = KimeraWarmGrey
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(12.dp))
                            .background(KimeraWhite)
                            .padding(horizontal = 14.dp, vertical = 8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            (1..5).forEach { star ->
                                val isFilled = star <= realismRating
                                Icon(
                                    imageVector = if (isFilled) Icons.Default.Star else Icons.Default.StarBorder,
                                    contentDescription = "$star Stars",
                                    tint = if (isFilled) KimeraDeepCoral else KimeraWarmSand,
                                    modifier = Modifier
                                        .size(24.dp)
                                        .clickable { viewModel.realismRating.value = star }
                                )
                            }
                        }

                        Text(
                            text = "$realismRating of 5 Stars",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Medium,
                            color = KimeraCharcoal
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Adaptation & Stylist Notes
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Adaptation & Stylist Notes",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    color = KimeraCharcoal
                )
                Text(
                    text = "Optional",
                    fontSize = 11.sp,
                    color = KimeraWarmGrey
                )
            }

            Spacer(modifier = Modifier.height(6.dp))

            OutlinedTextField(
                value = feedback,
                onValueChange = { viewModel.outcomeFeedback.value = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("outcome_notes_input"),
                placeholder = {
                    Text(
                        text = "Barber & client feedback or adaptation notes...",
                        fontSize = 12.sp,
                        color = KimeraWarmGrey
                    )
                },
                shape = RoundedCornerShape(14.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = KimeraWhite,
                    unfocusedContainerColor = KimeraWhite,
                    focusedBorderColor = KimeraCoral,
                    unfocusedBorderColor = KimeraWarmSand
                ),
                minLines = 3
            )

            Spacer(modifier = Modifier.height(6.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Default.Lock,
                    contentDescription = null,
                    tint = KimeraWarmGrey,
                    modifier = Modifier.size(11.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "Logged strictly for model quality tuning and salon re-engagement",
                    fontSize = 10.sp,
                    color = KimeraWarmGrey
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Submit Button
            Button(
                onClick = { viewModel.submitOutcomeAndComplete() },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(54.dp)
                    .testTag("submit_outcome_button"),
                colors = ButtonDefaults.buttonColors(
                    containerColor = KimeraCoral,
                    contentColor = KimeraWhite
                ),
                shape = RoundedCornerShape(9999.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Submit Outcome & Complete Session",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
private fun MatchOutcomeCard(
    outcome: MatchOutcome,
    isSelected: Boolean,
    icon: @Composable () -> Unit,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .testTag("match_option_${outcome.name.lowercase()}"),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (isSelected) KimeraIvory else KimeraWhite
        ),
        border = if (isSelected) androidx.compose.foundation.BorderStroke(1.5.dp, KimeraCoral) else androidx.compose.foundation.BorderStroke(1.dp, KimeraWarmSand.copy(alpha = 0.5f)),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.Top
        ) {
            Box(modifier = Modifier.padding(top = 2.dp)) {
                icon()
            }

            Spacer(modifier = Modifier.width(10.dp))

            Column {
                Text(
                    text = outcome.label,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    color = KimeraCharcoal
                )
                Text(
                    text = outcome.description,
                    fontSize = 11.sp,
                    color = KimeraWarmGrey,
                    lineHeight = 15.sp
                )
            }
        }
    }
}
