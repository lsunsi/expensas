package com.lsunsi.expensas

import androidx.lifecycle.ViewModel
import com.lsunsi.expensas.state.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.time.LocalDate
import java.time.OffsetDateTime

data class State(
    val tab: Tab,
    val me: Peer,
    val so: Peer,
    val expenses: List<Expense>,
    val transfers: List<Transfer>,
    val form: Form?
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
                    split = Unit,
                    label = Unit,
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
            form = null
        )
    )

    val s: StateFlow<State> = state.asStateFlow()

    fun tabClicked(tab: Tab) {
        state.update { state -> state.copy(tab = tab) }
    }

    fun lancarPressed() {
        state.update { state -> state.copy(form = defaultForm()) }
    }

    fun formDiscardPressed() {
        state.update { state -> state.copy(form = null) }
    }

    fun formTogglePressed() {
        state.update { state -> state.copy(form = state.form?.toggle()) }
    }
}
