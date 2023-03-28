package com.lsunsi.expensas

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class State(
    val tab: Tab,
    val me: Peer,
    val so: Peer,
)

enum class Tab {
    Resumao,
    Gastos,
    Ajustes
}

data class Peer(val name: String)

class StateViewModel : ViewModel() {
    private val state = MutableStateFlow(
        State(
            Tab.Resumao,
            Peer("Lu"),
            Peer("Alê")
        )
    )
    val s: StateFlow<State> = state.asStateFlow()

    fun tabClicked(tab: Tab) {
        state.update { state -> state.copy(tab = tab) }
    }

    fun lancarPressed() {

    }
}
