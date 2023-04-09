package com.lsunsi.expensas

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.lsunsi.expensas.view.*
import com.lsunsi.expensas.state.Tab

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun View(state: StateViewModel) {
    val s by state.s.collectAsState()

    Theme {
        Haptic(s.haptic)
        Scaffold(
            topBar = { TopBar(s.form) },
            floatingActionButton = { LaunchButton(s.tab, s.form, state::launchPressed) },
            bottomBar = {
                BottomBar(
                    s,
                    nav = { NavBottomBar(s.tab, s.form, state::tabClicked) },
                    form = {
                        FormBottomBar(
                            s.form,
                            state::formToggled,
                            state::formDiscarded,
                            state::formSubmitted
                        )
                    }
                )
            },
            snackbarHost = { SnackbarHost(s.snackbar.state) },
        ) { padding ->
            s.form?.let { form ->
                Surface(
                    Modifier
                        .fillMaxSize()
                        .padding(padding),
                    color = MaterialTheme.colorScheme.primary
                ) { FormView(form, state::formChanged) }
            } ?: run {
                Surface(Modifier.padding(padding), color = MaterialTheme.colorScheme.background) {
                    when (s.tab) {
                        Tab.Home -> HomeTab(s)
                        Tab.Items -> ItemsTab(s)
                        Tab.Settings -> SettingsTab()
                    }
                }
            }
        }
    }
}