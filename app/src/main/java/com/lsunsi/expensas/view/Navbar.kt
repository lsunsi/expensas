package com.lsunsi.expensas.view

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import com.lsunsi.expensas.state.Form
import com.lsunsi.expensas.state.Tab

@Composable
fun Navbar(tab: Tab, form: Form?, on: (Tab) -> Unit) {
    NavigationBar(containerColor = MaterialTheme.colorScheme.background) {
        NavigationBarItem(
            selected = tab == Tab.Summary,
            onClick = { on(Tab.Summary) },
            label = {
                Text("Resumão")
            },
            icon = {
                Icon(Icons.Default.Home, "Resumão")
            })

        NavigationBarItem(
            selected = tab == Tab.Statements,
            onClick = { on(Tab.Statements) },
            label = {
                Text("Gastos")
            },
            icon = {
                Icon(Icons.Default.List, "Gastos")
            })

        NavigationBarItem(
            selected = tab == Tab.Settings,
            onClick = { on(Tab.Settings) },
            label = {
                Text("Ajustes")
            },
            icon = {
                Icon(Icons.Default.Settings, "Ajustes")
            })
    }
}
