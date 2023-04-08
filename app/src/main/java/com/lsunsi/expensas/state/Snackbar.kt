package com.lsunsi.expensas.state

import androidx.compose.material3.SnackbarHostState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

data class Snackbar(val lifecycle: CoroutineScope, val state: SnackbarHostState) {
    fun formIncomplete() = show("Form Incomplete")
    fun formSubmitted() = show("Form Submitted")

    private fun show(str: String) = lifecycle.launch { state.showSnackbar(str) }
}