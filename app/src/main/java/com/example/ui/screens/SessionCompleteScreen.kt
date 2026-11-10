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
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.PhoneAndroid
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.components.KimeraLogoBadge
import com.example.ui.theme.KimeraBadgePink
import com.example.ui.theme.KimeraCharcoal
import com.example.ui.theme.KimeraCoral
import com.example.ui.theme.KimeraDeepCoral
import com.example.ui.theme.KimeraIvory
import com.example.ui.theme.KimeraSoftPeach
import com.example.ui.theme.KimeraSuccessGreen
import com.example.ui.theme.KimeraWarmCream
import com.example.ui.theme.KimeraWarmGrey
import com.example.ui.theme.KimeraWarmSand
import com.example.ui.theme.KimeraWhite
import com.example.viewmodel.ConsultationViewModel

@Composable
fun SessionCompleteScreen(
    viewModel: ConsultationViewModel,
    modifier: Modifier = Modifier
) {
    val sessionId by viewModel.sessionId.collectAsState()
    val clientName by viewModel.clientName.collectAsState()
    val stylistName by viewModel.stylistName.collectAsState()
    val consentRetention by viewModel.consentRetention.collectAsState()
    val selectedIndex by viewModel.selectedVariationIndex.collectAsState()
    val outcomeMatch by viewModel.outcomeMatch.collectAsState()

    val currentVariation = viewModel.variations[selectedIndex]
    val scrollState = rememberScrollState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(KimeraWarmCream)
            .verticalScroll(scrollState)
            .padding(horizontal = 20.dp, vertical = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Logo Header
        KimeraLogoBadge(darkBackground = true)

        Spacer(modifier = Modifier.height(20.dp))

        // Checkmark badge in circle with peach halo
        Box(
            modifier = Modifier
                .size(70.dp)
                .clip(CircleShape)
                .background(KimeraSoftPeach.copy(alpha = 0.5f)),
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
                    .background(KimeraIvory)
                    .border(2.dp, KimeraBadgePink, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = null,
                    tint = KimeraDeepCoral,
                    modifier = Modifier.size(24.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        Text(
            text = "Consultation Complete",
            style = MaterialTheme.typography.displayMedium,
            color = KimeraCharcoal,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = "Session $sessionId successfully logged at Kimera Jayanagar.",
            style = MaterialTheme.typography.bodyMedium,
            color = KimeraWarmGrey,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(20.dp))

        // Session Summary Card
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .testTag("session_summary_card"),
            shape = RoundedCornerShape(22.dp),
            colors = CardDefaults.cardColors(containerColor = KimeraIvory),
            elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                // Style Selected Header
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(14.dp))
                        .background(KimeraWhite)
                        .padding(10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = currentVariation.imageRes),
                        contentDescription = "Selected Haircut",
                        modifier = Modifier
                            .size(54.dp)
                            .clip(RoundedCornerShape(10.dp)),
                        contentScale = ContentScale.Crop
                    )

                    Spacer(modifier = Modifier.width(12.dp))

                    Column {
                        Text(
                            text = "STYLE SELECTED",
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold,
                            color = KimeraDeepCoral,
                            letterSpacing = 0.5.sp
                        )
                        Text(
                            text = currentVariation.title,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            color = KimeraCharcoal
                        )
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.AutoAwesome,
                                contentDescription = null,
                                tint = KimeraDeepCoral,
                                modifier = Modifier.size(11.dp)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = "Bespoke Texture Dial: ${currentVariation.textureDialPercentage}%",
                                fontSize = 11.sp,
                                color = KimeraWarmGrey,
                                fontWeight = FontWeight.Medium
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(14.dp))

                // Client & Stylist 2 Columns
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Card(
                        modifier = Modifier.weight(1f),
                        shape = RoundedCornerShape(12.dp),
                        colors = CardDefaults.cardColors(containerColor = KimeraWhite)
                    ) {
                        Column(modifier = Modifier.padding(10.dp)) {
                            Text(
                                text = "Client",
                                fontSize = 10.sp,
                                color = KimeraWarmGrey
                            )
                            Text(
                                text = clientName,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold,
                                color = KimeraCharcoal
                            )
                            Text(
                                text = if (consentRetention) "✓ Opt-in Verified" else "Session Only",
                                fontSize = 10.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = if (consentRetention) KimeraSuccessGreen else KimeraWarmGrey
                            )
                        }
                    }

                    Card(
                        modifier = Modifier.weight(1f),
                        shape = RoundedCornerShape(12.dp),
                        colors = CardDefaults.cardColors(containerColor = KimeraWhite)
                    ) {
                        Column(modifier = Modifier.padding(10.dp)) {
                            Text(
                                text = "Stylist",
                                fontSize = 10.sp,
                                color = KimeraWarmGrey
                            )
                            Text(
                                text = stylistName,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold,
                                color = KimeraCharcoal
                            )
                            Text(
                                text = "Master Stylist • Bay 3",
                                fontSize = 10.sp,
                                color = KimeraWarmGrey
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(14.dp))

                // Detail Metrics Rows
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(12.dp))
                        .background(KimeraWhite)
                        .padding(12.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    // Outcome Accuracy Row
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.CheckCircle,
                                contentDescription = null,
                                tint = KimeraDeepCoral,
                                modifier = Modifier.size(14.dp)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = "Outcome Accuracy",
                                fontSize = 11.sp,
                                color = KimeraWarmGrey
                            )
                        }

                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(9999.dp))
                                .background(KimeraBadgePink)
                                .padding(horizontal = 8.dp, vertical = 2.dp)
                        ) {
                            Text(
                                text = "${outcomeMatch.label} (Pilot Cohort #1)",
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold,
                                color = KimeraDeepCoral
                            )
                        }
                    }

                    // Photo Retention Row
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.Lock,
                                contentDescription = null,
                                tint = KimeraWarmGrey,
                                modifier = Modifier.size(14.dp)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = "Photo Retention",
                                fontSize = 11.sp,
                                color = KimeraWarmGrey
                            )
                        }

                        Column(horizontalAlignment = Alignment.End) {
                            Text(
                                text = if (consentRetention) "Retained per Client Opt-in" else "Auto-Purged Post-Session",
                                fontSize = 11.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = KimeraCharcoal
                            )
                            Text(
                                text = "Secure salon archive",
                                fontSize = 9.sp,
                                color = KimeraWarmGrey
                            )
                        }
                    }

                    // Elapsed Chair Time Row
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.Timer,
                                contentDescription = null,
                                tint = KimeraWarmGrey,
                                modifier = Modifier.size(14.dp)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = "Elapsed Chair Time",
                                fontSize = 11.sp,
                                color = KimeraWarmGrey
                            )
                        }

                        Text(
                            text = "6 mins 40 secs",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            color = KimeraCharcoal
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(22.dp))

        // WhatsApp / SMS Style Card Button
        OutlinedButton(
            onClick = {
                viewModel.showShareToast.value = "Style Card dispatched to $clientName via WhatsApp (+91 98450 21980)"
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp)
                .testTag("send_whatsapp_button"),
            shape = RoundedCornerShape(9999.dp),
            colors = ButtonDefaults.outlinedButtonColors(
                containerColor = KimeraWhite,
                contentColor = KimeraCharcoal
            ),
            border = androidx.compose.foundation.BorderStroke(1.dp, KimeraWarmSand)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Default.Send,
                    contentDescription = null,
                    tint = KimeraDeepCoral,
                    modifier = Modifier.size(16.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Send Style Card to Client WhatsApp / SMS",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        // Start New Client Session Button
        Button(
            onClick = { viewModel.startNewSession() },
            modifier = Modifier
                .fillMaxWidth()
                .height(54.dp)
                .testTag("start_new_session_button"),
            colors = ButtonDefaults.buttonColors(
                containerColor = KimeraCoral,
                contentColor = KimeraWhite
            ),
            shape = RoundedCornerShape(9999.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = null,
                    modifier = Modifier.size(18.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Start New Client Session",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Footer Copy
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier
                    .size(4.dp)
                    .clip(CircleShape)
                    .background(KimeraCoral)
            )
            Spacer(modifier = Modifier.width(6.dp))
            Text(
                text = "Kimera AI Style Preview • Empowering chairside craft & precision.",
                fontSize = 11.sp,
                fontStyle = androidx.compose.ui.text.font.FontStyle.Italic,
                color = KimeraWarmGrey,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.width(6.dp))
            Box(
                modifier = Modifier
                    .size(4.dp)
                    .clip(CircleShape)
                    .background(KimeraCoral)
            )
        }

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = "Salon Terminal #04 • Jayanagar 4th Block, Bengaluru",
            fontSize = 10.sp,
            color = KimeraWarmGrey,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(16.dp))
    }
}
