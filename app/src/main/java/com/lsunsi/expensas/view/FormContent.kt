package com.lsunsi.expensas.view

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.lsunsi.expensas.state.Form

@ExperimentalMaterial3Api
@Composable
fun FormContent(
    form: Form,
    changed: (Form) -> Unit,
) {

    AnimatedVisibility(
        visible = form.kind == Form.Kind.Expense,
        enter = slideInHorizontally(initialOffsetX = { -it }),
        exit = slideOutHorizontally(targetOffsetX = { -it })
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Expense(form, changed)
        }
    }

    AnimatedVisibility(
        visible = form.kind == Form.Kind.Transfer,
        enter = slideInHorizontally(initialOffsetX = { it }),
        exit = slideOutHorizontally(targetOffsetX = { it })
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Transfer(form, changed)
        }
    }
}

@ExperimentalMaterial3Api
@Composable
private fun Expense(
    f: Form,
    changed: (Form) -> Unit,
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
    f: Form,
    changed: (Form) -> Unit,
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
