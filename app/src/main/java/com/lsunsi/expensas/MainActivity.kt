package com.lsunsi.expensas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.ui.Modifier
import com.lsunsi.expensas.ui.theme.ExpensasTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ExpensasTheme {
                Scaffold(
                    topBar = {
                        TopAppBar(
                            colors = TopAppBarDefaults.largeTopAppBarColors(
                                MaterialTheme.colorScheme.primary
                            ), title = { Text(text = "Expensas") })
                    },
                    content = { padding ->
                        Surface(modifier = Modifier.padding(padding)) {
                            Text(text = "meu deus mano como é maneiro isso porra")
                        }
                    },
                    bottomBar = {
                        NavigationBar(containerColor = MaterialTheme.colorScheme.primary) {
                            NavigationBarItem(selected = false, onClick = { /*TODO*/ }, label = {
                                Text("Resumão")
                            }, icon = {
                                Icon(Icons.Default.Home, "Resumão")
                            })

                            NavigationBarItem(selected = false, onClick = { /*TODO*/ }, label = {
                                Text("Gastos")
                            }, icon = {
                                Icon(Icons.Default.List, "Tudão")
                            })

                            NavigationBarItem(selected = false, onClick = { /*TODO*/ }, label = {
                                Text("Ajustes")
                            }, icon = {
                                Icon(Icons.Default.Settings, "Trecos")
                            })
                        }
                    },
                )
            }
        }
    }
}
