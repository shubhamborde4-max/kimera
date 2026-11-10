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
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.ContentCut
import androidx.compose.material.icons.filled.Security
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.ui.components.StepProgressBar
import com.example.ui.components.StudioTopBar
import com.example.ui.theme.KimeraBadgePink
import com.example.ui.theme.KimeraCharcoal
import com.example.ui.theme.KimeraCoral
import com.example.ui.theme.KimeraDeepCoral
import com.example.ui.theme.KimeraIvory
import com.example.ui.theme.KimeraWarmCream
import com.example.ui.theme.KimeraWarmGrey
import com.example.ui.theme.KimeraWarmSand
import com.example.ui.theme.KimeraWhite
import com.example.viewmodel.ConsultationViewModel

@Composable
fun ConsentScreen(
    viewModel: ConsultationViewModel,
    modifier: Modifier = Modifier
) {
    val consentRender by viewModel.consentRender.collectAsState()
    val consentRetention by viewModel.consentRetention.collectAsState()

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

        StepProgressBar(
            stepText = "Step 1 of 4 • Client Consent & Privacy",
            progressPercentage = 25
        )

        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(scrollState)
                .padding(horizontal = 20.dp, vertical = 12.dp)
        ) {
            Text(
                text = "Photo Use & Visual Privacy",
                style = MaterialTheme.typography.displayMedium,
                color = KimeraCharcoal
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Kimera AI adapts your reference style directly to your face shape and hair profile. Your photo is captured chairside purely to generate your instant previews.",
                style = MaterialTheme.typography.bodyMedium,
                color = KimeraWarmGrey,
                lineHeight = 20.sp
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Hero Salon Image Card with Private Engine Badge
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(170.dp),
                shape = RoundedCornerShape(20.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
            ) {
                Box(modifier = Modifier.fillMaxSize()) {
                    Image(
                        painter = painterResource(id = R.drawable.img_salon_interior),
                        contentDescription = "Salon interior",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )

                    // Overlay bottom pill
                    Box(
                        modifier = Modifier
                            .align(Alignment.BottomStart)
                            .padding(14.dp)
                            .clip(RoundedCornerShape(9999.dp))
                            .background(Color.White.copy(alpha = 0.92f))
                            .padding(horizontal = 12.dp, vertical = 6.dp)
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Box(
                                modifier = Modifier
                                    .size(20.dp)
                                    .clip(CircleShape)
                                    .background(KimeraBadgePink),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Security,
                                    contentDescription = null,
                                    tint = KimeraDeepCoral,
                                    modifier = Modifier.size(12.dp)
                                )
                            }
                            Spacer(modifier = Modifier.width(8.dp))
                            Column {
                                Text(
                                    text = "PRIVATE CHAIRSIDE ENGINE",
                                    fontSize = 9.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = KimeraDeepCoral,
                                    letterSpacing = 0.5.sp
                                )
                                Text(
                                    text = "Bespoke Silhouette Mapping",
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.SemiBold,
                                    color = KimeraCharcoal
                                )
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Switch Card 1: Session Consultation Render (Required)
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
                        verticalAlignment = Alignment.Top
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(9999.dp))
                                    .background(KimeraBadgePink)
                                    .padding(horizontal = 8.dp, vertical = 2.dp)
                            ) {
                                Text(
                                    text = "Required",
                                    fontSize = 10.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = KimeraDeepCoral
                                )
                            }

                            Spacer(modifier = Modifier.height(6.dp))

                            Text(
                                text = "Session Consultation Render",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold,
                                color = KimeraCharcoal
                            )
                        }

                        Switch(
                            checked = consentRender,
                            onCheckedChange = { viewModel.consentRender.value = it },
                            colors = SwitchDefaults.colors(
                                checkedThumbColor = KimeraWhite,
                                checkedTrackColor = KimeraCoral,
                                uncheckedThumbColor = KimeraWhite,
                                uncheckedTrackColor = KimeraWarmSand
                            ),
                            modifier = Modifier.testTag("consent_render_switch")
                        )
                    }

                    Spacer(modifier = Modifier.height(6.dp))

                    Text(
                        text = "Allow camera photo capture for AI hairstyle adaptation during today's visit.",
                        style = MaterialTheme.typography.bodyMedium,
                        color = KimeraWarmGrey
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.ContentCut,
                            contentDescription = null,
                            tint = KimeraDeepCoral,
                            modifier = Modifier.size(14.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = "Enables realistic silhouette overlays & tone analysis",
                            fontSize = 11.sp,
                            color = KimeraCharcoal,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Switch Card 2: Retain Photo for Future Visits (Optional Opt-in)
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
                        verticalAlignment = Alignment.Top
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(9999.dp))
                                    .background(KimeraWarmSand.copy(alpha = 0.5f))
                                    .padding(horizontal = 8.dp, vertical = 2.dp)
                            ) {
                                Text(
                                    text = "Optional Opt-in",
                                    fontSize = 10.sp,
                                    fontWeight = FontWeight.Medium,
                                    color = KimeraCharcoal
                                )
                            }

                            Spacer(modifier = Modifier.height(6.dp))

                            Text(
                                text = "Retain Photo for Future Visits",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold,
                                color = KimeraCharcoal
                            )
                        }

                        Switch(
                            checked = consentRetention,
                            onCheckedChange = { viewModel.consentRetention.value = it },
                            colors = SwitchDefaults.colors(
                                checkedThumbColor = KimeraWhite,
                                checkedTrackColor = KimeraCoral,
                                uncheckedThumbColor = KimeraWhite,
                                uncheckedTrackColor = KimeraWarmSand
                            ),
                            modifier = Modifier.testTag("consent_retention_switch")
                        )
                    }

                    Spacer(modifier = Modifier.height(6.dp))

                    Text(
                        text = "Keep my adapted profile on file for future Kimera styling sessions and formula recalls (Opt-in).",
                        style = MaterialTheme.typography.bodyMedium,
                        color = KimeraWarmGrey
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.AccessTime,
                            contentDescription = null,
                            tint = KimeraDeepCoral,
                            modifier = Modifier.size(14.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = "Re-visit seasonal variations & hair growth timelines",
                            fontSize = 11.sp,
                            color = KimeraCharcoal,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            // Trust & Integrity Card
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = KimeraBadgePink.copy(alpha = 0.4f)),
                elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
            ) {
                Row(
                    modifier = Modifier.padding(14.dp),
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
                            imageVector = Icons.Default.Shield,
                            contentDescription = null,
                            tint = KimeraDeepCoral,
                            modifier = Modifier.size(16.dp)
                        )
                    }

                    Spacer(modifier = Modifier.width(10.dp))

                    Column {
                        Text(
                            text = "Kimera Trust & Integrity Guarantee",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            color = KimeraCharcoal
                        )
                        Text(
                            text = "Encrypted salon storage • Never shared with external third parties • Delete anytime.",
                            fontSize = 11.sp,
                            color = KimeraWarmGrey,
                            lineHeight = 15.sp
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Confirm & Continue Button
            Button(
                onClick = { viewModel.confirmConsent() },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(54.dp)
                    .testTag("confirm_consent_button"),
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
                        text = "Confirm & Continue",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                        contentDescription = null,
                        modifier = Modifier.size(18.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            // Decline Text Link
            Text(
                text = "Decline — Proceed with standard verbal consultation",
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { viewModel.declineConsent() }
                    .padding(vertical = 8.dp)
                    .testTag("decline_consent_button"),
                textAlign = TextAlign.Center,
                fontSize = 13.sp,
                fontWeight = FontWeight.Medium,
                color = KimeraWarmGrey
            )

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}
