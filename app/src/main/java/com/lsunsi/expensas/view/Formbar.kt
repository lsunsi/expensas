package com.lsunsi.expensas.view

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.lsunsi.expensas.state.Form

@Composable
fun Formbar(
    form: Form?,
    toggled: () -> Unit,
    discarded: () -> Unit,
    submitted: (Form) -> Unit,
) {
    BottomAppBar(containerColor = MaterialTheme.colorScheme.primaryContainer) {
        IconButton(discarded) { Icon(Icons.Default.Close, "Desisti") }
        IconButton(toggled) { Icon(Icons.Default.Refresh, "Troca") }
        Spacer(Modifier.weight(1f))

        FloatingActionButton(
            onClick = { form?.let(submitted) },
            elevation = FloatingActionButtonDefaults.elevation(0.dp),
            containerColor = form?.finish()?.let { MaterialTheme.colorScheme.primary }
                ?: MaterialTheme.colorScheme.primaryContainer
        ) { Icon(Icons.Default.Send, "Manda bala") }

        Spacer(modifier = Modifier.width(8.dp))
    }
}