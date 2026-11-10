package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChatBubbleOutline
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.ContentCut
import androidx.compose.material.icons.filled.Info
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
import androidx.compose.ui.platform.testTag
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
import com.example.ui.theme.KimeraWarmCream
import com.example.ui.theme.KimeraWarmGrey
import com.example.ui.theme.KimeraWarmSand
import com.example.ui.theme.KimeraWhite
import com.example.viewmodel.ConsultationViewModel

@Composable
fun VerbalFallbackScreen(
    viewModel: ConsultationViewModel,
    modifier: Modifier = Modifier
) {
    val clientName by viewModel.clientName.collectAsState()
    val stylistName by viewModel.stylistName.collectAsState()
    val sessionId by viewModel.sessionId.collectAsState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(KimeraWarmCream)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        KimeraLogoBadge(darkBackground = true)

        Spacer(modifier = Modifier.height(24.dp))

        Box(
            modifier = Modifier
                .size(72.dp)
                .clip(CircleShape)
                .background(KimeraSoftPeach.copy(alpha = 0.5f)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.ChatBubbleOutline,
                contentDescription = null,
                tint = KimeraDeepCoral,
                modifier = Modifier.size(36.dp)
            )
        }

        Spacer(modifier = Modifier.height(18.dp))

        Text(
            text = "Standard Verbal Consultation",
            style = MaterialTheme.typography.displayMedium,
            color = KimeraCharcoal,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Client opted out of camera synthesis. Stylist $stylistName will proceed with standard physical lookbook & mirror consultation for $clientName.",
            style = MaterialTheme.typography.bodyMedium,
            color = KimeraWarmGrey,
            textAlign = TextAlign.Center,
            lineHeight = 22.sp
        )

        Spacer(modifier = Modifier.height(24.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(18.dp),
            colors = CardDefaults.cardColors(containerColor = KimeraIvory),
            elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.Info,
                        contentDescription = null,
                        tint = KimeraDeepCoral,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Pilot Metric Separation",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = KimeraCharcoal
                    )
                }

                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = "Per Section 14 guidelines, this opt-out event ($sessionId) is logged separately from the 80%+ consent target, ensuring tool reliability tracking remains unpolluted.",
                    fontSize = 11.sp,
                    color = KimeraWarmGrey,
                    lineHeight = 16.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(30.dp))

        Button(
            onClick = { viewModel.startNewSession() },
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp)
                .testTag("verbal_new_session_button"),
            colors = ButtonDefaults.buttonColors(
                containerColor = KimeraCoral,
                contentColor = KimeraWhite
            ),
            shape = RoundedCornerShape(9999.dp)
        ) {
            Text(
                text = "Close Session & Start New",
                fontWeight = FontWeight.Bold,
                fontSize = 15.sp
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedButton(
            onClick = { viewModel.confirmConsent() },
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            shape = RoundedCornerShape(9999.dp),
            border = androidx.compose.foundation.BorderStroke(1.dp, KimeraWarmSand)
        ) {
            Text(
                text = "Client Changed Mind (Re-Enable AI)",
                fontSize = 13.sp,
                fontWeight = FontWeight.SemiBold,
                color = KimeraCharcoal
            )
        }
    }
}
