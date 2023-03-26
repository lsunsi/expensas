package com.lsunsi.expensas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.*
import com.lsunsi.expensas.ui.theme.ExpensasTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val state: StateViewModel by viewModels()
        setContent { ExpensasTheme { App(state) } }
    }
}
