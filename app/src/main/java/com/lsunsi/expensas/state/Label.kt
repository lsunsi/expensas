package com.lsunsi.expensas.state

enum class Label {
    Market {
        override val display = "Mercado"
    },
    Delivery {
        override val display = "Delivery"
    },
    Transport {
        override val display = "Transporte"
    },
    Leisure {
        override val display = "Lazer"
    },
    Water {
        override val display = "Conta de água"
    },
    Internet {
        override val display = "Conta de internet"
    },
    Gas {
        override val display = "Refil do gás"
    },
    Housing {
        override val display = "Aluguel"
    },
    Electricity {
        override val display = "Conta de luz"
    },
    Furnitance {
        override val display = "Coisas da casa"
    };

    abstract val display: String
}
