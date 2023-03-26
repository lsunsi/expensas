package com.lsunsi.expensas

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun Resumao(s: State) {
    Column(
        Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Column {
            Text(text = "Olá ${s.me.name}!", style = MaterialTheme.typography.displaySmall)
        }

        Column(Modifier.padding(8.dp, 32.dp, 8.dp, 8.dp)) {
            Text("Estado de devimento", style = MaterialTheme.typography.headlineSmall)
            Text("Você deve $ 154,84 atualmente", style = MaterialTheme.typography.bodyMedium)
        }

        ElevatedCard(
            Modifier
                .fillMaxWidth()
                .padding(PaddingValues(0.dp, 32.dp, 0.dp, 0.dp)),
            colors = CardDefaults.elevatedCardColors(MaterialTheme.colorScheme.primary)
        ) {
            Column(Modifier.padding(16.dp)) {
                Text("Gastos pendentes", style = MaterialTheme.typography.headlineSmall)
                Text(
                    "${s.so.name} lançou 3 gastos que você não avaliou",
                    modifier = Modifier.padding(0.dp, 8.dp),
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}