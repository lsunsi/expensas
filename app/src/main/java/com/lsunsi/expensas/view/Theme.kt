package com.lsunsi.expensas.view

import android.app.Activity
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView

private val LightColorScheme = lightColorScheme(
    primary = Color(189, 147, 249),
    secondary = Color(68, 71, 90),
    background = Color(248, 248, 242)
)

@Composable
fun Theme(content: @Composable () -> Unit) {
    val view = LocalView.current
    SideEffect {
        (view.context as Activity).window.statusBarColor = LightColorScheme.primary.toArgb()
    }

    MaterialTheme(
        colorScheme = LightColorScheme,
        content = content
    )
}