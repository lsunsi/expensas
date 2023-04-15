package com.lsunsi.expensas.view

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.lsunsi.expensas.state.Form
import com.lsunsi.expensas.state.Tab

@Composable
fun LaunchButton(tab: Tab, form: Form, snackbar: SnackbarHostState, pressed: () -> Unit) {
    if (tab != Tab.Settings && !form.open && snackbar.currentSnackbarData == null) {
        ExtendedFloatingActionButton(
            icon = { Icon(Icons.Default.Add, "Lançar") },
            text = { Text("Lançar") },
            expanded = tab == Tab.Home,
            onClick = pressed
        )
    }
}