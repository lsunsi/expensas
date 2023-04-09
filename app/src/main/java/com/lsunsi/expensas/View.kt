package com.lsunsi.expensas

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import com.lsunsi.expensas.view.*
import com.lsunsi.expensas.state.Tab

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun View(state: StateViewModel) {
    val s by state.s.collectAsState()

    Theme {
        Haptic(s.haptic)
        Scaffold(
            topBar = { Topbar(s.form) },
            floatingActionButton = { Launch(s.tab, s.form, state::launchPressed) },
            bottomBar = { Navbar(s.tab, s.form, state::tabClicked) },
            snackbarHost = { SnackbarHost(s.snackbar.state) },
        ) { padding ->
            s.form?.let { form ->
                Surface(
                    Modifier
                        .fillMaxSize()
                        .padding(padding),
                    color = MaterialTheme.colorScheme.primary
                ) {
                    Submission(
                        form,
                        toggled = state::formToggled,
                        discard = state::formDiscarded,
                        submitted = state::formSubmitted,
                        changed = state::formChanged
                    )
                }
            } ?: run {
                Surface(Modifier.padding(padding), color = MaterialTheme.colorScheme.background) {
                    when (s.tab) {
                        Tab.Summary -> Summary(s)
                        Tab.Statements -> Statements(s)
                        Tab.Settings -> Settings()
                    }
                }
            }
        }
    }
}