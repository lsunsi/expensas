package com.lsunsi.expensas.view

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import com.lsunsi.expensas.state.Expense
import com.lsunsi.expensas.state.Transfer
import java.time.Month
import java.time.OffsetDateTime
import com.lsunsi.expensas.State
import com.lsunsi.expensas.util.formatMonth

sealed class Item {
    abstract val createdAt: OffsetDateTime
    abstract val month: Month

    data class E(val e: Expense) : Item() {
        override val createdAt get() = e.createdAt
        override val month get() = e.date.month!!
    }

    data class T(val t: Transfer) : Item() {
        override val createdAt get() = t.createdAt
        override val month get() = t.date.month!!
    }
}

@ExperimentalMaterial3Api
@Composable
fun Gastos(s: State) {
    val items = mutableListOf<Item>()
    items.addAll(s.expenses.map(Item::E))
    items.addAll(s.transfers.map(Item::T))
    items.sortBy(Item::createdAt)
    items.reverse()

    Column {
        items.groupBy(Item::month).map { entry ->
            ListItem(headlineText = { Text(formatMonth(entry.key)) })

            entry.value.map { item ->
                ListItem(headlineText = {
                    when (item) {
                        is Item.E -> Text("Gasto")
                        is Item.T -> Text("Transferencia")
                    }
                })
            }
        }
    }
}