package com.lsunsi.expensas.state

import java.time.LocalDate
import java.time.OffsetDateTime

data class Expense(
    val id: Uuid,
    val creator: Tag,
    val payer: Tag,
    val split: Split,
    val label: Label,
    val detail: String?,
    val date: LocalDate,
    val paid: Cents,
    val owed: Cents,
    val confirmedAt: OffsetDateTime?,
    val refusedAt: OffsetDateTime?,
    val createdAt: OffsetDateTime
) {
    fun cost(who: Tag): Cents = if (payer == who) Cents(paid.a - owed.a) else owed
}
