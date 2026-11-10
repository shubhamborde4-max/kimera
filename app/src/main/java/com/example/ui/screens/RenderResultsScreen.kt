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
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Compare
import androidx.compose.material.icons.filled.ContentCut
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.filled.OpenInFull
import androidx.compose.material.icons.filled.RemoveRedEye
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.ViewSidebar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
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
import com.example.R
import com.example.model.HaircutVariation
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
fun RenderResultsScreen(
    viewModel: ConsultationViewModel,
    modifier: Modifier = Modifier
) {
    val selectedIndex by viewModel.selectedVariationIndex.collectAsState()
    val isSplitView by viewModel.isSplitView.collectAsState()
    val clientName by viewModel.clientName.collectAsState()
    val variations = viewModel.variations
    val currentVariation = variations[selectedIndex]

    var showGuidelinesDialog by remember { mutableStateOf(false) }
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

        // Progress row with Split View toggle
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
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(6.dp)
                            .clip(CircleShape)
                            .background(KimeraCoral)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "STEP 3 OF 4 • STYLE VARIATIONS",
                        style = MaterialTheme.typography.labelSmall,
                        fontWeight = FontWeight.Bold,
                        color = KimeraWarmGrey
                    )
                }
            }

            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(9999.dp))
                    .background(if (isSplitView) KimeraCoral else KimeraIvory)
                    .border(1.dp, if (isSplitView) KimeraCoral else KimeraWarmSand, RoundedCornerShape(9999.dp))
                    .clickable { viewModel.toggleSplitView() }
                    .padding(horizontal = 10.dp, vertical = 4.dp)
                    .testTag("split_view_toggle")
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.ViewSidebar,
                        contentDescription = null,
                        tint = if (isSplitView) KimeraWhite else KimeraCharcoal,
                        modifier = Modifier.size(12.dp)
                    )
                    Spacer(modifier = Modifier.width(5.dp))
                    Text(
                        text = "Split View",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = if (isSplitView) KimeraWhite else KimeraCharcoal
                    )
                }
            }
        }

        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(scrollState)
                .padding(horizontal = 20.dp, vertical = 8.dp)
        ) {
            Text(
                text = "Adapted Variations",
                style = MaterialTheme.typography.displayMedium,
                color = KimeraCharcoal
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "3 tailored adaptations for ${clientName.split(" ").firstOrNull() ?: "client"}'s face geometry and natural hair flow.",
                style = MaterialTheme.typography.bodyMedium,
                color = KimeraWarmGrey
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Fit Analysis & Ref Photo row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(9999.dp))
                        .background(KimeraIvory)
                        .border(1.dp, KimeraWarmSand, RoundedCornerShape(9999.dp))
                        .padding(horizontal = 10.dp, vertical = 5.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.AutoAwesome,
                            contentDescription = null,
                            tint = KimeraDeepCoral,
                            modifier = Modifier.size(13.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = "FIT ANALYSIS",
                            fontSize = 9.sp,
                            fontWeight = FontWeight.Bold,
                            color = KimeraDeepCoral
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = "Realism ${currentVariation.matchPercentage}% • Est. 35 mins",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Medium,
                            color = KimeraCharcoal
                        )
                    }
                }

                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(9999.dp))
                        .background(KimeraIvory)
                        .border(1.dp, KimeraWarmSand, RoundedCornerShape(9999.dp))
                        .clickable { viewModel.toggleSplitView() }
                        .padding(horizontal = 10.dp, vertical = 5.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.Compare,
                            contentDescription = null,
                            tint = KimeraWarmGrey,
                            modifier = Modifier.size(13.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "Ref Photo",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Medium,
                            color = KimeraCharcoal
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            // Side-by-Side Split View if active
            if (isSplitView) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 12.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = "ORIGINAL REFERENCE",
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold,
                            color = KimeraDeepCoral,
                            modifier = Modifier.padding(bottom = 4.dp)
                        )
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(180.dp)
                                .clip(RoundedCornerShape(14.dp))
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.img_style_ref),
                                contentDescription = "Original Reference",
                                modifier = Modifier.fillMaxSize(),
                                contentScale = ContentScale.Crop
                            )
                        }
                    }

                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = "CLIENT ADAPTATION",
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold,
                            color = KimeraDeepCoral,
                            modifier = Modifier.padding(bottom = 4.dp)
                        )
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(180.dp)
                                .clip(RoundedCornerShape(14.dp))
                        ) {
                            Image(
                                painter = painterResource(id = currentVariation.imageRes),
                                contentDescription = "Client Adaptation",
                                modifier = Modifier.fillMaxSize(),
                                contentScale = ContentScale.Crop
                            )
                        }
                    }
                }
            }

            // Main Hero Variation Card
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("hero_variation_card"),
                shape = RoundedCornerShape(22.dp),
                colors = CardDefaults.cardColors(containerColor = KimeraIvory),
                elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
            ) {
                Column(modifier = Modifier.padding(14.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(9999.dp))
                                .background(KimeraBadgePink)
                                .padding(horizontal = 10.dp, vertical = 4.dp)
                        ) {
                            Text(
                                text = "${currentVariation.variationNumber} • ${currentVariation.categoryLabel}",
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold,
                                color = KimeraDeepCoral,
                                letterSpacing = 0.5.sp
                            )
                        }

                        Text(
                            text = "Chairside 3D",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = KimeraWarmGrey
                        )
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(300.dp)
                            .clip(RoundedCornerShape(16.dp))
                            .clickable { showGuidelinesDialog = true }
                    ) {
                        Image(
                            painter = painterResource(id = currentVariation.imageRes),
                            contentDescription = currentVariation.title,
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Crop
                        )

                        // Top-left optimized silhouette badge
                        Box(
                            modifier = Modifier
                                .align(Alignment.TopStart)
                                .padding(12.dp)
                                .clip(RoundedCornerShape(9999.dp))
                                .background(Color.Black.copy(alpha = 0.65f))
                                .padding(horizontal = 10.dp, vertical = 4.dp)
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Box(
                                    modifier = Modifier
                                        .size(6.dp)
                                        .clip(CircleShape)
                                        .background(KimeraCoral)
                                )
                                Spacer(modifier = Modifier.width(6.dp))
                                Text(
                                    text = "Optimized Silhouette",
                                    fontSize = 11.sp,
                                    color = KimeraWhite,
                                    fontWeight = FontWeight.Medium
                                )
                            }
                        }

                        // Bottom-right expand icon
                        Box(
                            modifier = Modifier
                                .align(Alignment.BottomEnd)
                                .padding(12.dp)
                                .size(34.dp)
                                .clip(CircleShape)
                                .background(KimeraWhite.copy(alpha = 0.85f)),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.OpenInFull,
                                contentDescription = "Expand",
                                tint = KimeraCharcoal,
                                modifier = Modifier.size(16.dp)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(14.dp))

                    Text(
                        text = currentVariation.title,
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        color = KimeraCharcoal
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = currentVariation.description,
                        style = MaterialTheme.typography.bodyMedium,
                        color = KimeraWarmGrey,
                        lineHeight = 18.sp
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    // Tags row
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        currentVariation.tags.forEach { tag ->
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(9999.dp))
                                    .background(KimeraWhite)
                                    .border(1.dp, KimeraWarmSand, RoundedCornerShape(9999.dp))
                                    .padding(horizontal = 8.dp, vertical = 3.dp)
                            ) {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Icon(
                                        imageVector = Icons.Default.Check,
                                        contentDescription = null,
                                        tint = KimeraDeepCoral,
                                        modifier = Modifier.size(10.dp)
                                    )
                                    Spacer(modifier = Modifier.width(4.dp))
                                    Text(
                                        text = tag,
                                        fontSize = 10.sp,
                                        color = KimeraCharcoal,
                                        fontWeight = FontWeight.Medium
                                    )
                                }
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(14.dp))

                    // Selected Silhouette Action Button
                    Button(
                        onClick = { viewModel.proceedToStylistAlignment() },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp)
                            .testTag("select_silhouette_button"),
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
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "Selected Silhouette",
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Alternative Silhouettes Section
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Alternative Silhouettes",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = KimeraCharcoal
                )
                Text(
                    text = "2 more options",
                    fontSize = 12.sp,
                    color = KimeraWarmGrey
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Alternative 1 (Variation 02)
            val var2 = variations[1]
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { viewModel.selectVariation(1) }
                    .testTag("alt_variation_1"),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = if (selectedIndex == 1) KimeraIvory else KimeraWhite
                ),
                border = if (selectedIndex == 1) androidx.compose.foundation.BorderStroke(2.dp, KimeraCoral) else null
            ) {
                Row(
                    modifier = Modifier.padding(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = var2.imageRes),
                        contentDescription = var2.title,
                        modifier = Modifier
                            .size(64.dp)
                            .clip(RoundedCornerShape(10.dp)),
                        contentScale = ContentScale.Crop
                    )

                    Spacer(modifier = Modifier.width(12.dp))

                    Column(modifier = Modifier.weight(1f)) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                text = "VARIATION 02",
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold,
                                color = KimeraWarmGrey
                            )
                            Text(
                                text = "Match ${var2.matchPercentage}%",
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                color = KimeraDeepCoral
                            )
                        }
                        Text(
                            text = var2.title,
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold,
                            color = KimeraCharcoal
                        )
                        Text(
                            text = var2.description.take(40) + "...",
                            fontSize = 11.sp,
                            color = KimeraWarmGrey
                        )

                        Spacer(modifier = Modifier.height(4.dp))

                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(9999.dp))
                                    .background(KimeraIvory)
                                    .padding(horizontal = 8.dp, vertical = 2.dp)
                            ) {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Icon(
                                        imageVector = Icons.Default.RemoveRedEye,
                                        contentDescription = null,
                                        tint = KimeraDeepCoral,
                                        modifier = Modifier.size(11.dp)
                                    )
                                    Spacer(modifier = Modifier.width(4.dp))
                                    Text(
                                        text = "Preview on Face",
                                        fontSize = 10.sp,
                                        fontWeight = FontWeight.Medium,
                                        color = KimeraCharcoal
                                    )
                                }
                            }
                        }
                    }

                    IconButton(onClick = { /* favorite */ }) {
                        Icon(
                            imageVector = Icons.Default.FavoriteBorder,
                            contentDescription = "Save",
                            tint = KimeraWarmGrey,
                            modifier = Modifier.size(18.dp)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Alternative 2 (Variation 03)
            val var3 = variations[2]
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { viewModel.selectVariation(2) }
                    .testTag("alt_variation_2"),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = if (selectedIndex == 2) KimeraIvory else KimeraWhite
                ),
                border = if (selectedIndex == 2) androidx.compose.foundation.BorderStroke(2.dp, KimeraCoral) else null
            ) {
                Row(
                    modifier = Modifier.padding(14.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.weight(1f)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(40.dp)
                                .clip(CircleShape)
                                .background(KimeraIvory),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.ContentCut,
                                contentDescription = null,
                                tint = KimeraDeepCoral,
                                modifier = Modifier.size(18.dp)
                            )
                        }

                        Spacer(modifier = Modifier.width(12.dp))

                        Column {
                            Text(
                                text = "VARIATION 03 • Conservative",
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold,
                                color = KimeraWarmGrey
                            )
                            Text(
                                text = var3.title,
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Bold,
                                color = KimeraCharcoal
                            )
                        }
                    }

                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(9999.dp))
                            .background(KimeraIvory)
                            .padding(horizontal = 10.dp, vertical = 5.dp)
                    ) {
                        Text(
                            text = "Compare >",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = KimeraCharcoal
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            // Barber Quick Tip
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(14.dp),
                colors = CardDefaults.cardColors(containerColor = KimeraBadgePink.copy(alpha = 0.4f))
            ) {
                Row(
                    modifier = Modifier.padding(12.dp),
                    verticalAlignment = Alignment.Top
                ) {
                    Icon(
                        imageVector = Icons.Default.Lightbulb,
                        contentDescription = null,
                        tint = KimeraDeepCoral,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Column {
                        Text(
                            text = "Barber Quick Tip",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = KimeraDeepCoral
                        )
                        Text(
                            text = "Tap anywhere on the preview render to show haircut step guidelines, clipper guard sizes, and directional shears.",
                            fontSize = 11.sp,
                            color = KimeraWarmGrey,
                            lineHeight = 15.sp
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Bottom Continue Button Row
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(48.dp)
                        .clip(CircleShape)
                        .background(KimeraIvory)
                        .border(1.dp, KimeraWarmSand, CircleShape)
                        .clickable { viewModel.showShareToast.value = "Sharing style variation..." },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Share,
                        contentDescription = "Share",
                        tint = KimeraCharcoal,
                        modifier = Modifier.size(18.dp)
                    )
                }

                Spacer(modifier = Modifier.width(10.dp))

                Button(
                    onClick = { viewModel.proceedToStylistAlignment() },
                    modifier = Modifier
                        .weight(1f)
                        .height(54.dp)
                        .testTag("continue_alignment_button"),
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
                            text = "Continue to Stylist Alignment",
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                            contentDescription = null,
                            modifier = Modifier.size(16.dp)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}
