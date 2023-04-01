package com.lsunsi.expensas.state

import java.time.LocalDate
import java.time.OffsetDateTime

enum class Tab {
    Summary,
    Statements,
    Settings
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