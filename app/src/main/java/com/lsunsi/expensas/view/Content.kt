package com.lsunsi.expensas.view

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.lsunsi.expensas.State
import com.lsunsi.expensas.state.Form
import com.lsunsi.expensas.state.Tab

@ExperimentalMaterial3Api
@Composable
fun Content(padding: PaddingValues, s: State, formChanged: (Form) -> Unit) {
    Surface(
        Modifier.padding(padding),
        color = MaterialTheme.colorScheme.background
    ) {
        when (s.tab) {
            Tab.Home -> HomeTab(s)
            Tab.Items -> ItemsTab(s)
            Tab.Settings -> SettingsTab()
        }
    }

    AnimatedVisibility(
        visible = s.form.open,
        enter = slideInVertically(initialOffsetY = { -it }),
        exit = slideOutVertically(targetOffsetY = { -it })
    ) {
        Surface(
            Modifier
                .fillMaxSize()
                .padding(padding),
            color = MaterialTheme.colorScheme.primary
        ) { FormContent(s.form, formChanged) }
    }
}