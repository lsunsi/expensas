package com.lsunsi.expensas.view

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import com.lsunsi.expensas.state.Expense
import com.lsunsi.expensas.state.Transfer
import java.time.OffsetDateTime
import com.lsunsi.expensas.State
import java.time.LocalDate
import java.time.format.DateTimeFormatter

sealed class Item {
    abstract val headline: String
    abstract val createdAt: OffsetDateTime
    abstract val date: LocalDate

    data class E(val e: Expense) : Item() {
        override val headline = e.label.display
        override val createdAt get() = e.createdAt
        override val date get() = e.date
    }

    data class T(val t: Transfer) : Item() {
        override val headline = "Transferência"
        override val createdAt get() = t.createdAt
        override val date get() = t.date
    }
}

@ExperimentalMaterial3Api
@Composable
fun Statements(s: State) {
    val items = mutableListOf<Item>()
    items.addAll(s.expenses.map(Item::E))
    items.addAll(s.transfers.map(Item::T))
    items.sortBy(Item::createdAt)
    items.reverse()

    Column {
        items.groupBy { Pair(it.date.month, it.date.year) }.map { entry ->
            ListItem(
                { Text(entry.key.first.display) },
                supportingText = { Text(entry.key.second.toString()) })

            entry.value.map { item ->
                ListItem({ Text(item.headline) }, supportingText = {
                    Text(item.date.format(DateTimeFormatter.ISO_DATE))
                }, trailingContent = { Text(
                    when (item) {
                        is Item.E -> item.e.cost(s.me.tag).display
                        is Item.T -> "-"
                    }
                ) })
            }
        }
    }
}
