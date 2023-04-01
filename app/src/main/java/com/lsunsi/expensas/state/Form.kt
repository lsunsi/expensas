package com.lsunsi.expensas.state

import java.time.LocalDate

sealed class Form {
    data class Expense(val date: LocalDate, val payer: Tag?, val details: String?) : Form()
    data class Transfer(val date: LocalDate, val amount: String?) : Form()

    fun toggle(): Form {
        return when (this) {
            is Expense -> {
                Transfer(date = this.date, amount = null)
            }
            is Transfer -> {
                Expense(date = this.date, payer = null, details = null)
            }
        }
    }
}

fun defaultForm(): Form {
    return Form.Expense(date = LocalDate.now(), payer = null, details = null)
}