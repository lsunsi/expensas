package com.lsunsi.expensas

import androidx.lifecycle.ViewModel
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
    val transfers: List<Transfer>
)

enum class Tab {
    Resumao,
    Gastos,
    Ajustes
}

data class Peer(
    val tag: Tag,
    val name: String
)

data class Uuid(val i: String)
data class Tag(val t: String)

data class Transfer(
    val id: Uuid,
    val sender: Tag,
    val date: LocalDate,
    val amount: UInt,
    val confirmedAt: OffsetDateTime?,
    val refusedAt: OffsetDateTime?,
    val createdAt: OffsetDateTime
)

data class Expense(
    val id: Uuid,
    val creator: Tag,
    val payer: Tag,
    val split: Unit,
    val label: Unit,
    val detail: String?,
    val date: LocalDate,
    val paid: UInt,
    val owed: UInt,
    val confirmedAt: OffsetDateTime?,
    val refusedAt: OffsetDateTime?,
    val createdAt: OffsetDateTime
)

class StateViewModel : ViewModel() {
    private val state = MutableStateFlow(
        State(
            Tab.Resumao,
            Peer(Tag("lsunsi"),"Lu"),
            Peer(Tag("aleharit"),"Alê"),
            expenses = listOf(Expense(
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
            )),
            transfers = listOf(Transfer(
                id = Uuid("b6d57d76-f8fb-4fbd-8155-803bbb2e245a"),
                sender = Tag("lsunsi"),
                date = LocalDate.now(),
                amount = 55U,
                confirmedAt = null,
                refusedAt = null,
                createdAt = OffsetDateTime.now()
            ))
        )
    )

    val s: StateFlow<State> = state.asStateFlow()

    fun tabClicked(tab: Tab) {
        state.update { state -> state.copy(tab = tab) }
    }

    fun lancarPressed() {

    }
}
