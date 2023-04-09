package com.lsunsi.expensas.view

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import com.lsunsi.expensas.state.Form
import com.lsunsi.expensas.state.Tab

@Composable
fun Launch(tab: Tab, form: Form?, pressed: () -> Unit) {
    if (tab != Tab.Settings && form == null) {
        ExtendedFloatingActionButton(
            icon = { Icon(Icons.Default.Add, "Lançar") },
            text = { Text("Lançar") },
            onClick = pressed,
            expanded = tab == Tab.Summary
        )
    }
}