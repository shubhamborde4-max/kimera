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
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.ContentCut
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Tune
import androidx.compose.material.icons.filled.Tv
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.example.model.HaircutVariation
import com.example.ui.components.StudioTopBar
import com.example.ui.theme.KimeraBadgePink
import com.example.ui.theme.KimeraCharcoal
import com.example.ui.theme.KimeraCoral
import com.example.ui.theme.KimeraDeepCoral
import com.example.ui.theme.KimeraIvory
import com.example.ui.theme.KimeraSerifFamily
import com.example.ui.theme.KimeraSoftPeach
import com.example.ui.theme.KimeraSuccessBg
import com.example.ui.theme.KimeraSuccessGreen
import com.example.ui.theme.KimeraWarmCream
import com.example.ui.theme.KimeraWarmGrey
import com.example.ui.theme.KimeraWarmSand
import com.example.ui.theme.KimeraWhite
import com.example.viewmodel.ConsultationViewModel

@Composable
fun LockStyleScreen(
    viewModel: ConsultationViewModel,
    modifier: Modifier = Modifier
) {
    val selectedIndex by viewModel.selectedVariationIndex.collectAsState()
    val stylistName by viewModel.stylistName.collectAsState()
    val stylistNotes by viewModel.stylistNotes.collectAsState()
    val isVerified by viewModel.isHandoffVerified.collectAsState()

    val currentVariation = viewModel.variations[selectedIndex]
    val scrollState = rememberScrollState()

    var showEditNotesDialog by remember { mutableStateOf(false) }
    var notesDraft by remember { mutableStateOf(stylistNotes) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(KimeraWarmCream)
    ) {
        StudioTopBar(
            onBackClick = { viewModel.navigateBack() },
            onDashboardClick = { viewModel.showDashboard.value = true }
        )

        // Progress row
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 4.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(9999.dp))
                    .background(KimeraIvory)
                    .border(1.dp, KimeraWarmSand, RoundedCornerShape(9999.dp))
                    .padding(horizontal = 10.dp, vertical = 4.dp)
            ) {
                Text(
                    text = "STEP 4 OF 4 • STYLIST ALIGNMENT",
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold,
                    color = KimeraDeepCoral,
                    letterSpacing = 0.5.sp
                )
            }

            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(6.dp)
                        .clip(CircleShape)
                        .background(KimeraDeepCoral)
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = "Chairside Live",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = KimeraWarmGrey
                )
            }
        }

        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(scrollState)
                .padding(horizontal = 20.dp, vertical = 8.dp)
        ) {
            Text(
                text = "Lock Style & Consultation",
                style = MaterialTheme.typography.displayMedium,
                color = KimeraCharcoal
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "Review the verified generative silhouette and haircut specifications with your stylist before chair work begins.",
                style = MaterialTheme.typography.bodyMedium,
                color = KimeraWarmGrey
            )

            Spacer(modifier = Modifier.height(14.dp))

            // Large Pinned Image Card
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(22.dp),
                colors = CardDefaults.cardColors(containerColor = KimeraIvory),
                elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(310.dp)
                ) {
                    Image(
                        painter = painterResource(id = currentVariation.imageRes),
                        contentDescription = "Selected Haircut Silhouette",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )

                    // Top-Left Client Agreed badge
                    Box(
                        modifier = Modifier
                            .align(Alignment.TopStart)
                            .padding(12.dp)
                            .clip(RoundedCornerShape(9999.dp))
                            .background(Color.White.copy(alpha = 0.92f))
                            .padding(horizontal = 10.dp, vertical = 5.dp)
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.CheckCircle,
                                contentDescription = null,
                                tint = KimeraSuccessGreen,
                                modifier = Modifier.size(13.dp)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = "Client Agreed: ${currentVariation.variationNumber}",
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                color = KimeraCharcoal
                            )
                        }
                    }

                    // Top-Right Zoom Button
                    Box(
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .padding(12.dp)
                            .size(34.dp)
                            .clip(CircleShape)
                            .background(Color.White.copy(alpha = 0.9f)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Zoom",
                            tint = KimeraCharcoal,
                            modifier = Modifier.size(16.dp)
                        )
                    }

                    // Bottom Pill on Image
                    Box(
                        modifier = Modifier
                            .align(Alignment.BottomCenter)
                            .padding(12.dp)
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(14.dp))
                            .background(Color.Black.copy(alpha = 0.72f))
                            .padding(horizontal = 12.dp, vertical = 8.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.weight(1f)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Edit,
                                    contentDescription = null,
                                    tint = KimeraCoral,
                                    modifier = Modifier.size(12.dp)
                                )
                                Spacer(modifier = Modifier.width(6.dp))
                                Column {
                                    Text(
                                        text = currentVariation.title,
                                        fontSize = 11.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = KimeraWhite
                                    )
                                    Text(
                                        text = "Matte styling • Defined temple line",
                                        fontSize = 10.sp,
                                        color = Color.LightGray
                                    )
                                }
                            }

                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(6.dp))
                                    .background(KimeraDeepCoral)
                                    .padding(horizontal = 8.dp, vertical = 3.dp)
                            ) {
                                Text(
                                    text = "Final Cut",
                                    fontSize = 10.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = KimeraWhite
                                )
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            // Station Display Mirrored Card
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = KimeraBadgePink.copy(alpha = 0.4f)),
                elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
            ) {
                Row(
                    modifier = Modifier.padding(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(32.dp)
                            .clip(CircleShape)
                            .background(KimeraWhite),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Tv,
                            contentDescription = null,
                            tint = KimeraDeepCoral,
                            modifier = Modifier.size(16.dp)
                        )
                    }

                    Spacer(modifier = Modifier.width(10.dp))

                    Column {
                        Text(
                            text = "Station Display Mirrored",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            color = KimeraCharcoal
                        )
                        Text(
                            text = "This visual reference remains pinned to the barber station mirror during your cut for continuous 360° alignment.",
                            fontSize = 11.sp,
                            color = KimeraWarmGrey,
                            lineHeight = 15.sp
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Barber Arun's Technical Handoff Card
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = KimeraIvory),
                elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Box(
                                modifier = Modifier
                                    .size(32.dp)
                                    .clip(CircleShape)
                                    .background(KimeraDeepCoral),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = stylistName.take(1),
                                    fontSize = 14.sp,
                                    fontFamily = KimeraSerifFamily,
                                    fontWeight = FontWeight.Bold,
                                    color = KimeraWhite
                                )
                            }

                            Spacer(modifier = Modifier.width(10.dp))

                            Column {
                                Text(
                                    text = "Barber ${stylistName.split(" ").firstOrNull() ?: ""}'s Technical Handoff",
                                    fontSize = 13.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = KimeraCharcoal
                                )
                                Text(
                                    text = "Kimera Master Stylist • Chair 03",
                                    fontSize = 10.sp,
                                    color = KimeraWarmGrey
                                )
                            }
                        }

                        Icon(
                            imageVector = Icons.Default.ContentCut,
                            contentDescription = null,
                            tint = KimeraDeepCoral,
                            modifier = Modifier.size(16.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(14.dp))

                    // 2 Side-by-side technical specs
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        // Guard & Sides
                        Card(
                            modifier = Modifier.weight(1f),
                            shape = RoundedCornerShape(12.dp),
                            colors = CardDefaults.cardColors(containerColor = KimeraWhite)
                        ) {
                            Column(modifier = Modifier.padding(10.dp)) {
                                Text(
                                    text = "GUARD & SIDES",
                                    fontSize = 9.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = KimeraWarmGrey
                                )
                                Spacer(modifier = Modifier.height(2.dp))
                                Text(
                                    text = currentVariation.guardAndSides,
                                    fontSize = 13.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = KimeraCharcoal
                                )
                                Text(
                                    text = currentVariation.guardDetail,
                                    fontSize = 10.sp,
                                    color = KimeraWarmGrey
                                )
                            }
                        }

                        // Top & Weight
                        Card(
                            modifier = Modifier.weight(1f),
                            shape = RoundedCornerShape(12.dp),
                            colors = CardDefaults.cardColors(containerColor = KimeraWhite)
                        ) {
                            Column(modifier = Modifier.padding(10.dp)) {
                                Text(
                                    text = "TOP & WEIGHT",
                                    fontSize = 9.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = KimeraWarmGrey
                                )
                                Spacer(modifier = Modifier.height(2.dp))
                                Text(
                                    text = currentVariation.topAndWeight,
                                    fontSize = 13.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = KimeraCharcoal
                                )
                                Text(
                                    text = currentVariation.topDetail,
                                    fontSize = 10.sp,
                                    color = KimeraWarmGrey
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(14.dp))

                    // Stylist Direct Notes
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Stylist Direct Notes",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            color = KimeraCharcoal
                        )
                        Text(
                            text = "Edit note",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = KimeraDeepCoral,
                            modifier = Modifier
                                .clickable {
                                    notesDraft = stylistNotes
                                    showEditNotesDialog = true
                                }
                                .padding(4.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(6.dp))

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(10.dp))
                            .background(KimeraWhite)
                            .padding(10.dp)
                    ) {
                        Text(
                            text = stylistNotes,
                            fontSize = 12.sp,
                            color = KimeraCharcoal,
                            lineHeight = 17.sp
                        )
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    // Verified row
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Checkbox(
                                checked = isVerified,
                                onCheckedChange = { viewModel.isHandoffVerified.value = it },
                                colors = CheckboxDefaults.colors(
                                    checkedColor = KimeraDeepCoral,
                                    checkmarkColor = KimeraWhite
                                )
                            )
                            Text(
                                text = "Texture & Crown Direction Verified",
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Medium,
                                color = KimeraCharcoal
                            )
                        }

                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(9999.dp))
                                .background(KimeraSuccessBg)
                                .padding(horizontal = 8.dp, vertical = 2.dp)
                        ) {
                            Text(
                                text = "Ready",
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold,
                                color = KimeraSuccessGreen
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Primary Proceed to Chair Cut Button
            Button(
                onClick = { viewModel.proceedToChairCut() },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(54.dp)
                    .testTag("proceed_chair_cut_button"),
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
                        text = "Proceed to Chair Cut",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                        contentDescription = null,
                        modifier = Modifier.size(16.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Secondary Back to Variations Button
            OutlinedButton(
                onClick = { viewModel.backToVariations() },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
                    .testTag("back_to_variations_button"),
                shape = RoundedCornerShape(9999.dp),
                colors = ButtonDefaults.outlinedButtonColors(
                    containerColor = KimeraIvory,
                    contentColor = KimeraCharcoal
                ),
                border = androidx.compose.foundation.BorderStroke(1.dp, KimeraWarmSand)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.Tune,
                        contentDescription = null,
                        tint = KimeraCharcoal,
                        modifier = Modifier.size(14.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "Back to Variations",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
        }
    }

    if (showEditNotesDialog) {
        AlertDialog(
            onDismissRequest = { showEditNotesDialog = false },
            title = { Text("Edit Stylist Notes") },
            text = {
                OutlinedTextField(
                    value = notesDraft,
                    onValueChange = { notesDraft = it },
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text("Stylist Direct Notes") },
                    minLines = 3
                )
            },
            confirmButton = {
                TextButton(onClick = {
                    viewModel.stylistNotes.value = notesDraft
                    showEditNotesDialog = false
                }) {
                    Text("Save", color = KimeraDeepCoral, fontWeight = FontWeight.Bold)
                }
            },
            dismissButton = {
                TextButton(onClick = { showEditNotesDialog = false }) {
                    Text("Cancel", color = KimeraWarmGrey)
                }
            }
        )
    }
}
