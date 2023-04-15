package com.lsunsi.expensas.view

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import com.lsunsi.expensas.state.Form

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(f: Form) {
    TopAppBar(
        {
            AnimatedVisibility(
                visible = f.open && f.kind == Form.Kind.Expense,
                enter = fadeIn(),
                exit = fadeOut()
            ) { Text("\uD83D\uDCB8 Gastei") }

            AnimatedVisibility(
                visible = f.open && f.kind == Form.Kind.Transfer,
                enter = fadeIn(),
                exit = fadeOut()
            ) { Text("\uD83D\uDCB5 Transferi") }

            AnimatedVisibility(
                visible = !f.open,
                enter = fadeIn(),
                exit = fadeOut()
            ) { Text("Expensas \uD83D\uDCD2") }
        },
        colors = TopAppBarDefaults.largeTopAppBarColors(
            MaterialTheme.colorScheme.primary
        )
    )
}
