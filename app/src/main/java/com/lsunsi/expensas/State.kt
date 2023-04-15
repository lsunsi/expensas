package com.lsunsi.expensas

import androidx.compose.material3.SnackbarHostState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lsunsi.expensas.state.*
import kotlinx.coroutines.flow.*
import java.time.LocalDate
import java.time.OffsetDateTime
import java.util.*

data class State(
    val tab: Tab,
    val me: Peer,
    val so: Peer,
    val form: Form,
    val expenses: List<Expense>,
    val transfers: List<Transfer>,
    val snackbar: Snackbar,
    val haptic: Haptic,
)

class StateViewModel : ViewModel() {
    private val state = MutableStateFlow(
        State(
            Tab.Home,
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
                    date = LocalDate.now(),
                    paid = Cents(123456U),
                    owed = Cents(1234531233U),
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
            form = Form.default(false),
            snackbar = Snackbar(lifecycle = viewModelScope, SnackbarHostState()),
            haptic = Haptic()
        )
    )

    val s: StateFlow<State> = state.asStateFlow()

    fun tabClicked(tab: Tab) {
        s.value.haptic.tick()
        state.update { state -> state.copy(tab = tab) }
    }

    fun launchPressed() {
        s.value.haptic.tick()
        state.update { state -> state.copy(form = Form.default(true)) }
    }

    fun formDiscarded() {
        s.value.haptic.tick()
        state.update { state -> state.copy(form = state.form.close()) }
    }

    fun formChanged(form: Form) {
        state.update { state -> state.copy(form = form) }
    }

    fun formToggled() {
        s.value.haptic.tick()
        state.update { state -> state.copy(form = state.form.toggle()) }
    }

    fun formSubmitted(form: Form) {
        s.value.haptic.tick()

        when (val ready = form.finish()) {
            is Form.Ready.Expense -> {
                s.value.snackbar.formSubmitted()
                state.updateAndGet { state ->
                    state.copy(
                        form = state.form.close(), expenses = state.expenses + Expense(
                            id = Uuid(UUID.randomUUID().toString()),
                            creator = state.me.tag,
                            payer = state.me.tag,
                            split = Split.Proportional,
                            label = Label.Delivery,
                            detail = null,
                            date = ready.date,
                            paid = Cents(ready.amount),
                            owed = Cents(ready.amount),
                            confirmedAt = null,
                            refusedAt = null,
                            createdAt = OffsetDateTime.now()
                        )
                    )
                }
            }
            is Form.Ready.Transfer -> {
                s.value.snackbar.formSubmitted()
                state.updateAndGet { state ->
                    state.copy(
                        form = state.form.close(), transfers = state.transfers + Transfer(
                            id = Uuid(UUID.randomUUID().toString()),
                            sender = state.me.tag,
                            date = ready.date,
                            amount = ready.amount,
                            confirmedAt = null,
                            refusedAt = null,
                            createdAt = OffsetDateTime.now()
                        )
                    )
                }
            }
            null -> {
                s.value.snackbar.formIncomplete()
            }
        }
    }
}
