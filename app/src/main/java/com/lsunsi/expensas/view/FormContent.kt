package com.lsunsi.expensas.view

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.text.input.ImeAction
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
    Field("Data", "Que dia você pagou?", f.date) { changed(f.copy(date = it)) }
    Field("Pagante", "Quem pagou?", f.payer) { changed(f.copy(payer = it)) }
    Field("Etiqueta", "Como classificar o gasto?", f.label) { changed(f.copy(label = it)) }
    Field("Divisão", "Como vai ser a divisão?", f.split) { changed(f.copy(split = it)) }
    Field("Detalhe", "Algum detalhe extra?", f.detail) { changed(f.copy(detail = it)) }
    Field("Valor", "Quanto foi pago?", f.amount, true) { changed(f.copy(amount = it)) }
}

@ExperimentalMaterial3Api
@Composable
private fun Transfer(
    f: Form,
    changed: (Form) -> Unit,
) {
    Field("Data", "Que dia foi transferido?", f.date) { changed(f.copy(date = it)) }
    Field("Valor", "Quanto você transferiu?", f.amount, true) { changed(f.copy(amount = it)) }
}

@ExperimentalMaterial3Api
@Composable
private fun <T> Field(
    label: String,
    text: String,
    f: Form.Field<T>,
    last: Boolean = false,
    changed: (Form.Field<T>) -> Unit,
) {
    TextField(
        modifier = Modifier
            .padding(0.dp, 8.dp)
            .onFocusChanged { changed(f.focus(it.isFocused)) },
        onValueChange = { changed(f.update(it)) },
        colors = TextFieldDefaults.textFieldColors(containerColor = MaterialTheme.colorScheme.primaryContainer),
        keyboardOptions = KeyboardOptions(imeAction = if (last) ImeAction.Done else ImeAction.Next),
        label = { Text(label) }, supportingText = { Text(text) },
        trailingIcon = { if (f.success) Icon(Icons.Default.Check, contentDescription = "Check") },
        singleLine = true, value = f.raw, isError = f.error,
    )
}