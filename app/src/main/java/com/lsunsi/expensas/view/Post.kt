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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Post(f: Form, discard: () -> Unit, toggle: () -> Unit) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        OutlinedTextField(
            modifier = Modifier.padding(0.dp, 4.dp),
            colors = TextFieldDefaults.textFieldColors(containerColor = MaterialTheme.colorScheme.primaryContainer),
            value = "",
            label = { Text("Data") },
            onValueChange = { })

        OutlinedTextField(
            modifier = Modifier.padding(0.dp, 4.dp),
            colors = TextFieldDefaults.textFieldColors(containerColor = MaterialTheme.colorScheme.primaryContainer),
            value = "",
            label = { Text("Valor") },
            onValueChange = { })

        Spacer(modifier = Modifier.weight(1f))

        BottomAppBar(
            Modifier.align(Alignment.End),
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ) {
            IconButton(discard) { Icon(Icons.Default.Close, "Desisti") }
            IconButton(toggle) { Icon(Icons.Default.Refresh, "Troca") }
            Spacer(modifier = Modifier.weight(1f))

            FloatingActionButton(
                onClick = { /*TODO*/ },
                elevation = FloatingActionButtonDefaults.elevation(0.dp),
                containerColor = MaterialTheme.colorScheme.primary
            ) {
                Icon(Icons.Default.Send, "Manda bala")
            }

            Spacer(modifier = Modifier.width(8.dp))
        }
    }
}