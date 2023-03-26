package com.lsunsi.expensas

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class State(
    val tab: Tab,
)

enum class Tab {
    Resumao,
    Gastos,
    Ajustes
}

class StateViewModel : ViewModel() {
    private val state = MutableStateFlow(State(Tab.Resumao))
    val s: StateFlow<State> = state.asStateFlow()

    fun tabClicked(tab: Tab) {
        state.update { state -> state.copy(tab = tab) }
    }
}
