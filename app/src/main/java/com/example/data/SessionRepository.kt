package com.example.data

import com.example.R
import com.example.model.ConsultationSession
import com.example.model.HaircutVariation
import kotlinx.coroutines.flow.Flow

class SessionRepository(private val sessionDao: SessionDao) {

    val allSessions: Flow<List<ConsultationSession>> = sessionDao.getAllSessions()
    val totalSessionsCount: Flow<Int> = sessionDao.getTotalSessionsCount()
    val consentGrantedCount: Flow<Int> = sessionDao.getConsentGrantedCount()
    val exactMatchCount: Flow<Int> = sessionDao.getExactMatchCount()

    suspend fun saveSession(session: ConsultationSession) {
        sessionDao.insertSession(session)
    }

    suspend fun updateSession(session: ConsultationSession) {
        sessionDao.updateSession(session)
    }

    suspend fun getSession(id: String): ConsultationSession? {
        return sessionDao.getSessionById(id)
    }

    companion object {
        fun getDefaultVariations(): List<HaircutVariation> {
            return listOf(
                HaircutVariation(
                    id = "var_1",
                    variationNumber = "VARIATION 01",
                    title = "Low Taper Textured Crop",
                    categoryLabel = "RECOMMENDED",
                    description = "Softened side bulk while preserving temple density. Accentuates cheekbones with high tactile crown volume.",
                    matchPercentage = 94,
                    imageRes = R.drawable.img_style_var1,
                    tags = listOf("Clean neckline", "High crown texture", "Matte clay finish"),
                    guardAndSides = "#1.5 Closed -> #2 Open",
                    guardDetail = "Low natural temple fade",
                    topAndWeight = "Shear 2.5 Inches",
                    topDetail = "Point-cut textured crown",
                    defaultNotes = "Client prefers natural dry texture, keeping cowlick intact on left side. Light sea salt spray for pre-blowout.",
                    textureDialPercentage = 72
                ),
                HaircutVariation(
                    id = "var_2",
                    variationNumber = "VARIATION 02",
                    title = "Relaxed Messy Fringe & Low Taper",
                    categoryLabel = "MODERN CASUAL",
                    description = "Forward movement with soft feathered ends. Deconstructed top silhouette for effortless daily styling.",
                    matchPercentage = 89,
                    imageRes = R.drawable.img_style_var2,
                    tags = listOf("Soft temple taper", "Forward directional flow", "Natural matte"),
                    guardAndSides = "#2 Closed -> #3 Open",
                    guardDetail = "Soft tapered nape & sideburns",
                    topAndWeight = "Shear 3.0 Inches",
                    topDetail = "Texturizing shears on fringe",
                    defaultNotes = "Client wants low-maintenance finger styling. Use light matte paste.",
                    textureDialPercentage = 65
                ),
                HaircutVariation(
                    id = "var_3",
                    variationNumber = "VARIATION 03",
                    title = "Classic Scissor Taper & Side Part",
                    categoryLabel = "CONSERVATIVE",
                    description = "Tailored perimeter scissor work preserving ear clearance. Traditional gentleman taper with natural growth blend.",
                    matchPercentage = 84,
                    imageRes = R.drawable.img_style_var3,
                    tags = listOf("All-scissor taper", "Structured contour", "Low sheen finish"),
                    guardAndSides = "Scissor-over-comb",
                    guardDetail = "Graduated taper down to neckline",
                    topAndWeight = "Shear 2.25 Inches",
                    topDetail = "Club cut top with blended crown",
                    defaultNotes = "Client prefers classic corporate executive styling with slight side sweep.",
                    textureDialPercentage = 50
                )
            )
        }
    }
}
