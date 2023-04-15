package com.lsunsi.expensas

import androidx.compose.material3.*
import androidx.compose.runtime.*
import com.lsunsi.expensas.view.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun View(state: StateViewModel) {
    val s by state.s.collectAsState()

    Theme {
        Haptic(s.haptic)
        Scaffold(
            topBar = { TopBar(s.form) },
            floatingActionButton = {
                LaunchButton(
                    s.tab,
                    s.form,
                    s.snackbar.state,
                    state::launchPressed
                )
            },
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
        ) { padding -> Content(padding, s, state::formChanged) }
    }
}