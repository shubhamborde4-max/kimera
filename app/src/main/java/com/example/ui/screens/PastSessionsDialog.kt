package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Assessment
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.model.ConsultationSession
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
fun PastSessionsDialog(
    viewModel: ConsultationViewModel,
    onDismiss: () -> Unit
) {
    val sessions by viewModel.allSessions.collectAsState()
    val totalCount by viewModel.totalSessionsCount.collectAsState()
    val consentCount by viewModel.consentGrantedCount.collectAsState()
    val matchCount by viewModel.exactMatchCount.collectAsState()

    val consentRate = if (totalCount > 0) ((consentCount * 100) / totalCount) else 100
    val matchRate = if (totalCount > 0) ((matchCount * 100) / totalCount) else 100

    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Surface(
            modifier = Modifier
                .fillMaxWidth(0.95f)
                .fillMaxHeight(0.85f)
                .clip(RoundedCornerShape(24.dp)),
            color = KimeraWarmCream
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp)
            ) {
                // Dialog Header
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier
                                .size(36.dp)
                                .clip(CircleShape)
                                .background(KimeraBadgePink),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.Assessment,
                                contentDescription = null,
                                tint = KimeraDeepCoral,
                                modifier = Modifier.size(20.dp)
                            )
                        }
                        Spacer(modifier = Modifier.width(10.dp))
                        Column {
                            Text(
                                text = "Salon Session Tracker",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold,
                                color = KimeraCharcoal
                            )
                            Text(
                                text = "Pilot Metrics & Return-to-Session",
                                fontSize = 11.sp,
                                color = KimeraWarmGrey
                            )
                        }
                    }

                    IconButton(onClick = onDismiss) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Close",
                            tint = KimeraCharcoal
                        )
                    }
                }

                Spacer(modifier = Modifier.height(14.dp))

                // Pilot Metrics Cards (80%+ target & 66%+ target)
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Card(
                        modifier = Modifier.weight(1f),
                        shape = RoundedCornerShape(14.dp),
                        colors = CardDefaults.cardColors(containerColor = KimeraIvory)
                    ) {
                        Column(modifier = Modifier.padding(12.dp)) {
                            Text(
                                text = "CONSENT OPT-IN",
                                fontSize = 9.sp,
                                fontWeight = FontWeight.Bold,
                                color = KimeraDeepCoral
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = "$consentRate%",
                                fontSize = 22.sp,
                                fontWeight = FontWeight.Bold,
                                color = KimeraCharcoal
                            )
                            Text(
                                text = "Target: ≥80% (Pilot)",
                                fontSize = 10.sp,
                                color = KimeraSuccessGreen,
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                    }

                    Card(
                        modifier = Modifier.weight(1f),
                        shape = RoundedCornerShape(14.dp),
                        colors = CardDefaults.cardColors(containerColor = KimeraIvory)
                    ) {
                        Column(modifier = Modifier.padding(12.dp)) {
                            Text(
                                text = "MATCH ACCURACY",
                                fontSize = 9.sp,
                                fontWeight = FontWeight.Bold,
                                color = KimeraDeepCoral
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = "$matchRate%",
                                fontSize = 22.sp,
                                fontWeight = FontWeight.Bold,
                                color = KimeraCharcoal
                            )
                            Text(
                                text = "Target: ≥66% (Pilot)",
                                fontSize = 10.sp,
                                color = KimeraSuccessGreen,
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Saved Sessions & Outcomes (${sessions.size})",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    color = KimeraCharcoal
                )

                Spacer(modifier = Modifier.height(8.dp))

                LazyColumn(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    if (sessions.isEmpty()) {
                        item {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(24.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = "No sessions logged yet. Complete a consultation to record.",
                                    fontSize = 12.sp,
                                    color = KimeraWarmGrey
                                )
                            }
                        }
                    }

                    items(sessions) { session ->
                        SessionItemRow(
                            session = session,
                            onClick = {
                                viewModel.loadExistingSession(session)
                                onDismiss()
                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun SessionItemRow(
    session: ConsultationSession,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .testTag("session_item_${session.id}"),
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = KimeraWhite),
        border = androidx.compose.foundation.BorderStroke(1.dp, KimeraWarmSand.copy(alpha = 0.6f))
    ) {
        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.weight(1f)
            ) {
                Box(
                    modifier = Modifier
                        .size(36.dp)
                        .clip(CircleShape)
                        .background(KimeraIvory),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = null,
                        tint = KimeraDeepCoral,
                        modifier = Modifier.size(18.dp)
                    )
                }

                Spacer(modifier = Modifier.width(10.dp))

                Column {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = session.id,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = KimeraDeepCoral
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = session.clientName,
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold,
                            color = KimeraCharcoal
                        )
                    }
                    Text(
                        text = "${session.selectedStyleTitle} • ${session.stylistName}",
                        fontSize = 11.sp,
                        color = KimeraWarmGrey
                    )
                }
            }

            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(9999.dp))
                    .background(KimeraBadgePink)
                    .padding(horizontal = 8.dp, vertical = 4.dp)
            ) {
                Text(
                    text = session.outcomeMatch ?: "Logged",
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold,
                    color = KimeraDeepCoral
                )
            }
        }
    }
}
