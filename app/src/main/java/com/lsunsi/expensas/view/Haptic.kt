package com.lsunsi.expensas.view

import androidx.compose.runtime.*
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import com.lsunsi.expensas.state.Haptic

@Composable
fun Haptic(hh: Haptic) {
    val h = LocalHapticFeedback.current
    LaunchedEffect(hh) {
        hh.ticks().collect { h.performHapticFeedback(HapticFeedbackType.LongPress) }
    }
}