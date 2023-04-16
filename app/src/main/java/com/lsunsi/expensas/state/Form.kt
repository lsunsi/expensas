package com.lsunsi.expensas.state

import java.time.LocalDate
import java.time.format.DateTimeFormatter

data class Form(
    var open: Boolean,
    val kind: Kind,
    val date: Field<LocalDate>,
    val amount: Field<UInt>,
    val payer: Field<String>,
    val label: Field<Label>,
    val split: Field<Split>,
    val detail: Field<String>
) {
    enum class Kind { Expense, Transfer }

    sealed class Field<T> {
        abstract val raw: String
        abstract val parsed: T?
        abstract val focused: Boolean
        abstract fun update(s: String): Field<T>
        abstract fun focus(b: Boolean): Field<T>

        val error: Boolean get() = !focused && raw != "" && parsed == null
        val success: Boolean get() = raw != "" && parsed != null

        data class Date(
            override val raw: String = "",
            override val parsed: LocalDate? = null,
            override val focused: Boolean = false,
        ) : Field<LocalDate>() {
            override fun focus(b: Boolean): Date = copy(focused = b)
            override fun update(s: String): Date = copy(
                parsed = runCatching { LocalDate.parse(s, DateTimeFormatter.ISO_DATE) }.getOrNull(),
                focused = focused,
                raw = s,
            )
        }

        data class Amount(
            override val raw: String = "",
            override val parsed: UInt? = null,
            override val focused: Boolean = false
        ) : Field<UInt>() {
            override fun focus(b: Boolean): Amount = copy(focused = b)
            override fun update(s: String): Amount = copy(
                parsed = s.toUIntOrNull(),
                focused = focused,
                raw = s,
            )
        }

        data class Label(
            override val raw: String = "",
            override val parsed: com.lsunsi.expensas.state.Label? = null,
            override val focused: Boolean = false
        ) : Field<com.lsunsi.expensas.state.Label>() {
            override fun focus(b: Boolean): Label = copy(focused = b)
            override fun update(s: String): Label =
                copy(parsed = kotlin.runCatching { com.lsunsi.expensas.state.Label.valueOf(s) }
                    .getOrNull(), focused = focused, raw = s)
        }

        data class Split(
            override val raw: String = "",
            override val parsed: com.lsunsi.expensas.state.Split? = null,
            override val focused: Boolean = false
        ) : Field<com.lsunsi.expensas.state.Split>() {
            override fun focus(b: Boolean): Split = copy(focused = b)
            override fun update(s: String): Split =
                copy(parsed = kotlin.runCatching { com.lsunsi.expensas.state.Split.valueOf(s) }
                    .getOrNull(), focused = focused, raw = s)
        }

        data class Detail(
            override val raw: String = "",
            override val parsed: String? = null,
            override val focused: Boolean = false
        ) : Field<String>() {
            override fun focus(b: Boolean): Detail = copy(focused = b)
            override fun update(s: String): Detail = copy(parsed = s, focused = focused, raw = s)
        }

        data class OneOf(
            override val raw: String = "",
            override val parsed: String? = null,
            override val focused: Boolean = false,
            val options: Set<String>
        ) : Field<String>() {
            override fun focus(b: Boolean): OneOf = copy(focused = b)
            override fun update(s: String): OneOf =
                copy(parsed = if (options.contains(s)) s else null, focused = focused, raw = s)
        }
    }

    companion object {
        fun closed(): Form {
            return Form(
                open = false,
                kind = Kind.Expense,
                date = Field.Date(),
                amount = Field.Amount(),
                payer = Field.OneOf(options = setOf<String>()),
                label = Field.Label(),
                split = Field.Split(),
                detail = Field.Detail()
            )
        }

        fun open(payers: Set<String>): Form {
            return Form(
                open = true,
                kind = Kind.Expense,
                date = Field.Date(),
                amount = Field.Amount(),
                payer = Field.OneOf(options = payers),
                label = Field.Label(),
                split = Field.Split(),
                detail = Field.Detail()
            )
        }
    }

    sealed class Ready {
        data class Expense(
            val date: LocalDate,
            val amount: UInt,
            val payer: String,
            val label: Label,
            val split: Split,
            val detail: String
        ) : Ready()

        data class Transfer(
            val date: LocalDate,
            val amount: UInt
        ) : Ready()
    }

    fun finish(): Ready? {
        when (kind) {
            Kind.Expense -> {
                val date = date.parsed ?: return null
                val amount = amount.parsed ?: return null
                val payer = payer.parsed ?: return null
                val label = label.parsed ?: return null
                val split = split.parsed ?: return null
                val detail = detail.parsed ?: return null
                return Ready.Expense(date, amount, payer, label, split, detail)
            }
            Kind.Transfer -> {
                val date = date.parsed ?: return null
                val amount = amount.parsed ?: return null
                return Ready.Transfer(date, amount)
            }
        }
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
