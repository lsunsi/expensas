package com.lsunsi.expensas

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.material3.*
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun App(state: StateViewModel) {
    val s by state.s.collectAsState()

    Scaffold(
        topBar = { Bar() },
        bottomBar = { Nav(tab = s.tab, on = state::tabClicked) }
    ) { padding ->
        Surface(Modifier.padding(padding), color = MaterialTheme.colorScheme.background) {
            when (s.tab) {
                Tab.Resumao -> Resumao(s, state::lancarPressed)
                Tab.Gastos -> Gastos(s)
                Tab.Ajustes -> Ajustes()
            }
        }
    }
}