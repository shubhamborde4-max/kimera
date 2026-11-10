package com.example.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.AppDatabase
import com.example.data.SessionRepository
import com.example.model.ConsultationSession
import com.example.model.HaircutVariation
import com.example.model.MatchOutcome
import com.example.model.ScreenStep
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.util.Random

class ConsultationViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: SessionRepository

    init {
        val database = AppDatabase.getDatabase(application)
        repository = SessionRepository(database.sessionDao())
        
        // Seed initial pilot session if empty
        viewModelScope.launch {
            val initial = repository.getSession("KMR-840")
            if (initial == null) {
                repository.saveSession(
                    ConsultationSession(
                        id = "KMR-840",
                        clientName = "Aditya Rao",
                        clientPhone = "+91 98450 11220",
                        visitInfo = "2nd Visit • High Fade",
                        stylistName = "Arun K.",
                        stylistTitle = "Master Stylist • Texture & Fade Specialist",
                        stationLabel = "Station 04 • Jayanagar",
                        focusTarget = "Haircut & Silhouette",
                        consentRenderGranted = true,
                        consentRetentionGranted = true,
                        selectedVariationId = "var_1",
                        selectedStyleTitle = "Low Taper Textured Crop",
                        textureDial = 72,
                        guardSpecs = "#1.5 Closed -> #2 Open",
                        topSpecs = "Shear 2.5 Inches",
                        stylistNotes = "Taper blended into temple hairline with natural matte styling.",
                        isVerifiedReady = true,
                        outcomeMatch = "Exact Match",
                        realismRating = 5,
                        outcomeFeedback = "Fidelity exceeded client expectation; mirror match perfect.",
                        elapsedSeconds = 400
                    )
                )
            }
        }
    }

    val allSessions: StateFlow<List<ConsultationSession>> = repository.allSessions
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    val totalSessionsCount: StateFlow<Int> = repository.totalSessionsCount
        .stateIn(viewModelScope, SharingStarted.Lazily, 0)

    val consentGrantedCount: StateFlow<Int> = repository.consentGrantedCount
        .stateIn(viewModelScope, SharingStarted.Lazily, 0)

    val exactMatchCount: StateFlow<Int> = repository.exactMatchCount
        .stateIn(viewModelScope, SharingStarted.Lazily, 0)

    val variations: List<HaircutVariation> = SessionRepository.getDefaultVariations()

    private val _currentStep = MutableStateFlow(ScreenStep.SESSION_START)
    val currentStep: StateFlow<ScreenStep> = _currentStep.asStateFlow()

    // Session Data
    val sessionId = MutableStateFlow("KMR-842")
    val clientName = MutableStateFlow("Rohan Verma")
    val clientPhone = MutableStateFlow("+91 98450 21980")
    val visitInfo = MutableStateFlow("3rd Visit • Regular Fade")
    val stylistName = MutableStateFlow("Arun K.")
    val stylistTitle = MutableStateFlow("Master Stylist • Texture & Fade Specialist")
    val stationLabel = MutableStateFlow("Station 04 • Jayanagar")
    val focusTarget = MutableStateFlow("Haircut & Silhouette") // or "Hair + Beard Grooming"

    // Consent
    val consentRender = MutableStateFlow(true)
    val consentRetention = MutableStateFlow(false)

    // Capture & Focus Tags
    val selectedTags = MutableStateFlow(setOf("High Fade", "Textured Crown", "Matte Finish"))

    // Synthesis Generation
    val generationTimer = MutableStateFlow(45)
    val isGenerating = MutableStateFlow(false)
    private var generationJob: Job? = null

    // Variations & Selection
    val selectedVariationIndex = MutableStateFlow(0)
    val isSplitView = MutableStateFlow(false)

    // Technical Handoff
    val stylistNotes = MutableStateFlow("Client prefers natural dry texture, keeping cowlick intact on left side. Light sea salt spray for pre-blowout.")
    val isHandoffVerified = MutableStateFlow(true)

    // Outcome Log
    val hasCapturedFinalCut = MutableStateFlow(false)
    val outcomeMatch = MutableStateFlow(MatchOutcome.EXACT_MATCH)
    val realismRating = MutableStateFlow(5)
    val outcomeFeedback = MutableStateFlow("")

    // Dashboard Modal
    val showDashboard = MutableStateFlow(false)
    val showShareToast = MutableStateFlow<String?>(null)

    fun startSession() {
        _currentStep.value = ScreenStep.CONSENT
    }

    fun confirmConsent() {
        if (consentRender.value) {
            _currentStep.value = ScreenStep.REFERENCE_CAPTURE
        } else {
            _currentStep.value = ScreenStep.VERBAL_FALLBACK
        }
    }

    fun declineConsent() {
        consentRender.value = false
        _currentStep.value = ScreenStep.VERBAL_FALLBACK
    }

    fun proceedToGeneration() {
        _currentStep.value = ScreenStep.GENERATING_PREVIEW
        startGenerationTimer()
    }

    private fun startGenerationTimer() {
        isGenerating.value = true
        generationTimer.value = 45
        generationJob?.cancel()
        generationJob = viewModelScope.launch {
            while (generationTimer.value < 105 && isGenerating.value) {
                delay(1000)
                generationTimer.value += 1
                if (generationTimer.value >= 52) {
                    // Ready to auto-complete or let user review
                }
            }
        }
    }

    fun completeGeneration() {
        generationJob?.cancel()
        isGenerating.value = false
        _currentStep.value = ScreenStep.RENDER_RESULTS
    }

    fun cancelGeneration() {
        generationJob?.cancel()
        isGenerating.value = false
        _currentStep.value = ScreenStep.REFERENCE_CAPTURE
    }

    fun selectVariation(index: Int) {
        selectedVariationIndex.value = index
    }

    fun toggleSplitView() {
        isSplitView.value = !isSplitView.value
    }

    fun toggleTag(tag: String) {
        val current = selectedTags.value.toMutableSet()
        if (current.contains(tag)) {
            current.remove(tag)
        } else {
            current.add(tag)
        }
        selectedTags.value = current
    }

    fun proceedToStylistAlignment() {
        _currentStep.value = ScreenStep.SELECTION_DISCUSSION
    }

    fun proceedToChairCut() {
        // Cut starts chairside; proceed to outcome log
        _currentStep.value = ScreenStep.OUTCOME_LOG
    }

    fun backToVariations() {
        _currentStep.value = ScreenStep.RENDER_RESULTS
    }

    fun captureFinalPhoto() {
        hasCapturedFinalCut.value = true
    }

    fun submitOutcomeAndComplete() {
        val selectedVar = variations[selectedVariationIndex.value]
        val session = ConsultationSession(
            id = sessionId.value,
            clientName = clientName.value,
            clientPhone = clientPhone.value,
            visitInfo = visitInfo.value,
            stylistName = stylistName.value,
            stylistTitle = stylistTitle.value,
            stationLabel = stationLabel.value,
            focusTarget = focusTarget.value,
            consentRenderGranted = consentRender.value,
            consentRetentionGranted = consentRetention.value,
            selectedVariationId = selectedVar.id,
            selectedStyleTitle = selectedVar.title,
            textureDial = selectedVar.textureDialPercentage,
            guardSpecs = selectedVar.guardAndSides,
            topSpecs = selectedVar.topAndWeight,
            stylistNotes = stylistNotes.value,
            isVerifiedReady = isHandoffVerified.value,
            outcomeMatch = outcomeMatch.value.label,
            realismRating = realismRating.value,
            outcomeFeedback = outcomeFeedback.value.ifBlank { "Client and stylist aligned on variation; post-cut review completed." },
            elapsedSeconds = 400
        )

        viewModelScope.launch {
            repository.saveSession(session)
        }

        _currentStep.value = ScreenStep.SESSION_COMPLETE
    }

    fun startNewSession() {
        val randomNum = 843 + Random().nextInt(100)
        sessionId.value = "KMR-$randomNum"
        clientName.value = "Vikram Sen"
        clientPhone.value = "+91 98450 78210"
        visitInfo.value = "1st Visit • Fade Consultation"
        consentRender.value = true
        consentRetention.value = false
        selectedVariationIndex.value = 0
        hasCapturedFinalCut.value = false
        outcomeMatch.value = MatchOutcome.EXACT_MATCH
        realismRating.value = 5
        outcomeFeedback.value = ""
        _currentStep.value = ScreenStep.SESSION_START
    }

    fun loadExistingSession(session: ConsultationSession) {
        sessionId.value = session.id
        clientName.value = session.clientName
        clientPhone.value = session.clientPhone
        visitInfo.value = session.visitInfo
        stylistName.value = session.stylistName
        focusTarget.value = session.focusTarget
        consentRender.value = session.consentRenderGranted
        consentRetention.value = session.consentRetentionGranted
        stylistNotes.value = session.stylistNotes
        val idx = variations.indexOfFirst { it.id == session.selectedVariationId }
        selectedVariationIndex.value = if (idx >= 0) idx else 0
        showDashboard.value = false
        _currentStep.value = ScreenStep.SESSION_COMPLETE
    }

    fun navigateBack() {
        when (_currentStep.value) {
            ScreenStep.SESSION_START -> {}
            ScreenStep.CONSENT -> _currentStep.value = ScreenStep.SESSION_START
            ScreenStep.REFERENCE_CAPTURE -> _currentStep.value = ScreenStep.CONSENT
            ScreenStep.GENERATING_PREVIEW -> cancelGeneration()
            ScreenStep.RENDER_RESULTS -> _currentStep.value = ScreenStep.REFERENCE_CAPTURE
            ScreenStep.SELECTION_DISCUSSION -> _currentStep.value = ScreenStep.RENDER_RESULTS
            ScreenStep.OUTCOME_LOG -> _currentStep.value = ScreenStep.SELECTION_DISCUSSION
            ScreenStep.SESSION_COMPLETE -> _currentStep.value = ScreenStep.SESSION_START
            ScreenStep.VERBAL_FALLBACK -> _currentStep.value = ScreenStep.SESSION_START
        }
    }
}
