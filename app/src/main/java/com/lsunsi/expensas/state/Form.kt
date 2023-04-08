package com.lsunsi.expensas.state

import java.time.LocalDate
import java.time.format.DateTimeFormatter

sealed class Form {
    companion object {
        fun default(): Form {
            return Expense(
                date = "",
                amount = "",
                payer = "",
                label = "",
                split = "",
                detail = ""
            )
        }
    }

    abstract fun finish(): Ready?

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

    data class Expense(
        val date: String,
        val amount: String,
        val payer: String,
        val label: String,
        val split: String,
        val detail: String
    ) : Form() {
        override fun finish(): Ready.Expense? {
            val date = runCatching { LocalDate.parse(date, DateTimeFormatter.ISO_DATE) }.getOrNull()
                ?: return null

            val amount = amount.toUIntOrNull() ?: return null

            return Ready.Expense(date, amount)
        }
    }

    data class Transfer(val date: String, val amount: String) : Form() {
        override fun finish(): Ready.Transfer? {
            val date = runCatching { LocalDate.parse(date, DateTimeFormatter.ISO_DATE) }.getOrNull()
                ?: return null

            val amount = amount.toUIntOrNull() ?: return null

            return Ready.Transfer(date, amount)
        }
    }

    fun toggle(): Form {
        return when (this) {
            is Expense -> {
                Transfer(date = this.date, amount = this.amount)
            }
            is Transfer -> {
                Expense(
                    date = this.date,
                    amount = this.amount,
                    payer = "",
                    detail = "",
                    split = "",
                    label = ""
                )
            }
        }
    }
}
