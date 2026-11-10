package com.example.model

import androidx.annotation.DrawableRes
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.R

enum class ScreenStep {
    SESSION_START,
    CONSENT,
    REFERENCE_CAPTURE,
    GENERATING_PREVIEW,
    RENDER_RESULTS,
    SELECTION_DISCUSSION,
    OUTCOME_LOG,
    SESSION_COMPLETE,
    VERBAL_FALLBACK
}

enum class MatchOutcome(val label: String, val description: String) {
    EXACT_MATCH(
        "Exact Match",
        "High precision silhouette, fade lines, & weight distribution"
    ),
    MINOR_DIFFERENCES(
        "Minor Differences",
        "Client requested chairside adjustment or texture tweaks"
    ),
    DID_NOT_MATCH(
        "Did Not Match",
        "Different style path chosen or anatomical divergence"
    )
}

data class HaircutVariation(
    val id: String,
    val variationNumber: String,
    val title: String,
    val categoryLabel: String,
    val description: String,
    val matchPercentage: Int,
    @DrawableRes val imageRes: Int,
    val tags: List<String>,
    val guardAndSides: String,
    val guardDetail: String,
    val topAndWeight: String,
    val topDetail: String,
    val defaultNotes: String,
    val textureDialPercentage: Int = 72
)

@Entity(tableName = "consultation_sessions")
data class ConsultationSession(
    @PrimaryKey val id: String,
    val clientName: String,
    val clientPhone: String,
    val visitInfo: String,
    val stylistName: String,
    val stylistTitle: String,
    val stationLabel: String,
    val focusTarget: String,
    val consentRenderGranted: Boolean,
    val consentRetentionGranted: Boolean,
    val selectedVariationId: String,
    val selectedStyleTitle: String,
    val textureDial: Int,
    val guardSpecs: String,
    val topSpecs: String,
    val stylistNotes: String,
    val isVerifiedReady: Boolean,
    val outcomeMatch: String? = null,
    val realismRating: Int = 5,
    val outcomeFeedback: String? = null,
    val elapsedSeconds: Int = 400,
    val timestamp: Long = System.currentTimeMillis()
)
