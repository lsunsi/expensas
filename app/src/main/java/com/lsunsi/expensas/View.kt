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
            topBar = { Topbar(s.form) },
            floatingActionButton = { Launch(s.tab, s.form, state::launchPressed) },
            bottomBar = {
                Bottombar(
                    s,
                    nav = { Navbar(s.tab, s.form, state::tabClicked) },
                    form = {
                        Formbar(
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
                ) { Form(form, state::formChanged) }
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