package com.lsunsi.expensas.state

data class Peer(
    val tag: Tag,
    val name: String
) {
    data class Tag(val t: String)
}
