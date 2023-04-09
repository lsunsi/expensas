package com.lsunsi.expensas.state

enum class Split {
    Proportional {
        override val display = "Proporcional"
    },
    Arbitrary {
        override val display = "Arbitrário"
    },
    Evenly {
        override val display = "Igualitário"
    };

    abstract val display: String
}