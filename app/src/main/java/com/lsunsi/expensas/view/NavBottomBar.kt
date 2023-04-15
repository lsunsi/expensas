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
fun NavBottomBar(tab: Tab, form: Form?, on: (Tab) -> Unit) {
    NavigationBar(containerColor = MaterialTheme.colorScheme.background) {
        NavigationBarItem(
            selected = tab == Tab.Home,
            onClick = { on(Tab.Home) },
            label = { Text("Resumo") },
            icon = { Icon(Icons.Default.Home, "Resumo") }
        )

        NavigationBarItem(
            selected = tab == Tab.Items,
            onClick = { on(Tab.Items) },
            label = { Text("Extrato") },
            icon = { Icon(Icons.Default.List, "Extrato") }
        )

        NavigationBarItem(
            selected = tab == Tab.Settings,
            onClick = { on(Tab.Settings) },
            label = { Text("Ajustes") },
            icon = { Icon(Icons.Default.Settings, "Ajustes") }
        )
    }
}
