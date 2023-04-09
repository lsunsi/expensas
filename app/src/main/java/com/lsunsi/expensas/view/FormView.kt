package com.lsunsi.expensas.view

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.lsunsi.expensas.state.Form

@ExperimentalMaterial3Api
@Composable
fun FormView(
    form: Form,
    changed: (Form) -> Unit,
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        when (form) {
            is Form.Expense -> Expense(form, changed)
            is Form.Transfer -> Transfer(form, changed)
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
