package com.lsunsi.expensas.view

import androidx.compose.runtime.*
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import kotlinx.coroutines.flow.Flow

@Composable
fun Haptic(f: Flow<Boolean>) {
    val h = LocalHapticFeedback.current
    LaunchedEffect(f) {
        f.collect { h.performHapticFeedback(HapticFeedbackType.LongPress) }
    }
}