package com.lsunsi.expensas.state

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update

class Haptic {
    private val flow = MutableStateFlow(false)

    fun ticks(): Flow<Unit> = flow.map { }
    fun tick() = flow.update { !it }
}