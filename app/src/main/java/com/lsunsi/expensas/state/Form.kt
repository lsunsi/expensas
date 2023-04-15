package com.lsunsi.expensas.state

import java.time.LocalDate
import java.time.format.DateTimeFormatter

data class Form(
    val open: Boolean,
    val kind: Kind,
    val date: String,
    val amount: String,
    val payer: String,
    val label: String,
    val split: String,
    val detail: String
) {
    enum class Kind { Expense, Transfer }

    companion object {
        fun default(open: Boolean): Form {
            return Form(
                open = open,
                kind = Kind.Expense,
                date = "",
                amount = "",
                payer = "",
                label = "",
                split = "",
                detail = ""
            )
        }
    }

    sealed class Ready {
        data class Expense(
            val date: LocalDate,
            val amount: UInt,
        ) : Ready()

        data class Transfer(
            val date: LocalDate,
            val amount: UInt,
        ) : Ready()
    }

    fun finish(): Ready? {
        val date = runCatching { LocalDate.parse(date, DateTimeFormatter.ISO_DATE) }.getOrNull()
            ?: return null

        val amount = amount.toUIntOrNull() ?: return null

        return Ready.Transfer(date, amount)
    }

    fun toggle(): Form {
        return copy(
            kind = when (this.kind) {
                Kind.Transfer -> Kind.Expense
                Kind.Expense -> Kind.Transfer
            }
        )
    }

    fun close(): Form {
        return copy(open = false)
    }
}
