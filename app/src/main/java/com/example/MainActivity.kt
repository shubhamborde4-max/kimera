package com.example

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.example.model.ScreenStep
import com.example.ui.screens.ConsentScreen
import com.example.ui.screens.GeneratingPreviewScreen
import com.example.ui.screens.LockStyleScreen
import com.example.ui.screens.OutcomeLogScreen
import com.example.ui.screens.PastSessionsDialog
import com.example.ui.screens.ReferenceCaptureScreen
import com.example.ui.screens.RenderResultsScreen
import com.example.ui.screens.SessionCompleteScreen
import com.example.ui.screens.SessionStartScreen
import com.example.ui.screens.VerbalFallbackScreen
import com.example.ui.theme.KimeraTheme
import com.example.viewmodel.ConsultationViewModel
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    private val viewModel: ConsultationViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            KimeraTheme {
                KimeraApp(viewModel = viewModel)
            }
        }
    }
}

@Composable
fun KimeraApp(
    viewModel: ConsultationViewModel,
    modifier: Modifier = Modifier
) {
    val currentStep by viewModel.currentStep.collectAsState()
    val showDashboard by viewModel.showDashboard.collectAsState()
    val shareToastMessage by viewModel.showShareToast.collectAsState()
    val context = LocalContext.current
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    LaunchedEffect(shareToastMessage) {
        shareToastMessage?.let { msg ->
            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
            scope.launch {
                snackbarHostState.showSnackbar(msg)
            }
            viewModel.showShareToast.value = null
        }
    }

    // Android System Back Navigation Handler
    BackHandler(enabled = currentStep != ScreenStep.SESSION_START) {
        viewModel.navigateBack()
    }

    Scaffold(
        modifier = modifier
            .fillMaxSize()
            .safeDrawingPadding(),
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { innerPadding ->
        val contentModifier = Modifier.padding(innerPadding)

        when (currentStep) {
            ScreenStep.SESSION_START -> {
                SessionStartScreen(
                    viewModel = viewModel,
                    modifier = contentModifier
                )
            }
            ScreenStep.CONSENT -> {
                ConsentScreen(
                    viewModel = viewModel,
                    modifier = contentModifier
                )
            }
            ScreenStep.REFERENCE_CAPTURE -> {
                ReferenceCaptureScreen(
                    viewModel = viewModel,
                    modifier = contentModifier
                )
            }
            ScreenStep.GENERATING_PREVIEW -> {
                GeneratingPreviewScreen(
                    viewModel = viewModel,
                    modifier = contentModifier
                )
            }
            ScreenStep.RENDER_RESULTS -> {
                RenderResultsScreen(
                    viewModel = viewModel,
                    modifier = contentModifier
                )
            }
            ScreenStep.SELECTION_DISCUSSION -> {
                LockStyleScreen(
                    viewModel = viewModel,
                    modifier = contentModifier
                )
            }
            ScreenStep.OUTCOME_LOG -> {
                OutcomeLogScreen(
                    viewModel = viewModel,
                    modifier = contentModifier
                )
            }
            ScreenStep.SESSION_COMPLETE -> {
                SessionCompleteScreen(
                    viewModel = viewModel,
                    modifier = contentModifier
                )
            }
            ScreenStep.VERBAL_FALLBACK -> {
                VerbalFallbackScreen(
                    viewModel = viewModel,
                    modifier = contentModifier
                )
            }
        }

        if (showDashboard) {
            PastSessionsDialog(
                viewModel = viewModel,
                onDismiss = { viewModel.showDashboard.value = false }
            )
        }
    }
}
