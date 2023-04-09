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
import java.time.Month

@ExperimentalMaterial3Api
@Composable
fun Submission(
    form: Form,
    discard: () -> Unit,
    toggled: () -> Unit,
    changed: (Form) -> Unit,
    submitted: (Form) -> Unit,
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        when (form) {
            is Form.Expense -> Expense(form, changed)
            is Form.Transfer -> Transfer(form, changed)
        }

        Spacer(Modifier.weight(1f))

        BottomAppBar(containerColor = MaterialTheme.colorScheme.primaryContainer) {
            IconButton(discard) { Icon(Icons.Default.Close, "Desisti") }
            IconButton(toggled) { Icon(Icons.Default.Refresh, "Troca") }
            Spacer(Modifier.weight(1f))

            FloatingActionButton(
                onClick = { submitted(form) },
                elevation = FloatingActionButtonDefaults.elevation(0.dp),
                containerColor = form.finish()?.let { MaterialTheme.colorScheme.primary }
                    ?: MaterialTheme.colorScheme.primaryContainer
            ) { Icon(Icons.Default.Send, "Manda bala") }

            Spacer(modifier = Modifier.width(8.dp))
        }
    }
}

@ExperimentalMaterial3Api
@Composable
private fun Expense(
    f: Form.Expense,
    changed: (Form.Expense) -> Unit,
) {
    TextField(modifier = Modifier.padding(0.dp, 8.dp),
        colors = TextFieldDefaults.textFieldColors(containerColor = MaterialTheme.colorScheme.primaryContainer),
        value = f.date,
        label = { Text("Data") },
        supportingText = { Text("Que dia você pagou?") },
        onValueChange = { changed(f.copy(date = it)) })

    TextField(modifier = Modifier.padding(0.dp, 8.dp),
        colors = TextFieldDefaults.textFieldColors(containerColor = MaterialTheme.colorScheme.primaryContainer),
        value = f.payer,
        label = { Text("Pagante") },
        supportingText = { Text("Quem pagou?") },
        onValueChange = { changed(f.copy(payer = it)) })

    TextField(modifier = Modifier.padding(0.dp, 8.dp),
        colors = TextFieldDefaults.textFieldColors(containerColor = MaterialTheme.colorScheme.primaryContainer),
        value = f.label,
        label = { Text("Etiqueta") },
        supportingText = { Text("Qual o tipo do gasto?") },
        onValueChange = { changed(f.copy(label = it)) })

    TextField(modifier = Modifier.padding(0.dp, 8.dp),
        colors = TextFieldDefaults.textFieldColors(containerColor = MaterialTheme.colorScheme.primaryContainer),
        value = f.split,
        label = { Text("Divisão") },
        supportingText = { Text("Qual o tipo da divisão?") },
        onValueChange = { changed(f.copy(split = it)) })

    TextField(modifier = Modifier.padding(0.dp, 8.dp),
        colors = TextFieldDefaults.textFieldColors(containerColor = MaterialTheme.colorScheme.primaryContainer),
        value = f.detail,
        label = { Text("Detalhe") },
        supportingText = { Text("Algum detalhe extra?") },
        onValueChange = { changed(f.copy(detail = it)) })

    TextField(modifier = Modifier.padding(0.dp, 8.dp),
        colors = TextFieldDefaults.textFieldColors(containerColor = MaterialTheme.colorScheme.primaryContainer),
        value = f.amount,
        label = { Text("Valor") },
        supportingText = { Text("Quanto foi pago?") },
        onValueChange = { changed(f.copy(amount = it)) })
}

@ExperimentalMaterial3Api
@Composable
private fun Transfer(
    f: Form.Transfer,
    changed: (Form.Transfer) -> Unit,
) {
    TextField(modifier = Modifier.padding(0.dp, 8.dp),
        colors = TextFieldDefaults.textFieldColors(containerColor = MaterialTheme.colorScheme.primaryContainer),
        value = f.date,
        label = { Text("Data") },
        supportingText = { Text("Que dia você transferiu?") },
        onValueChange = { changed(f.copy(date = it)) })

    TextField(modifier = Modifier.padding(0.dp, 8.dp),
        colors = TextFieldDefaults.textFieldColors(containerColor = MaterialTheme.colorScheme.primaryContainer),
        value = f.amount,
        label = { Text("Valor") },
        supportingText = { Text("Quanto você transferiu?") },
        onValueChange = { changed(f.copy(amount = it)) })
}

val Month.display: String
    get() = when (this) {
        Month.JANUARY -> "Janeiro"
        Month.FEBRUARY -> "Fevereiro"
        Month.MARCH -> "Março"
        Month.APRIL -> "Apil"
        Month.MAY -> "May"
        Month.JUNE -> "Junho"
        Month.JULY -> "Julho"
        Month.AUGUST -> "Agosto"
        Month.SEPTEMBER -> "Setembro"
        Month.OCTOBER -> "Outubro"
        Month.NOVEMBER -> "Novembro"
        Month.DECEMBER -> "Dezembro"
    }
