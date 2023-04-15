package com.lsunsi.expensas.view

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.lsunsi.expensas.State
import com.lsunsi.expensas.state.Item
import java.time.LocalDate
import java.time.Month
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter

sealed class I {
    abstract val headline: String
    abstract val createdAt: OffsetDateTime
    abstract val date: LocalDate

    data class E(val e: Item.Expense) : I() {
        override val headline = e.label.display
        override val createdAt get() = e.ts
        override val date get() = e.date
    }

    data class T(val t: Item.Transfer) : I() {
        override val headline = "Transferência"
        override val createdAt get() = t.ts
        override val date get() = t.date
    }
}

@ExperimentalMaterial3Api
@Composable
fun ItemsTab(s: State) {
    Column {
        s.items.groupBy { Pair(it.date.month, it.date.year) }.map { entry ->
            ListItem(
                { Text(entry.key.first.display) },
                supportingText = { Text(entry.key.second.toString()) })

            entry.value.map { item ->
                when (item) {
                    is Item.Expense -> {
                        ListItem({ Text(item.label.display) },
                            supportingText = { Text(item.date.format(DateTimeFormatter.ISO_DATE)) },
                            trailingContent = { Text(item.cost(s.me.tag).display) })
                    }
                    is Item.Transfer -> {
                        ListItem({ Text("Transferência") },
                            supportingText = { Text(item.date.format(DateTimeFormatter.ISO_DATE)) },
                            trailingContent = { Text("-") })
                    }
                }
            }
        }
    }
}

val Month.display: String
    get() = when (this) {
        Month.JANUARY -> "Janeiro"
        Month.FEBRUARY -> "Fevereiro"
        Month.MARCH -> "Março"
        Month.APRIL -> "Abril"
        Month.MAY -> "Maio"
        Month.JUNE -> "Junho"
        Month.JULY -> "Julho"
        Month.AUGUST -> "Agosto"
        Month.SEPTEMBER -> "Setembro"
        Month.OCTOBER -> "Outubro"
        Month.NOVEMBER -> "Novembro"
        Month.DECEMBER -> "Dezembro"
    }
