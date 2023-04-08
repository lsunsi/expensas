package com.lsunsi.expensas

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lsunsi.expensas.state.*
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.OffsetDateTime

data class State(
    val tab: Tab,
    val me: Peer,
    val so: Peer,
    val expenses: List<Expense>,
    val transfers: List<Transfer>,
    val form: Form?,
    val snackbar: Snackbar,
    val haptic: MutableStateFlow<Boolean>
)

class StateViewModel : ViewModel() {
    private val state = MutableStateFlow(
        State(
            Tab.Summary,
            Peer(Tag("lsunsi"), "Lu"),
            Peer(Tag("aleharit"), "Alê"),
            expenses = listOf(
                Expense(
                    id = Uuid("2dcad30c-80c7-4489-9467-3fd269c63244"),
                    creator = Tag("lsunsi"),
                    payer = Tag("aleharit"),
                    split = Split.Proportional,
                    label = Label.Delivery,
                    detail = null,
                    date = LocalDate.MIN,
                    paid = 123U,
                    owed = 23U,
                    confirmedAt = null,
                    refusedAt = null,
                    createdAt = OffsetDateTime.now()
                )
            ),
            transfers = listOf(
                Transfer(
                    id = Uuid("b6d57d76-f8fb-4fbd-8155-803bbb2e245a"),
                    sender = Tag("lsunsi"),
                    date = LocalDate.now(),
                    amount = 55U,
                    confirmedAt = null,
                    refusedAt = null,
                    createdAt = OffsetDateTime.now()
                )
            ),
            form = null,
            snackbar = Snackbar(lifecycle = viewModelScope, SnackbarHostState()),
            haptic = MutableStateFlow(true)
        )
    )

    val s: StateFlow<State> = state.asStateFlow()

    fun tabClicked(tab: Tab) {
        s.value.haptic.update { !it }
        state.update { state -> state.copy(tab = tab) }
    }

    fun lancarPressed() {
        s.value.haptic.update { !it }
        state.update { state -> state.copy(form = Form.default()) }
    }

    fun formDiscardPressed() {
        s.value.haptic.update { !it }
        state.update { state -> state.copy(form = null) }
    }

    fun formChanged(form: Form) {
        state.update { state -> state.copy(form = form) }
    }

    fun formSubmitted(form: Form) {
        s.value.haptic.update { !it }

        when (form.finish()) {
            is Form.Ready.Expense -> {
                s.value.snackbar.formSubmitted()
            }
            is Form.Ready.Transfer -> {
                s.value.snackbar.formSubmitted()
            }
            null -> {
                s.value.snackbar.formIncomplete()
            }
        }
    }
}
