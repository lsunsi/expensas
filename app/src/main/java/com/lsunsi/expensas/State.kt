package com.lsunsi.expensas

import androidx.compose.material3.SnackbarHostState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
    val form: Form,
    var items: List<Item>,
    val snackbar: Snackbar,
    val haptic: Haptic,
)

class StateViewModel : ViewModel() {
    private val state = MutableStateFlow(
        State(
            Tab.Home,
            Peer(Peer.Tag("lsunsi"), "Lu"),
            Peer(Peer.Tag("aleharit"), "Alê"),
            items = listOf(
                Item.Expense(
                    iv = Item.Iv.new(),
                    date = LocalDate.now(),
                    ts = OffsetDateTime.now(),
                    verdict = null,
                    creator = Peer.Tag("lsunsi"),
                    payer = Peer.Tag("aleharit"),
                    split = Split.Proportional,
                    label = Label.Delivery,
                    detail = null,
                    paid = Cents(123456U),
                    owed = Cents(1234531233U),
                ), Item.Transfer(
                    iv = Item.Iv.new(),
                    date = LocalDate.now(),
                    ts = OffsetDateTime.now(),
                    verdict = null,
                    sender = Peer.Tag("lsunsi"),
                    amount = 55U,
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
        state.update { it.copy(tab = tab) }
    }

    fun launchPressed() {
        s.value.haptic.tick()
        state.update { it.copy(form = Form.default(true)) }
    }

    fun formDiscarded() {
        s.value.haptic.tick()
        state.update { it.copy(form = it.form.close()) }
    }

    fun formChanged(form: Form) {
        state.update { it.copy(form = form) }
    }

    fun formToggled() {
        s.value.haptic.tick()
        state.update { it.copy(form = it.form.toggle()) }
    }

    fun formSubmitted(form: Form) {
        s.value.haptic.tick()

        when (val ready = form.finish()) {
            is Form.Ready.Expense -> {
                s.value.snackbar.formSubmitted()
                state.update {
                    it.copy(
                        form = it.form.close(), items = it.items + Item.Expense(
                            iv = Item.Iv.new(),
                            date = ready.date,
                            verdict = null,
                            ts = OffsetDateTime.now(),
                            creator = it.me.tag,
                            payer = it.me.tag,
                            split = Split.Proportional,
                            label = Label.Delivery,
                            detail = null,
                            paid = Cents(ready.amount),
                            owed = Cents(ready.amount),
                        )
                    )
                }
            }
            is Form.Ready.Transfer -> {
                s.value.snackbar.formSubmitted()
                state.update {
                    it.copy(
                        form = it.form.close(), items = it.items + Item.Transfer(
                            iv = Item.Iv.new(),
                            date = ready.date,
                            verdict = null,
                            ts = OffsetDateTime.now(),
                            sender = it.me.tag,
                            amount = ready.amount,
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
