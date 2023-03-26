package com.lsunsi.expensas

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.material3.*

@Composable
fun Nav(tab: Tab, on: (Tab) -> Unit) {
    NavigationBar(containerColor = MaterialTheme.colorScheme.background) {
        NavigationBarItem(
            selected = tab == Tab.Resumao,
            onClick = { on(Tab.Resumao) },
            label = {
                Text("Resumão")
            },
            icon = {
                Icon(Icons.Default.Home, "Resumão")
            })

        NavigationBarItem(
            selected = tab == Tab.Gastos,
            onClick = { on(Tab.Gastos) },
            label = {
                Text("Gastos")
            },
            icon = {
                Icon(Icons.Default.List, "Gastos")
            })

        NavigationBarItem(
            selected = tab == Tab.Ajustes,
            onClick = { on(Tab.Ajustes) },
            label = {
                Text("Ajustes")
            },
            icon = {
                Icon(Icons.Default.Settings, "Ajustes")
            })
    }
}
