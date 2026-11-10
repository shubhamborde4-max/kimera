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
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.filled.ContentCut
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.LockClock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PhotoCamera
import androidx.compose.material.icons.filled.Spa
import androidx.compose.material.icons.filled.UnfoldMore
import androidx.compose.material.icons.filled.ViewCarousel
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
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.ui.theme.KimeraBadgePink
import com.example.ui.theme.KimeraCharcoal
import com.example.ui.theme.KimeraCoral
import com.example.ui.theme.KimeraDeepCoral
import com.example.ui.theme.KimeraIvory
import com.example.ui.theme.KimeraSerifFamily
import com.example.ui.theme.KimeraSoftPeach
import com.example.ui.theme.KimeraWarmCream
import com.example.ui.theme.KimeraWarmGrey
import com.example.ui.theme.KimeraWarmSand
import com.example.ui.theme.KimeraWhite
import com.example.viewmodel.ConsultationViewModel

@Composable
fun SessionStartScreen(
    viewModel: ConsultationViewModel,
    modifier: Modifier = Modifier
) {
    val clientName by viewModel.clientName.collectAsState()
    val clientPhone by viewModel.clientPhone.collectAsState()
    val visitInfo by viewModel.visitInfo.collectAsState()
    val stylistName by viewModel.stylistName.collectAsState()
    val stylistTitle by viewModel.stylistTitle.collectAsState()
    val stationLabel by viewModel.stationLabel.collectAsState()
    val focusTarget by viewModel.focusTarget.collectAsState()
    val sessionId by viewModel.sessionId.collectAsState()

    val scrollState = rememberScrollState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(KimeraWarmCream)
            .verticalScroll(scrollState)
            .padding(bottom = 36.dp)
    ) {
        // Top Header
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 14.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "kimera",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = KimeraCharcoal,
                    letterSpacing = 0.5.sp
                )
                Box(
                    modifier = Modifier
                        .padding(horizontal = 6.dp)
                        .size(5.dp)
                        .clip(CircleShape)
                        .background(KimeraCoral)
                )
                Text(
                    text = "CONSULTATION",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = KimeraWarmGrey,
                    letterSpacing = 1.sp
                )
            }

            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(9999.dp))
                    .background(KimeraIvory)
                    .border(1.dp, KimeraWarmSand, RoundedCornerShape(9999.dp))
                    .padding(horizontal = 10.dp, vertical = 5.dp)
                    .clickable { viewModel.showDashboard.value = true }
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
                        text = stationLabel,
                        style = MaterialTheme.typography.labelSmall,
                        color = KimeraCharcoal,
                        fontWeight = FontWeight.Medium
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Pilot tags row
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(9999.dp))
                    .background(KimeraBadgePink)
                    .padding(horizontal = 12.dp, vertical = 6.dp)
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
                        text = "PILOT SESSION #14",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = KimeraDeepCoral,
                        letterSpacing = 0.5.sp
                    )
                }
            }

            Text(
                text = "Split Pricing Enabled",
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium,
                color = KimeraWarmGrey
            )
        }

        Spacer(modifier = Modifier.height(18.dp))

        // Hero Titles
        Column(modifier = Modifier.padding(horizontal = 20.dp)) {
            Text(
                text = "Begin Chairside",
                style = MaterialTheme.typography.displayLarge,
                color = KimeraCharcoal
            )
            Text(
                text = "AI Consultation",
                style = MaterialTheme.typography.displayLarge.copy(
                    fontStyle = FontStyle.Italic,
                    fontFamily = KimeraSerifFamily
                ),
                color = KimeraCoral
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Bridge client reference styles with tailored face adaptation before the first cut.",
                style = MaterialTheme.typography.bodyMedium,
                color = KimeraWarmGrey,
                lineHeight = 20.sp
            )
        }

        Spacer(modifier = Modifier.height(18.dp))

        // Spec badges row
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            BadgeChip(
                icon = { Icon(Icons.Default.LockClock, null, Modifier.size(12.dp), tint = KimeraDeepCoral) },
                text = "28-min slot"
            )
            BadgeChip(
                icon = { Icon(Icons.Default.AutoAwesome, null, Modifier.size(12.dp), tint = KimeraDeepCoral) },
                text = "2-min AI render"
            )
            BadgeChip(
                icon = { Icon(Icons.Default.ViewCarousel, null, Modifier.size(12.dp), tint = KimeraDeepCoral) },
                text = "3–4 variations"
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Client Details Card
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
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
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = null,
                            tint = KimeraDeepCoral,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = "Client Details",
                            style = MaterialTheme.typography.titleSmall,
                            fontWeight = FontWeight.SemiBold,
                            color = KimeraCharcoal
                        )
                    }

                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(9999.dp))
                            .background(KimeraBadgePink)
                            .padding(horizontal = 8.dp, vertical = 3.dp)
                    ) {
                        Text(
                            text = sessionId,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = KimeraDeepCoral
                        )
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                OutlinedTextField(
                    value = clientName,
                    onValueChange = { viewModel.clientName.value = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("client_name_input"),
                    shape = RoundedCornerShape(12.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor = KimeraWhite,
                        unfocusedContainerColor = KimeraWhite,
                        focusedBorderColor = KimeraCoral,
                        unfocusedBorderColor = KimeraWarmSand
                    ),
                    singleLine = true
                )

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "📞 $clientPhone",
                        fontSize = 12.sp,
                        color = KimeraWarmGrey
                    )
                    Text(
                        text = visitInfo,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium,
                        color = KimeraCharcoal
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Assigned Stylist Card
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
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
                        Icon(
                            imageVector = Icons.Default.ContentCut,
                            contentDescription = null,
                            tint = KimeraDeepCoral,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = "Assigned Stylist",
                            style = MaterialTheme.typography.titleSmall,
                            fontWeight = FontWeight.SemiBold,
                            color = KimeraCharcoal
                        )
                    }

                    Text(
                        text = "Chairside Ready",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = KimeraWarmGrey
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(14.dp))
                        .background(KimeraWhite)
                        .padding(12.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Box(
                                modifier = Modifier
                                    .size(42.dp)
                                    .clip(CircleShape)
                                    .background(KimeraSoftPeach),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = stylistName.take(1),
                                    fontSize = 18.sp,
                                    fontFamily = KimeraSerifFamily,
                                    fontWeight = FontWeight.Bold,
                                    color = KimeraDeepCoral
                                )
                            }

                            Spacer(modifier = Modifier.width(12.dp))

                            Column {
                                Text(
                                    text = stylistName,
                                    fontSize = 15.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = KimeraCharcoal
                                )
                                Text(
                                    text = stylistTitle,
                                    fontSize = 11.sp,
                                    color = KimeraWarmGrey
                                )
                            }
                        }

                        Icon(
                            imageVector = Icons.Default.UnfoldMore,
                            contentDescription = "Change Stylist",
                            tint = KimeraWarmGrey
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Focus Target
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = KimeraIvory),
            elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "Focus Target",
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.SemiBold,
                    color = KimeraCharcoal
                )

                Spacer(modifier = Modifier.height(10.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    val isHaircutSelected = focusTarget == "Haircut & Silhouette"

                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .clip(RoundedCornerShape(14.dp))
                            .background(KimeraWhite)
                            .border(
                                width = if (isHaircutSelected) 2.dp else 1.dp,
                                color = if (isHaircutSelected) KimeraCoral else KimeraWarmSand,
                                shape = RoundedCornerShape(14.dp)
                            )
                            .clickable { viewModel.focusTarget.value = "Haircut & Silhouette" }
                            .padding(vertical = 12.dp, horizontal = 8.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Box(
                                modifier = Modifier
                                    .size(24.dp)
                                    .clip(CircleShape)
                                    .background(if (isHaircutSelected) KimeraBadgePink else KimeraIvory),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Face,
                                    contentDescription = null,
                                    tint = KimeraDeepCoral,
                                    modifier = Modifier.size(15.dp)
                                )
                            }
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "Haircut &\nSilhouette",
                                fontSize = 12.sp,
                                fontWeight = if (isHaircutSelected) FontWeight.Bold else FontWeight.Medium,
                                color = KimeraCharcoal,
                                lineHeight = 16.sp
                            )
                        }
                    }

                    val isBeardSelected = focusTarget == "Hair + Beard Grooming"

                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .clip(RoundedCornerShape(14.dp))
                            .background(KimeraWhite)
                            .border(
                                width = if (isBeardSelected) 2.dp else 1.dp,
                                color = if (isBeardSelected) KimeraCoral else KimeraWarmSand,
                                shape = RoundedCornerShape(14.dp)
                            )
                            .clickable { viewModel.focusTarget.value = "Hair + Beard Grooming" }
                            .padding(vertical = 12.dp, horizontal = 8.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Box(
                                modifier = Modifier
                                    .size(24.dp)
                                    .clip(CircleShape)
                                    .background(if (isBeardSelected) KimeraBadgePink else KimeraIvory),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Spa,
                                    contentDescription = null,
                                    tint = KimeraDeepCoral,
                                    modifier = Modifier.size(15.dp)
                                )
                            }
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "Hair + Beard\nGrooming",
                                fontSize = 12.sp,
                                fontWeight = if (isBeardSelected) FontWeight.Bold else FontWeight.Medium,
                                color = KimeraCharcoal,
                                lineHeight = 16.sp
                            )
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Previous Inspiration Card
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = KimeraIvory),
            elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(14.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.img_client_chair),
                    contentDescription = "Previous haircut",
                    modifier = Modifier
                        .size(56.dp)
                        .clip(RoundedCornerShape(10.dp)),
                    contentScale = ContentScale.Crop
                )

                Spacer(modifier = Modifier.width(12.dp))

                Column {
                    Text(
                        text = "PREVIOUS INSPIRATION",
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        color = KimeraDeepCoral,
                        letterSpacing = 0.5.sp
                    )
                    Text(
                        text = "Modern Textured Crop with Low Taper",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold,
                        color = KimeraCharcoal
                    )
                    Text(
                        text = "Saved 12 days ago in client locker",
                        fontSize = 11.sp,
                        color = KimeraWarmGrey
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(26.dp))

        // Primary Start Session Button
        Button(
            onClick = { viewModel.startSession() },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .padding(horizontal = 20.dp)
                .testTag("start_session_button"),
            colors = ButtonDefaults.buttonColors(
                containerColor = KimeraCoral,
                contentColor = KimeraWhite
            ),
            shape = RoundedCornerShape(9999.dp),
            elevation = ButtonDefaults.buttonElevation(defaultElevation = 0.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Start Session",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 0.2.sp
                )
                Spacer(modifier = Modifier.width(8.dp))
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                    contentDescription = null,
                    modifier = Modifier.size(18.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.PhotoCamera,
                contentDescription = null,
                tint = KimeraWarmGrey,
                modifier = Modifier.size(14.dp)
            )
            Spacer(modifier = Modifier.width(6.dp))
            Text(
                text = "Face calibration camera activates on start",
                fontSize = 12.sp,
                color = KimeraWarmGrey
            )
        }
    }
}

@Composable
private fun BadgeChip(
    icon: @Composable () -> Unit,
    text: String
) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(9999.dp))
            .background(KimeraIvory)
            .border(1.dp, KimeraWarmSand, RoundedCornerShape(9999.dp))
            .padding(horizontal = 10.dp, vertical = 6.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            icon()
            Spacer(modifier = Modifier.width(5.dp))
            Text(
                text = text,
                fontSize = 11.sp,
                fontWeight = FontWeight.Medium,
                color = KimeraCharcoal
            )
        }
    }
}
