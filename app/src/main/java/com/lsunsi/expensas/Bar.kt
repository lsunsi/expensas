package com.lsunsi.expensas

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.material3.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Bar() {
    TopAppBar(
        colors = TopAppBarDefaults.largeTopAppBarColors(
            MaterialTheme.colorScheme.primary
        ), title = { Text(text = "Expensas") })
}