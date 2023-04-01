package com.lsunsi.expensas.view

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import com.lsunsi.expensas.state.Form

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Bar(f: Form?) {
    TopAppBar(
        {
            Text(
                when (f) {
                    is Form.Expense -> "Gastei \uD83D\uDCB8"
                    is Form.Transfer -> "Transferi \uD83D\uDCB5"
                    null -> "Expensas"
                }
            )
        },
        colors = TopAppBarDefaults.largeTopAppBarColors(
            MaterialTheme.colorScheme.primary
        )
    )
}
