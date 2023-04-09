package com.lsunsi.expensas.view

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import com.lsunsi.expensas.state.Form

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(f: Form?) {
    TopAppBar(
        {
            Text(
                when (f) {
                    is Form.Expense -> "\uD83D\uDCB8 Gastei"
                    is Form.Transfer -> "\uD83D\uDCB5 Transferi"
                    null -> "Expensas \uD83D\uDCD2"
                }
            )
        },
        colors = TopAppBarDefaults.largeTopAppBarColors(
            MaterialTheme.colorScheme.primary
        )
    )
}
