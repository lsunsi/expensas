package com.lsunsi.expensas.state

import java.time.LocalDate
import java.time.OffsetDateTime
import java.util.*

sealed class Item {
    abstract val iv: Iv
    abstract val date: LocalDate
    abstract val verdict: Verdict?
    abstract val ts: OffsetDateTime

    data class Iv(val i: UUID, val v: Int) {
        companion object {
            fun new(): Iv {
                return Iv(UUID.randomUUID(), 0)
            }
        }
    }

    enum class Verdict { Confirmed, Refused }

    data class Expense(
        override val iv: Iv,
        override val date: LocalDate,
        override val verdict: Verdict?,
        override val ts: OffsetDateTime,
        val creator: Peer.Tag,
        val payer: Peer.Tag,
        val split: Split,
        val label: Label,
        val detail: String?,
        val paid: Cents,
        val owed: Cents,
    ) : Item() {
        fun cost(who: Peer.Tag): Cents = if (payer == who) Cents(paid.a - owed.a) else owed
    }

    data class Transfer(
        override val iv: Iv,
        override val date: LocalDate,
        override val verdict: Verdict?,
        override val ts: OffsetDateTime,
        val sender: Peer.Tag,
        val amount: UInt,
    ) : Item()
}