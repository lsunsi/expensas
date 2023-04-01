package com.lsunsi.expensas

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.material3.*
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.lsunsi.expensas.view.*
import com.lsunsi.expensas.state.Tab

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun View(state: StateViewModel) {
    val s by state.s.collectAsState()

    Theme {
        Scaffold(
            topBar = { Bar(s.form) },
            bottomBar = { Nav(tab = s.tab, on = state::tabClicked, s.form) }
        ) { padding ->
            s.form?.let {
                Surface(
                    Modifier
                        .fillMaxSize()
                        .padding(padding),
                    color = MaterialTheme.colorScheme.primary
                ) {
                    Post(
                        it,
                        discard = state::formDiscardPressed,
                        toggle = state::formTogglePressed
                    )
                }
            } ?: run {
                Surface(Modifier.padding(padding), color = MaterialTheme.colorScheme.background) {
                    when (s.tab) {
                        Tab.Summary -> Summary(s, state::lancarPressed)
                        Tab.Statements -> Statements(s)
                        Tab.Settings -> Settings()
                    }
                }
            }
        }
    }
}