package com.lsunsi.expensas.view

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.runtime.*
import com.lsunsi.expensas.State

@Composable
fun BottomBar(
    s: State,
    nav: @Composable () -> Unit,
    form: @Composable () -> Unit,
) {
    AnimatedVisibility(
        visible = s.form == null,
        enter = slideInVertically(initialOffsetY = { it }),
        exit = slideOutVertically(targetOffsetY = { it })
    ) { nav() }

    AnimatedVisibility(
        visible = s.form != null,
        enter = slideInVertically(initialOffsetY = { it }),
        exit = slideOutVertically(targetOffsetY = { it })
    ) { form() }
}