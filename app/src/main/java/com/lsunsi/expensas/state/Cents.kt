package com.lsunsi.expensas.state

import java.text.DecimalFormat

data class Cents(val a: UInt) {
    val display = "R$ ${DecimalFormat("#,###.00").format(a.toFloat() / 100)}"
}